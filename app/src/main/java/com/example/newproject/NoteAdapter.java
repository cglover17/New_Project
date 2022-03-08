package com.example.newproject;

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

    private boolean isLow;
    private boolean isMedium;

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        public TextView textSubject;
        public TextView textLowPriority;
        public TextView textMediumPriority;
        public TextView textHighPriority;
        public Button deleteButton;
        public ContactViewHolder(@NonNull View itemView){

            super(itemView);
            textSubject = itemView.findViewById(R.id.textSubject);
            textLowPriority= itemView.findViewById(R.id.textLow);
            textMediumPriority= itemView.findViewById(R.id.textMed);
            textHighPriority= itemView.findViewById(R.id.textHigh);
            deleteButton= itemView.findViewById(R.id.deleteButton);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
        public TextView getTextName(){
            return textSubject;
        }
        public TextView getTextLow(){
            return textLowPriority;
        }
        public TextView getTextMed(){
            return textMediumPriority;
        }
        public TextView getTextHigh(){
            return textHighPriority;
        }
        public Button getDeleteButton(){
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
        if (isLow) {
        cvh.getTextLow().setVisibility(View.VISIBLE);
      //  cvh.getTextMed().setVisibility(View.INVISIBLE);
      //  cvh.getTextHigh().setVisibility(View.INVISIBLE);

        }
        else if (isMedium) {
            //cvh.getTextLow().setVisibility(View.INVISIBLE);
            cvh.getTextMed().setVisibility(View.VISIBLE);
           // cvh.getTextHigh().setVisibility(View.INVISIBLE);
     }
        else {

          //  cvh.getTextLow().setVisibility(View.INVISIBLE);
         //   cvh.getTextMed().setVisibility(View.INVISIBLE);
            cvh.getTextHigh().setVisibility(View.VISIBLE);     }

    }

    public void setPriority3(boolean b) {
        isLow = b;
    }

    public void setPriority2(boolean b) {
        isMedium = b;
    }

    @Override
    public int getItemCount() {
        return noteData.size();
    }

}
