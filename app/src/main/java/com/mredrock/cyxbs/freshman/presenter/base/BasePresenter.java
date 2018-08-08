package com.mredrock.cyxbs.freshman.presenter.base;

import android.content.Context;

import com.mredrock.cyxbs.freshman.view.view.BaseView;

public class BasePresenter<T extends BaseView> {
    protected T view;
    protected Context mContext;

    public void attachView(T view,Context context){
        this.view = view;
        mContext = context;
    }

    public void detachView(){
        this.view = null;
    }

    public T getView(){
        return view;
    }

    boolean isViewAttach(){
        return view!=null;
    }
}
