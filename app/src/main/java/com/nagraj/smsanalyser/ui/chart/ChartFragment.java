package com.nagraj.smsanalyser.ui.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.nagraj.local.Message;
import com.nagraj.smsanalyser.App;
import com.nagraj.smsanalyser.R;
import com.nagraj.smsanalyser.databinding.FragmentChartBinding;
import com.nagraj.smsanalyser.ui.BaseHomeFragment;
import java.util.ArrayList;
import javax.inject.Inject;

public class ChartFragment extends BaseHomeFragment implements ChartView {
    @Inject
    ChartPresenter presenter;

    private FragmentChartBinding binding;
    PieChart pieChart;

    ArrayList<PieEntry> tnxTypeList = new ArrayList<>();
    float totalCreditAmount=0;
    float totalDebitAmount=0;


    public ChartFragment() {
    }

    public static ChartFragment newInstance() {
        return new ChartFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChartBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initArguments(Bundle extras) {

    }

    @Override
    protected void initDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    protected void initViews() {
        activityCallback.setPageData(getString(R.string.report_in_pie_chart),true);
        for(Message message:activityCallback.getMessageList()){
            if(message.isCredit()){
               totalCreditAmount+=message.amount();
            }else{
                totalDebitAmount+=message.amount();
            }
        }
        pieChart = binding.idPieChart;
        pieChart.setDescription("Total Transactions:"+activityCallback.getMessageList().size());
        pieChart.setHoleRadius(25f);
        pieChart.setDescriptionTextSize(14);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Net Income Amount:\n"+(totalCreditAmount-totalDebitAmount));
        pieChart.setCenterTextSize(12);
        tnxTypeList.add(new PieEntry(totalCreditAmount,"Income"));
        tnxTypeList.add(new PieEntry(totalDebitAmount,"Expense"));

        PieDataSet pieDataSet = new PieDataSet(tnxTypeList, "Transactions Type");
        pieDataSet.setSliceSpace(4);
        pieDataSet.setValueTextSize(14);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }

    @Override
    protected void onReady() {
        presenter.attachView(this);
    }

    @Override
    protected void destroyPresenter() {
        binding = null;
        presenter.detachView();
    }
}
