package org.example.reports.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class MakeZipReportService {
    public Boolean makeZip() throws FileNotFoundException {
        String zipFile = "C:\\Users\\HP\\Desktop\\04\\archive.123";
        String [] srcFiles = {"C:\\Users\\HP\\Desktop\\04\\I0119904.005","C:\\Users\\HP\\Desktop\\04\\I0119904.006","C:\\Users\\HP\\Desktop\\04\\I0119904.007"};

        try {
            byte[] buffer = new byte[1024];

            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i=0; i < srcFiles.length; i++) {

                File srcFile = new File(srcFiles[i]);

                FileInputStream fis = new FileInputStream(srcFile);

                // begin writing a new ZIP entry, positions the stream to the start of the entry data
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();

                // close the InputStream
                fis.close();

            }

            // close the ZipOutputStream
            zos.close();
        }catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }


        return true;


    }

    public String generateNameOfZip( Integer
            sequenceNumber){

        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"1");
        map.put(2,"2");
        map.put(3,"3");
        map.put(4,"4");
        map.put(5,"5");
        map.put(6,"6");
        map.put(7,"7");
        map.put(8,"8");
        map.put(9,"9");
        map.put(10,"A");
        map.put(11,"B");
        map.put(12,"C");
        map.put(13,"D");
        map.put(14,"E");
        map.put(15,"F");
        map.put(16,"G");
        map.put(17,"H");
        map.put(18,"I");
        map.put(19,"J");
        map.put(20,"K");
        map.put(21,"L");
        map.put(22,"M");
        map.put(23,"N");
        map.put(24,"O");
        map.put(25,"P");
        map.put(26,"Q");
        map.put(27,"R");
        map.put(28,"S");
        map.put(29,"T");
        map.put(30,"U");
        map.put(31,"V");
        map.put(32,"W");
        map.put(33,"X");
        map.put(34,"Y");
        map.put(35,"Z");


        Integer year  = LocalDate.now().getYear() % 100;
        Integer month = LocalDate.now().getMonthValue();
        Integer day = LocalDate.now().getDayOfMonth();
        Integer hour = LocalTime.now().getHour();

        return "I06600"+map.get(sequenceNumber) + map.get(hour) + "." +map.get(year)   +  map.get(month)  + map.get(day);

    }



}
