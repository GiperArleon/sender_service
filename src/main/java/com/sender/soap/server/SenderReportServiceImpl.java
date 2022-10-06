package com.sender.soap.server;

import com.sender.service.ReportsPdfThread;
import com.sender.service.ReportsThread;
import lombok.extern.slf4j.Slf4j;
import javax.jws.WebService;

@Slf4j
@WebService(endpointInterface = "com.sender.soap.server.SenderReportService")
public class SenderReportServiceImpl implements SenderReportService {

    @Override
    public void sendReportToLectors() {
        ReportsThread reportsThread = new ReportsThread();
        Thread thread = new Thread(reportsThread);
        thread.start();
    }

    @Override
    public void sendPdfReportToLectors() {
        ReportsPdfThread reportsThread = new ReportsPdfThread();
        Thread thread = new Thread(reportsThread);
        thread.start();
    }
}
