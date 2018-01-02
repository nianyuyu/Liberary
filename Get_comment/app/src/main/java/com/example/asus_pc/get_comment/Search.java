package com.example.asus_pc.get_comment;



/**
 * Created by asus-pc on 2015/11/13.
 */
public class Search {
    private String book_name;
    private String book_writer;
    private String book_id;
    private String book_status;
    private String borrow_date;
    private String book_publish;
    private String pic_url;



    public Search(String book_id,String book_name,String book_writer,String book_status,String book_publish,String borrow_date,String pic_url){
        setBook_name(book_name);
        setBook_id(book_id);
        setBook_writer(book_writer);
        setBook_status(book_status);
        setBook_publish(book_publish);
        setBorrow_date(borrow_date);
        setPic_url(pic_url);
    }
    public String getPic_url(){return pic_url;}

    public String getBook_name(){
        return book_name;
    }
    public String getBook_id(){
        return book_id;
    }
    public String getBook_writer(){
        return book_writer;
    }
    public String getBorrow_date(){return borrow_date;}
    public String getBook_status(){return book_status;}
    public String getBook_publish(){return book_publish;}
public  void setPic_url(String pic_url){this.pic_url=pic_url;}
    public void setBook_name(String book_name){
        this.book_name=book_name;
    }
    public void setBook_writer(String book_writer){
        this.book_writer=book_writer;
    }
    public void setBook_id(String book_id){
        this.book_id=book_id;
    }
    public void setBook_status(String book_status){this.book_status=book_status;}
    public void setBorrow_date(String borrow_date){this.borrow_date=borrow_date;}
    public void setBook_publish(String book_publish){this.book_publish=book_publish;}

}
