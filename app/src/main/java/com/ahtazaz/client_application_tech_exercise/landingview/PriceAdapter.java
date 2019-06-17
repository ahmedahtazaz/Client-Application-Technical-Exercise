package com.ahtazaz.client_application_tech_exercise.landingview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class PriceAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private static final String TAG = PriceAdapter.class.getSimpleName();

    private int count = 0;
    private Object mTag = null;

    private ItemViewEvent viewCallBacks = null;

    public PriceAdapter() {
        setHasStableIds(false);
    }


    public void setViewEventListener(ItemViewEvent context) {
        this.viewCallBacks = context;
    }

    public void setCount(int count)
    {
        this.count = count;

        if (count == 0 && viewCallBacks != null) {
            viewCallBacks.onGetViewAdapterEvent(RecyclerAdapterEventType.NO_CONTENT, mTag, null);
        }
    }

    public int getCount()
    {
        return count;
    }

    @Override
    public int getItemViewType(int position)
    {
        Object viewTypeObj = viewCallBacks.onGetViewAdapterEvent(RecyclerAdapterEventType.GET_VIEW_HOLDER_TYPE, mTag, position);
        return viewTypeObj != null ? (Integer) viewTypeObj : 0;
    }

    @Override
    public long getItemId(int position)
    {
//        return position;
//        return NO_ID;
        return (long) viewCallBacks.onGetViewAdapterEvent(RecyclerAdapterEventType.GET_ID, mTag, position);
    }

    public void setAdapterTag(Object tag) {
        mTag = tag;
    }

    public Object getAdapterTag() {
        return mTag;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        VH vh = (VH) viewCallBacks.onGetViewAdapterEvent(RecyclerAdapterEventType.CREATE_VIEW_HOLDER, mTag, viewGroup, viewType);
        vh.itemView.setTag(mTag);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH viewHolder, final int position)
    {

        viewCallBacks.onGetViewAdapterEvent(RecyclerAdapterEventType.BIND_VIEW_HOLDER, mTag, position, viewHolder);

        if(position == count - /*Config.NEXT_CALL_ADAPTER*/1) {
            viewCallBacks.onGetViewAdapterEvent(RecyclerAdapterEventType.REQUEST_ITEMS, mTag, position);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull VH viewHolder, int position, @NonNull List<Object> payloads) {
        this.onBindViewHolder(viewHolder, position);
    }

    @Override
    public void onViewRecycled(@NonNull VH holder)
    {

    }

    @Override
    public boolean onFailedToRecycleView(@NonNull VH holder)
    {


        return false;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull VH holder)
    {

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VH holder)
    {

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public void clean()
    {
        count = 0;
    }

    public interface RecyclerItemClickListener {
        void onItemClick(int position, RecyclerView.ViewHolder viewHolder);
        void onItemLockClick(int position, RecyclerView.ViewHolder viewHolder);
    }

    public interface RecyclerItemLongClickListener {
        boolean onItemLongClick(int position, RecyclerView.ViewHolder viewHolder);
    }
}
