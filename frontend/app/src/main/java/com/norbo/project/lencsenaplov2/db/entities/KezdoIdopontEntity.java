package com.norbo.project.lencsenaplov2.db.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "kezdoidopont_table")
public class KezdoIdopontEntity implements Serializable {
    @PrimaryKey
    private long kezdoIdopont;

    public KezdoIdopontEntity() {
    }

    @Ignore
    public KezdoIdopontEntity(long kezdoIdopont) {
        this.kezdoIdopont = kezdoIdopont;
    }

    public long getKezdoIdopont() {
        return kezdoIdopont;
    }

    public void setKezdoIdopont(long kezdoIdopont) {
        this.kezdoIdopont = kezdoIdopont;
    }
}
