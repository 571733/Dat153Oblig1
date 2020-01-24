package com.inducesmile.oblig1;

import java.util.ArrayList;

public class ObjectsArray {

    private static ImageObjects i1 = new ImageObjects(R.drawable.bart, "Bart");
    private  static ImageObjects i2 = new ImageObjects(R.drawable.cartman, "Cartman");
    private static ImageObjects i3 = new ImageObjects(R.drawable.homer, "Homer");
    private static ImageObjects i4 = new ImageObjects(R.drawable.kenny, "Kenny");
    private static ImageObjects i5 = new ImageObjects(R.drawable.kyle, "Kyle");
    private static ImageObjects i6 = new ImageObjects(R.drawable.marge, "Marge");


public static ArrayList<ImageObjects> addStandardPic(){
    ArrayList arrayList = new ArrayList();
    arrayList.add(i1);
    arrayList.add(i2);
    arrayList.add(i3);
    //arrayList.add(i4);
    //arrayList.add(i5);
    //arrayList.add(i6);

    return arrayList;
}

    public static ImageObjects getI1() {
        return i1;
    }

    public static void setI1(ImageObjects i1) {
        ObjectsArray.i1 = i1;
    }

    public static ImageObjects getI2() {
        return i2;
    }

    public static void setI2(ImageObjects i2) {
        ObjectsArray.i2 = i2;
    }

    public static ImageObjects getI3() {
        return i3;
    }

    public static void setI3(ImageObjects i3) {
        ObjectsArray.i3 = i3;
    }

    public static ImageObjects getI4() {
        return i4;
    }

    public static void setI4(ImageObjects i4) {
        ObjectsArray.i4 = i4;
    }

    public static ImageObjects getI5() {
        return i5;
    }

    public static void setI5(ImageObjects i5) {
        ObjectsArray.i5 = i5;
    }

    public static ImageObjects getI6() {
        return i6;
    }

    public static void setI6(ImageObjects i6) {
        ObjectsArray.i6 = i6;
    }
}
