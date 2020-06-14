package com.norbo.project.lencsenaplov2.data.model;

import java.io.Serializable;

public class KezdoIdopont implements Serializable {
    private long kezdoIdopont;

    public KezdoIdopont() {
    }

    public KezdoIdopont(long kezdoIdopont) {
        this.kezdoIdopont = kezdoIdopont;
    }

    public long getKezdoIdopont() {
        return kezdoIdopont;
    }

    public void setKezdoIdopont(long kezdoIdopont) {
        this.kezdoIdopont = kezdoIdopont;
    }
}
