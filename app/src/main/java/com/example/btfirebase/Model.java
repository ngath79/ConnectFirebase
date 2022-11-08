package com.example.btfirebase;

public class Model {
    String name,classes,email,surl;

    public Model() {
    }

    public Model(String name, String classes, String email, String surl) {
        this.name = name;
        this.classes = classes;
        this.email = email;
        this.surl = surl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
