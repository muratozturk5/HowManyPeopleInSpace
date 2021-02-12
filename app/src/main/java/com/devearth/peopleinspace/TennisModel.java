package com.devearth.peopleinspace;

public class TennisModel {
    String name, biophoto,biophotowidth,biophotoheight,country,countryflag,launchdate,careerdays,title,location,bio,biolink,twitter;




    public TennisModel(String name, String biophoto, String biophotowidth, String biophotoheight, String country, String countryflag, String launchdate, String careerdays, String title, String location, String bio, String biolink, String twitter) {
        this.name = name;
        this.biophoto = biophoto;
        this.biophotowidth = biophotowidth;
        this.biophotoheight = biophotoheight;
        this.country = country;
        this.countryflag = countryflag;
        this.launchdate = launchdate;
        this.careerdays = careerdays;
        this.title = title;
        this.location = location;
        this.bio = bio;
        this.biolink = biolink;
        this.twitter = twitter;
    }

    public String getName() {
        return name;
    }

    public String getBiophoto() {
        return biophoto;
    }

    public String getBiophotowidth() {
        return biophotowidth;
    }

    public String getBiophotoheight() {
        return biophotoheight;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryflag() {
        return countryflag;
    }

    public String getLaunchdate() {
        return launchdate;
    }

    public String getCareerdays() {
        return careerdays;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
    }

    public String getBiolink() {
        return biolink;
    }

    public String getTwitter() {
        return twitter;
    }
}