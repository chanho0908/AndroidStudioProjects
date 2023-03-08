package org.techtown.databasemission22;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface OnBookItemClickListener {
    public void onItemClick(BookAdapter.ViewHolder holder, View view, int position);
}
