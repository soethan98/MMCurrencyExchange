package com.example.soe_than.currencyexchange.ui.list.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.soe_than.currencyexchange.R;
import com.example.soe_than.currencyexchange.Utils;
import com.example.soe_than.currencyexchange.data.network.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soe_than on 5/15/18.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.MyViewHolder> {

    private List<Currency> mCurrencies = new ArrayList<>();
    private Context context;
    private ItemClick mItemClick;

    public CurrencyAdapter(Context context, ItemClick mItemClick) {
        this.context = context;
        this.mItemClick = mItemClick;
    }

    public interface ItemClick {
        void itemClickListener(Currency currency);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_content, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.countPrice.setText(mCurrencies.get(position).getRate());
        holder.countName.setText(mCurrencies.get(position).getName());
        Utils.setFlag(mCurrencies.get(position).getName(), holder.imgFlag);

    }

    @Override
    public int getItemCount() {
        if (null == mCurrencies) return 0;
        return mCurrencies.size();
    }

    public void swapList(List<Currency> currencyList) {
        this.mCurrencies = currencyList;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgFlag;
        private TextView countName;
        private TextView countPrice;
        RelativeLayout rlLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgFlag = itemView.findViewById(R.id.img_flag);
            countName = itemView.findViewById(R.id.country_name);
            countPrice = itemView.findViewById(R.id.count_price);
            rlLayout = itemView.findViewById(R.id.rl_layout);
            rlLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


            mItemClick.itemClickListener(mCurrencies.get(getAdapterPosition()));

        }
    }
}
