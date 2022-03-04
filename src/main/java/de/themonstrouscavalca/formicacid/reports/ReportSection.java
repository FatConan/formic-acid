package de.themonstrouscavalca.formicacid.reports;

import de.themonstrouscavalca.formicacid.reports.interfaces.IReportRow;
import de.themonstrouscavalca.formicacid.reports.rows.HeaderRow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportSection{
    private String sectionName;
    private List<IReportRow> data = new ArrayList<>();

    public ReportSection(ReportSectionBuilder builder){
        this.sectionName = builder.sectionName;
        this.data.addAll(builder.rows);
    }

    public void mergeSection(ReportSection section){
        this.data.addAll(section.getData());
    }

    public void addRow(IReportRow row){
        this.data.add(row);
    }

    public List<IReportRow> getData(){
        return data;
    }

    public List<IReportRow> getHeaders(){
        return data.stream().filter(t -> t instanceof HeaderRow).collect(Collectors.toList());
    }

    public List<IReportRow> getBody(){
        return data.stream().filter(t -> !(t instanceof HeaderRow)).collect(Collectors.toList());
    }

    public String getSectionName(){
        return sectionName;
    }
}
