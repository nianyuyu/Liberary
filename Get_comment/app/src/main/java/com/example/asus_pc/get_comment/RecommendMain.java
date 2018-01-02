package com.example.asus_pc.get_comment;

import android.content.Intent;
import android.media.Image;
import android.os.*;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by asus-pc on 2015/11/3.
 */
public class RecommendMain extends ActionBarActivity {
    private RecommendAdapter adapter;
    private ListView recommend_area;
    private List<Recommend> recommend_List;
    private ImageButton  intentBorrow,intentRecommend,intentSearch,intentUser;
    private String user_id_data,book_id_data,user_name_data,book_name_data;
    public static final String GET_RECOMMEND="http://192.168.253.1/school/recommend.php";

    private Handler getRecommendHandler= new Handler(){
        public void handleMessage(android.os.Message msg){
            String jsonData=(String) msg.obj;
            System.out.println(jsonData);
            try {

                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String user_name = object.getString("user_name");
                    String book_name = object.getString("book_name");
                    String recommend_date = object.getString("recommend_date");
                    String pic_url=object.getString("pic_url");
                    recommend_List.add(new Recommend(user_name,recommend_date,book_name,pic_url));
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
        setContentView(R.layout.recommend_main);
//接受主页面传来的user_id
        Intent intent=getIntent();
        user_id_data=intent.getStringExtra("user_id");
        user_name_data=intent.getStringExtra("user_name");
        book_id_data=intent.getStringExtra("user_id");
        book_name_data=intent.getStringExtra("book_name");

        intentBorrow = (ImageButton) findViewById(R.id.record_image);
        intentRecommend=(ImageButton) findViewById(R.id.recommend_image);
        intentSearch=(ImageButton) findViewById(R.id.searchBook_image);
        intentUser=(ImageButton) findViewById(R.id.userPage_image);
        intentUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendMain.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendMain.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendMain.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendMain.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });


        recommend_area = (ListView) findViewById(R.id.recommend_area);
        recommend_List = new ArrayList<Recommend>();
        adapter = new RecommendAdapter(this,recommend_List);

        recommend_area.setAdapter(adapter);



        HttpUtils.getRecommendJson(GET_RECOMMEND, getRecommendHandler);
    }

}
