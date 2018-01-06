package com.gb.gunjanbendale.rentnow;



public class MachineType {
    String Name;
    String imageURL;

    public MachineType(String N, String imageURL){
        Name=N;
        this.imageURL=imageURL;
    }

    public String getName(){
        return Name;
    }

    public void setName(String n){
        Name=n;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
