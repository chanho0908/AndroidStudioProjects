package org.techtown.databasemission;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface OnBookItemClickListener {
    public void onItemClick(RecyclerView.ViewHolder holder, View view, int position);
}
