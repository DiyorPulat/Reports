package org.example.reports.service;

import lombok.extern.slf4j.Slf4j;
import org.example.reports.contants.UrlFiles;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class MakeZipReportService {
    public Boolean makeZip(Integer sequenceNumber) throws FileNotFoundException {
        String zipFile = UrlFiles.FILE_URL_FOR_ZIP + generateNameOfZip(sequenceNumber);
        List<String> srcFiles = List.of(UrlFiles.REPORT_O4_005,UrlFiles.REPORT_04_006, UrlFiles.REPORT_04_007);


        try {
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (String file : srcFiles) {

                File srcFile = new File(file);

                FileInputStream fis = new FileInputStream(srcFile);

                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                zos.write(fis.readAllBytes());

                zos.closeEntry();

                fis.close();

            }

            zos.close();

        } catch (IOException ioe) {
            log.error("Error creating zip file: {} ", ioe.getMessage());
        }
        return true;


    }

    public static String generateNameOfZip(Integer sequenceNumber) {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
        map.put(5, "5");
        map.put(6, "6");
        map.put(7, "7");
        map.put(8, "8");
        map.put(9, "9");
        map.put(10, "A");
        map.put(11, "B");
        map.put(12, "C");
        map.put(13, "D");
        map.put(14, "E");
        map.put(15, "F");
        map.put(16, "G");
        map.put(17, "H");
        map.put(18, "I");
        map.put(19, "J");
        map.put(20, "K");
        map.put(21, "L");
        map.put(22, "M");
        map.put(23, "N");
        map.put(24, "O");
        map.put(25, "P");
        map.put(26, "Q");
        map.put(27, "R");
        map.put(28, "S");
        map.put(29, "T");
        map.put(30, "U");
        map.put(31, "V");
        map.put(32, "W");
        map.put(33, "X");
        map.put(34, "Y");
        map.put(35, "Z");

        Integer year = LocalDate.now().getYear() % 100;
        Integer month = LocalDate.now().getMonthValue();
        Integer day = LocalDate.now().getDayOfMonth();
        Integer hour = LocalTime.now().getHour();

        return "I06600" + map.get(sequenceNumber) + map.get(hour) + "." + map.get(year) + map.get(month) + map.get(day);

    }



}
