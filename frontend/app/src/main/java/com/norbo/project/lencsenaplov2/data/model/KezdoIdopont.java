package com.norbo.project.lencsenaplov2.data.model;

import androidx.room.Entity;

@Entity
public class KezdoIdopont {
    private String kezdoIdopont;

    public KezdoIdopont() {
    }

    public String getKezdoIdopont() {
        return kezdoIdopont;
    }

    public void setKezdoIdopont(String kezdoIdopont) {
        this.kezdoIdopont = kezdoIdopont;
    }
}
