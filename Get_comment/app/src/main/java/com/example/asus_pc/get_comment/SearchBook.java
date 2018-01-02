package com.example.asus_pc.get_comment;

/**
 * Created by asus-pc on 2015/11/20.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class SearchBook extends ActionBarActivity {
    private ImageButton  intentBorrow,intentRecommend,intentSearch,intentUser;
    String user_id_data,book_name_data,user_name_data;

    private EditText search_edit;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_book);
        ImageButton btn_search=(ImageButton) findViewById(R.id.btn_search);
        search_edit=(EditText) findViewById(R.id.edit_search);

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
                Intent intent = new Intent(SearchBook.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });

        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchBook.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SearchBook.this,RecommendMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SearchBook.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });



        Intent intent=getIntent();
        user_id_data=intent.getStringExtra("user_id");

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book_name_data=search_edit.getText().toString();
                Intent intent = new Intent(SearchBook.this,SearchMain.class);
                intent.putExtra("book_name",book_name_data);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });


//        btn_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                test=(Button) findViewById(R.id.test);
//                test.setText(b);
//                Log.d("SearchBook.class", b);
////                Intent intent = new Intent(SearchBook.this,SearchMain.class);
////                intent.putExtra("book_name",book_name_data);
////                intent.putExtra("user_id",user_id_data);
//
//
////                startActivity(intent);
//            }
//        });


    }
}