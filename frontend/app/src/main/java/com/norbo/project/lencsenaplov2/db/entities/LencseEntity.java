package com.norbo.project.lencsenaplov2.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "lencse_table")
public class LencseEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long betetelIdopont;
    private long kivetelIdopont;
    private int tisztitoViz;

    public LencseEntity() {
    }

    @Ignore
    public LencseEntity(long betetelIdopont, long kivetelIdopont, int tisztitoViz) {
        this.betetelIdopont = betetelIdopont;
        this.kivetelIdopont = kivetelIdopont;
        this.tisztitoViz = tisztitoViz;
    }

    @Ignore
    public LencseEntity(int id, long betetelIdopont, long kivetelIdopont, int tisztitoViz) {
        this.id = id;
        this.betetelIdopont = betetelIdopont;
        this.kivetelIdopont = kivetelIdopont;
        this.tisztitoViz = tisztitoViz;
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

    public int getTisztitoViz() {
        return tisztitoViz;
    }

    public void setTisztitoViz(int tisztitoViz) {
        this.tisztitoViz = tisztitoViz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LencseEntity that = (LencseEntity) o;
        return id == that.id &&
                betetelIdopont == that.betetelIdopont &&
                kivetelIdopont == that.kivetelIdopont &&
                tisztitoViz == that.tisztitoViz;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, betetelIdopont, kivetelIdopont, tisztitoViz);
    }
}
