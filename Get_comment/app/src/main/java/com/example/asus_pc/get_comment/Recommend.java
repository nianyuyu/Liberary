package com.example.asus_pc.get_comment;

/**
 * Created by asus-pc on 2015/11/3.
 */
public class Recommend {

    private String user_name;
    private String recommend_date;
    private String book_id;
    private String book_name;
    private String pic_url;


    public Recommend(String user_name,String recommend_date,String book_name,String pic_url){

        setUser_name(user_name);
        setRecommend_date(recommend_date);
        setBook_name(book_name);
        setPic_url(pic_url);
    }
    public String getUser_name(){
        return user_name;
    }
    public String getRecommend_date(){
        return recommend_date;
    }
    public String getBook_id(){
        return book_id;
    }
    public String getBook_name(){
        return book_name;
    }
    public String getPic_url(){return pic_url;}
    public void setPic_url(String pic_url){this.pic_url=pic_url;}

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public void setUser_name(String user_name){
        this.user_name=user_name;
    }
    public void setRecommend_date(String recommend_date){
        this.recommend_date=recommend_date;
    }
    public void setBook_id(String book_id){
        this.book_id=book_id;
    }

}
