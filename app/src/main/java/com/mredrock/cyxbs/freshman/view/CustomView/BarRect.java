package com.mredrock.cyxbs.freshman.view.CustomView;

import android.graphics.Paint;
import android.graphics.RectF;

public class BarRect extends RectF{

    private Paint paint;
    private float namePositionY;
    private Paint numberPaint;
    private float numberPositionY;
    private float numberPositionX;
    private float namePositionX;
    private int number;
    private Paint namePaint;
    private int belowDistance;


    public int getBelowDistance() {
        return belowDistance;
    }

    public void setBelowDistance(int belowDistance) {
        this.belowDistance = belowDistance;
    }

    public Paint getNamePaint() {
        return namePaint;
    }

    public void setNamePaint(Paint namePaint) {
        this.namePaint = namePaint;
    }

    public Paint getNumberPaint() {
        return numberPaint;
    }

    public void setNumberPaint(Paint numberPaint) {
        this.numberPaint = numberPaint;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getNumberPositionX() {
        return numberPositionX;
    }

    public void setNumberPositionX(float numberPositionX) {
        this.numberPositionX = numberPositionX;
    }

    public float getNamePositionX() {
        return namePositionX;
    }

    public void setNamePositionX(float namePositionX) {
        this.namePositionX = namePositionX;
    }

    public float getNamePositionY() {
        return namePositionY;
    }

    public void setNamePositionY(float namePositionY) {
        this.namePositionY = namePositionY;
    }

    public float getNumberPositionY() {
        return numberPositionY;
    }

    public void setNumberPositionY(float numberPositionY) {
        this.numberPositionY = numberPositionY;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public float getBottom(){
        return this.bottom;
    }
    public void setBottom(float bottom){
        this.bottom = bottom;
    }

    public void setTop(float top){
        this.top = top;
    }

    public float getTop(){
        return this.top;
    }
}

