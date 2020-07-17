package com.vanderkast.moex_search.ui.stocks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.vanderkast.moex_search.R;
import com.vanderkast.moex_search.entity.Stock;

import javax.inject.Inject;
import java.util.List;

public class StockListConstraintAdapter extends RecyclerView.Adapter<StockListConstraintAdapter.StockView> {
    private List<Stock> stockArray;

    @Inject
    public StockListConstraintAdapter() {
    }

    public void setUp(List<Stock> stockArray) {
        this.stockArray = stockArray;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StockView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StockView(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StockView holder, int position) {
        holder.setUp(stockArray.get(position));
    }

    @Override
    public int getItemCount() {
        return stockArray == null ? 0 : stockArray.size();
    }

    static class StockView extends RecyclerView.ViewHolder {
        TextView name;
        TextView securityId;
        TextView price;

        public StockView(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.stock_name_text);
            securityId = itemView.findViewById(R.id.stock_security_id_text);
            price = itemView.findViewById(R.id.stock_preview_value_text);
        }

        public void setUp(Stock stock) {
            name.setText(stock.getName());
            securityId.setText(stock.getSecurityId());
            price.setText(stock.getPreviewValue());
        }
    }
}
