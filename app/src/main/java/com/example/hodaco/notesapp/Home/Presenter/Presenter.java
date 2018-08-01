package com.example.hodaco.notesapp.Home.Presenter;

import android.util.Log;

import com.example.hodaco.notesapp.Home.DataAccessLayer.Interactor;
import com.example.hodaco.notesapp.Home.Pojo.Notes;
import com.example.hodaco.notesapp.Home.View.InteractorInterface;
import com.example.hodaco.notesapp.Home.View.PresenterInterface;
import com.example.hodaco.notesapp.Home.View.ViewInterface;

import java.util.ArrayList;

/**
 * Created by hoda.CO on 31/07/2018.
 */

public class Presenter implements PresenterInterface{
    ViewInterface view;
    InteractorInterface interactor;

    public Presenter(ViewInterface view) {
        this.view = view;
        initPresenter();
    }
    void initPresenter()
    {
        interactor=new Interactor(this);
        Log.i("hoda","presenter");
        view.initView();
    }
    @Override
    public void getNotes() {
       interactor.getNotes();
    }

    @Override
    public void setNote(Notes note) {
         interactor.setNote(note);
    }

    @Override
    public void ShowNotes(ArrayList<Notes> notes) {
               view.ShowNotes(notes);
    }

    @Override
    public void noConnection() {
       view.noConnection();
    }
}
