package com.example.hodaco.notesapp.Home.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hodaco.notesapp.Home.Pojo.Notes;
import com.example.hodaco.notesapp.Home.Presenter.Presenter;
import com.example.hodaco.notesapp.Home.View.PresenterInterface;
import com.example.hodaco.notesapp.Home.View.ViewInterface;
import com.example.hodaco.notesapp.R;
import com.example.hodaco.notesapp.SecondActivity.Activity.SecondActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewInterface{
    PresenterInterface presenter;

    @BindView(R.id.addbtn)
    FloatingActionButton addbtn;
    @BindView(R.id.list)
    ListView list;
    EditText title;
    EditText body;
    AlertDialog alert;
    AlertDialog alert2;
    ArrayList<Notes> Notelist;
    Notes currrentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new Presenter(this);
        Notelist=new ArrayList<Notes>();
    }

    @Override
    public void initView() {
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                Notes note = Notelist.get(i);
                intent.putExtra("note", (Serializable) note);
                startActivity(intent);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                currrentNote = Notelist.get(i);
                showDeleteDialog();
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getNotes();
    }

    private void showDialog() {
        View vieww =null;
        AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
        vieww=LayoutInflater.from(MainActivity.this).inflate(R.layout.addnote_dialog, null);
        dia.setView(vieww);
        alert = dia.create();
        Button button = vieww.findViewById(R.id.button);
        title = vieww.findViewById(R.id.titlenote);
         body = vieww.findViewById(R.id.bodynote);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title_note = title.getText().toString();
                String body_note = body.getText().toString();
                if (!title_note.isEmpty() && !body_note.isEmpty()) {

                    Notes note = new Notes();
                    note.setBody(body_note);
                    note.setTitle(title_note);
                    note.setDate(getCurrentDate());
                    note.setTime(getActionTime());
                    presenter.setNote(note);
                    alert.cancel();
                    Toast.makeText(MainActivity.this, "note added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "please enter fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alert.show();
    }
   private void showDeleteDialog(){
        AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.edit_deletenote,null);
        dia.setView(view);
        alert2 = dia.create();
       Button update = view.findViewById(R.id.update);
       Button delete = view.findViewById(R.id.delete);
       title = view.findViewById(R.id.titlenote);
       body = view.findViewById(R.id.bodynote);
       title.setText(currrentNote.getTitle());
       body.setText(currrentNote.getBody());
       update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String title_note = title.getText().toString();
               String body_note = body.getText().toString();

               if (!title_note.isEmpty() && !body_note.isEmpty()) {
                   Notes note = new Notes();
                   note.setId(currrentNote.getId());
                   note.setBody(body_note);
                   note.setTitle(title_note);
                   note.setDate(getCurrentDate());
                   note.setTime(getActionTime());
                   presenter.updateNote(note);
                   alert2.cancel();
                   Toast.makeText(MainActivity.this, "note edited", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(MainActivity.this, "please enter fields", Toast.LENGTH_SHORT).show();
               }
           }
       });
       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               presenter.removeNote(currrentNote);
               alert2.cancel();
           }
       });
        alert2.show();

    }

    public String getCurrentDate() {
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = df.format(calender.getTime());
        return currentDate;
    }
    public String getActionTime()
    {
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a");
        String currentTime = df.format(calender.getTime());
        return currentTime;
    }



    @Override
    public void ShowNotes(ArrayList<Notes> notes) {

        Notelist = notes;
        NotesAdaptor adaptor = new NotesAdaptor(getApplicationContext(),0,Notelist);
        list.setAdapter(adaptor);
    }

    @Override
    public void noConnection() {
         Toast.makeText(MainActivity.this, "No Connection", Toast.LENGTH_SHORT).show();
    }
}
