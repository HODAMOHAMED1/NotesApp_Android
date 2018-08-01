package com.example.hodaco.notesapp.SecondActivity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.hodaco.notesapp.Home.Pojo.Notes;
import com.example.hodaco.notesapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.body)
    TextView body;
    @BindView(R.id.textView6)
    TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Notes note = (Notes) intent.getSerializableExtra("note");
        title.setText(note.getTitle().toString());
        body.setText(note.getBody().toString());
        date.setText(note.getDate().toString());
        time.setText(note.getTime().toString());




    }
}
