package com.norbo.project.lencsenaplov2.db;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.norbo.project.lencsenaplov2.db.dao.LencseDao;
import com.norbo.project.lencsenaplov2.db.entities.LencseEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class RoomDatabaseTest {
    private LencseDatabase lencseDatabase;
    private LencseDao lencseDao;

    @Before
    public void init() {
        lencseDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().getTargetContext(), LencseDatabase.class
        ).build();
        lencseDao = lencseDatabase.lencseDao();
    }

    @After
    public void dbClose() {
        lencseDatabase.close();
    }

    @Test
    public void LencseDao_Insert_Select_is_one_equal() {
        LencseEntity lencse = new LencseEntity(112, 114, 0);
        long id = lencseDao.insert(lencse);
        lencse.setId((int) id);
//        assertTrue(id > 0);
        List<LencseEntity> lencseEntities = lencseDao.selectEntities();
        assertThat(lencseEntities.get(0), equalTo(lencse));
    }
}