package com.sender.soap.client;

import com.router.sender.SenderService;
import com.router.sender.SenderServiceImplService;
import lombok.extern.slf4j.Slf4j;
import java.net.URL;

@Slf4j
public class SoapRoaterClientFactory {

    private static SenderService instance;

    private SoapRoaterClientFactory() {
    }

    public static SenderService getSoapRoaterClient() {
        if(instance == null) {
            try {
                URL url = new URL("http://localhost:8076/senderNotify?wsdl");
                SenderServiceImplService senderServiceImplService = new SenderServiceImplService(url);
                instance = senderServiceImplService.getSenderServiceImplPort();
            } catch(Exception e) {
                log.error("Can not init user soap interface: {}", e.toString());
            }
        }
        return instance;
    }
}
