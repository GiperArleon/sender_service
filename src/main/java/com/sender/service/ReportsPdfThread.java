package com.sender.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportsPdfThread implements Runnable {
    @Override
    public void run() {
        if(ReportService.makeReportForLeadsPdf())
            log.info("ReportPdfThread task success!");
        else
            log.error("ReportPdfThread task error");
    }
}
