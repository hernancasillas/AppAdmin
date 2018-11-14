package com.purvik.studinfo;

public class Job {

    private int id;
    private String name;

    public Job(){}

    public Job(int id, String name){ this.id = id; this.name = name; }

    public Job(String name){ this.name = name; }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }


}
