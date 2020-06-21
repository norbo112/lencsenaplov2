package com.norbo.project.lencsenaplov2.ui.utils;

import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.ui.utilts.DataUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

public class DataUtilsTest {
    private DataUtils underTest;

    @Mock
    private Lencse lencse;

    private final long adatStart = LocalDateTime.of(2020, Month.JANUARY, 1, 12,0, 0)
            .toInstant(ZoneOffset.ofHours(2)).toEpochMilli();;
    private final long adatEnd = LocalDateTime.of(2020, Month.JANUARY, 1, 15,0,0)
            .toInstant(ZoneOffset.ofHours(2)).toEpochMilli();;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new DataUtils();
    }

    @Test
    public void elapsedTime_helyes_szoveget_ad_vissza() {
        String actual = underTest.elapsedTime(adatStart, adatEnd);
        assertEquals("Eltelt idő: 3 óra és 0 perc", actual);
    }

    @Test
    public void elapsedTime_nulla_kezdetre_helyes_szoveget_ad_vissza() {
        String actual = underTest.elapsedTime(0, adatEnd);
        assertEquals("Nincs kezdő időpont", actual);
    }

    @Test
    public void elapsedTimeFloat_helyes_eltelt_ido_mutatasa() {
        float actual = underTest.elapsedTimeFloat(adatStart, adatEnd);
        assertEquals(180, actual);
    }

    @Test
    public void getTisztitoViz_lencse_gettisztitoviz_metodus_vegrehajtva() {
        //given
        BDDMockito.given(lencse.getTisztitoViz()).willReturn(1);
        //when
        underTest.getTisztitoViz(lencse);
        //then
        BDDMockito.then(lencse).should().getTisztitoViz();
    }

    @Test
    public void getTisztitoViz_jo_erteket_ad_vissza_ha_tisztitoviz_1() {
        //given
        BDDMockito.given(lencse.getTisztitoViz()).willReturn(1);
        //when
        String tisztitoViz = underTest.getTisztitoViz(lencse);

        assertEquals("Tablettás vízbe berakva", tisztitoViz);
    }
}
