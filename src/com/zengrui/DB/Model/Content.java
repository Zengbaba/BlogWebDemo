package com.zengrui.DB.Model;

import com.zengrui.DB.Inerface.Model;

/**
 * Created by Zeng Rui on 2017/8/31.
 */
public class Content implements Model{
    private String id;
    private char[] titel = new char[100];
    private char[] blog = new char[4000];

    public Content(){}

    public Content(String id, char[] titel, char[] blog) {
        this.id = id;
        this.titel = titel;
        this.blog = blog;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public char[] getTitel() {
        return titel;
    }

    public void setTitel(char[] titel) {
        this.titel = titel;
    }

    public char[] getBlog() {
        return blog;
    }

    public void setBlog(char[] blog) {
        this.blog = blog;
    }
}
