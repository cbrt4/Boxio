package dev.challenge.boxio.model.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import dev.challenge.boxio.model.BoxEntity;
import io.reactivex.Flowable;

@Dao
public interface BoxDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBox(BoxEntity boxEntity);

    @Query("SELECT * FROM box")
    Flowable<List<BoxEntity>> getBoxes();

    @Query("DELETE FROM box")
    void clearBoxes();
}
