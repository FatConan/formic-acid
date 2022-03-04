package de.themonstrouscavalca.formicacid.reports;

import com.opencsv.CSVWriter;
import de.themonstrouscavalca.formicacid.reports.interfaces.IReportRow;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CSVReportWriter{
    public static void reportToCSV(Report report, Writer output) throws IOException{
        CSVWriter writer = new CSVWriter(output, ',');
        for(ReportSection reportSection : report.getReportSections()){
            int flushCount = 0;
            for(IReportRow row : reportSection.getData()){
                writer.writeNext(row.getData());
                flushCount = ++flushCount % 100;
                if(flushCount == 0){
                    writer.flush();
                }
            }
        }
        writer.flush();
        writer.close();
    }

    public static File reportToCSVTempFile(Report report) throws IOException{
        File temp = File.createTempFile("output", ".csv");
        reportToCSV(report, new OutputStreamWriter(
                new FileOutputStream(temp.getAbsoluteFile()), StandardCharsets.UTF_8)
        );
        return temp;
    }
}
