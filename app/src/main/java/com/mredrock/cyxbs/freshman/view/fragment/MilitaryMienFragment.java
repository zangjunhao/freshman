package com.mredrock.cyxbs.freshman.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.JunXun;
import com.mredrock.cyxbs.freshman.presenter.presenter.JunxunPresenter;
import com.mredrock.cyxbs.freshman.view.adapter.MilitaryVideoAdapter;
import com.mredrock.cyxbs.freshman.view.view.JunxunView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 67698 on 2018/8/10.
 */

public class MilitaryMienFragment extends Fragment implements JunxunView {
    private RecyclerView PhotoList;
    private RecyclerView VideoList;
    private LayoutInflater mLayoutInflater;
    JunXun junXun= new JunXun();
    private JunxunPresenter junxunPresenter;
    ViewPager viewPager;
    private int[] Type=new int[10000];
    private String[] Photo={"https://ws1.sinaimg.cn/large/610dc034ly1fp9qm6nv50j20u00miacg.jpg",
            "https://ws1.sinaimg.cn/large/610dc034ly1foowtrkpvkj20sg0izdkx.jpg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/20171227115959_lmlLZ3_Screenshot.jpeg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/20171206084331_wylXWG_misafighting_6_12_2017_8_43_16_390.jpeg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/20171113084220_LuJgqv_sakura.gun_13_11_2017_8_42_12_311.jpeg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/2017-11-17-22794158_128707347832045_9158114204975104000_n.jpg",
            "https://ws1.sinaimg.cn/large/610dc034ly1fgi3vd6irmj20u011i439.jpg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/20171102092251_AY0l4b_alrisaa_2_11_2017_9_22_44_335.jpeg"
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mien_viewpager,container,false);
        junxunPresenter=new JunxunPresenter(this,getContext());
        junxunPresenter.getData();
        mLayoutInflater = LayoutInflater.from(getContext());
        VideoList=(RecyclerView)view.findViewById(R.id.junxunVideolist);
        final LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        VideoList.setLayoutManager(linearLayoutManager1);
        MilitaryVideoAdapter militaryVideoAdapter=new MilitaryVideoAdapter();
        VideoList.setAdapter(militaryVideoAdapter);

        viewPager= (ViewPager) view.findViewById(R.id.junxunPhotolist);
        viewPager.setAdapter(new MyAdapter());
        initViewPagerScroll();
        viewPager.setOffscreenPageLimit(junXun.getPicture().size());
        viewPager.setPageMargin(20);
        viewPager.setPageTransformer(true,new RotationPageTransformer());
        viewPager.setCurrentItem(junXun.getPicture().size()*10,false);
        AutoSlide(viewPager);
        return view;
    }

    @Override
    public void getJunxunlist(JunXun junXun ) {
        this.junXun=junXun;

    }




    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % junXun.getPicture().size();
            View view = mLayoutInflater.inflate(R.layout.item_viewpager, container, false);
            ImageView img = (ImageView) view.findViewById(R.id.viewpager_image);
            TextView textView=(TextView) view.findViewById(R.id.junxunPhoto_name) ;
            textView.setText(junXun.getPicture().get(position).getName());
            Glide.with(getContext()).load(junXun.getPicture().get(position).getUrl()).into(img);
            final int finalPosition = position;
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    junxunPresenter.showPhoto(junXun.getPicture().get(finalPosition).getUrl());
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    private void initViewPagerScroll() {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            BannerScroller scroller = new BannerScroller(getContext());
            mField.set(viewPager, scroller);
        } catch (Exception e) {
            Log.d("military", e.getMessage());
        }
    }

    public class RotationPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE=0.85f;

        @Override
        public void transformPage(View page, float position) {
            float scaleFactor = Math.max(MIN_SCALE,1 - Math.abs(position));
            float rotate = 10 * Math.abs(position);
            //position小于等于1的时候，代表page已经位于中心item的最左边，
            //此时设置为最小的缩放率以及最大的旋转度数
            if (position <= -1){
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
                page.setRotationY(rotate);
            }//position从0变化到-1，page逐渐向左滑动
            else if (position < 0){
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setRotationY(rotate);
            }//position从0变化到1，page逐渐向右滑动
            else if (position >=0 && position < 1){
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setRotationY(-rotate);
            }//position大于等于1的时候，代表page已经位于中心item的最右边
            else if (position >= 1){
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setRotationY(-rotate);
            }
        }

    }


    public class BannerScroller extends Scroller {

        private static final int BANNER_DURATION = 2000;
        private int mDuration = BANNER_DURATION;

        public BannerScroller(Context context) {
            super(context);
        }


        public BannerScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }


    private void AutoSlide(final ViewPager vip)
    {
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    int  currentItem=vip.getCurrentItem();
                    currentItem++;
                    final int pp=currentItem;
                    vip.setCurrentItem(pp,true);
                }
            }
        };

        new Thread(){
            @Override
            public void run() {
                super.run();

                while(true){
                    SystemClock.sleep(4000);
                    handler.sendEmptyMessage(1);
                }
            }
        }.start();
    }

//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//        @Override
//        public void run() {
//            int currentItem = viewPager.getCurrentItem();
//            currentItem++;
//            viewPager.setCurrentItem(currentItem);
//            Log.d("AutoSlide", "run:启动了 ");
//        }
//    }, 4000, 4000, TimeUnit.MILLISECONDS);
}

