package org.example.reports.controller;

import lombok.RequiredArgsConstructor;
import org.example.reports.service.MakeZipReportService;
import org.example.reports.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;



@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class reportController {
    private final ReportService reportService;
    private final MakeZipReportService makeZipReportService;

    @PostMapping("/report04_007")
    public Boolean addRaw() throws IOException {
        return  reportService.report04_007() ;
    }

    @PostMapping("/report04_006")
    public Boolean test() throws IOException {
        return  reportService.report04_006();
    }

    @PostMapping("/04")
    public Boolean test1() throws IOException {
        return  makeZipReportService.makeZip(2);
    }



}
