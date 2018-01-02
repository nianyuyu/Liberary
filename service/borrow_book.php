<?php
	//导入数据库连接文件
	require_once('mysqli_connect.php');

	//获取android以get方式提交的数据
	$book_id=$_GET['book_id'];
	$user_name=$_GET['user_name'];
	$user_id=$_GET['user_id'];
	$book_status=$_GET['book_status'];

	//更新book表
	$q="update book set user_id='$user_id',user_name='$user_name',book_status='$book_status' where book_id='$book_id'";
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