package com.example.asus_pc.get_comment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by asus-pc on 2015/10/30.
 */
public class BorrowMain extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private BorrowAdapter adapter;
    private ListView borrow_area;
    private List<Borrow> borrow_list;
    private ImageButton  intentBorrow,intentRecommend,intentSearch,intentUser;
    String user_id_data, book_id_data, book_name_data,user_name_data;



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
                    String user_id= object.getString("borrow_date");
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
        setContentView(R.layout.borrow_main);



        Intent intent1 = getIntent();
        user_id_data = intent1.getStringExtra("user_id");

        user_name_data=intent1.getStringExtra("user_name");



        borrow_area = (ListView) findViewById(R.id.borrow_area);
        borrow_list = new ArrayList<Borrow>();
        adapter = new BorrowAdapter(this,borrow_list);
        borrow_area.setAdapter(adapter);
        borrow_area.setEmptyView(findViewById(R.id.noSmsData));
        borrow_area.setOnItemClickListener(this);
        intentUser=(ImageButton) findViewById(R.id.userPage_image);
        intentUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BorrowMain.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentBorrow = (ImageButton) findViewById(R.id.record_image);
        intentRecommend=(ImageButton) findViewById(R.id.recommend_image);
        intentSearch=(ImageButton) findViewById(R.id.searchBook_image);

        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BorrowMain.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BorrowMain.this,RecommendMain.class);
                intent.putExtra("user_id",user_id_data);

                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BorrowMain.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });




        HttpUtils.getRecommendJson("http://192.168.253.1/school/borrow.php?user_id="+user_id_data, getRecommendHandler);
    }


    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

        Borrow borrow_book =  borrow_list.get(position);
        Intent intent = new Intent(BorrowMain.this, RecordMain.class);

        Intent intent2=getIntent();
        String user_name_data=intent2.getStringExtra("user_name");
        String user_id_data=intent2.getStringExtra("user_id");

        intent.putExtra("book_id", borrow_book.getBook_id());
        intent.putExtra("book_name", borrow_book.getBook_name());
        intent.putExtra("user_id",  user_id_data);
        intent.putExtra("user_name", user_name_data);

        startActivity(intent);
    }

}