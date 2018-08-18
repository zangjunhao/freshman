package com.mredrock.cyxbs.freshman.view.CustomView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class RoundView extends View {

    private List<Circle> mList;
    private float halfWidth;
    private int data1;
    private int data2;
    private int durationMillis;
    private int outDegree;
    private int proportion1;
    private int proportion2;
    private AnimatorSet animatorSet;
    private Path mPath;
    private Paint outTextPaint;
    private Paint inTextPaint;

    public RoundView(Context context, int data1, int data2, int durationMillis) {
        super(context);
        this.data1 = data1;
        this.data2 = data2;
        this.durationMillis = durationMillis;
        mPath = new Path();
        outDegree = data1*360/(data2+data1);
        proportion1 = data1*100/(data1+data2);
        proportion2 = 100-proportion1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width,width);
        halfWidth = width/2;
        initRound(width);
        animatorSet.start();
        initTextPaint();
    }

    public void initTextPaint(){
        outTextPaint = new Paint();
        outTextPaint.setStyle(Paint.Style.FILL);
        outTextPaint.setStrokeWidth(1);
        outTextPaint.setTextSize(dip2px(16));
        outTextPaint.setColor(Color.parseColor("#70CEFE"));
        inTextPaint = new Paint();
        inTextPaint.setStyle(Paint.Style.FILL);
        inTextPaint.setStrokeWidth(1);
        inTextPaint.setTextSize(dip2px(16));
        inTextPaint.setColor(Color.parseColor("#FF8CC8"));
    }

    public void initRound(int size){
        int outRadius = size/2-size/10;
        int inRadius = size*2/9;
        int paintWidth = size/10;
        mList = new ArrayList<>();
        Circle outCircle = new Circle();
        outCircle.setRadius(outRadius);
        outCircle.setPaint(getPaint(Color.parseColor("#FFF3F9"),paintWidth));
        mList.add(outCircle);
        Circle inCircle = new Circle();  //内层背景
        inCircle.setRadius(inRadius);
        inCircle.setPaint(getPaint(Color.parseColor("#E3E9EF"),paintWidth));
        mList.add(inCircle);
        Circle outRealCircle = new Circle();  //外层实体
        Paint paint = getPaint(Color.parseColor("#ADB2FF"),paintWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        outRealCircle.setPaint(paint);
        outRealCircle.radius = outRadius;
        outRealCircle.textPositionX = -dip2px(50);
        mList.add(outRealCircle);
        Circle inRealCircle = new Circle();   //内层实体
        inRealCircle.radius = inRadius;
        Paint inPaint = getPaint(Color.parseColor("#FFABD7"),paintWidth);
        inPaint.setStrokeCap(Paint.Cap.ROUND);
        inRealCircle.setPaint(inPaint);
        inRealCircle.textPositionX = -dip2px(50);
        mList.add(inRealCircle);
        ValueAnimator proportionAnimator1 = ObjectAnimator.ofInt(outRealCircle,"proportion",0,proportion1);
        ValueAnimator proportionAnimator2= ObjectAnimator.ofInt(inRealCircle,"proportion",0,proportion2);
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(outRealCircle,"degree",0,outDegree);
        ValueAnimator inAnimator  = ObjectAnimator.ofInt(inRealCircle,"degree",0,360-outDegree);
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueAnimator,inAnimator,proportionAnimator1,proportionAnimator2);
        animatorSet.setDuration(durationMillis/2);
    }

    private Paint getPaint(int color,int width){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(halfWidth, halfWidth);
        for (int i = 0;i<2;i++){
            Circle circle = mList.get(i);
            canvas.drawCircle(0, 0, circle.radius, circle.paint);
        }
        for (int k = 2;k<4;k++){
            Circle circle = mList.get(k);
            float radius = circle.radius;
            mPath.arcTo(new RectF(-radius,-radius,radius,radius),-90,circle.getDegree());
            canvas.drawPath(mPath,circle.paint);
            if (k == 2) {
                canvas.drawText(circle.proportion + "%",circle.textPositionX, -radius, outTextPaint);
            }else {
                canvas.drawText(circle.proportion+"%",circle.textPositionX,-radius,inTextPaint);
            }
            mPath.reset();
        }
    }

    public void startAnimator() {
        requestLayout();
        CountDownTimer countDownTimer = new CountDownTimer(durationMillis, 10) {
            @Override
            public void onTick(long l) {
                invalidate();
            }

            @Override
            public void onFinish() {
                invalidate();
            }
        };
        countDownTimer.start();
    }

    private int dip2px(int dp){
        float density = getResources().getDisplayMetrics().density;
        return (int)(density*dp+0.5);
    }

    private class Circle{
        private float radius;
        private Paint paint;
        private int degree;
        private int proportion;
        private Paint textPaint;
        private int textPositionX;

        public Paint getTextPaint() {
            return textPaint;
        }

        public void setTextPaint(Paint textPaint) {
            this.textPaint = textPaint;
        }

        public int getTextPositionX() {
            return textPositionX;
        }

        public void setTextPositionX(int textPositionX) {
            this.textPositionX = textPositionX;
        }

        public int getProportion() {
            return proportion;
        }

        public void setProportion(int proportion) {
            this.proportion = proportion;
        }

        public int getDegree() {
            return degree;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }

        public void setRadius(float radius) {
            this.radius = radius;
        }

        public void setPaint(Paint paint) {
            this.paint = paint;
        }

        @Override
        public String toString() {
            return "radius:"+radius+" degree"+degree;
        }
    }
}
