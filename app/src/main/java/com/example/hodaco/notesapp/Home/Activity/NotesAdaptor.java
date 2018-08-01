package com.example.hodaco.notesapp.Home.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hodaco.notesapp.Home.Pojo.Notes;
import com.example.hodaco.notesapp.R;

import java.util.ArrayList;

/**
 * Created by hoda.CO on 30/07/2018.
 */
public class NotesAdaptor extends ArrayAdapter {
    ArrayList<com.example.hodaco.notesapp.Home.Pojo.Notes> Notes;
    public NotesAdaptor(@NonNull Context context, int resource, @NonNull ArrayList<Notes> notes) {
        super(context, 0, notes);
        Notes=notes;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =LayoutInflater.from(getContext()).inflate(R.layout.single_row,parent,false);
       TextView title= view.findViewById(R.id.titletext);
        TextView date= view.findViewById(R.id.datetext);
        title.setText(String.valueOf(Notes.get(position).getTitle()));
        date.setText(String.valueOf(Notes.get(position).getDate()));
           return view;
    }
}
