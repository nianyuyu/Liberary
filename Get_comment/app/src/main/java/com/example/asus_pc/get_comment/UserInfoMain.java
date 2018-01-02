package com.example.asus_pc.get_comment;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
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
 * Created by asus-pc on 2015/11/27.
 */
public class UserInfoMain extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText pass_password,new_password,again_new_password;
    private ImageButton sure_change;


    private int returnCode;

    private ImageButton  intentBorrow,intentRecommend,intentSearch,intentUser;
    private String passPassword,newPassword,againNewPassword,user_id_data,user_name_data;

    Response.Listener<String> listener = new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {


            try {
                JSONObject jsonObject = new JSONObject(s);

                returnCode = jsonObject.getInt("success");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (returnCode==1) {

                Toast.makeText(UserInfoMain.this, "修改成功!", Toast.LENGTH_SHORT).show();
            } else  if(returnCode==2){

                Toast.makeText(UserInfoMain.this, "旧密码有误!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(UserInfoMain.this, "服务器错误!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.e(TAG, volleyError.getMessage(), volleyError);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_main);

        Intent intent=getIntent();
        user_id_data=intent.getStringExtra("user_id");
        user_name_data=intent.getStringExtra("user_name");
        pass_password = (EditText) findViewById(R.id.pass_password);
        new_password = (EditText)findViewById(R.id.new_password);
        again_new_password=(EditText) findViewById(R.id.again_new_password);
        sure_change = (ImageButton)findViewById(R.id.sure_change);

        intentBorrow = (ImageButton) findViewById(R.id.record_image);
        intentRecommend=(ImageButton) findViewById(R.id.recommend_image);
        intentSearch=(ImageButton) findViewById(R.id.searchBook_image);
        intentUser=(ImageButton) findViewById(R.id.userPage_image);
        intentUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInfoMain.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInfoMain.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInfoMain.this,RecommendMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInfoMain.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });

        sure_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newPassword= new_password.getText().toString();
                passPassword = pass_password.getText().toString();
                againNewPassword=again_new_password.getText().toString();

                if (newPassword.equals("") || passPassword.equals("")||againNewPassword.equals("")) {
                    Toast.makeText(UserInfoMain.this,"新，旧密码不能为空，请重新输入!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!newPassword.equals(againNewPassword)){
                    Toast.makeText(UserInfoMain.this,"2次输入的密码不一致，请重新输入!",Toast.LENGTH_SHORT).show();
                    return;
                }

                RequestQueue requestQueue = Volley.newRequestQueue(UserInfoMain.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://192.168.253.1/school/user_info.php",listener,errorListener) {
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String, String>();
                        map.put("user_id",user_id_data);
                        map.put("pass_password",passPassword);
                        map.put("new_password",newPassword);
                        return map;
                    }
                };

                requestQueue.add(stringRequest);
            }
        });

    }


}