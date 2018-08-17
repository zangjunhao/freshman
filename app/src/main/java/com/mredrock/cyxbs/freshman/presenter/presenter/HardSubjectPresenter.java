package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.content.Context;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.model.cache.DatabaseUtil;
import com.mredrock.cyxbs.freshman.model.convert.BelowSubject;
import com.mredrock.cyxbs.freshman.model.convert.School;
import com.mredrock.cyxbs.freshman.model.http.httpmethods.HttpMethods;
import com.mredrock.cyxbs.freshman.presenter.base.BasePresenter;
import com.mredrock.cyxbs.freshman.view.view.PatternView;

import java.util.List;

import rx.Subscriber;

public class HardSubjectPresenter extends BasePresenter<PatternView> {

    private Context mContext;
    private PatternView patternView;
    private DatabaseUtil databaseUtil;
    private int tab = 0;
    public HardSubjectPresenter(Context mContext, PatternView patternView) {
        this.mContext = mContext;
        this.patternView = patternView;
        databaseUtil = DatabaseUtil.DatabaseUtilHelper.getInstance();
        databaseUtil.initDatabasse(mContext,"Freshman.db",4);
    }

    public void getData(String name){
        Subscriber hardSubscriber = new Subscriber< List< BelowSubject>>() {
            @Override
            public void onCompleted() {
                patternView.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<BelowSubject> list) {
                patternView.getData(list);
            }
        };
        HttpMethods.getInstance().getServiceOfHardSubject(hardSubscriber,name);

        Subscriber subscriber = new Subscriber<School>() {
            @Override
            public void onCompleted() {
                    patternView.getProportionFinish();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onNext(School school) {
                patternView.getProportion(school.getMale_amount(),school.getFemale_amount());
            }
        };
        HttpMethods.getInstance().getServiceOfSchool(subscriber,name);
    }
}
