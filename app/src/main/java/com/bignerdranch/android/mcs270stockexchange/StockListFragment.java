package com.bignerdranch.android.mcs270stockexchange;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class StockListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mStockRecyclerView;
    private StockAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_list, container, false);

        mStockRecyclerView = (RecyclerView) view.findViewById(R.id.stock_recycler_view);
        mStockRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_stock_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }
    */

    /*@Override
   public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                Stock stock = new Stock();
                StockLab.get(getActivity()).addStock(stock);
                Intent intent = StockPagerActivity
                        .newIntent(getActivity(), stock.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    */

    /*private void updateSubtitle() {
        StockLab stockLab = StockLab.get(getActivity());
        int stockCount = stockLab.getStocks().size();
        String subtitle = getString(R.string.subtitle_format, stockCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }
    */

    private void updateUI() {
        StockLab stockLab = StockLab.get(getActivity());
        List<Stock> stocks = StockLab.getStocks();

        if (mAdapter == null) {
            mAdapter = new StockAdapter(stocks);
            mStockRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setStocks(stocks);
            mAdapter.notifyDataSetChanged();
        }

        //updateSubtitle();
    }

    private class StockHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        private Stock mStock;

        public StockHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            //mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            //mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            //mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindStock(Stock stock) {
            mStock = stock;
            mTitleTextView.setText(mStock.getTicker());


            //To-do shit. Overweiight, underweight stuff


            //mSolvedCheckBox.setChecked(mStock.isSolved());
        }

        @Override
        public void onClick(View v) {
            //Intent intent = StockPagerActivity.newIntent(getActivity(), mStock.getId());
            //startActivity(intent);
        }
    }


    private class StockAdapter extends RecyclerView.Adapter<StockHolder> {

        private List<Stock> mStocks;

        public StockAdapter(List<Stock> stocks) {
            mStocks = stocks;
        }

        @Override
        public StockHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_stock, parent, false);
            return new StockHolder(view);
        }

        @Override
        public void onBindViewHolder(StockHolder holder, int position) {
            Stock stock = mStocks.get(position);
            holder.bindStock(stock);
        }

        @Override
        public int getItemCount() {
            return mStocks.size();
        }

        public void setStocks(List<Stock> stocks) {
            mStocks = stocks;
        }
    }
}

