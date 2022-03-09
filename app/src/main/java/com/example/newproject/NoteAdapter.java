package com.example.newproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter{

    private ArrayList<Note> noteData;
    private View.OnClickListener mOnItemClickListener;
private boolean isDeleting;
private Context parentContext;

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

    public NoteAdapter (ArrayList<Note> arrayList, Context context) {
        noteData = arrayList;
        parentContext = context;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ContactViewHolder cvh = (ContactViewHolder) holder;
        cvh.getTextName().setText(noteData.get(position).getNoteSubject());
        cvh.getTextPriority().setText(noteData.get(position).getNotePriority());
        if (isDeleting){
            cvh.getDeleteButton().setVisibility(View.VISIBLE);
            cvh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(position);
                }
            });
        }
    else {
        cvh.getDeleteButton().setVisibility(View.INVISIBLE);
    }
    }
    public void setDelete(boolean b){
        isDeleting = b;
    }

    private void deleteItem(int position){
        Note note = noteData.get(position);
        NoteDataSource ds = new NoteDataSource(parentContext);
        try{
            ds.open();
            boolean didDelete= ds.deleteSubject(note.getNoteID());
            ds.close();
            if (didDelete){
                noteData.remove(position);
                notifyDataSetChanged();
            }
            else{
                Toast.makeText(parentContext,"Delete Failed!", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public int getItemCount() {
        return noteData.size();
    }

}
