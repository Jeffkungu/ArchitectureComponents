package com.jeffkungu.architectureexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeffkungu.architectureexample.R;
import com.jeffkungu.architectureexample.entity.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffkungu on 09/01/2019.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NotesViewHolder>{
    private List<Note> notes = new ArrayList<>();

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NotesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textViewDescription.setText(note.getDescription());
        holder.textViewTitle.setText(note.getTitle());
        holder.textViewPriority.setText(String.valueOf(note.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;


        public NotesViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewPriority = itemView.findViewById(R.id.textViewPriority);
        }
    }
}
