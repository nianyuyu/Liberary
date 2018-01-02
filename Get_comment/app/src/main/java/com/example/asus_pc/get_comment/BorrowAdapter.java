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
 * Created by asus-pc on 2015/11/6.
 */
public class BorrowAdapter extends BaseAdapter {

    private Context context;
    private List<Borrow> borrow_list;
    public BorrowAdapter(Context context,List<Borrow> borrow_list){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.borrow_item,null);
        }

        TextView book_name=(TextView) convertView.findViewById(R.id.book_name);
        TextView borrow_date=(TextView) convertView.findViewById(R.id.borrow_date);
        ImageView imgView=(ImageView) convertView.findViewById(R.id.book_picture);


        Borrow borrows=borrow_list.get(position);
        book_name.setText(borrows.getBook_name());
        borrow_date.setText("借阅时间："+borrows.getBorrow_date());

        String pic_url = borrows.getPic_url();
        HttpUtils.setPicBitmap(imgView, pic_url);

        return convertView;
    }
}