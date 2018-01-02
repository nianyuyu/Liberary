package com.example.asus_pc.get_comment;

/**
 * Created by asus-pc on 2015/11/12.
 */

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by asus-pc on 2015/11/11.
 */
public class RecordResult extends ActionBarActivity {

    String user_id_data, book_id_data, book_name_data,user_name_data;
    private ImageButton  intentBorrow,intentRecommend,intentSearch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_result);


        Intent intent1 = getIntent();
        user_id_data = intent1.getStringExtra("user_id");
        user_name_data=intent1.getStringExtra("user_name");

        intentBorrow = (ImageButton) findViewById(R.id.record_image);
        intentRecommend=(ImageButton) findViewById(R.id.recommend_image);
        intentSearch=(ImageButton) findViewById(R.id.searchBook_image);
        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordResult.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RecordResult.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RecordResult.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });



    }
}
