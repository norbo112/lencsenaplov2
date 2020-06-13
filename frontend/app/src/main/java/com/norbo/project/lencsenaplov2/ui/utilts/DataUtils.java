package com.norbo.project.lencsenaplov2.ui.utilts;

import java.time.Duration;
import java.time.Instant;

public class DataUtils {
    public static String elapsedTime(long begin, long end) {
        if(begin == 0) return "Nincs kezdő időpont";

        Duration duration = Duration.between(Instant.ofEpochMilli(begin),
                Instant.ofEpochMilli(end));
        long ora = duration.toMinutes() / 60;
        long perc0 = duration.toMinutes() % 60;
        return ora+" óra és "+ perc0+" perc";
    }

    public static float elapsedTimeFloat(long begin, long end) {
        Duration duration = Duration.between(Instant.ofEpochMilli(begin),
                Instant.ofEpochMilli(end));
        return duration.toMinutes();
    }
}
