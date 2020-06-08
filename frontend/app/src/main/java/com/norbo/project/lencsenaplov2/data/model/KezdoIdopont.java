package com.norbo.project.lencsenaplov2.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "kezdoidopont_table")
public class KezdoIdopont implements Serializable {
    @PrimaryKey
    private long kezdoIdopont;

    public KezdoIdopont() {
    }

    @Ignore
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
