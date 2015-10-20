package com.manoelcampos.papersreview.service;

import com.manoelcampos.papersreview.dao.DAO;
import com.manoelcampos.papersreview.dao.FieldDAO;
import com.manoelcampos.papersreview.dao.FieldOptionDAO;
import com.manoelcampos.papersreview.dao.PaperDAO;
import com.manoelcampos.papersreview.dao.PaperFieldAnswerDAO;
import com.manoelcampos.papersreview.dao.SearchSectionDAO;
import com.manoelcampos.papersreview.dto.PaperFieldAnswerDTO;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.FieldOption;
import com.manoelcampos.papersreview.model.PaperType;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.PaperFieldAnswer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Dependent
public class PaperService {
    @Inject
    private PaperDAO dao;
    
    @Inject
    private PaperFieldAnswerDAO paperFieldAnswerDao;
    
    @Inject
    private FieldDAO fieldDao;

    @Inject
    private FieldOptionDAO fieldOptionDao;

    @Inject
    private DAO<PaperType> paperTypeDao;

    @Inject
    private PaperFieldAnswerDAO answerDao;

    @Inject
    private SearchSectionDAO searchSectionDao;

    /**
     * @return the dao
     */
    public List<Paper> list() {
        return dao.list();
    }
    
    public Paper remove(final Long id){
        Paper f = dao.findById(id);
        return (dao.remove(f) ? f : null);
    }

    public boolean save(final Paper o){
        if(o.getPaperType() != null)
            o.setPaperType(paperTypeDao.findById(o.getPaperType().getId()));
        o.setSearchSection(searchSectionDao.findById(o.getSearchSection().getId()));
        return dao.save(o);
    }
    
    public Paper findById(final Long id){
        return dao.findById(id);
    }
    
    public Paper removeAnswer(final Long paperFieldAnswerId){
        PaperFieldAnswer answer = paperFieldAnswerDao.findById(paperFieldAnswerId);
        Paper paper = answer.getPaper();
        paper.removePaperFieldAnswer(answer);
        paperFieldAnswerDao.remove(answer);
        
        return paper;
    }
    
    /**
     * Tenta localizar uma resposta já existente para atualizar.
     * Caso a resposta seja encontrada para o Field indicado, remove tal resposta da lista e retorna 
     * a mesma para ser atualizada e readicionada.
     * @param paper
     * @param userAnswer Resposta do usuário
     * @return Caso a resposta tentando ser atualizada já exista, retorna tal objeto
     * do banco para que ele de fato seja atualizado. Caso a resposta informada
     * não exista, retorna ela mesma para ser inserida no banco.
     */
    private PaperFieldAnswer tryToFindExistingAnswerAndPopItToBeUpdated(
            final Paper paper, final PaperFieldAnswer userAnswer){
        for(PaperFieldAnswer existing: paper.getPaperFieldAnswers()){
            if(userAnswerFieldIsEqualsToExistingAnswerField(userAnswer, existing)) {
                if(userAnswerAndExistingAnswerAreNotMultipleChoice(userAnswer, existing))  {
                    return paper.removePaperFieldAnswer(existing);
                }
                else if(userObjectiveAnswerIsEqualsToExistingObjectiveAnswer(userAnswer, existing))
                    return paper.removePaperFieldAnswer(existing);
            }
        }
        
        return userAnswer;
    }

    private static boolean userAnswerFieldIsEqualsToExistingAnswerField(final PaperFieldAnswer userAnswer, final PaperFieldAnswer existing) {
        return userAnswer.getField().getId().equals(existing.getField().getId());
    }

    private static boolean userObjectiveAnswerIsEqualsToExistingObjectiveAnswer(final PaperFieldAnswer userAnswerField, final PaperFieldAnswer existing) {
        return userAnswerField.getFieldOption().getId().equals(existing.getFieldOption().getId());
    }

    private static boolean userAnswerAndExistingAnswerAreNotMultipleChoice(final PaperFieldAnswer userAnswer, final PaperFieldAnswer existing) {
        return userAnswer.getField().isNotMultiple() && existing.getField().isNotMultiple();
    }
    
    /**
     * Para uma determinada resposta salva no BD para campos de múltipla escolha,
     * verifica se tal Option foi selecionada pelo usuário. 
     * Caso não tenha sido, indica que o usuário desmarcou tal opção e a mesma
     * deve ser removida da lista de respostas para o Field
     * 
     * @param paper
     * @param userAnswers
     * @param fieldOption
     * @return 
     */
    private boolean findExistingAnswerInUserAnswerListForMultipleChoiceFields(
            final PaperFieldAnswer existingAnswer, 
            final List<PaperFieldAnswerDTO> userAnswers){
        if(existingAnswer.getField().isNotMultiple())
            return true;
        
        for(PaperFieldAnswerDTO userAnswer: userAnswers){
            if(userAnswer.getField().getId().equals(existingAnswer.getField().getId()) && 
            userAnswer.getFieldOptions().contains(existingAnswer.getFieldOption().getId()))
                return true;
        }
        
        return false;
    }
    
    /**
     * Remove das respostas salvas no banco de dados as respostas que o usuário desmarcou para 
     * os campos de multipla escolha.
     * @param paper
     * @param userAnswers 
     */
    private void popRemovedAnswersFromList(final Paper paper, final List<PaperFieldAnswerDTO> userAnswers){
        PaperFieldAnswer existing;
        for(int i = paper.getPaperFieldAnswers().size()-1; i >= 0; i--){
            existing = paper.getPaperFieldAnswers().get(i);
            if(!findExistingAnswerInUserAnswerListForMultipleChoiceFields(existing, userAnswers)) {
                paper.removePaperFieldAnswer(existing);
                this.paperFieldAnswerDao.remove(existing);
            }
        }
    }
    
    public void saveAnswers(final Long paperId, final List<PaperFieldAnswerDTO> userAnswers) {
        final Paper paper = findById(paperId);
        for(PaperFieldAnswerDTO dto: userAnswers){
            dto.setField(fieldDao.findById(dto.getField().getId()));
            PaperFieldAnswer answer;
            if(dto.getField().isSubjective()) {
                if(dto.getSubjectiveAnswer() != null && !dto.getSubjectiveAnswer().trim().isEmpty()){
                    answer = new PaperFieldAnswer(paper, dto.getField());
                    answer = tryToFindExistingAnswerAndPopItToBeUpdated(paper, answer);
                    answer.setSubjectiveAnswer(dto.getSubjectiveAnswer());
                    paper.addPaperFieldAnswer(answer);
                }
            } else {
               for(Long fieldOptionId: dto.getFieldOptions()){
                   FieldOption fieldOption = fieldOptionDao.findById(fieldOptionId);
                   answer = new PaperFieldAnswer(paper, fieldOption);
                   answer = tryToFindExistingAnswerAndPopItToBeUpdated(paper, answer);
                   answer.setFieldOption(fieldOption);
                   paper.addPaperFieldAnswer(answer);
               } 
            }
        }
        popRemovedAnswersFromList(paper, userAnswers);
        save(paper);
    }

    
    public List<PaperType> listPaperTypes(){
        return paperTypeDao.list();
    }    
    
    public Map<Field, List<PaperFieldAnswer>> listAnswersForAllFields(final Paper p) {
        final Map<Field, List<PaperFieldAnswer>> result = new HashMap<>();

        final List<Field> fields = 
                fieldDao.listByProject(p.getSearchSection().getProject().getId());
        for(Field f: fields){
            result.put(f, answerDao.listAnswersByPaperAndField(p, f));
        }
         
        return result;
    }
    
}
