package org.techtown.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ProxyInfo;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class PersonProvider extends ContentProvider {
    // ContentProvider의 고유한 Content Uri
    private static final String AUTHORITY = "org.techtown.provider"; // 특정 내용 제공자 구분
    private static final String BASE_PATH = "person"; // 요청 데이터의 자료형 (여기선 테이블 이름)
    public static final Uri CONTENT_URI = Uri.parse("content://org.techtown.provider/person");

    private static final int PERSONS = 1;
    private static final int PERSON_ID = 2;

    public static final UriMatcher urimatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        urimatcher.addURI(AUTHORITY, BASE_PATH, PERSONS);
        urimatcher.addURI(AUTHORITY, BASE_PATH + "/#", PERSON_ID);
    }

    private SQLiteDatabase database;
    DatabaseHelper helper;

    @Override
    public boolean onCreate() {
        helper = new DatabaseHelper(getContext());
        database = helper.getWritableDatabase();

        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (urimatcher.match(uri)){
            case PERSONS:
                return "vnd.android.cursor.dir/person";
            default:
                throw new IllegalArgumentException("알 수 없는 URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long id = database.insert(helper.TABLE_NAME, null, contentValues);

        if(id > 0){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
            throw new SQLException("추가 실패 -> URI" + uri);

    }

    // 조회
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor;

        switch (urimatcher.match(uri)){
            case PERSONS:
                cursor = database.query(helper.TABLE_NAME, helper.ALL_COLUMNS, null, null, null, null, null);
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 URI " + uri);
        }

        return cursor;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int cnt = 0;
        switch (urimatcher.match(uri)){
            case PERSONS:
                cnt = database.update(helper.TABLE_NAME, contentValues, s, strings);break;
            default:
                throw new IllegalArgumentException("알 수 없는 URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int cnt = 0;
        switch (urimatcher.match(uri)){
            case PERSONS:
                cnt = database.delete(helper.TABLE_NAME, s, strings);break;
            default:
                throw new IllegalArgumentException("알 수 없는 URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return cnt;
    }


}
