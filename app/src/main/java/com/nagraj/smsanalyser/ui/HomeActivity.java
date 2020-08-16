package com.nagraj.smsanalyser.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import com.nagraj.base.BaseActivity;
import com.nagraj.smsanalyser.App;
import com.nagraj.smsanalyser.R;
import com.nagraj.smsanalyser.databinding.ActivityHomeBinding;
import com.nagraj.smsanalyser.ui.chart.ChartFragment;
import com.nagraj.smsanalyser.ui.list.ListFragment;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HomeView, BaseHomeFragment.HomeActivityCallback {

    @Inject
    HomePresenter presenter;
    private ActivityHomeBinding binding;
    Button btnChart, btnList;
    FragmentContainerView fcvHome;
    int currentItem=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    protected void initIntentExtras(Bundle extras) {

    }

    @Override
    protected void initDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    protected void initViews() {
        fcvHome = binding.fcvHome;
        btnChart = binding.btnChart;
        btnList = binding.btnList;
        btnChart.setOnClickListener(v -> {
            if(currentItem!=0) {
                replaceFragment(ChartFragment.newInstance());
                currentItem=0;
            }
        });
        btnList.setOnClickListener(v -> {
            if(currentItem!=1) {
                replaceFragment(ListFragment.newInstance());
                currentItem=1;
            }
        });
    }

    @Override
    protected void onReady() {
        presenter.attachView(this);
        currentItem=0;
        replaceFragment(ChartFragment.newInstance());
    }

    @Override
    protected void destroyPresenter() {
        binding = null;
        presenter.detachView();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_fragment, R.anim.slide_out_fragment,
                        R.anim.slide_in_fragment, R.anim.slide_out_fragment)
                .replace(R.id.fcv_home, fragment)
                .commit();
    }
}
