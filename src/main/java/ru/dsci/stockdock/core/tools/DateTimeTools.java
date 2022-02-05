package ru.dsci.stockdock.core.tools;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DateTimeTools {

    public static ZoneId DEFAULT_ZONE_ID = ZoneId.of("Europe/Moscow");

    public static ZoneId zoneId = DEFAULT_ZONE_ID;

    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern(DATE_PATTERN)
            .withZone(zoneId);
    public static final String DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern(DATE_TIME_PATTERN)
            .withZone(zoneId);

    public static String getTimeFormatted(ZonedDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    public static String getDateFormatted(ZonedDateTime dateTime) {
        return dateTime.format(DATE_FORMATTER);
    }

    public static void checkInterval(ZonedDateTime begInterval, ZonedDateTime endInterval) {
        if (endInterval.isBefore(begInterval))
            throw new DateTimeException(String.format("Invalid date interval: %s - %s",
                    getTimeFormatted(begInterval),
                    getTimeFormatted(endInterval)));
    }

    public static List<TimeInterval> splitInterval(
            ChronoUnit chronoUnit, int chronoSize, ZonedDateTime begInterval, ZonedDateTime endInterval) {
        checkInterval(begInterval, endInterval);
        if (chronoSize <= 0)
            throw new DateTimeException(String.format("Invalid interval: %d", chronoSize));
        List<TimeInterval> intervals = new ArrayList<>();
        ChronoZonedDateTime[] periodTmp = new ChronoZonedDateTime[2];
        periodTmp[0] = begInterval;
        while (periodTmp[0].isBefore(endInterval)) {
            periodTmp[1] = periodTmp[0].plus(chronoSize, chronoUnit);
            if (periodTmp[1].isAfter(endInterval))
                periodTmp[1] = endInterval;
            intervals.add(new TimeInterval((ZonedDateTime) periodTmp[0], (ZonedDateTime) periodTmp[1]));
            periodTmp[0] = periodTmp[0]
                    .plus(chronoSize, chronoUnit)
                    .plus(1, ChronoUnit.MICROS);
        }
        return intervals;
    }

    public static ZonedDateTime parseDate(String textDate) {
        ZonedDateTime date;
        try {
            String[] dateArray = textDate.split("\\.");
            if (dateArray.length < 3)
                throw new IllegalArgumentException(String.format("Incorrect date: %s", textDate));
            int day = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);
            int year = Integer.parseInt(dateArray[2]);
            date = ZonedDateTime.of(year, month, day, 0, 0, 0, 0, DEFAULT_ZONE_ID);
        } catch (Throwable e) {
            throw new DateTimeException(
                    String.format("Can't parse '%s' into date: %s", textDate, e.getMessage()));
        }
        return date;
    }

}
