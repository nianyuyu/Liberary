package com.example.asus_pc.get_comment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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


public class MainActivity extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText userId,userPassword;
    private ImageButton btn_login;
    private SharedPreferences sharedPreferences;
    private int returnCode;
    private String returnCode2;
    private String user_id,user_password;
    Response.Listener<String> listener = new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {
            Log.e(TAG, s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject jsonObject2 = new JSONObject(s);
                returnCode = jsonObject.getInt("success");
                returnCode2 = jsonObject2.getString("user_name");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (returnCode == 1) {


                Intent intent= new Intent(MainActivity.this,RecommendMain.class);
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_name",returnCode2);
                startActivity(intent);

            } else {
                Toast.makeText(MainActivity.this, "用户名或密码错误!", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_main);

        userId = (EditText) findViewById(R.id.user_id);
        userPassword = (EditText)findViewById(R.id.user_password);
        btn_login = (ImageButton)findViewById(R.id.btn_login);

        //显示已保存的用户名和密码
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        String getID = sharedPreferences.getString("user_id", null);
        if (getID== null) {
            Toast.makeText(MainActivity.this,"没有保存用户信息，请重新输入!",Toast.LENGTH_SHORT).show();
        } else {
            userId.setText(sharedPreferences.getString("user_id", null));
            userPassword.setText(sharedPreferences.getString("user_passWord", null));
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_id= userId.getText().toString();
                user_password = userPassword.getText().toString();

                if (user_id.equals("") || user_password.equals("")) {
                    Toast.makeText(MainActivity.this,"用户名或密码不能为空，请重新输入!",Toast.LENGTH_SHORT).show();
                    return;
                }

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://192.168.253.1/school/login.php",listener,errorListener) {
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String, String>();

                        map.put("user_id",user_id);
                        map.put("user_password",user_password);
                        return map;
                    }
                };

                requestQueue.add(stringRequest);
            }
        });

    }


}
