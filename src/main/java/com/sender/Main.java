package com.sender;

import com.sender.service.ReportsThread;
import com.sender.soap.server.SenderReportServiceImpl;
import com.sender.tools.Utils;
import lombok.extern.slf4j.Slf4j;
import javax.xml.ws.Endpoint;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {
    static final int SEND_REPORTS_TIME = 1320;    // 1060 = 17:40    1215 = 20:15   1280 = 21:20    1320 = 22:00    1350 = 22:30     1380 = 23:00     1440 = 24:00
    static final int POOL_SIZE = 1;

    public static void main(String[] args) {
        try {
            log.info("* * *");
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(POOL_SIZE);
            scheduler.scheduleAtFixedRate(new ReportsThread(), Utils.initialDelayMinutes(SEND_REPORTS_TIME), Utils.MINUTES_IN_DAY, TimeUnit.MINUTES);

            Endpoint endpoint = Endpoint.create(new SenderReportServiceImpl());
            endpoint.publish("http://localhost:8044/reports");
        } catch(Throwable e) {
            log.error(e.toString());
        }
    }
}
