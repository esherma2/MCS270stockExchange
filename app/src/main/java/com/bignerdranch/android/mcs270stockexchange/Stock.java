package com.bignerdranch.android.mcs270stockexchange;

import java.util.UUID;

/**
 * Created by nbens_000 on 5/2/2016.
 */
public class Stock {

    private UUID mId;
    private String mTitle;
    private boolean mOverWeight;
    private boolean mUnderWeight;
    private boolean mNeutral;
    private int mWeight;



    public boolean isNeutral(){
        return mNeutral;
    }

    public void setNeutral(boolean neutral){
        mNeutral = neutral;
        if (neutral) {
            this.setOverWeight(false);
            this.setUnderWeight(false);
        }
    }

    public int getWeight(){
        if(mWeight == 0){
            setOverWeight(true);
        }
        else if (mWeight == 1){
            setNeutral(true);
        }
        else if(mWeight==2){
            setUnderWeight(true);
        }
        return mWeight;
    }

    public void setWeight(int weight){
        /*if (weight == 0){
            this.setOverWeight(true);
        }else if (weight == 1){
            this.setNeutral(true);
        }else if (weight==2){
            this.setUnderWeight(true);
        }*/
        mWeight = weight;
    }

    public boolean isUnderWeight(){
        return mUnderWeight;
    }

    public void setUnderWeight(boolean UW){
        mUnderWeight = UW;
        if (UW) {
            this.setOverWeight(false);
            this.setNeutral(false);
        }
    }

    public boolean isOverWeight(){
        return mOverWeight;
    }

    public void setOverWeight(boolean OW){
        mOverWeight = OW;
        if (OW){
            this.setNeutral(false);
            this.setUnderWeight(false);
        }
    }


    public Stock(){
        // Generate unique modifier
        this(UUID.randomUUID());
    }

    public Stock(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
