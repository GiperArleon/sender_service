package com.sender.tools;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Utils {

    public static final int MINUTES_IN_HOUR = 60;
    public static final int MINUTES_IN_DAY = 1440;

    public static int initialDelayMinutes(int minutesTimeStump) {
        Instant nowUtc = Instant.now();
        ZoneId euMSK = ZoneId.of("Europe/Moscow");
        ZonedDateTime nowEuMSK = ZonedDateTime.ofInstant(nowUtc, euMSK);

        int currTimeMin = nowEuMSK.getHour() * MINUTES_IN_HOUR + nowEuMSK.getMinute();
        if(currTimeMin < minutesTimeStump) {
            return minutesTimeStump - currTimeMin;
        } else {
            return MINUTES_IN_DAY - currTimeMin + minutesTimeStump;
        }
    }
}
