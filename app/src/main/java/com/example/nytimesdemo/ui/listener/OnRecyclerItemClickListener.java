package com.example.nytimesdemo.ui.listener;

/**
 * Created by NEX0HUH on 2/10/2018.
 * interface to get callback from recycler view
 */

public interface OnRecyclerItemClickListener<T> {
    void onItemClick(T model, int itemPOsition);
}
