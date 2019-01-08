package com.jeffkungu.architectureexample.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.net.wifi.aware.PublishConfig;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.jeffkungu.architectureexample.dao.NoteDao;
import com.jeffkungu.architectureexample.entity.Note;

/**
 * Created by jeffkungu on 07/01/2019.
 * Creating a Database we use the @Database annotation which takes two arguments: Entity/List of Entity
 * and version number.
 */

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase {

    // This class is a singletone class.
    private static NoteDataBase instance;

    public abstract NoteDao noteDao();

    /**
     * Method used to create the database instance.
     * We use synchronised annotation meaning it can only be called once by any thread, hence prevents creating
     * more than one instance.
     * @param context
     * @return NoteDatabase instance.
     */
    public static synchronized NoteDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDataBase.class, "name_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDataBase dataBase) {
            noteDao = dataBase.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            return null;
        }
    }
}
