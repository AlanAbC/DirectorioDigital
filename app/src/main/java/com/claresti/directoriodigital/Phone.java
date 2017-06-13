package com.claresti.directoriodigital;

/**
 * Created by pizano on 12/06/17.
 */

public class Phone {

    private String name;
    private String number;

    public Phone(){

    }

    public Phone(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
