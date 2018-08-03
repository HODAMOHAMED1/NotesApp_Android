package com.example.hodaco.notesapp.Home.View;

import com.example.hodaco.notesapp.Home.Pojo.Notes;

import java.util.ArrayList;

/**
 * Created by hoda.CO on 31/07/2018.
 */

public interface PresenterInterface {
    void getNotes();
    void setNote(Notes note);
    void updateNote(Notes note);
    void removeNote(Notes note);
    void ShowNotes(ArrayList<Notes> notes);
    void noConnection();

}
