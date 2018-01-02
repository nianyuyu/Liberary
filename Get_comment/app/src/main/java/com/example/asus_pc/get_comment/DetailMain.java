package com.example.asus_pc.get_comment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus-pc on 2015/11/20.
 */
public class DetailMain extends ActionBarActivity {
    private DetailAdapter adapter;
    private ListView detail_area;
    private List<Detail> detail_list;
    int returnCode;

    private ImageButton  intentBorrow,intentRecommend,intentSearch,intentUser;
    private String user_name_data,book_publish_data,book_writer_data,book_id_data,user_id_data,book_name_data,book_status_data,pic_url_data;

    //接受user_id


    private Handler getRecommendHandler= new Handler(){
        public void handleMessage(android.os.Message msg){
            String jsonData=(String) msg.obj;
            System.out.println(jsonData);
            try {

                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String user_name=object.getString("user_name");
                    String record_content=object.getString("record_content");
                    String record_date = object.getString("record_date");

                    detail_list.add(new Detail(record_date, record_content,user_name));
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main);

        Intent intent1 = getIntent();
        user_id_data = intent1.getStringExtra("user_id");
        user_name_data=intent1.getStringExtra("user_name");

        intentBorrow = (ImageButton) findViewById(R.id.record_image);
        intentRecommend=(ImageButton) findViewById(R.id.recommend_image);
        intentSearch=(ImageButton) findViewById(R.id.searchBook_image);
        intentUser=(ImageButton) findViewById(R.id.userPage_image);
        intentUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailMain.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailMain.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Button text=(Button) findViewById(R.id.book_collect);
//                text.setText(user_id_data);

                Intent intent = new Intent(DetailMain.this,RecommendMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Button text=(Button) findViewById(R.id.book_collect);
//                text.setText(user_id_data);

                Intent intent = new Intent(DetailMain.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });


        Intent intent=getIntent();

        user_name_data=intent.getStringExtra("user_name");
        user_id_data=intent.getStringExtra("user_id");
        book_id_data=intent.getStringExtra("book_id");
        book_name_data=intent.getStringExtra("book_name");
        book_writer_data=intent.getStringExtra("book_writer");
        book_publish_data=intent.getStringExtra("book_publish");
        book_status_data=intent.getStringExtra("book_status");
        pic_url_data=intent.getStringExtra("pic_url");

        ImageView book_pic=(ImageView) findViewById(R.id.book_pic);
        TextView name_area=(TextView) findViewById(R.id.name_area);
        TextView writer_area=(TextView) findViewById(R.id.writer_area);
        TextView publish_area=(TextView) findViewById(R.id.publish_area);
        name_area.setText("书名:"+book_name_data);
        writer_area.setText("作者:"+book_writer_data);
        publish_area.setText("出版社"+book_publish_data);
        HttpUtils.setPicBitmap(book_pic, pic_url_data);

        if(book_status_data.equals("1")) {

            ImageButton book_borrow1 = (ImageButton) findViewById(R.id.book_borrow);
            book_borrow1.setEnabled(false);
           book_borrow1.setBackgroundResource(R.mipmap.pic_unable);
        }

        ImageButton book_recommend=(ImageButton) findViewById(R.id.book_recommend);
        book_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest request = new StringRequest(Request.Method.GET," http://192.168.253.1/school/recommend_book.php?user_name="+user_name_data+"&&book_id="+book_id_data+"&&user_id="+user_id_data+"&&book_name="+book_name_data+"&&pic_url="+pic_url_data, new Response.Listener<String>() {


                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            returnCode = jsonObject.getInt("success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (returnCode == 1) {
//                               Button text=(Button) findViewById(R.id.book_collect);
//                                text.setText(user_id_data);
                            Toast.makeText(DetailMain.this,"推荐书籍成功",Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(DetailMain.this, "服务器故障，推荐失败!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                requestQueue.add(request);

            }
        });


        ImageButton btn_borrow=(ImageButton) findViewById(R.id.book_borrow);
        btn_borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest request = new StringRequest(Request.Method.GET," http://192.168.253.1/school/borrow_book.php?user_name="+user_name_data+"&&book_id="+book_id_data+"&&user_id="+user_id_data+"&&book_status=1", new Response.Listener<String>() {


                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            returnCode = jsonObject.getInt("success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (returnCode == 1) {
//                               Button text=(Button) findViewById(R.id.book_collect);
//                                text.setText(user_id_data);
                            Toast.makeText(DetailMain.this,"借阅书籍成功",Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(DetailMain.this, "服务器故障，借阅失败!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                requestQueue.add(request);

            }
        });



        ImageButton btn_collect=(ImageButton) findViewById(R.id.book_collect);
        btn_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RequestQueue  requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest request = new StringRequest(Request.Method.GET," http://192.168.253.1/school/collect.php?book_id="+book_id_data+"&&user_id="+user_id_data, new Response.Listener<String>() {


                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            returnCode = jsonObject.getInt("success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (returnCode == 1) {
//                               Button text=(Button) findViewById(R.id.book_collect);
//                                text.setText(user_id_data);
                            Toast.makeText(DetailMain.this,"已经收藏书籍",Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(DetailMain.this, "服务器故障，收藏失败!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                requestQueue.add(request);

            }
        });




        detail_area = (ListView) findViewById(R.id.detail_area);
        detail_list = new ArrayList<Detail>();
        adapter = new DetailAdapter(this,detail_list);
        detail_area.setEmptyView(findViewById(R.id.noSmsData));
        detail_area.setAdapter(adapter);
//        detail_area.setOnItemClickListener(this);




        HttpUtils.getRecommendJson("http://192.168.253.1/school/detail.php?book_id="+book_id_data, getRecommendHandler);
    }



}

