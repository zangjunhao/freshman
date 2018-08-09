package com.mredrock.cyxbs.freshman.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.GetName;
import com.mredrock.cyxbs.freshman.model.http.httpmethods.GetNameMethods;

import java.util.HashMap;
import java.util.List;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Subscriber subscriber = new Subscriber<GetName>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(GetName getName) {
                List<String> o = getName.getName();
                    Log.d("MainActivity","success"+o.toString());
                    Toast.makeText(MainActivity.this,o.toString(),Toast.LENGTH_LONG).show();
                    for (String s:o){
                        Log.d("MainActivity",s);
                    }
            }
        };
        HashMap hashMap = new HashMap<String,String>();
       // hashMap.put("index","学生食堂");
    }
}
