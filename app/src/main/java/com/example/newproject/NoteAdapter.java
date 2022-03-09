package com.example.newproject;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter{

    private ArrayList<Note> noteData;
    private View.OnClickListener mOnItemClickListener;

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        public TextView textSubject;
        public TextView textPriority;
        public Button deleteButton;

        public ContactViewHolder (@NonNull View itemView){

            super(itemView);
            textSubject = itemView.findViewById(R.id.textSubject);
            textPriority= itemView.findViewById(R.id.textLow);
            deleteButton= itemView.findViewById(R.id.deleteButton);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
        public TextView getTextName(){
            return textSubject;
        }
        public TextView getTextPriority(){
            return textPriority;
        }
        public Button getDeleteButton() {
            return deleteButton;
        }
    }

    public NoteAdapter (ArrayList<Note> arrayList) {
        noteData = arrayList;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2,parent,false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ContactViewHolder cvh = (ContactViewHolder) holder;
        cvh.getTextName().setText(noteData.get(position).getNoteSubject());
        cvh.getTextPriority().setText(noteData.get(position).getNotePriority());
    }

    @Override
    public int getItemCount() {
        return noteData.size();
    }

}
