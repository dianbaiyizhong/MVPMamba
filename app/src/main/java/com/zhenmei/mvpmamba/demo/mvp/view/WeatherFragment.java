package com.zhenmei.mvpmamba.demo.mvp.view;

import com.zhenmei.mvpmamba.demo.fragment.MyBaseFragment;
import com.zhenmei.mvpmamba.demo.mvp.contract.WeatherContract;
import com.zhenmei.mvpmamba.demo.mvp.presenter.WeatherPresenter;

public class WeatherFragment extends MyBaseFragment<WeatherPresenter> implements WeatherContract.MView {


    @Override
    public void loadSuccess() {

    }

    @Override
    public void loadError() {

    }

}
