package com.zengrui.DB.Model;

import com.zengrui.DB.Inerface.Model;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Time;


/**
 * Created by Zeng Rui on 2017/8/23.
 */
public class Blog implements Model{
    private String id;
    private String titel;
    private Time time;
    private Date date;
    private String author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getData() {
        return date;
    }

    public void setData(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Blog() {
    }

    public Blog(String id, String titel, Time time, Date date, String author) {
        this.id = id;
        this.titel = titel;
        this.time = time;
        this.date = date;
        this.author = author;
    }
}
