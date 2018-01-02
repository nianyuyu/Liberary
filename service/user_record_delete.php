<?php
	//导入数据库连接文件
	require_once('mysqli_connect.php');

	//接受android端以GET方式传递的信息
	$record_id=$_GET['record_id'];

	//删除record表中符合条件的数据
	$q="delete  from  record where record_id=$record_id";
	$r=mysqli_query($dbc,$q);
	if($r){
	 	$response["success"]=1;
	}else{
		$response["success"]=0;
	}

	//以JSON格式封装数据
	echo json_encode($response);
	mysqli_close($dbc);
   


?>