<?php
	//导入数据库连接文件
	require_once('mysqli_connect.php');

	//接受android端以POST方式提交的值
	$record_id=$_POST['record_id'];	
	$record_content=$_POST['record_content'];

	//更新数据表record中的数据
	$q="update  record set record_content='$record_content' where record_id='$record_id'";
	$r=mysqli_query($dbc,$q);
	if($r){
		$response['success']=1;
	}else{
		$response['success']=0;
	}

	//将执行结果封装成JSON格式
	echo json_encode($response);
	mysqli_close($dbc);	
  
?>