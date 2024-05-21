package com.test;

public class Student {
    private String stId;
    private int markIT;
    private int markCW;

    public Student(){

    }

    public Student(String stId, int markIT, int markCW){
        this.stId = stId;
        this.markIT = markIT;
        this.markCW = markCW;
    }

    public String getStId(){
        return this.stId;
    }

    public void setStId(){
        this.setStId();
    }

    public int getMarkIT(){
        return this.markIT;
    }

    public void setMarkIT(){
        this.setMarkIT();
    }

    public int getMarkCW(){
        return this.markCW;
    }

    public void setMarkCW(){
        this.setMarkCW();
    }
}
