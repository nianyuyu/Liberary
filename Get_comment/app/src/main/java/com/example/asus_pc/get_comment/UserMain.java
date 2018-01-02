package com.example.asus_pc.get_comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus-pc on 2015/11/25.
 */
public class UserMain extends ActionBarActivity {
    String user_id_data,user_name_data;

    private ImageButton intentBorrow,intentRecommend,intentSearch,intentUser;

    private List<User> userList=new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);

        initFruit();
        UserAdapter adapter=new UserAdapter(UserMain.this,R.layout.user_item,userList);
        ListView listView= (ListView) findViewById(R.id.user_list);
        listView.setAdapter(adapter);
        Intent intent1 = getIntent();
        user_id_data = intent1.getStringExtra("user_id");
        user_name_data=intent1.getStringExtra("user_name");

        TextView t=(TextView) findViewById(R.id.user_name);
        t.setText(user_name_data);


        intentBorrow = (ImageButton) findViewById(R.id.record_image);
        intentRecommend=(ImageButton) findViewById(R.id.recommend_image);
        intentSearch=(ImageButton) findViewById(R.id.searchBook_image);
        intentUser=(ImageButton) findViewById(R.id.userPage_image);
        intentUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserMain.this,UserMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserMain.this,SearchBook.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });
        intentRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserMain.this,RecommendMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);

            }
        });
        intentBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserMain.this,BorrowMain.class);
                intent.putExtra("user_id",user_id_data);
                intent.putExtra("user_name",user_name_data);
                startActivity(intent);
            }
        });




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        Intent intent0 =new Intent(UserMain.this,UserBorrowMain.class);
                        intent0.putExtra("user_id",user_id_data);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 =new Intent(UserMain.this,UserRecordMain.class);
                        intent1.putExtra("user_id",user_id_data);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 =new Intent(UserMain.this,UserCollectMain.class);
                        intent2.putExtra("user_id",user_id_data);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 =new Intent(UserMain.this,UserInfoMain.class);
                        intent3.putExtra("user_id",user_id_data);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 =new Intent(UserMain.this,UserAskMain.class);
                        intent4.putExtra("user_id",user_id_data);
                        intent4.putExtra("user_name",user_name_data);
                        startActivity(intent4);
                        break;

                    default:break;
                }
            }
        });
    }

    private void initFruit() {
        User my_borrow=new User("我的借阅",R.mipmap.pic_borrow);
        userList.add(my_borrow);
        User my_record=new User("我的评论",R.mipmap.pic_record);
        userList.add(my_record);
        User my_saved=new User("收藏书籍",R.mipmap.pic_collect);
        userList.add(my_saved);
        User my_info=new User("个人信息",R.mipmap.pic_user);
        userList.add(my_info);
        User my_ask=new User("我有话说",R.mipmap.pic_say);
        userList.add(my_ask);

    }




}