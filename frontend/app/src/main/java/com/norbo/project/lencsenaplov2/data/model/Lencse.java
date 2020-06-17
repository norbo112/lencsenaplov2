package com.norbo.project.lencsenaplov2.data.model;

import java.io.Serializable;

public class Lencse implements Serializable {
    private int id;
    private long betetelIdopont;
    private long kivetelIdopont;
    private int tisztitoViz;

    public Lencse() {
    }

    public Lencse(int id, long betetelIdopont, long kivetelIdopont, int tisztitoViz) {
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
}
