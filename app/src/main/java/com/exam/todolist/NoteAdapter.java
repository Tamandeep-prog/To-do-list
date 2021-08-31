package com.exam.todolist;

import android.content.Context;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {


    ArrayList<note> notes;
    Context context;
    ItemClicked itemClicked;
    ViewGroup parent;

    public NoteAdapter(ArrayList<note> notes, Context context, ItemClicked itemClicked) {
        this.notes = notes;
        this.context = context;
        this.itemClicked= itemClicked;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.note_holder, parent, false);
        this.parent=parent;

        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        holder.title.setText(notes.get(position).getTitle());
        holder.description.setText(notes.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description;
        ImageView imgedit;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            title= itemView.findViewById(R.id.txt_note_name);
            description= itemView.findViewById(R.id.txt_note_description);
            imgedit= itemView.findViewById(R.id.imgedit);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(description.getMaxLines()==1)
                    {
                        description.setMaxLines(Integer.MAX_VALUE);
                    }
                    else
                    {
                        description.setMaxLines(1);
                    }
                    TransitionManager.beginDelayedTransition(parent);
                }
            });

            imgedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClicked.onClick(getAdapterPosition(), itemView);
                }
            });

        }
    }

    interface ItemClicked{
        void onClick(int position, View view);
    }


}
