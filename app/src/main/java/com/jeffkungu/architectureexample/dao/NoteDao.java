package com.jeffkungu.architectureexample.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jeffkungu.architectureexample.entity.Note;

import java.util.List;

/**
 * Created by jeffkungu on 07/01/2019.
 * This is an interface used by Room to perform data base operations on the table created by the Entity.
 * Its advisable to use one Dao PER entity.
 */
@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    /**
     * Room doesn't have an Annotation for deleting all data from the table.
     * So we have to define our own method using the @Query annotation
     */
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    /**
     * Room doesn't have an Annotation for fetching all data from the table.
     * So we have to define our own method using the @Query annotation.
     * We use LiveData which helps observe any change made to the table.
     */
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
