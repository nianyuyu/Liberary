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

public class UserBorrowMain extends ActionBarActivity {
    private UserBorrowAdapter adapter;
    private ListView borrow_area;
    private List<Borrow> borrow_list;

    private ImageButton  intentBorrow,intentRecommend,intentSearch,intentUser;
    private String user_id_data,book_id_data,user_name_data,book_name_data;

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
                    String book_id=object.getString("book_id");
                    String book_name = object.getString("book_name");
                    String borrow_date = object.getString("borrow_date");
                    String user_id= object.getString("user_id");
                    String pic_url=object.getString("pic_url");
                    borrow_list.add(new Borrow(book_name,borrow_date,book_id,user_name,user_id,pic_url));
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
        setContentView(R.layout.user_borrow_main);
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
                Intent intent = new Intent(UserBorrowMain.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserBorrowMain.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserBorrowMain.this,RecommendMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserBorrowMain.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });





        borrow_area = (ListView) findViewById(R.id.borrow_area);
        borrow_list = new ArrayList<Borrow>();
        adapter = new UserBorrowAdapter(this,borrow_list);
        borrow_area.setEmptyView(findViewById(R.id.noSmsData));

        borrow_area.setAdapter(adapter);





        HttpUtils.getRecommendJson("http://192.168.253.1/school/borrow.php?user_id="+user_id_data, getRecommendHandler);
    }



}


