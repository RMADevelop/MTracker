package com.example.roma.mtracker_v3.model;

import com.example.roma.mtracker_v3.R;

import java.util.ArrayList;

/**
 * Created by Roma on 01.07.2017.
 */

public class InsertDescription {
    private Integer imageId;
    private String description;
    private ArrayList<InsertDescription> arrayImages;

    public InsertDescription(){
initArrayImages();
    }

    public InsertDescription(Integer imageId, String description) {
        this.imageId = imageId;
        this.description = description;
    }

    public void initArrayImages() {
        arrayImages = new ArrayList<>();
        arrayImages.add(new InsertDescription(R.mipmap.ic_food, "Food"));
        arrayImages.add(new InsertDescription(R.mipmap.ic_car, "Transport"));
        arrayImages.add(new InsertDescription(R.mipmap.ic_medic, "Medicine"));
        arrayImages.add(new InsertDescription(R.mipmap.ic_travel, "Travel"));
        arrayImages.add(new InsertDescription(R.mipmap.ic_education, "Education"));
    }

    public ArrayList<InsertDescription> getArrayImages() {
        return arrayImages;
    }

    public Integer getImageId() {
        return imageId;
    }


    public String getDescription() {
        return description;
    }

}
