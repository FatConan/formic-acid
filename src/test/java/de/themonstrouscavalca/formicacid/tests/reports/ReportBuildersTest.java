package de.themonstrouscavalca.formicacid.tests.reports;

import de.themonstrouscavalca.formicacid.reports.CSVReportWriter;
import de.themonstrouscavalca.formicacid.reports.Report;
import de.themonstrouscavalca.formicacid.reports.ReportBuilder;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ReportBuildersTest{
    @Test
    public void testReport(){
        ReportBuilder reportBuilder = new ReportBuilder();
        Report report = reportBuilder
                .defaultSection()
                    .newHeaderRow()
                        .newCell().setValue("A1").end()
                        .newCell().setValue("A2").end()
                        .newCell().setValue("A3").end()
                    .end()
                .end()
                .defaultSection()
                    .newRow()
                        .newCell().setValue("B1").end()
                        .newCell().setValue("B2").end()
                        .newCell().setValue("B3").end()
                    .end()
                .end()
                .newSection("S1")
                .newRow()
                .newCell().setValue("C1").end()
                .newCell().setValue("C2").end()
                .newCell().setValue("C3").end()
                .end()
                .end()
                .defaultSection()
                .newRow()
                .newCell().setValue("D1").end()
                .newCell().setValue("D2").end()
                .newCell().setValue("D3").end()
                .end()
                .end()
            .build();

        StringWriter writer = new StringWriter();
        try{
            String expected = "\"A1\",\"A2\",\"A3\"\n" +
                              "\"B1\",\"B2\",\"B3\"\n" +
                              "\"D1\",\"D2\",\"D3\"\n" +
                              "\"C1\",\"C2\",\"C3\"\n";

            CSVReportWriter.reportToCSV(report, writer);
            assertEquals("Mismatch in expected report output", expected, writer.toString());
        }catch(IOException e){
            fail("Unable to output csv");
        }
    }
}
