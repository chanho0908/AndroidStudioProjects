package org.techtown.challenge22;

import java.util.ArrayList;

public interface OnDatabaseCallBack {
    public void insert(String name, String author, String contents);
    public ArrayList<BookInfo> selectAll();
}
