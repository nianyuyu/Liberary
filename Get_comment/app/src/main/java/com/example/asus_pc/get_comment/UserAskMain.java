package com.example.asus_pc.get_comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
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
public class UserAskMain extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private int returnCode;
    EditText edi_suggest;
    ImageButton sure_ask;
    RequestQueue requestQueue;
    String record_url = "http://192.168.253.1/school/user_suggest.php";

    private ImageButton  intentBorrow,intentRecommend,intentSearch,intentUser;
    String user_id_data,  user_name_data,suggest_content;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_ask_main);
        Intent intent = getIntent();
        user_id_data = intent.getStringExtra("user_id");


        user_name_data=intent.getStringExtra("user_name");
        intentSearch=(ImageButton) findViewById(R.id.searchBook_image);
        intentBorrow = (ImageButton) findViewById(R.id.record_image);
        intentRecommend=(ImageButton) findViewById(R.id.recommend_image);
        intentUser=(ImageButton) findViewById(R.id.userPage_image);
        intentUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAskMain.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAskMain.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UserAskMain.this,RecommendMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UserAskMain.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });





        edi_suggest = (EditText) findViewById(R.id.edi_suggest);

        sure_ask = (ImageButton) findViewById(R.id.sure_ask);

        //接受user_id,book_id,book_name;


        requestQueue = Volley.newRequestQueue(getApplicationContext());
        sure_ask.setOnClickListener(new View.OnClickListener() {
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

                            Toast.makeText(UserAskMain.this, "提交建议成功，感谢您的支持!", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(UserAskMain.this, "服务器故障，书评提交失败!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("user_id", user_id_data);

                        parameters.put("user_name", user_name_data);
                        parameters.put("suggest_content", edi_suggest.getText().toString());
                        return parameters;
                    }
                };
                requestQueue.add(request);

            }
        });
    }
}
