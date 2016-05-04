package com.manoelcampos.papersreview.service;

import com.manoelcampos.papersreview.dao.*;
import com.manoelcampos.papersreview.dto.PaperCountByFieldOptionDTO;
import com.manoelcampos.papersreview.dto.PaperCountByRepositoryDTO;
import com.manoelcampos.papersreview.dto.PaperCountByStatusDTO;
import com.manoelcampos.papersreview.dto.PaperCountByTypeDTO;
import com.manoelcampos.papersreview.model.*;
import com.manoelcampos.papersreview.report.TableRow;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Dependent
public class PapersSummaryTableService {
    @Inject
    private ProjectDAO projectDao;

    @Inject
    private FieldDAO fieldDao;

    @Inject
    private PaperDAO paperDao;

    @Inject
    private FieldGroupDAO fieldGroupDao;

    @Inject
    private PaperFieldAnswerDAO paperFieldAnswerDao;

    @Inject
    private PaperTypeDAO paperTypeDao;

    private Project project;

    public PapersSummaryTableService setProject(final Project project){
        this.project = project;
        return this;
    }

    public List<PaperCountByStatusDTO> getPaperCountByStatus(final Project project) {
        return getProjectDao().getPaperCountByStatus(project);
    }

    public List<PaperCountByRepositoryDTO> getPaperCountByRepository(final Project project){
        return getProjectDao().getPaperCountByRepository(project);
    }

    public List<PaperCountByTypeDTO> getPaperCountByType(final Project p){
        return getProjectDao().getPaperCountByType(p);
    }

    public Map<Field, List<PaperCountByFieldOptionDTO>> getApprovedPaperCountByFieldOption(final Field field){
        Map<Field, List<PaperCountByFieldOptionDTO>> map = new HashMap<>();

        getFieldDao().getNonSubjectiveFieldsToBeShownInReports(getProject(), field)
                .forEach(f -> map.put(f, getPaperFieldAnswerDao().listAnswersCountOfApprovedPapersGroupedByFieldOptionToBeShownInReports(f)));

        return map;
    }

    public long findAnswerForFieldOption(List<PaperCountByFieldOptionDTO> fieldAnswers, TableRow row, FieldOption fo) {
        Optional<PaperCountByFieldOptionDTO> obj =
                fieldAnswers.stream().filter(a -> a.getFieldOption().equals(fo)).findFirst();
        if(obj.isPresent()) {
            row.addColumn(obj.get().getCount().toString());
            return obj.get().getCount();
        }
        row.addColumn("");
        return 0L;
    }

    public List<Field> getFieldsToBeShownInReports(final Project project){
        return getFieldDao().getFieldsToBeShownInReports(project);
    }

    public Field getField(final Field field){
        if(field == null || field.getId() == null || field.getId() <= 0)
            return Field.NULL;

        return getFieldDao().findById(field.getId());
    }

    public Project getProject(final Project project){
        if(project == null || project.getId() == null || project.getId() <= 0)
            return Project.NULL;

        return getProjectDao().findById(project.getId());
    }

    public ProjectDAO getProjectDao() {
        return projectDao;
    }
    public FieldDAO getFieldDao() {
        return fieldDao;
    }
    public PaperDAO getPaperDao() {
        return paperDao;
    }
    public PaperFieldAnswerDAO getPaperFieldAnswerDao() {
        return paperFieldAnswerDao;
    }
    public PaperTypeDAO getPaperTypeDao() {
        return paperTypeDao;
    }
    public Project getProject() {
        return project;
    }

    public List<FieldGroup> listFieldGroups(Project project){
        return fieldGroupDao.listByProject(project);
    }
}