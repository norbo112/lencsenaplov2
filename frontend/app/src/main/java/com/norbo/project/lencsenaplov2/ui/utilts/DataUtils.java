package com.norbo.project.lencsenaplov2.ui.utilts;

import com.norbo.project.lencsenaplov2.data.model.Lencse;

import java.time.Duration;
import java.time.Instant;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataUtils {
    @Inject
    public DataUtils() {
    }

    public String elapsedTime(long begin, long end) {
        if(begin == 0) return "Nincs kezdő időpont";

        Duration duration = Duration.between(Instant.ofEpochMilli(begin),
                Instant.ofEpochMilli(end));
        long ora = duration.toMinutes() / 60;
        long perc0 = duration.toMinutes() % 60;
        return "Eltelt idő: "+ora+" óra és "+ perc0+" perc";
    }

    public float elapsedTimeFloat(long begin, long end) {
        Duration duration = Duration.between(Instant.ofEpochMilli(begin),
                Instant.ofEpochMilli(end));
        return duration.toMinutes();
    }

    public String getTisztitoViz(Lencse lencseadat) {
        if(lencseadat.getTisztitoViz() == 1)
            return "Tablettás vízbe berakva";
        else {
            return "";
        }
    }
}
