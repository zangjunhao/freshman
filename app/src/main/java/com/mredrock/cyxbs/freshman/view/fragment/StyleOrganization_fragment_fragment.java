package com.mredrock.cyxbs.freshman.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;

import java.util.List;

/**
 * Created by 67698 on 2018/8/16.
 */



@SuppressLint("ValidFragment")
public class StyleOrganization_fragment_fragment extends Fragment{


    String name;
    String imageurl;
    String content;
    int isHeshang=0;
        public StyleOrganization_fragment_fragment(String title,String content, String imageurl)
        {
            this.content=content;
            this.name=title;
            this.imageurl=imageurl;
            Log.d(getTag(), "StyleOrganization_fragment_fragment: "+imageurl);
        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.item_style_organization_vip,container,false);
        TextView textView= (TextView) view.findViewById(R.id.style_organization_title);
        textView.setText(name);
        final TextView textView1=(TextView)view.findViewById(R.id.style_organization_content);
        textView1.setText(content);
        final ImageView imageView1=(ImageView)view.findViewById(R.id.style_organization_heshang);
        ImageView imageView=(ImageView)view.findViewById(R.id.style_organization_image1);
       Glide.with(getContext()).load("http://47.106.33.112:8080/welcome2018"+imageurl).into(imageView);
       imageView1.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View view) {
               Log.d("onclike_Style", "onClick: "+isHeshang);
               if(isHeshang==0)
               {

                   textView1.setMaxLines(6);
                   isHeshang=1;
                   imageView1.setImageResource(R.drawable.freshman_hes_down);
               }
               else if(isHeshang==1)
               {
                   textView1.setMaxLines(10000);
                   imageView1.setImageResource(R.drawable.freshman_hes_down);
                   isHeshang=0;
               }
           }
       });
        return view;
    }
}
