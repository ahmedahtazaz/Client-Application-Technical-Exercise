package com.ahtazaz.client_application_tech_exercise.landingview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ahtazaz.client_application_tech_exercise.R;
import com.ahtazaz.client_application_tech_exercise.Utlities;
import com.ahtazaz.client_application_tech_exercise.databinding.LandingScreenBinding;

import java.util.ArrayList;

import static com.ahtazaz.client_application_tech_exercise.Utlities.fullScreenActivity;

public class LandingViewActivity extends AppCompatActivity implements ItemViewEvent {

    private LandingViewModel landingViewModel = null;
    private LandingScreenBinding binding = null;
    private RecyclerView view = null;
    private PriceAdapter<RecyclerView.ViewHolder> adapter = null;
    private int count = 0;
    private LandingViewHandler handler = null;
    private LandingViewInterface handlerListener = null;
    private ArrayList<DataBean> itemsList = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fullScreenActivity(this);

        landingViewModel = new LandingViewModel();
        binding = DataBindingUtil.setContentView(this, R.layout.landing_screen);

        setViewModel();
        setHandler();
        setView();
        createAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(landingViewModel != null)
            landingViewModel.setEmailId(Utlities.readString(Utlities.EMAIL_KEY, "na", Utlities.EMAIL_PREFERENCE));

        requestData();
    }

    private void setHandler()
    {
        if(binding != null)
        {
            handler = new LandingViewHandler(this);

            handler.setEventListener(handlerListener = new LandingViewInterface() {

                @Override
                public void onResult(DataBean data)
                {
                    if(itemsList == null)
                        itemsList = new ArrayList<>();

                    if(landingViewModel != null)
                        landingViewModel.firstLoad.set(false);

                    if(data != null)
                        itemsList.add(data);

                    count = itemsList.size();

                    updateAdapter();
                }

                @Override
                public void clean() {

                    if(itemsList != null)
                        itemsList.clear();

                    count = 0;

                    updateAdapter();
                }
            });

            binding.setHandler(handler);
        }
    }

    private void setViewModel()
    {
        if(binding != null && landingViewModel != null)
            binding.setViewModel(landingViewModel);
    }

    private void setView()
    {
        if(binding != null && view == null)
            view = binding.recyclerView;
    }

    private void createAdapter()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        view.setLayoutManager(linearLayoutManager);
        view.addItemDecoration(new DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL));

        adapter = new PriceAdapter<>();
        adapter.setCount(count);
        adapter.setViewEventListener(this);

        view.setAdapter(adapter);
    }

    @Override
    public Object onGetViewAdapterEvent(RecyclerAdapterEventType eventType, Object... args)
    {
        switch (eventType)
        {
            case GET_VIEW_HOLDER_TYPE:

                return R.layout.price_item;

            case CREATE_VIEW_HOLDER:

                return setView((ViewGroup) args[1]);

            case BIND_VIEW_HOLDER:

                setData((Integer) args[1], (RecyclerView.ViewHolder) args[2]);

                break;

            case REQUEST_ITEMS:

                requestData();

                break;
        }

        return null;
    }

    private void updateAdapter()
    {
        if(adapter != null)
        {
            adapter.setCount(count);
            adapter.notifyDataSetChanged();
        }
    }

    private void requestData()
    {
        if(handler != null)
            handler.getPrice(null);
    }

    public RecyclerView.ViewHolder setView(ViewGroup parent)
    {
        PriceViewHolder viewHolder = new PriceViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.price_item, parent, false));

        return viewHolder;
    }

    public void setData(int position, RecyclerView.ViewHolder viewHolder)
    {
        PriceViewHolder tier2ViewHolder = (PriceViewHolder) viewHolder;

        DataBean currItem = itemsList != null ? itemsList.get(position) : null;

        if(position == 0)
        {
            tier2ViewHolder.time.setText("Time");
            tier2ViewHolder.buy.setText("Buying");
            tier2ViewHolder.sell.setText("Selling");
        }
        else
        {
            tier2ViewHolder.time.setText(currItem.timestamp);
            tier2ViewHolder.buy.setText(currItem.buy);
            tier2ViewHolder.sell.setText(currItem.sell);
        }
    }
}
