<?php
	//导入数据库连接文件
	require_once('mysqli_connect.php');

	////获取android以get方式提交的数据
	$book_id=$_GET['book_id'];
	$book_name=$_GET['book_name'];
	$user_name=$_GET['user_name'];
	$user_id=$_GET['user_id'];
	$pic_url=$_GET['pic_url'];

	//向recommend表中添加一条记录
	$q="insert into  recommend (user_id,user_name,book_id,book_name,pic_url) value('$user_id','$user_name','$book_id','$book_name','$pic_url')";
	$r=mysqli_query($dbc,$q);
	if($r){
		$response['success']=1;
	}else{
		$response['success']=0;
	}

	//将查询结果封装成JSON格式
	echo json_encode($response);
	//关闭数据库连接
	mysqli_close($dbc);  
?>