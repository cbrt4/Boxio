package dev.challenge.boxio.model.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import dev.challenge.boxio.model.ColorEntity;
import io.reactivex.Flowable;

@Dao
public interface ColorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertColor(ColorEntity colorEntity);

    @Query("SELECT * FROM color")
    Flowable<List<ColorEntity>> getColors();

    @Query("DELETE FROM color")
    void clearColors();
}
