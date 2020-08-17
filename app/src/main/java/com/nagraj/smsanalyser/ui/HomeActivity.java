package com.nagraj.smsanalyser.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import com.nagraj.base.BaseActivity;
import com.nagraj.local.Message;
import com.nagraj.smsanalyser.App;
import com.nagraj.smsanalyser.R;
import com.nagraj.smsanalyser.databinding.ActivityHomeBinding;
import com.nagraj.smsanalyser.ui.chart.ChartFragment;
import com.nagraj.smsanalyser.ui.list.ListFragment;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HomeView, BaseHomeFragment.HomeActivityCallback {

    final int REQUEST_CODE_SMS_PERMISSIONS = 123;

    @Inject
    HomePresenter presenter;
    private ActivityHomeBinding binding;
    Button btnChart, btnList;
    FragmentContainerView fcvHome;
    int currentItem = 0;
    List<Message> messageList;

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_SMS_PERMISSIONS) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    presenter.getMessages(this);
                }
            } else {
                showToast("you have no read msg permission");
            }
        }
    }

    @Override
    protected void initViews() {
        fcvHome = binding.fcvHome;
        btnChart = binding.btnChart;
        btnList = binding.btnList;
        btnChart.setOnClickListener(v -> {
            if (currentItem != 0) {
                replaceFragment(ChartFragment.newInstance());
                currentItem = 0;
            }
        });
        btnList.setOnClickListener(v -> {
            if (currentItem != 1) {
                replaceFragment(ListFragment.newInstance());
                currentItem = 1;
            }
        });

    }

    @Override
    protected void onReady() {
        presenter.attachView(this);
        if (ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {
            presenter.getMessages(this);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_SMS_PERMISSIONS);

        }
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

    @Override
    public void setMessages(List<Message> messageList) {
        this.messageList = messageList;
        showToast(messageList.size()+"");
        log(messageList.toString());
        currentItem = 0;
        replaceFragment(ChartFragment.newInstance());
    }
}
