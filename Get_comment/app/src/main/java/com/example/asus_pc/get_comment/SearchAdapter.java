package com.example.asus_pc.get_comment;

/**
 * Created by asus-pc on 2015/11/20.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus-pc on 2015/11/13.
 */
public class SearchAdapter  extends BaseAdapter {
    private Context context;
    private List<Search> search_list;
    public SearchAdapter(Context context,List<Search> search_list){
        this.context=context;
        this.search_list=search_list;
    }


    @Override
    public int getCount() {
        return search_list.size();
    }

    @Override
    public Object getItem(int position) {
        return search_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //加载布局
            convertView = LayoutInflater.from(context).inflate(R.layout.search_item, null);

        }

        TextView book_name = (TextView) convertView.findViewById(R.id.book_name);
        TextView book_writer = (TextView) convertView.findViewById(R.id.book_writer);
        ImageView book_picture=(ImageView) convertView.findViewById(R.id.book_picture);

        Search searchBook = search_list.get(position);
        book_name.setText(searchBook.getBook_name());
        book_writer.setText("作者：" + searchBook.getBook_writer());
        String book_id = searchBook.getBook_id();
        String pic_url = searchBook.getPic_url();
        HttpUtils.setPicBitmap(book_picture, pic_url);
        return convertView;
    }

}