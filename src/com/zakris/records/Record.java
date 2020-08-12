package com.zakris.records;

public class Record {
    private String artist;
    private String name;
    private int year;
    private int pressYear = 0;
    private String index;
    private String notes = "";

    // constructor
    public Record(String artist, String name, int year, int pressYear, String index, String notes) {
        this.artist = artist;
        this.name = name;
        this.year = year;
        this.pressYear = pressYear;
        this.index = index;
        this.notes = notes;
    }

    // setters
    public void setArtist(String artistName) {
        this.artist = artistName;
    }

    public void setName(String albumName) {
        this.name = albumName;
    }

    public void setYear(int albumYear) {
        this.year = albumYear;
    }

    public void setPressYear(int albumPressYear) {
        this.pressYear = albumPressYear;
    }

    public void setIndex(String albumIndex) {
        this.index = albumIndex;
    }

    public void setnotes(String albumNotes) {
        this.notes = albumNotes;
    }
    

    // getters
    public String getArtist() {
        return this.artist;
    }

    public String getName() {
        return this.name;
    }

    public int getYear() {
        return this.year;
    }

    public int getPressYear() {
        return this.pressYear;
    }

    public String getIndex() {
        return this.index;
    }

    public String getNotes() {
        return this.notes;
    }
}