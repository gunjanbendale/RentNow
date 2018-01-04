package com.gb.gunjanbendale.rentnow;



public class MachineType {
    String Name;
    private int thumbnail;

    public MachineType(String N, int t){
        Name=N;
        thumbnail= t;
    }

    public String getName(){
        return Name;
    }

    public void setName(String n){
        Name=n;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
