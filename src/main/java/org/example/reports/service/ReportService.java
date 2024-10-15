package org.example.reports.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportChangeService reportChangeService;


    public Boolean report04_007() throws IOException {
        StringBuilder stringBuilder = new StringBuilder(reportChangeService.readRaws());
        String header = reportChangeService.readRaws().split("\n")[0];



        String body07 = reportChangeService.readReport07();
        for (String s : body07.split("\n")) {
            if (!reportChangeService.existAccount(s)){
                if (s.startsWith("22618") || s.startsWith("22616")) {
                    stringBuilder.append(reportChangeService.createRow(s));
                }else {
                    System.out.println(s);
                }
            }

        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        List<String> body = new java.util.ArrayList<>(List.of(stringBuilder.toString().split("\n")));
        body.set(0,reportChangeService.makeHeader2(header, body.size()));


        reportChangeService.WriteToFile(String.join("\n", body));





        return true;
    }



    public Boolean report04_006() throws IOException {
        final String  filePath = "C:\\Users\\HP\\Desktop\\B04_reports\\B0119904.006";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder reports1 = new StringBuilder();
        String line;
        List<String> listOfRemove = new ArrayList<>();
        List<String> listOfChange = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            if (line.contains("05332")) {
                reports1.append(line.split("\\(")[1].length()).append("\n");
                if(line.split("\\(")[1].length()==80){
                    listOfChange.add(line.split("\\(")[2].split("\\)")[0]);
                }else if (line.split("\\(")[1].length()==81){
                    listOfRemove.add(line.split("\\(")[2].split("\\)")[0]);
                }

            }
        }


        reportChangeService.changeAccount(listOfChange);
        reportChangeService.removeAccounts(listOfRemove);
        return true;
    }




}
