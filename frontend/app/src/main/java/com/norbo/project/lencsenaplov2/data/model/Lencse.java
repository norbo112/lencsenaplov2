package com.norbo.project.lencsenaplov2.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "lencse_table")
public class Lencse implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long betetelIdopont;
    private long kivetelIdopont;

    public Lencse() {
    }

    @Ignore
    public Lencse(long betetelIdopont, long kivetelIdopont) {
        this.betetelIdopont = betetelIdopont;
        this.kivetelIdopont = kivetelIdopont;
    }

    public long getBetetelIdopont() {
        return betetelIdopont;
    }

    public void setBetetelIdopont(long betetelIdopont) {
        this.betetelIdopont = betetelIdopont;
    }

    public long getKivetelIdopont() {
        return kivetelIdopont;
    }

    public void setKivetelIdopont(long kivetelIdopont) {
        this.kivetelIdopont = kivetelIdopont;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
