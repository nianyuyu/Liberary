package com.example.asus_pc.get_comment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
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
 * Created by asus-pc on 2015/11/29.
 */
public class UserRecordAdapter extends BaseAdapter {
    private Context context;
    private List<Record> record_list;
    private int returnCode;

    public UserRecordAdapter(Context context,List<Record> record_list){
        this.context=context;
        this.record_list=record_list;
    }
    public int getCount() {
        return record_list.size();
    }

    @Override
    public Object getItem(int position) {
        return record_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            //加载布局
            convertView= LayoutInflater.from(context).inflate(R.layout.user_record_item,null);

        }

        TextView book_name=(TextView) convertView.findViewById(R.id.book_name);
        TextView record_content=(TextView) convertView.findViewById(R.id.record_content);
        TextView record_date=(TextView) convertView.findViewById(R.id.record_date);


        Record records=record_list.get(position);
        if(records.getRecord_content().length()>=7){
            String text=records.getRecord_content().substring(0,5);
            record_content.setText("评论："+text+"...");
        }else{
            String text=records.getRecord_content();
            record_content.setText("评论："+text);
        }

        book_name.setText("书名："+records.getBook_name());

        record_date.setText("日期："+records.getRecord_date());

        final String book_id_data=records.getBook_id();

        final String user_id_data=records.getUser_id();
        final String record_id_data=records.getRecord_id();
        final String record_content_date=records.getRecord_content();

        final String book_name_data=records.getBook_name();
        final String user_name_data=records.getUser_name();


        ImageButton change_record=(ImageButton) convertView.findViewById(R.id.change_record);
        change_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserRecordChange.class);
                intent.putExtra("record_id",record_id_data);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                intent.putExtra("book_id",book_id_data);
                intent.putExtra("record_content",record_content_date);
                intent.putExtra("book_name",book_name_data);
                context.startActivity(intent);
            }
        });




        ImageButton delete_record=(ImageButton) convertView.findViewById(R.id.delete_record);

        delete_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                StringRequest request = new StringRequest(Request.Method.GET," http://192.168.253.1/school/user_record_delete.php?record_id="+record_id_data, new Response.Listener<String>() {



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
                            Intent intent = new Intent(context, UserRecordMain.class);

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