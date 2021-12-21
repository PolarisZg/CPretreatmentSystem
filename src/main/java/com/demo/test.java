package com.demo;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class test {
    public static void main(String [] args){
        ArrayList<String> arrayList1 = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList1.add("1");
        arrayList1.add("2");
        arrayList1.add("3");

        arrayList2.add("4");
        arrayList2.add("5");
        arrayList2.add("6");

        replace(arrayList1,2,arrayList2);
        System.out.println("Happy");
    }

    static void replace(ArrayList arrayList1, int location , ArrayList arrayList2){
        int size2 = arrayList2.size();
        for(int i = 0 ; i < size2 ; i++){
            arrayList1.add(i + location, arrayList2.get(i));
        }
        arrayList1.remove(location - 1);
    }
}
