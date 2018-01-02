package com.example.asus_pc.get_comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus-pc on 2015/11/30.
 */
public class UserAdapter extends ArrayAdapter<User> {
    private int resourceId;
    public UserAdapter(Context context,int textViewResourceId,List<User> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;

    }
    public View getView(int position,View convertView,ViewGroup parent){
        User  user=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        ImageView fruitImage=(ImageView) view.findViewById(R.id.item_image);
        TextView fruitName=(TextView) view.findViewById(R.id.item_name);
        fruitImage.setImageResource(user.getImageId());
        fruitName.setText(user.getName());
        return view;
    }
}