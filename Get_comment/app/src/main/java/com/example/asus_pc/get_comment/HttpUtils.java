package com.example.asus_pc.get_comment;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import  android.os.*;
import android.widget.ImageView;


/**
 * Created by asus-pc on 2015/11/3.
 */
public class HttpUtils {
    public static void getRecommendJson(final String url,final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connect;
                InputStream is;
                try {
                    connect= (HttpURLConnection) new URL(url).openConnection();
                    connect.setRequestMethod("GET");
                    is=connect.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line="";
                    StringBuilder result =new StringBuilder();
                    while((line=reader.readLine())!=null){
                        result.append(line);

                    }
                    Message msg=new Message();
                    msg.obj=result.toString();
                    handler.sendMessage(msg);
                    //通知主线程message已经收到了，现在交给handle处理
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static void setPicBitmap(final ImageView ivPic, final String pic_url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(pic_url).openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    ivPic.setImageBitmap(bitmap);
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
