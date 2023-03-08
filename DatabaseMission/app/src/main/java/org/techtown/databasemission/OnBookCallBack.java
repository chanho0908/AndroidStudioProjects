package org.techtown.databasemission;

import java.util.ArrayList;

public interface OnBookCallBack {
    public void insert(String name, String author, String contents);
    public ArrayList<BookInfo> selectAll();
}
