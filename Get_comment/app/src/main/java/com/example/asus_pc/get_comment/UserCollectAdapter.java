package com.example.asus_pc.get_comment;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
public class UserCollectAdapter extends BaseAdapter {

    private Context context;
    private List<Collect> collect_list;
    private int returnCode;

    public UserCollectAdapter(Context context,List<Collect> collect_list){
        this.context=context;
        this.collect_list=collect_list;
    }
    public int getCount() {
        return collect_list.size();
    }

    @Override
    public Object getItem(int position) {
        return collect_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            //加载布局
            convertView= LayoutInflater.from(context).inflate(R.layout.user_collect_item,null);

        }

        TextView book_name=(TextView) convertView.findViewById(R.id.book_name);
        final TextView book_writer=(TextView) convertView.findViewById(R.id.book_writer);
        ImageView book_picture=(ImageView) convertView.findViewById(R.id.book_picture);

        Collect collects=collect_list.get(position);
        book_name.setText(collects.getBook_name());
        book_writer.setText("作者："+collects.getBook_writer());
        String pic_url = collects.getPic_url();
        HttpUtils.setPicBitmap(book_picture, pic_url);
        final String book_writer_data=collects.getBook_writer();
        final String book_id_data=collects.getBook_id();
        final String collect_id_data=collects.getCollect_id();
        final String user_id_data=collects.getUser_id();
        final String book_publish_data=collects.getBook_publish();
        final String book_status_data=collects.getBook_status();
        final String borrow_date_data=collects.getBorrow_date();
        final String book_name_data=collects.getBook_name();
        final String user_name_data=collects.getUser_name();
        final String pic_url_data=collects.getPic_url();


        ImageButton borrow_collect=(ImageButton) convertView.findViewById(R.id.borrow_collect);
        borrow_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

//
                Intent intent = new Intent(context, DetailMain.class);
                intent.putExtra("book_id", book_id_data);
                intent.putExtra("book_writer",book_writer_data);
                intent.putExtra("book_publish",book_publish_data);
                intent.putExtra("book_status",book_status_data);
                intent.putExtra("book_name",book_name_data);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                intent.putExtra("pic_url",pic_url_data);
                context.startActivity(intent);

            }
        });




        ImageButton delete_collect=(ImageButton) convertView.findViewById(R.id.delete_collect);

        delete_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                StringRequest request = new StringRequest(Request.Method.GET," http://192.168.253.1/school/user_collect_delete.php?collect_id="+collect_id_data, new Response.Listener<String>() {



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
                            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, UserCollectMain.class);
                          intent.putExtra("user_name",user_name_data);
                            intent.putExtra("user_id",user_id_data);
                            context.startActivity(intent);
                        } else {

                            Toast.makeText(context,"服务器原因，删除失败", Toast.LENGTH_SHORT).show();
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

