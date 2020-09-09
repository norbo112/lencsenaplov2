package com.norbo.project.lencsenaplov2.ui.utils.cleanlencelist;

import com.norbo.project.lencsenaplov2.ui.utils.FormatUtils;

public class TisztitoVizElem {
    private long datum;

    public TisztitoVizElem(long datum) {
        this.datum = datum;
    }

    public long getDatum() {
        return datum;
    }

    public void setDatum(long datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return FormatUtils.getDayString(datum);
    }
}
