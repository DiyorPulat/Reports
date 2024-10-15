package org.example.reports.service;

import lombok.RequiredArgsConstructor;
import org.example.reports.entity.Report04;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.*;

@Service
@RequiredArgsConstructor
public class ReportChangeService {
    public Boolean existAccount(String account) throws IOException {
        String filePath = "C:\\Users\\HP\\Desktop\\04_reports\\I0119904.006";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            boolean success = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("#");

                if (parts[5].equals(account)) {
                    success = true;
                }

            }

        return success;
    }

    public void removeAccounts(List<String> accounts) throws IOException {
        String filePath = "C:\\Users\\HP\\Desktop\\04_reports\\I0119904.006";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        StringBuilder body = new StringBuilder(this.makeHeader1(reader.readLine(), accounts.size()));
        body.append("\n");
        while ((line = reader.readLine()) != null) {

            boolean success = true;
            String[] parts = line.split("#");


            for (String account : accounts) {
                String part = parts[5];

                if (String.valueOf(part).trim().equals(String.valueOf(account).trim())) {
                    success = false;
                    break;
                }
            }
            if (success) {
                body.append(line).append("\n");
            }
        }
        reader.close();
        body.deleteCharAt(body.length() - 1);

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(body.toString());
        writer.close();

    }


    String makeHeader1(String header,Integer accountsLength){
        StringBuilder stringBuilder = new StringBuilder();
        String[] part1= header.split("#");
        part1[6] = String.valueOf(Integer.parseInt(part1[6])-accountsLength);
        for (String s : part1) {
            stringBuilder.append(s).append('#');
        }
        return stringBuilder.toString();
    }


    String makeHeader2(String header,Integer accountsLength){
        StringBuilder stringBuilder = new StringBuilder();
        String[] part1= header.split("#");
        part1[6] = String.valueOf(accountsLength);
        for (String s : part1) {
            stringBuilder.append(s).append('#');
        }
        return stringBuilder.toString();
    }

    public void changeAccount(List<String> accounts) throws IOException {
        String filePath = "C:\\Users\\HP\\Desktop\\04_reports\\I0119904.006";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        String header = reader.readLine();
        List<Report04> reports = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            boolean success = true;
            List<String> parts = List.of(line.split("#"));
            for (String account : accounts) {
                String part = parts.get(5);

                if (String.valueOf(part).trim().equals(String.valueOf(account).trim())) {
                    success = false;

                    break;
                }
            }

           if (success) {
               if (parts.size() == 21) {
                   reports.add(new Report04(parts.get(0),parts.get(1),parts.get(2),parts.get(3),parts.get(4),parts.get(5),parts.get(6),parts.get(7),parts.get(8),parts.get(9),parts.get(10),parts.get(11),parts.get(12),parts.get(13),parts.get(14),parts.get(15),parts.get(16),parts.get(17),parts.get(18),parts.get(19),parts.get(20)));
               }else {
                   reports.add(new Report04(parts.get(0), parts.get(1),parts.get(2),parts.get(3),parts.get(4),parts.get(5),parts.get(6),parts.get(7),parts.get(8),parts.get(9),parts.get(10),parts.get(11),parts.get(12),parts.get(13),parts.get(14),parts.get(15),parts.get(16),parts.get(17),parts.get(18),parts.get(19),""));
               }
           }else {
               if (parts.size() == 21) {
                   reports.add(new Report04(parts.get(0),parts.get(1),parts.get(2),parts.get(3),"1",parts.get(5),parts.get(6),parts.get(7),parts.get(8),parts.get(9),parts.get(10),parts.get(11),parts.get(12),parts.get(13),parts.get(14),parts.get(15),parts.get(16),parts.get(17),parts.get(18),parts.get(19),parts.get(20)));
               }else {
                   reports.add(new Report04(parts.get(0), parts.get(1),parts.get(2),parts.get(3),"1",parts.get(5),parts.get(6),parts.get(7),parts.get(8),parts.get(9),parts.get(10),parts.get(11),parts.get(12),parts.get(13),parts.get(14),parts.get(15),parts.get(16),parts.get(17),parts.get(18),parts.get(19),""));
               }
           }

        }


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(header).append("\n");
        for (Report04 report : reports) {
            stringBuilder.append(report.toString());
        }


        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        writer.write(stringBuilder.toString());
        writer.close();
    }





    public String readReport07() throws IOException {
        final String  filePath = "C:\\Users\\HP\\Desktop\\B04_reports\\B0119904.007";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder reports1 = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {

            if (line.contains("04_006")) {
                reports1.append(line.split("\\(")[2].split("\\)")[0].split(":")[1]).append("\n");
            } else if (line.contains("04_005")) {
                out.println("04_005 =>  "+line.split("\\(")[2].split("\\)")[0].split(":")[1]);
            }
        }
        reader.close();
        return reports1.toString();
    }


    public String readRaws() throws IOException {
        String filePath = "C:\\Users\\HP\\Desktop\\04_reports\\I0119904.006";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        StringBuilder body = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            body.append(line).append("\n");
        }
        reader.close();
        return body.toString();
    }


    public String getOneRow(String row,String value) throws IOException {
        String body = this.readRaws();
        List<String> rows = Arrays.asList(body.split("\n"));
        for (String s : rows) {
            if (s.contains(row) && s.split("#")[4].trim().equals(value.trim())) {
                 return s;
            }
        }
        return null;

    }


    public void WriteToFile(String data) throws IOException {
        String filePath = "C:\\Users\\HP\\Desktop\\04_reports\\I0119904.006";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(data);
        writer.close();
    }


    public String createRow(String report) throws IOException {

        String row = this.getOneRow(report.substring(0,5),"1");
        List<String> parts = Arrays.asList(row.split("#"));
        Report04 report04;
        if (parts.size() == 21) {
            report04 = new Report04(parts.get(0),parts.get(1),parts.get(2),parts.get(3),"1",report,report.substring(9,17),parts.get(7),parts.get(8),parts.get(9),parts.get(10),parts.get(11),parts.get(12),parts.get(13),parts.get(14),parts.get(15),parts.get(16),parts.get(17),report,parts.get(19),parts.get(20));
        }else {
            report04 = new Report04(parts.get(0), parts.get(1),parts.get(2),parts.get(3),"1",report,report.substring(9,17),parts.get(7),parts.get(8),parts.get(9),parts.get(10),parts.get(11),parts.get(12),parts.get(13),parts.get(14),parts.get(15),parts.get(16),parts.get(17),report,parts.get(19),"");
        }
        report04.controlSum();

        return report04.toString();



    }

    public String BReport06() throws IOException {
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


        this.changeAccount(listOfChange);
        this.removeAccounts(listOfRemove);

        reader.close();
        return  reports1.toString();
    }



















}
