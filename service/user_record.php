<?php
require_once("mysqli_connect.php");
if(isset($_GET['user_id'])){
	$arr=null;
	$n=0;
	$user_id=$_GET['user_id'];
	$q="select * from record where user_id=$user_id" ;
	$r=mysqli_query($dbc,$q);
	
	while($row=mysqli_fetch_array($r)){
		
		$arr[$n++]=array("record_id"=>$row['record_id'],"book_name"=>$row['book_name'],"user_id"=>$row['user_id'],"record_content"=>$row['record_content'],"book_id"=>$row['book_id'],"user_name"=>$row['user_name'],"record_date"=>$row['record_date']);
	}
	echo json_encode($arr);
}
?>