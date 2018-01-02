package com.example.asus_pc.get_comment;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by asus-pc on 2015/11/9.
 */
public class RecordMain extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private int returnCode;
    EditText add_content;
    ImageButton btn_record;
    RequestQueue requestQueue;
    private ImageButton  intentBorrow,intentRecommend,intentSearch,intentUser;
    String record_url = "http://192.168.253.1/school/record.php";
    String user_id_data, book_id_data, book_name_data,user_name_data;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_main);

        //接受user_id,book_id,book_name;
        Intent intent1 = getIntent();
        user_id_data = intent1.getStringExtra("user_id");
        book_id_data = intent1.getStringExtra("book_id");
        book_name_data = intent1.getStringExtra("book_name");
        user_name_data=intent1.getStringExtra("user_name");

        add_content = (EditText) findViewById(R.id.record_content);
        btn_record = (ImageButton) findViewById(R.id.btn_record);

        intentSearch=(ImageButton) findViewById(R.id.searchBook_image);
        intentBorrow = (ImageButton) findViewById(R.id.record_image);
        intentRecommend=(ImageButton) findViewById(R.id.recommend_image);
        intentUser=(ImageButton) findViewById(R.id.userPage_image);
        intentUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordMain.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordMain.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RecordMain.this,RecommendMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(RecordMain.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });







        requestQueue = Volley.newRequestQueue(getApplicationContext());
        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, record_url, new Response.Listener<String>() {


                    @Override
                    public void onResponse(String s) {


                        Log.e(TAG, s);

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            returnCode = jsonObject.getInt("success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (returnCode == 1) {

                            Toast.makeText(RecordMain.this, "书评提交成功!", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(RecordMain.this, "服务器故障，书评提交失败!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("user_id", user_id_data);
                        parameters.put("book_name", book_name_data);
                        parameters.put("book_id", book_id_data);
                        parameters.put("user_name", user_name_data);
                        parameters.put("record_content", add_content.getText().toString());
                        return parameters;
                    }
                };
                requestQueue.add(request);

            }
        });
    }
}