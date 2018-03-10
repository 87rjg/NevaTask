package com.ram.nevatask;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by RAMJEE on 09-03-2018.
 *
 */

class JsonResponse {

    @SerializedName("data")
    private ArrayList<JsonData> data = new ArrayList<>();

    public ArrayList<JsonData> getData(){
        return data;
    }

   public void setData(ArrayList<JsonData> data){
        this.data = data;
   }
}
