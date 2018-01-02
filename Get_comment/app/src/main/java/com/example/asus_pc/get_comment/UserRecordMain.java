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

public class UserRecordMain  extends ActionBarActivity {
    private UserRecordAdapter adapter;
    private ListView record_area;
    private List<Record> record_list;

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
                    String record_date = object.getString("record_date");
                    String record_content=object.getString("record_content");
                    String user_id=object.getString("user_id");
                    String record_id=object.getString("record_id");
                    String user_name=object.getString("user_name");

                    record_list.add(new Record(book_name,record_content,record_date,user_id,book_id,user_name,record_id));
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
        setContentView(R.layout.user_record_main);
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
                Intent intent = new Intent(UserRecordMain.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRecordMain.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRecordMain.this,RecommendMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRecordMain.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });



        record_area = (ListView) findViewById(R.id.record_area);
        record_list = new ArrayList<Record>();
        adapter = new UserRecordAdapter(this,record_list);
        record_area.setEmptyView(findViewById(R.id.noSmsData));
        record_area.setAdapter(adapter);







        HttpUtils.getRecommendJson("http://192.168.253.1/school/user_record.php?user_id="+user_id_data, getRecommendHandler);
    }




}
