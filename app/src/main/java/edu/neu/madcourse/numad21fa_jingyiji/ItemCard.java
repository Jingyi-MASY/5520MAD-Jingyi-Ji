package edu.neu.madcourse.numad21fa_jingyiji;


import android.os.Parcel;
import android.os.Parcelable;

public class ItemCard implements ItemClickListener{


    private final String link;
    private final String name;


    //Constructor
    public ItemCard(String webLink, String name) {
        this.link = webLink;
        this.name = name;
    }

    public String getItemName() {
        return name;
    }

    public String getItemLink() {
        return link;
    }

    @Override
    public void onItemClick(int position) {
        //open in browser
    }




}