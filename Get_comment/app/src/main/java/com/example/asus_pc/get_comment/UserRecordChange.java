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
 * Created by asus-pc on 2015/11/30.
 */
public class UserRecordChange extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private int returnCode;
    EditText add_content;
    ImageButton btn_record;
    RequestQueue requestQueue;
    String record_url = "http://192.168.253.1/school/user_record_change.php";

    private ImageButton  intentBorrow,intentRecommend,intentSearch,intentUser;
    String user_id_data, record_content_data,record_id_data,user_name_data,book_id_data,book_name_data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_record_change);
        add_content = (EditText) findViewById(R.id.record_content);

        btn_record = (ImageButton) findViewById(R.id.btn_record);


        intentBorrow = (ImageButton) findViewById(R.id.record_image);
        intentRecommend=(ImageButton) findViewById(R.id.recommend_image);
        intentSearch=(ImageButton) findViewById(R.id.searchBook_image);
        intentUser=(ImageButton) findViewById(R.id.userPage_image);
        intentUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRecordChange.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRecordChange.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRecordChange.this,RecommendMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRecordChange.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });



        //接受user_id,book_id,book_name;
        Intent intent = getIntent();
        user_id_data = intent.getStringExtra("user_id");
        record_content_data=intent.getStringExtra("record_content");
        record_id_data=intent.getStringExtra("record_id");
        book_name_data=intent.getStringExtra("book_name");
        book_id_data=intent.getStringExtra("book_id");
        user_name_data=intent.getStringExtra("user_name");

        add_content.setText(record_content_data);
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
                            Toast.makeText(UserRecordChange.this, "书评修改成功!", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(UserRecordChange.this, "服务器故障，书评提交失败!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();



                        parameters.put("user_id", record_id_data);

                        parameters.put("record_id", record_id_data);
                        parameters.put("record_content", add_content.getText().toString());
                        return parameters;
                    }
                };
                requestQueue.add(request);

            }
        });
    }
}