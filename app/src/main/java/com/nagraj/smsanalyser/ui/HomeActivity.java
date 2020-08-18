package com.nagraj.smsanalyser.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HomeView, BaseHomeFragment.HomeActivityCallback {

    final int REQUEST_CODE_SMS_PERMISSIONS = 123;

    @Inject
    HomePresenter presenter;
    private ActivityHomeBinding binding;
    ImageButton btnChart, btnList;
    FragmentContainerView fcvHome;
    int currentItem = 0;
    int filterOption = 0;
    List<Message> messageList = new ArrayList<>();
    ListFragment listFragment;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_right, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        listFragment=(ListFragment)getSupportFragmentManager().findFragmentById(R.id.fcv_home);
        switch (item.getItemId()) {
            case R.id.nav_all:
                filterOption=0;
                listFragment.setFilterOption(filterOption);
                item.setChecked(true);
                return true;
            case R.id.nav_only_credit:
                filterOption=1;
                listFragment.setFilterOption(filterOption);
                item.setChecked(true);
                return true;
            case R.id.nav_only_debit:
                filterOption=2;
                listFragment.setFilterOption(filterOption);
                item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        btnChart.getDrawable().setTint(Color.parseColor("#FFFFFF"));
        btnList.getDrawable().setTint(Color.parseColor("#000000"));
        btnChart.setOnClickListener(v -> {
            btnChart.getDrawable().setTint(Color.parseColor("#FFFFFF"));
            btnList.getDrawable().setTint(Color.parseColor("#000000"));
            if (currentItem != 0) {
                currentItem = 0;
                replaceFragment(ChartFragment.newInstance());
            }
        });
        btnList.setOnClickListener(v -> {
            btnList.getDrawable().setTint(Color.parseColor("#FFFFFF"));
            btnChart.getDrawable().setTint(Color.parseColor("#000000"));
            if (currentItem != 1) {
                currentItem = 1;
                replaceFragment(ListFragment.newInstance());
            }
        });

    }

    @Override
    protected void onReady() {
        presenter.attachView(this);
        if (ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {
            presenter.getMessages(this);
        } else {
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
    public void setPageData(String title,boolean isChartPage){
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        }
        if(isChartPage){
            menu.setGroupVisible(R.id.mg_option,false);
        }else {
            menu.setGroupVisible(R.id.mg_option,true);
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
        currentItem = 0;
        replaceFragment(ChartFragment.newInstance());
    }

    @Override
    public List<Message> getMessageList() {
        return messageList;
    }

    @Override
    public int getFilterOption() {
        return filterOption;
    }
}
