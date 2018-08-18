package com.mredrock.cyxbs.freshman.view.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.CampusStrategy;
import com.mredrock.cyxbs.freshman.model.convert.GetName;
import com.mredrock.cyxbs.freshman.view.tool.MyService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sharedPreferences;
    private int PageNumber=-1;
    private ImageView RuXue;
    private ImageView Junxun;
    private ImageView GongLue;
    private ImageView JiaoLiu;
    private ImageView FengCai;
    private ImageView BaoDao;
    private ImageView WantToSay;
    private ImageView car1;
    private ImageView car2;
    private ImageView car3;
    private ImageView car4;
    private ImageView car5;
    private ImageView car6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isSaveToLocal();
        MyService.setStatusBar(this);
        haveSharePreference();
        initview();
    }

    private void initview()
    {
       RuXue=(ImageView)findViewById(R.id.xiaomeng);
       Junxun=(ImageView)findViewById(R.id.junxuncao);
       GongLue=(ImageView)findViewById(R.id.gonglue);
       JiaoLiu=(ImageView)findViewById(R.id.jiaoliu);
       FengCai=(ImageView)findViewById(R.id.fengcai);
       BaoDao=(ImageView)findViewById(R.id.baodao);
       WantToSay=(ImageView)findViewById(R.id.wantsay);
       car1=(ImageView)findViewById(R.id.car1);
       car2=(ImageView)findViewById(R.id.car2);
       car3=(ImageView)findViewById(R.id.car3);
       car4=(ImageView)findViewById(R.id.car4);
       car5=(ImageView)findViewById(R.id.car5);
       car6=(ImageView)findViewById(R.id.car6);
       RuXue.setOnClickListener(this);
       Junxun.setOnClickListener(this);
       GongLue.setOnClickListener(this);
       JiaoLiu.setOnClickListener(this);
       FengCai.setOnClickListener(this);
       BaoDao.setOnClickListener(this);
       WantToSay.setOnClickListener(this);
       if (PageNumber>1)
       {
           car1.setVisibility(View.INVISIBLE);
       }
       initPage();
    }

    private void initPage()
    {
        if (PageNumber==2){
            car2.setVisibility(View.VISIBLE);
            GongLue.setImageResource(R.drawable.freshman_xiaoyuangong);

        }

        else if (PageNumber==3){
            car3.setVisibility(View.VISIBLE);
            GongLue.setImageResource(R.drawable.freshman_xiaoyuangong);
            JiaoLiu.setImageResource(R.drawable.freshman_xianshang);
        }
        else if(PageNumber==4){
            car4.setVisibility(View.VISIBLE);
            GongLue.setImageResource(R.drawable.freshman_xiaoyuangong);
            JiaoLiu.setImageResource(R.drawable.freshman_xianshang);
            BaoDao.setImageResource(R.drawable.freshman_baodao);
        }
        else if(PageNumber==5){
            car5.setVisibility(View.VISIBLE);
            GongLue.setImageResource(R.drawable.freshman_xiaoyuangong);
            JiaoLiu.setImageResource(R.drawable.freshman_xianshang);
            BaoDao.setImageResource(R.drawable.freshman_baodao);
            WantToSay.setImageResource(R.drawable.freshman_iwantsay);
        }
    }

    private void PageNumberToChange()//多次调用进行图片的变换
    {
       String TAG="难受呀马飞";
        if(PageNumber==2)
        {
            GongLue.setImageResource(R.drawable.freshman_xiaoyuangong);

            Log.d(TAG, "PageNumberToChange: number2");
            ChangeSharePreference();
            return;
        }
        else if (PageNumber==3)
        {
            GongLue.setImageResource(R.drawable.freshman_xiaoyuangong);
            JiaoLiu.setImageResource(R.drawable.freshman_xianshang);
            Log.d(TAG, "PageNumberToChange: number3");

            ChangeSharePreference();
            return;
        }
        else if(PageNumber==4)
        {
            GongLue.setImageResource(R.drawable.freshman_xiaoyuangong);
            JiaoLiu.setImageResource(R.drawable.freshman_xianshang);
            BaoDao.setImageResource(R.drawable.freshman_baodao);
            Log.d(TAG, "PageNumberToChange: number4");

            ChangeSharePreference();
            return;
        }
        else if(PageNumber==5)
        {
            GongLue.setImageResource(R.drawable.freshman_xiaoyuangong);
            JiaoLiu.setImageResource(R.drawable.freshman_xianshang);
            BaoDao.setImageResource(R.drawable.freshman_baodao);
            WantToSay.setImageResource(R.drawable.freshman_iwantsay);
            Log.d(TAG, "PageNumberToChange: number5");

            ChangeSharePreference();
            return;
        }
    }


    private void CarAnimator(final ImageView carfirst, final ImageView carsecond, final Intent intent)
    {

        String TAG="CarAnimator";
        float carfirst_X= carfirst.getX();
        float carfirst_Y= carfirst.getY();
        float carsecond_X=carsecond.getX();
        float carsecond_Y=carsecond.getY();
        float scaleX=carsecond.getWidth()/carfirst.getWidth();
        float scaleY=carsecond.getHeight()/carfirst.getHeight();
        float translation_x=carfirst_X-carsecond_X;
        float translation_y=carfirst_Y-carsecond_Y;
        if(carfirst==car2)
        {
            ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(carfirst,"translationX",0,-(car2.getX()-car6.getX()));
            ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(carfirst,"translationY",0,-(car2.getY()-car6.getY()));
            AnimatorSet animatorSet=new AnimatorSet();
            animatorSet.setDuration(1000).setInterpolator(new AccelerateDecelerateInterpolator());
            animatorSet.play(objectAnimator).with(objectAnimator1);
            animatorSet.start();
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    carfirst.setVisibility(View.INVISIBLE);
                    car6.setVisibility(View.VISIBLE);
                    ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(car6,"translationX",0,-(car6.getX()-car3.getX()));
                    ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(car6,"translationY",0,-(car6.getY()-car3.getY()));
                    AnimatorSet animatorSet=new AnimatorSet();
                    animatorSet.setDuration(1000).setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.play(objectAnimator).with(objectAnimator1);
                    animatorSet.start();
                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            car6.setVisibility(View.INVISIBLE);
                            carsecond.setVisibility(View.VISIBLE);
                            startActivity(intent);

                        }
                    });
                }
            });
        }
        else {
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(carfirst,"translationX",0,-translation_x);
        ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(carfirst,"translationY",0,-translation_y);
        ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(carfirst,"scaleX",1f,scaleX);
        ObjectAnimator objectAnimator3=ObjectAnimator.ofFloat(carfirst,"scaleY",1f,scaleY);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.play(objectAnimator).with(objectAnimator1).with(objectAnimator2).with(objectAnimator3);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                carfirst.setVisibility(View.INVISIBLE);
                carsecond.setVisibility(View.VISIBLE);
                startActivity(intent);
            }
        });}
    }

    private  void isSaveToLocal()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        }
    }

    private void haveSharePreference()
    {
        sharedPreferences=getSharedPreferences("PageNumber", Context.MODE_PRIVATE);
        PageNumber =sharedPreferences.getInt("pagenumber",-1);
        if(PageNumber==-1)
        {
            PageNumber=1;
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("pagenumber",PageNumber);
            editor.commit();
        }
    }

    private void ChangeSharePreference()
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("pagenumber",PageNumber);
        editor.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.xiaomeng :
                Intent intent=new Intent(MainActivity.this,RuXueActivity.class);
                startActivity(intent);
                break;

            case R.id.fengcai :
                Intent intent1=new Intent(MainActivity.this,CQUPTStyleActivity.class);
                startActivity(intent1);
                break;

            case R.id.junxuncao :
                    Intent intent2=new Intent(MainActivity.this,MilitaryTrainingActivity.class);
                    startActivity(intent2);
                break;

            case R.id.gonglue :
                if(PageNumber==1)
                {
                    PageNumber++;
                    PageNumberToChange();
                    Intent intent3=new Intent(MainActivity.this,CampusStrategyActivity.class);
                   CarAnimator(car1,car2,intent3);
                }
                else if(PageNumber>1)
                {
                    Intent intent3=new Intent(MainActivity.this,CampusStrategyActivity.class);
                    startActivity(intent3);
                }
                break;
            case R.id.jiaoliu :
                if(PageNumber==2)
                {
                    PageNumber++;
                    PageNumberToChange();
                    Intent intent4=new Intent(MainActivity.this,OnlineCommunicationActivity.class);
                    CarAnimator(car2,car3,intent4);
                }
                else if(PageNumber>2)
                {
                    Intent intent4=new Intent(MainActivity.this,OnlineCommunicationActivity.class);
                    startActivity(intent4);
                }
                break;
            case R.id.baodao :
                if(PageNumber==3)
                {
                    PageNumber++;
                    PageNumberToChange();
                    Intent intent5=new Intent(MainActivity.this,BaoDaoActivity.class);
                    CarAnimator(car3,car4,intent5);
                }
                else if(PageNumber>3)
                {
                    Intent intent5=new Intent(MainActivity.this,BaoDaoActivity.class);
                    startActivity(intent5);
                }
                break;

            case R.id.wantsay :
                if(PageNumber==4)
                {
                    PageNumber++;
                    PageNumberToChange();
                    Intent intent6=new Intent(MainActivity.this,IWantSayActivity.class);
                    CarAnimator(car4,car5,intent6);
                }
                else if(PageNumber>4)
                {
                    Intent intent6=new Intent(MainActivity.this,IWantSayActivity.class);
                    startActivity(intent6);
                }
                break;
        }
    }
}
