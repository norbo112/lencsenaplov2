package com.norbo.project.lencsenaplov2.ui.utilts;

import com.norbo.project.lencsenaplov2.data.model.KezdoIdopont;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.db.entities.KezdoIdopontEntity;
import com.norbo.project.lencsenaplov2.db.entities.LencseEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConvertEntities {
    @Inject
    public ConvertEntities() {
    }

    public KezdoIdopont convertFromEntityKezdo(KezdoIdopontEntity kezdoIdopontEntity) {
        if(kezdoIdopontEntity == null) return null;
        return new KezdoIdopont(kezdoIdopontEntity.getKezdoIdopont());
    }

    public KezdoIdopontEntity convertFromUI(KezdoIdopont kezdoIdopont) {
        return new KezdoIdopontEntity(kezdoIdopont.getKezdoIdopont());
    }

    public Lencse convertFromEntityLencse(LencseEntity lencseEntity) {
        return new Lencse(lencseEntity.getId(), lencseEntity.getBetetelIdopont(), lencseEntity.getKivetelIdopont(), lencseEntity.getTisztitoViz());
    }

    public LencseEntity convertFromUILencse(Lencse lencse) {
        return new LencseEntity(lencse.getId(), lencse.getBetetelIdopont(), lencse.getKivetelIdopont(), lencse.getTisztitoViz());
    }
}
