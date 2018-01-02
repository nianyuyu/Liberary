package com.example.asus_pc.get_comment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by asus-pc on 2015/11/30.
 */
public class UserBorrowAdapter extends BaseAdapter {

    private Context context;
    private List<Borrow> borrow_list;
    private int returnCode;

    public UserBorrowAdapter(Context context,List<Borrow> borrow_list){
        this.context=context;
        this.borrow_list=borrow_list;
    }
    public int getCount() {
        return borrow_list.size();
    }

    @Override
    public Object getItem(int position) {
        return borrow_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            //加载布局
            convertView= LayoutInflater.from(context).inflate(R.layout.user_borrow_item,null);

        }



        TextView book_name=(TextView) convertView.findViewById(R.id.book_name);
        TextView borrow_date=(TextView) convertView.findViewById(R.id.borrow_date);
        ImageView book_picture=(ImageView) convertView.findViewById(R.id.book_picture);

        Borrow borrows=borrow_list.get(position);
        book_name.setText(borrows.getBook_name());
        borrow_date.setText("借阅时间："+borrows.getBorrow_date());
        String pic_url = borrows.getPic_url();
        HttpUtils.setPicBitmap(book_picture, pic_url);

        final String book_id_data=borrows.getBook_id();
        final String user_id_data=borrows.getUser_id();
        final String user_name_data=borrows.getUser_name();


        ImageButton return_book=(ImageButton) convertView.findViewById(R.id.return_book);
        ImageButton add_time=(ImageButton) convertView.findViewById(R.id.add_time);

        add_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                StringRequest request = new StringRequest(Request.Method.GET," http://192.168.253.1/school/user_borrow_add.php?book_id="+book_id_data, new Response.Listener<String>() {



                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            returnCode = jsonObject.getInt("success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (returnCode == 1) {
//                               Button text=(Button) findViewById(R.id.book_collect);
//                                text.setText(user_id_data);
                            Toast.makeText(context, "延期成功", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(context,"服务器原因，延期失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                requestQueue.add(request);

            }
        });


        return_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                StringRequest request = new StringRequest(Request.Method.GET," http://192.168.253.1/school/user_borrow_return.php?book_id="+book_id_data, new Response.Listener<String>() {



                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            returnCode = jsonObject.getInt("success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (returnCode == 1) {
//                               Button text=(Button) findViewById(R.id.book_collect);
//                                text.setText(user_id_data);
                            Toast.makeText(context, "归还成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, UserBorrowMain.class);
                            intent.putExtra("user_id",user_id_data);
                            intent.putExtra("user_name",user_name_data);
                            intent.putExtra("book_id",book_id_data);

                            context.startActivity(intent);
                        } else {

                            Toast.makeText(context,"服务器原因，归还失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                requestQueue.add(request);

            }




        });









        return convertView;
    }

}

