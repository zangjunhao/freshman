package com.mredrock.cyxbs.freshman.view.CustomView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BarView extends View {

    private Paint mPaint;
    private Path mPath;
    private Paint textPaint;
    private int space;
    private int[] startYs = new int[7];
    private int dashStartX;
    private int dashEndX;
    private int durationMillis;
    private List<BarRect> rects;
    private int[] colors = new int[]{Color.parseColor("#C59AFF"),Color.parseColor("#FF9DAF"),Color.parseColor("#9DB4FF")};
    private int data[];
    private AnimatorSet set;
    private String[] names;
    private double proportion;
    private int topNum;
    private TextPaint namePaint;
//    private StaticLayout[] layouts = new StaticLayout[3];

    public BarView(Context context,int[] data,String[]names,int durationMillis){
        super(context);
        this.data = data;
        this.durationMillis = durationMillis;
        this.names = names;
        if (data[0]<=120){
            proportion = 1;
            topNum = 120;
        }else {
            proportion = data[0]/(120+0.0);
            topNum =(int) proportion*120;
        }
        initPaint();
        initTextPaint();
        initNamePaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width / 1.32);
        setMeasuredDimension(width, height);
        drawDash(height,width);
        initRect(width, height);
        set.start();
    }

    private void initNamePaint(){
        namePaint = new TextPaint();
        namePaint.setAntiAlias(true);
        namePaint.setStrokeWidth(1);
        namePaint.setStyle(Paint.Style.FILL);
        namePaint.setColor(Color.parseColor("#0083FF"));
        namePaint.setTextSize(dip2px(14));
        namePaint.setHinting(Display.Mode.PARCELABLE_WRITE_RETURN_VALUE);
        namePaint.setTextAlign(Paint.Align.CENTER);
//        for (int i = 0;i<names.length;i++){
//            layouts[i] = new StaticLayout(names[i], namePaint, (int) (400),
//                    Layout.Alignment.ALIGN_CENTER, 1F, 0, false);
//        }
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#440083FE"));
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setPathEffect(new DashPathEffect(new float[]{10, 10}, 0));
        mPath = new Path();
    }

    private void drawDash(int height,int width) {
        dashStartX = width / 7;
        dashEndX = width - width / 9;
        space =(int)( height /(8*proportion)) ;
        int endSpace = height / 8;
        int startY = height - endSpace;
        mPath.reset();
        for (int i = 0; i < 7; i++) {
            mPath.moveTo(dashStartX, startY);
            mPath.lineTo(dashEndX, startY);
            startYs[i] = startY;
            startY -= space;
        }
    }

    private void initRect(int layoutWidth,int layoutHeight){
        rects = new ArrayList<>();
        int centreSpace = layoutWidth/4;
        int haftWidth = layoutWidth/20;
        int allHeight = startYs[0] - startYs[startYs.length-1];
        int start = layoutWidth/4+layoutWidth/40;
        set = new AnimatorSet();
        Paint numberPaint = new Paint();
        numberPaint.setStyle(Paint.Style.FILL);
        numberPaint.setStrokeWidth(1);
        numberPaint.setTextSize(dip2px(14));
        numberPaint.setColor(Color.parseColor("#FF5A5A"));
        numberPaint.setTextAlign(Paint.Align.CENTER);
        numberPaint.setTypeface(Typeface.SERIF);
        numberPaint.setHinting(Display.Mode.CONTENTS_FILE_DESCRIPTOR);
        for (int i = 0;i<3;i++){
            BarRect rect = new BarRect();
            rect.bottom = startYs[0];
            rect.top = startYs[0];
            int centrePosition = start+i*centreSpace;
            rect.left = centrePosition-haftWidth;
            rect.right = centrePosition+haftWidth;
            Paint paint = new Paint();
            paint.setColor(colors[i]);
            paint.setStyle(Paint.Style.FILL);
            rect.setPaint(paint);
            float height = Float.valueOf(data[i]*allHeight/topNum+"");
            float topPosition = startYs[0] -height;
            ValueAnimator valueAnimator = ObjectAnimator.ofFloat(rect,"top",rect.bottom,topPosition);
            ValueAnimator numberAnimator = ObjectAnimator.ofInt(rect,"number",0,data[i]);
            rect.setNumberPositionY(rect.top);
            rect.setNumberPositionX(centrePosition);
            rect.setNamePositionY(rect.bottom);
            rect.setBelowDistance(layoutHeight/10);
            rect.setNamePositionX(centrePosition);
            rect.setNumberPaint(numberPaint);
            rect.setNamePaint(namePaint);
            set.play(valueAnimator);
            set.play(numberAnimator);
            rects.add(rect);
        }
        set.setDuration(durationMillis/2);
    }

    private int dip2px(int dp){
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }

    private void drawText(Canvas canvas) {
        int width = getWidth();
        int offSet = getHeight() / 90;
        float startX = width / 20;
        int text = 0;
        for (int i = 0; i < startYs.length; i++) {
            canvas.drawText(String.valueOf(text), startX, startYs[i] + offSet, textPaint);
            text += 20;
        }
    }

    private void initTextPaint(){
        textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#0083FE"));
        textPaint.setStrokeWidth(1);
        textPaint.setTextSize(dip2px(13));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawText(canvas);
        canvas.drawPath(mPath, mPaint);
        for (int i = 0; i < data.length; i++) {
            BarRect rect = rects.get(i);
            canvas.drawRect(rect, rect.getPaint());
            canvas.drawText(names[i],rect.getNamePositionX(),rect.getNumberPositionY()+rect.getBelowDistance(),rect.getNamePaint());
            canvas.drawText(rect.getNumber()+"人",rect.getNumberPositionX(),rect.top-dip2px(2),rect.getNumberPaint());
//            canvas.save();
//            canvas.translate(rect.getNamePositionX(),rect.getNamePositionY());
//            layouts[i].draw(canvas);
//            canvas.restore();
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

}
