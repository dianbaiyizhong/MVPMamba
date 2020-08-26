package com.zhenmei.p7i.mvpmamba.mvp.view;

import com.zhenmei.p7i.mvpmamba.fragment.MyBaseFragment;
import com.zhenmei.p7i.mvpmamba.mvp.contract.WeatherContract;
import com.zhenmei.p7i.mvpmamba.mvp.presenter.WeatherPresenter;

public class WeatherFragment extends MyBaseFragment<WeatherPresenter> implements WeatherContract.MView {


    @Override
    public void loadSuccess() {

    }

}
