package de.themonstrouscavalca.formicacid.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportSection{
    private String sectionName;
    private List<ReportRow> data = new ArrayList<>();

    public ReportSection(){
        this.sectionName = "";
    }

    public ReportSection(String name){
        this.sectionName = name;
    }

    public void addRow(ReportRow row){
        this.data.add(row);
    }

    public List<ReportRow> getData(){
        return data;
    }

    public List<ReportRow> getHeaders(){
        return data.stream().filter(t -> t instanceof ReportHeader).collect(Collectors.toList());
    }

    public List<ReportRow> getBody(){
        return data.stream().filter(t -> !(t instanceof ReportHeader)).collect(Collectors.toList());
    }

    public String getSectionName(){
        return sectionName;
    }
}
