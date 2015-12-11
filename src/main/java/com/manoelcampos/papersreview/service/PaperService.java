package com.manoelcampos.papersreview.service;

import com.manoelcampos.papersreview.dao.*;
import com.manoelcampos.papersreview.dto.PaperFieldAnswerDTO;
import com.manoelcampos.papersreview.model.EndUser;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.FieldOption;
import com.manoelcampos.papersreview.model.PaperType;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.PaperFieldAnswer;
import com.manoelcampos.papersreview.model.PaperStatus;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.model.Repository;
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
    private DAO<PaperStatus> statusDao;

    @Inject
    private ProjectDAO projectDao;

    @Inject
    private PaperFieldAnswerDAO paperFieldAnswerDao;
    
    @Inject
    private FieldDAO fieldDao;

    @Inject
    private FieldOptionDAO fieldOptionDao;

    @Inject
    private PaperTypeDAO paperTypeDao;

    @Inject
    private DAO<Repository> repositoryDao;

    @Inject
    private PaperFieldAnswerDAO answerDao;

    @Inject
    private SearchSessionDAO searchSessionDao;

    /**
     * @return the dao
     */
    public List<Paper> list() {
        return dao.list();
    }
    
    public boolean remove(final Paper paper){
        return dao.remove(paper);
    }

    public boolean save(final Paper o){
        loadEntityAttibutesFromDB(o);
        return dao.save(o);
    }

    private void loadEntityAttibutesFromDB(final Paper o) {
        if(o.getStatus() != null)
            o.setStatus(statusDao.findById(o.getStatus().getId()));
        if(o.getPaperType() != null)
            o.setPaperType(paperTypeDao.findById(o.getPaperType().getId()));
        o.setSearchSession(searchSessionDao.findById(o.getSearchSession().getId()));
    }
    
    public Paper findById(final Long id){
        return dao.findById(id);
    }
    
    public boolean removeAnswer(final PaperFieldAnswer answer){
        answer.getPaper().removePaperFieldAnswer(answer);
        return paperFieldAnswerDao.remove(answer);
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
     * @param existingAnswer
     * @param userAnswers
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
    
    public void saveAnswers(final Paper paper, final List<PaperFieldAnswerDTO> userAnswers) {
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
                   if(fieldOptionId!=null && fieldOptionId > 0){
                    FieldOption fieldOption = fieldOptionDao.findById(fieldOptionId);
                    answer = new PaperFieldAnswer(paper, fieldOption);
                    answer = tryToFindExistingAnswerAndPopItToBeUpdated(paper, answer);
                    answer.setFieldOption(fieldOption);
                    paper.addPaperFieldAnswer(answer);
                   }
               } 
            }
        }
        popRemovedAnswersFromList(paper, userAnswers);
        save(paper);
    }
    
    public List<Paper> search(final Paper searchCriteria){
        if(searchCriteria.getPaperType()!=null)
            searchCriteria.setPaperType(paperTypeDao.findById(searchCriteria.getPaperType().getId()));
        if(searchCriteria.getSearchSession()!=null){
            if(searchCriteria.getSearchSession().getProject()!=null)
                searchCriteria.getSearchSession().setProject(
                        projectDao.findById(searchCriteria.getSearchSession().getProject().getId()));
            if(searchCriteria.getSearchSession().getRepository()!=null)
                searchCriteria.getSearchSession().setRepository(
                        repositoryDao.findById(searchCriteria.getSearchSession().getRepository().getId()));
        }
        
        return dao.search(searchCriteria);
    }
    
    public List<PaperStatus> listStatus() {
        return statusDao.list();
    }
    
    public List<PaperType> listPaperTypes(){
        return paperTypeDao.list();
    }    
    
    public List<Project> listProjects(){
        return  projectDao.list();
    }    

    public List<Repository> listRepositories(){
        return  repositoryDao.list();
    }    

    public List<Project> listProjects(final EndUser endUser){
        return projectDao.listByEndUser(endUser);
    }    
    
    public Map<Field, List<PaperFieldAnswer>> listAnswersForAllFields(final Paper p) {
        final Map<Field, List<PaperFieldAnswer>> result = new HashMap<>();

        final List<Field> fields = 
                fieldDao.listByProject(p.getSearchSession().getProject());
        for(Field f: fields){
            result.put(f, answerDao.listAnswersByPaperAndField(p, f));
        }
         
        return result;
    }

    /**
     * @return the projectDao
     */
    public ProjectDAO getProjectDao() {
        return projectDao;
    }

    /**
     * @return the repositoryDao
     */
    public DAO<Repository> getRepositoryDao() {
        return repositoryDao;
    }
    
    public boolean saveListOfPapers(List<Paper> list) {
        for(Paper p: list){
            saveWithoutFlush(p);
        }
        dao.flush();
        return true;
    }    

    private void saveWithoutFlush(Paper p) {
        loadEntityAttibutesFromDB(p);
        dao.saveWithoutFlush(p);
    }
}
