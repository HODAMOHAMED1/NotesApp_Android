package com.example.hodaco.notesapp.Home.Pojo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by hoda.CO on 29/07/2018.
 */

public class Notes implements Serializable{
    private  String id;
    private String title;
    private String body;
    private String date;
    private String time;

    public Notes() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
