package com.sender.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportsThread implements Runnable {

    @Override
    public void run() {
        if(ReportService.makeReportForLeads())
            log.info("ReportThread task success!");
        else
            log.error("ReportThread task error");
    }
}
