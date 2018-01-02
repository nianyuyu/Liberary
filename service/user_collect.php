<?php
require_once("mysqli_connect.php");
if(isset($_GET['user_id'])){
	$arr='';
	$n=0;
	$user_id=$_GET['user_id'];
	$q="select book.pic_url,book.borrow_date,book.user_name, book.book_name,book.book_status,book.book_publish,book_writer,book.book_id,collect.collect_id,collect.user_id from collect,book where book.book_id=collect.book_id and  collect.user_id=$user_id" ;
	$r=mysqli_query($dbc,$q);
	
	while($row=mysqli_fetch_array($r)){
		
		$arr[$n++]=array("pic_url"=>$row['pic_url'],"user_name"=>$row['user_name'],"borrow_date"=>$row['borrow_date'],"book_publish"=>$row['book_publish'],"book_status"=>$row['book_status'],"collect_id"=>$row['collect_id'],"book_name"=>$row['book_name'],"book_writer"=>$row['book_writer'],"book_id"=>$row['book_id']);
	}
	echo json_encode($arr);
}
?>