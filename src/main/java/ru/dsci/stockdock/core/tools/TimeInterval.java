package ru.dsci.stockdock.core.tools;

import lombok.Data;
import lombok.NonNull;

import java.time.ZonedDateTime;

@Data
public class TimeInterval {

    private ZonedDateTime begInterval;
    private ZonedDateTime endInterval;

    @Override
    public String toString() {
        return String.format("%s-%s",
                DateTimeTools.getTimeFormatted(begInterval),
                DateTimeTools.getTimeFormatted(endInterval));
    }

    public TimeInterval(@NonNull ZonedDateTime begInterval, @NonNull ZonedDateTime endInterval) {
        DateTimeTools.checkInterval(begInterval, endInterval);
        this.begInterval = begInterval;
        this.endInterval = endInterval;
    }
}
