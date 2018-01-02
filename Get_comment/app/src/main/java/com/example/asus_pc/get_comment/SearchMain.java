package com.example.asus_pc.get_comment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus-pc on 2015/11/20.
 */
public class SearchMain extends ActionBarActivity implements AdapterView.OnItemClickListener{
    private SearchAdapter adapter;
    private ListView search_area;
    private List<Search> search_list;
    private ImageButton  intentBorrow,intentRecommend,intentSearch,intentUser;
    String user_id_data,user_name_data;




    private Handler getBorrowHandler= new Handler(){
        public void handleMessage(android.os.Message msg){
            String jsonData=(String) msg.obj;
            System.out.println(jsonData);
            try {

                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String book_name=object.getString("book_name");
                    String book_id=object.getString("book_id");
                    String book_writer = object.getString("book_writer");
                    String book_status = object.getString("book_status");
                    String book_publish = object.getString("book_publish");
                    String borrow_date = object.getString("borrow_date");
                    String pic_url=object.getString("pic_url");
                    search_list.add(new Search(book_id,book_name,book_writer,book_status,book_publish,borrow_date,pic_url));
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    };


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);
        Intent intent=getIntent();

        String book_name_data=intent.getStringExtra("book_name");

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
                Intent intent = new Intent(SearchMain.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchMain.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SearchMain.this,RecommendMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SearchMain.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });


        search_area = (ListView) findViewById(R.id.search_area);
        search_list = new ArrayList<Search>();
        adapter = new SearchAdapter(this,search_list);
        search_area.setAdapter(adapter);
        search_area.setEmptyView(findViewById(R.id.noSmsData));
        search_area.setOnItemClickListener(this);




        HttpUtils.getRecommendJson("http://192.168.253.1/school/search.php?book_name="+book_name_data, getBorrowHandler);
    }


    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

        Search search_book =  search_list.get(position);
        Intent intent = new Intent(SearchMain.this, DetailMain.class);

        Intent intent1 = getIntent();
        user_id_data = intent1.getStringExtra("user_id");
        user_name_data=intent1.getStringExtra("user_name");
        intent.putExtra("user_id",user_id_data);
        intent.putExtra("user_name",user_name_data);

        intent.putExtra("pic_url",search_book.getPic_url());

        intent.putExtra("book_id", search_book.getBook_id());
        intent.putExtra("book_writer",search_book.getBook_writer());
        intent.putExtra("book_publish",search_book.getBook_publish());
        intent.putExtra("book_status",search_book.getBook_status());
        intent.putExtra("book_name",search_book.getBook_name());


        startActivity(intent);
    }

}
