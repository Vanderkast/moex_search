package com.vanderkast.moex_search.ui.stocks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vanderkast.moex_search.R;
import com.vanderkast.moex_search.entity.Stock;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@AndroidEntryPoint
public class StockListFragment extends Fragment {
    @Inject
    StockListConstraintAdapter adapter;

    @Inject
    StocksViewModelFactory viewModelFactory;

    private StockListViewModel viewModel;

    @Inject
    public StockListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(StockListViewModel.class);
        viewModel.getStockLiveData().observe(getViewLifecycleOwner(), this::onStocksLoaded);

        View root = inflater.inflate(R.layout.fragment_stock_list, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.stock_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return root;
    }

    private void onStocksLoaded(Set<Stock> stockSet) {
        adapter.setUp(new ArrayList<>(stockSet));
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
