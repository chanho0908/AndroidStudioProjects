package org.techtown.rssreport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Item> items = new ArrayList<Item>();
    MyAdapter adapter;
    int eventType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        adapter = new MyAdapter(items, this);
        recyclerView.setAdapter(adapter);

        readRss();

    }

    private void readRss() {
        try {
            URL url = new URL("http://rss.hankyung.com/new/news_main.xml");
            //RssFeedTase task = new RssFeedTase();
            RssFeedTask task = new RssFeedTask();
            task.execute(url);

        }catch (Exception e){e.printStackTrace();}
    }

    class RssFeedTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL url= urls[0];

            try {
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(is, "utf-8");
                eventType = xpp.getEventType();

                Item item = null;
                String tagName;

                while (eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            tagName = xpp.getName();

                            if(tagName.equals("item")){
                                item= new Item();
                            }else if(tagName.equals("title")){
                                xpp.next();
                                if(item!=null) item.setTitle(xpp.getText());
                            }else if(tagName.equals("link")){
                                xpp.next();
                                if(item!=null) item.setLink(xpp.getText());
                            }else if(tagName.equals("description")){
                                xpp.next();
                                if(item!=null) item.setDesc(xpp.getText());
                            }else if(tagName.equals("image")){
                                xpp.next();
                                if(item!=null) item.setImgUrl(xpp.getText());
                            }else if(tagName.equals("pubDate")){
                                xpp.next();
                                if(item!=null) item.setDate(xpp.getText());
                            }
                            break;
                        case XmlPullParser.TEXT:
                            break;
                        case XmlPullParser.END_TAG:
                            tagName=xpp.getName();
                            if(tagName.equals("item")){
                                items.add(item);
                                item = null;

                                publishProgress();
                            }
                            break;
                    }

                } eventType = xpp.next();

            }catch (IOException e){e.printStackTrace();}
            catch (XmlPullParserException e){e.printStackTrace();}

            return "작업완료";

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            adapter.notifyItemInserted(items.size());
        }
    }


}