package com.mredrock.cyxbs.freshman.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.presenter.presenter.Junxun_hintPresenter;
import com.mredrock.cyxbs.freshman.view.view.JunxunHintView;

/**
 * Created by 67698 on 2018/8/10.
 */

public class MilitrayHintFragment  extends Fragment implements JunxunHintView {
    String Content=null;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hint,container,false);
        Junxun_hintPresenter junxun_hintPresenter=new Junxun_hintPresenter(this,getContext());
        junxun_hintPresenter.getData();
        textView= (TextView) view.findViewById(R.id.junxunHint);

        return view;
    }

    @Override
    public void getJunxunHint(String hint) {
        Content=hint;
        textView.setText(Content);
    }
}
