package com.example.hodaco.notesapp.Home.DataAccessLayer;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.hodaco.notesapp.Home.Activity.MainActivity;
import com.example.hodaco.notesapp.Home.Activity.NotesAdaptor;
import com.example.hodaco.notesapp.Home.Pojo.Notes;
import com.example.hodaco.notesapp.Home.View.InteractorInterface;
import com.example.hodaco.notesapp.Home.View.ModelInterface;
import com.example.hodaco.notesapp.Home.View.PresenterInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by hoda.CO on 31/07/2018.
 */

public class Model implements ModelInterface{
    InteractorInterface interactor;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<Notes> Noteslist;

    public Model(InteractorInterface interactor) {
        this.interactor = interactor;
    }

    @Override
    public void getNotes() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Notes");
        Noteslist = new ArrayList<Notes>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Noteslist.clear();
                for (DataSnapshot dsp : dataSnapshot.getChildren())
                {
                    Notes note = dsp.getValue(Notes.class);
                    Noteslist.add(0,note);
                }
                 interactor.ShowNotes(Noteslist);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                interactor.noConnection();

            }
        });
    }

    @Override
    public void setNote(Notes note) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Notes");
        String id = myRef.push().getKey();
        note.setId(id);
        myRef.child(id).setValue(note);
    }

    @Override
    public void removeNote(Notes note) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Notes");
        myRef.child(note.getId()).removeValue();
    }

    @Override
    public void updateNote(Notes note) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Notes");
        myRef.child(note.getId()).setValue(note);
    }
}
