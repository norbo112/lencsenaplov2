package com.norbo.project.lencsenaplov2.ui.utils;

import com.norbo.project.lencsenaplov2.ui.utilts.DataUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

public class DataUtilsTest {
    private DataUtils underTest;

    @BeforeEach
    void setUp() {
        underTest = new DataUtils();
    }

    @Test
    public void elapsedTimeFloat_helyes_eltelt_ido_mutatasa() {
        long adatStart = LocalDateTime.of(2020, Month.JANUARY, 1, 12,00,00).toInstant(ZoneOffset.ofHours(2)).toEpochMilli();
        long adatEnd = LocalDateTime.of(2020, Month.JANUARY, 1, 15,00,00).toInstant(ZoneOffset.ofHours(2)).toEpochMilli();
        float actual = underTest.elapsedTimeFloat(adatStart, adatEnd);
        Assertions.assertEquals(180, actual);
    }
}
