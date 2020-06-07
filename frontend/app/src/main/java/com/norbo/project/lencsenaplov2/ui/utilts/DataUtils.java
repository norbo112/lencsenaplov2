package com.norbo.project.lencsenaplov2.ui.utilts;

import java.time.Duration;
import java.time.Instant;

public class DataUtils {
    public static String elapsedTime(long begin, long end) {
        Duration duration = Duration.between(Instant.ofEpochMilli(begin),
                Instant.ofEpochMilli(end));
        return duration.toMinutes() + " perc telt el.";
    }
}
