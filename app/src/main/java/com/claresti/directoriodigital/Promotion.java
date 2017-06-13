package com.claresti.directoriodigital;

/**
 * Created by pizano on 12/06/17.
 */

public class Promotion {

    private String valid;
    private String description;

    public Promotion(){

    }

    public Promotion(String valid, String description) {
        this.valid = valid;
        this.description = description;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
