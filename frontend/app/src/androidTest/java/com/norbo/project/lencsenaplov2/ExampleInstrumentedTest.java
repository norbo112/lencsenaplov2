package com.norbo.project.lencsenaplov2;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.db.LencseDatabase;
import com.norbo.project.lencsenaplov2.db.dao.LencseDao;
import com.norbo.project.lencsenaplov2.db.entities.LencseEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
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
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.norbo.project.lencsenaplov2", appContext.getPackageName());
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