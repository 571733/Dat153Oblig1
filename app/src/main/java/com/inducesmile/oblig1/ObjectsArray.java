package com.inducesmile.oblig1;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ObjectsArray {

    public static ArrayList<ImageObjects> arrayList = new ArrayList<>();







    public static ArrayList<ImageObjects> addPicture (ArrayList<ImageObjects> arrayList, Bitmap addImage, String addName){
    arrayList.add(new ImageObjects(addImage, addName));
    return arrayList;
}




}
