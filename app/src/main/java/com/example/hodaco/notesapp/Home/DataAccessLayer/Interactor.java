package com.example.hodaco.notesapp.Home.DataAccessLayer;

import android.util.Log;

import com.example.hodaco.notesapp.Home.Pojo.Notes;
import com.example.hodaco.notesapp.Home.View.InteractorInterface;
import com.example.hodaco.notesapp.Home.View.ModelInterface;
import com.example.hodaco.notesapp.Home.View.PresenterInterface;

import java.util.ArrayList;

/**
 * Created by hoda.CO on 31/07/2018.
 */

public class Interactor implements InteractorInterface {
 PresenterInterface presenter;
 ModelInterface model;

    public Interactor(PresenterInterface presenter) {
        this.presenter = presenter;
        initInteractor();
    }
    void initInteractor()
    {
        model=new Model(this);
        Log.i("hoda","interactor");
    }

    @Override
    public void getNotes() {
        model.getNotes();

    }

    @Override
    public void setNote(Notes note){
        model.setNote(note);
    }

    @Override
    public void updateNote(Notes note) {
       model.updateNote(note);
    }

    @Override
    public void removeNote(Notes note) {
       model.removeNote(note);
    }

    @Override
    public void ShowNotes(ArrayList<Notes> notes) {
        presenter.ShowNotes(notes);

    }

    @Override
    public void noConnection() {
       presenter.noConnection();
    }
}
