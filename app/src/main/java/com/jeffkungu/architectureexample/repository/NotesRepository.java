package com.jeffkungu.architectureexample.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.jeffkungu.architectureexample.dao.NoteDao;
import com.jeffkungu.architectureexample.database.NoteDataBase;
import com.jeffkungu.architectureexample.entity.Note;

import java.util.List;

/**
 * Created by jeffkungu on 08/01/2019.
 */

public class NotesRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NotesRepository(Application application) {
        NoteDataBase dataBase = NoteDataBase.getInstance(application);
        noteDao = dataBase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        InsertNoteAsyncTask insertNoteAsyncTask =  new InsertNoteAsyncTask(noteDao);
        insertNoteAsyncTask.execute(note);
    }

    public void update(Note note) {
        UpdateNoteAsyncTask updateNoteAsyncTask = new UpdateNoteAsyncTask(noteDao);
        updateNoteAsyncTask.execute(note);
    }

    public void delete(Note note) {
        DeleteNoteAsyncTask deleteNoteAsyncTask = new DeleteNoteAsyncTask(noteDao);
        deleteNoteAsyncTask.execute(note);
    }

    public void deleteAllNotes() {
        DeleteAllNoteAsyncTask deleteAllNoteAsyncTask = new DeleteAllNoteAsyncTask(noteDao);
        deleteAllNoteAsyncTask.execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        public DeleteAllNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... notes) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
