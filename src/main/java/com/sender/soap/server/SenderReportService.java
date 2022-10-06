package com.sender.soap.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style= SOAPBinding.Style.RPC)
public interface SenderReportService {

    @WebMethod
    void sendReportToLectors();

    @WebMethod
    void sendPdfReportToLectors();
}
