package com.zakris.records;

public class Record {
    private int id;
    private String index;
    private String artist;
    private String name;
    private int year;
    private String format;
    private int pressYear = 0;
    private String notes = "";

    // constructor
    public Record(int id, String index, String artist, String name, int year, String format, int pressYear, String notes) {
        this.id = id;
        this.index = index;
        this.artist = artist;
        this.name = name;
        this.year = year;
        this.format = format;
        this.pressYear = pressYear;
        this.notes = notes;
    }

    // setters
    public void setid(int albumId) {
        this.id = albumId;
    }

    public void setArtist(String artistName) {
        this.artist = artistName;
    }

    public void setName(String albumName) {
        this.name = albumName;
    }

    public void setYear(int albumYear) {
        this.year = albumYear;
    }

    public void setFormat(String albumFormat) {
        this.format = albumFormat;
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
    public int getId() {
        return this.id;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getName() {
        return this.name;
    }

    public int getYear() {
        return this.year;
    }

    public String getFormat() {
        return this.format;
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