package com.inducesmile.oblig1;

import java.util.ArrayList;

public class ObjectsArray {

    public static ArrayList<ImageObjects> arrayList = new ArrayList<>();
    private static ImageObjects i1 = new ImageObjects(R.drawable.bart, "Bart");
    private  static ImageObjects i2 = new ImageObjects(R.drawable.cartman, "Cartman");
    private static ImageObjects i3 = new ImageObjects(R.drawable.homer, "Homer");
    private static ImageObjects i4 = new ImageObjects(R.drawable.kenny, "Kenny");
    private static ImageObjects i5 = new ImageObjects(R.drawable.kyle, "Kyle");
    private static ImageObjects i6 = new ImageObjects(R.drawable.marge, "Marge");


public static ArrayList<ImageObjects> addStandardPic(){
    //ArrayList arrayList = new ArrayList();
    arrayList.add(i1);
    arrayList.add(i2);
    arrayList.add(i3);
    arrayList.add(i4);
    arrayList.add(i5);
    arrayList.add(i6);

    return arrayList;
}



    public static ArrayList<ImageObjects> addPicture (ArrayList<ImageObjects> arrayList, int addImage, String addName){
    arrayList.add(new ImageObjects(addImage, addName));
    return arrayList;
}




}
