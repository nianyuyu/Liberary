<?php
	//导入数据库连接文件
	require_once('mysqli_connect.php');

	//接收来自GET方式提交的值（用户键入的值）
	$collect_id=$_GET['collect_id'];

	//删除collect数据表中符合条件的数据
	$q="delete  from  collect where collect_id=$collect_id";
	$r=mysqli_query($dbc,$q);
	if($r){
	 	$response["success"]=1;
	}else{
		$response["success"]=0;
	}

	//将删除结果以JSON格式封装
	echo json_encode($response);	
	mysqli_close($dbc);
   


?>