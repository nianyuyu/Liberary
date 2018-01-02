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
 * Created by asus-pc on 2015/11/3.
 */
public class RecommendAdapter extends BaseAdapter {

    private Context context;//创造一个上下文对象
    private  List<Recommend> recommend_List;
    public RecommendAdapter(Context content, List<Recommend> recommend_List){
        this.context=content;
        this.recommend_List = recommend_List;
    }

    //控制该Adapter包含的列表项的个数
    public int getCount() {
        return recommend_List.size();
    }

    //决定第position处的列表项位置
    @Override
    public Object getItem(int position) {
        return recommend_List.get(position);
    }

    @Override
    //决定第position处的列表项ID
    public long getItemId(int position) {
        return position;
    }

    //决定第position处列表项的组件
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            //加载布局
            convertView= LayoutInflater.from(context).inflate(R.layout.recommend_item,null);
        }

        TextView recommend_content=(TextView) convertView.findViewById(R.id.recommend_content);
        TextView recommend_date=(TextView) convertView.findViewById(R.id.recommend_date);
        ImageView recommend_picture=(ImageView) convertView.findViewById(R.id.recommend_picture);

        Recommend recommends=recommend_List.get(position);
        recommend_content.setText(recommends.getUser_name()+"推荐了"+recommends.getBook_name());
        recommend_date.setText(recommends.getRecommend_date());
        String pic_url =recommends.getPic_url();
        HttpUtils.setPicBitmap(recommend_picture, pic_url);

        return convertView;
    }
}
