package com.example.asus_pc.get_comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus-pc on 2015/11/20.
 */
public class DetailAdapter extends BaseAdapter {
    private Context context;
    private List<Detail> detail_list;

    public DetailAdapter(Context context,List<Detail> detail_list){
        this.context=context;
        this.detail_list=detail_list;
    }
    public int getCount() {
        return detail_list.size();
    }


    public Object getItem(int position) {
        return detail_list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            //加载布局
            convertView= LayoutInflater.from(context).inflate(R.layout.detail_item,null);

        }

        TextView record_content=(TextView) convertView.findViewById(R.id.record_content);
        TextView record_date=(TextView) convertView.findViewById(R.id.record_date);
        ImageView book_picture=(ImageView) convertView.findViewById(R.id.book_picture);

    Detail details = detail_list.get(position);
    record_content.setText(details.getUser_name() + "评论：" + details.getRecord_content());
    record_date.setText(details.getRecord_date());






        return convertView;
    }

}
