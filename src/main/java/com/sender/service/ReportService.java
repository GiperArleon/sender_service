package com.sender.service;

import com.router.sender.*;
import com.sender.soap.client.SoapRoaterClientFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReportService {
    static SenderService roaterClient = SoapRoaterClientFactory.getSoapRoaterClient();

    public static boolean makeReportForLeads() {
        try {
            UserArray users = roaterClient.getAllUsers();
            List<User> userList = users.getItem();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("отчет за: ").append(LocalDate.now().toString()).append("\n");
            for(User usr: userList) {
                stringBuilder.append(usr.getUsername()).append(" ").append(usr.getSurname()).append("\n");
                TimeRecordArray recordArray = roaterClient.getRecordsForToday(usr.getId());
                List<TimeRecord> records = recordArray.getItem();
                if(records.isEmpty()) {
                    stringBuilder.append("   нет записей");
                }
                String tmp = records.stream()
                        .map(r -> String.format("   %2d ч %2d м %s", r.getHours(), r.getMinutes(), r.getDescription()))
                        .collect(Collectors.joining("\n"));
                stringBuilder.append(tmp);
                stringBuilder.append("\n");
            }
            roaterClient.sendTxtReportToLectors(stringBuilder.toString());
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean makeReportForLeadsPdf() {
        try {
            UserArray users = roaterClient.getAllUsers();
            List<User> userList = users.getItem();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("отчет за: ").append(LocalDate.now().toString()).append("\n");
            for(User usr: userList) {
                stringBuilder.append(usr.getUsername()).append("\n");
                TimeRecordArray recordArray = roaterClient.getRecordsForToday(usr.getId());
                List<TimeRecord> records = recordArray.getItem();
                if(records.isEmpty()) {
                    stringBuilder.append("   нет записей");
                }
                String tmp = records.stream()
                        .map(r -> String.format("   %2d ч %2d м %s", r.getHours(), r.getMinutes(), r.getDescription()))
                        .collect(Collectors.joining("\n"));
                stringBuilder.append(tmp);
                stringBuilder.append("\n");
            }
            roaterClient.sendPdfReportToLectors(stringBuilder.toString());
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
