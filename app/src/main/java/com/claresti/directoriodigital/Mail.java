package com.claresti.directoriodigital;

/**
 * Created by pizano on 12/06/17.
 */

public class Mail {

    private String name;
    private String mail;

    public Mail(){

    }

    public Mail(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
