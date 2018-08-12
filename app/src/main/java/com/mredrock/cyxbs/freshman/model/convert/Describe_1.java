package com.mredrock.cyxbs.freshman.model.convert;

import android.support.annotation.NonNull;

public class Describe_1 implements Comparable<Describe_1>{

    /*
    *接口7
     */
        private int id;
        private String name;
        private String content;
       private int oldPosition;
       private int newPosition;
       private int number;
       private String property;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(int oldPosition) {
        this.oldPosition = oldPosition;
    }

    public int getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(int newPosition) {
        this.newPosition = newPosition;
    }
    public Describe_1() {
    }

    public Describe_1(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int compareTo(@NonNull Describe_1 describe_1) {
       if (this.number>describe_1.getNumber()||this.number<=0){
           return 1;
       }else if (this.number<describe_1.getNumber()){
           return -1;
       }else {
           if (this.id>describe_1.getId()){
               return 1;
           }else if (this.id<describe_1.getId()){
               return -1;
           }else {
               return 0;
           }
       }
    }
}
