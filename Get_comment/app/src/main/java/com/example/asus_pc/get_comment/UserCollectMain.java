package com.example.asus_pc.get_comment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserCollectMain extends ActionBarActivity {
    private UserCollectAdapter adapter;
    private ListView collect_area;
    private List<Collect> collect_list;

    private ImageButton  intentBorrow,intentRecommend,intentSearch,intentUser;
    private String user_id_data,book_id_data,user_name_data,book_name_data;
    //接受user_id


    private Handler getRecommendHandler= new Handler(){
        public void handleMessage(android.os.Message msg){
            String jsonData=(String) msg.obj;
            System.out.println(jsonData);

            Intent intent=getIntent();
            String user_id_data=intent.getStringExtra("user_id");

            try {

                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);

                    String book_id=object.getString("book_id");
                    String book_name = object.getString("book_name");
                    String book_writer = object.getString("book_writer");
                    String collect_id=object.getString("collect_id");
                    String book_status=object.getString("book_status");
                    String book_publish=object.getString("book_publish");
                    String borrow_date=object.getString("borrow_date");
                    String user_name=object.getString("user_name");
                    String pic_url=object.getString("pic_url");
                    collect_list.add(new Collect(book_name,book_writer,book_id,collect_id,user_id_data,book_status,borrow_date,book_publish,user_name,pic_url));
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
        setContentView(R.layout.user_collect_main);
        Intent intent=getIntent();

        user_id_data=intent.getStringExtra("user_id");
        user_name_data=intent.getStringExtra("user_name");


        intentBorrow = (ImageButton) findViewById(R.id.record_image);
        intentRecommend=(ImageButton) findViewById(R.id.recommend_image);
        intentSearch=(ImageButton) findViewById(R.id.searchBook_image);
        intentUser=(ImageButton) findViewById(R.id.userPage_image);
        intentUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserCollectMain.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserCollectMain.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserCollectMain.this,RecommendMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserCollectMain.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });




        collect_area = (ListView) findViewById(R.id.collect_area);
        collect_list = new ArrayList<Collect>();
        adapter = new UserCollectAdapter(this,collect_list);
        collect_area.setEmptyView(findViewById(R.id.noSmsData));

        collect_area.setAdapter(adapter);







        HttpUtils.getRecommendJson("http://192.168.253.1/school/user_collect.php?user_id="+user_id_data, getRecommendHandler);
    }




}

