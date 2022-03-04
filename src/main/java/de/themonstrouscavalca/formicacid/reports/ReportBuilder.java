package de.themonstrouscavalca.formicacid.reports;

import java.util.ArrayList;
import java.util.List;

public class ReportBuilder{
    ReportSection defaultSection;
    List<ReportSection> sections = new ArrayList<>();

    public ReportSectionBuilder newSection(String sectionName){
        return new ReportSectionBuilder(this, sectionName);
    }

    public ReportSectionBuilder defaultSection(){
        return new ReportSectionBuilder(this, "");
    }

    public ReportBuilder addSection(ReportSection section){
        if(section.getSectionName().isEmpty()){
            if(this.defaultSection == null){
                this.defaultSection = section;
            }else{
                this.defaultSection.mergeSection(section);
            }
        }else{
            this.sections.add(section);
        }
        return this;
    }

    public Report build(){
        return new Report(this);
    }
}
