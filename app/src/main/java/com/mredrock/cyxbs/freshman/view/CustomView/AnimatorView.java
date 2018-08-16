package com.mredrock.cyxbs.freshman.view.CustomView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AnimatorView extends View {
    public AnimatorView(Context context) {
        super(context);
    }

    public AnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public int getRealHeight(){
       return this.getLayoutParams().height;
    }

    public void setRealHeight(int height){
        this.getLayoutParams().height = height;
        this.requestLayout();
    }
}
