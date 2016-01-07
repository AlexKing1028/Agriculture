package com.example.redrock.agriculture.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.redrock.agriculture.R;
import com.example.redrock.agriculture.widget.ListItem.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by REDROCK on 12/30/2015.
 */
public class LoadMoreListView extends ListView{
    LayoutInflater inflater;
    OnLoadMoreListener listner;
    int lastItemIndex;
    boolean canLoadMore;
    ViewGroup progress;
    TextView load_info;


    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    public LoadMoreListView(Context context, AttributeSet set){
        super(context, set);
        initLoadMore(context);
    }

    public LoadMoreListView(Context context){
        super(context);
        initLoadMore(context);
    }

    public LoadMoreListView(Context context, AttributeSet set, int defStyple){
        super(context, set, defStyple);
        initLoadMore(context);
    }

    public void setListener(OnLoadMoreListener listner){
        this.listner=listner;
    }

    public void enableLoadMore(boolean enabled){
        canLoadMore = enabled;
    }

    private void initLoadMore(Context context){
        inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_refresh_footer, this, false);
        progress = (ViewGroup)view.findViewById(R.id.load_more_progress);
        load_info=(TextView)view.findViewById(R.id.load_more);
        addFooterView(view);
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE && lastItemIndex == getCount()-2){
                    progress.setVisibility(canLoadMore? VISIBLE:INVISIBLE);
                    if (listner != null && canLoadMore) listner.onLoadMore();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastItemIndex=firstVisibleItem+visibleItemCount-2;
            }
        });
        view.invalidate();
    }

    public static abstract class LoadMoreAdapter extends BaseAdapter implements OnLoadMoreListener{
        protected List<Item> data;
        protected LayoutInflater inflater;
        protected LoadMoreListView listView;
        public abstract int getPageSize();

        public LoadMoreAdapter(LayoutInflater inflater, LoadMoreListView view){
            this.inflater=inflater;
            this.listView=view;
            data=new ArrayList<>();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return (position>=0 && position<data.size())? data.get(position):null;
        }

        @Override
        public final void onLoadMore() {
            int startCursor=getCount()-1;
            int size=getPageSize();
            /**
             * todo request net for data;
             */
            loadMoreData(startCursor, size);
        }

        protected final void canLoadMore(boolean enabled){
            listView.enableLoadMore(enabled);
        }
        public abstract void loadMoreData(int startCursor, int size);

        public void addAll(Collection<Item> c){
            if (data != null){
                data.addAll(c);
            }
        }
    }
}
