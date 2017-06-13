package com.claresti.directoriodigital;

import java.util.ArrayList;

/**
 * Created by pizano on 12/06/17.
 */

public class Commerce {

    private String id;
    private String name;
    private String description;
    private Category category;
    private String address;
    private String website;
    private String lat;
    private String lng;
    private ArrayList<Phone> phones;
    private ArrayList<Mail> mails;
    private String logo;
    private Promotion promotion;

    public Commerce(){
        phones = new ArrayList<Phone>();
        mails = new ArrayList<Mail>();
    }

    public Commerce(String id, String name, String description, Category category, String address,
                    String website, String lat, String lng, ArrayList<Phone> phones,
                    ArrayList<Mail> mails, String logo, Promotion promotion) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.address = address;
        this.website = website;
        this.lat = lat;
        this.lng = lng;
        this.phones = phones;
        this.mails = mails;
        this.logo = logo;
        this.promotion = promotion;
    }

    public Commerce(String id, String name, String description, Category category, String address,
                    String website, String lat, String lng, String logo, Promotion promotion) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.address = address;
        this.website = website;
        this.lat = lat;
        this.lng = lng;
        this.logo = logo;
        this.promotion = promotion;
        phones = new ArrayList<Phone>();
        mails = new ArrayList<Mail>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public ArrayList<Phone> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone){
        phones.add(phone);
    }

    public boolean removePhone(Phone phone){
        return phones.remove(phone);
    }

    public ArrayList<Mail> getMails() {
        return mails;
    }

    public void setMails(ArrayList<Mail> mails) {
        this.mails = mails;
    }

    public void addMail(Mail mail){
        mails.add(mail);
    }

    public boolean removeMail(Mail mail){
        return mails.remove(mail);
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}
