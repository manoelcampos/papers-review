package com.manoelcampos.papersreview.report.databuilder;

import com.manoelcampos.papersreview.dto.PaperCountByFieldOptionDTO;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.report.ReportTableGenerator;
import com.manoelcampos.papersreview.report.TableRow;
import com.manoelcampos.papersreview.service.PapersSummaryTableService;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public abstract class PaperCountByFieldOptionTableDataBuilder extends AbstractTableDataBuilder {
    private PapersSummaryTableService service;
    private Map<Field, List<PaperCountByFieldOptionDTO>> answers;

    @Override
    public void addDataRows() {
        getAnswers().keySet().stream().forEach(f -> addDataRows(f, getAnswers().get(f)));
    }

    protected abstract void addDataRows(Field currentField, List<PaperCountByFieldOptionDTO> fieldAnswers);

    public String generate(final Field field) {
        this.answers = service.getApprovedPaperCountByFieldOption(field);
        return super.generate();
    }

    public Map<Field, List<PaperCountByFieldOptionDTO>> getAnswers() {
        return answers;
    }

    public PapersSummaryTableService getService() {
        return service;
    }

    public PaperCountByFieldOptionTableDataBuilder setService(PapersSummaryTableService service) {
        this.service = service;
        return this;
    }
}
