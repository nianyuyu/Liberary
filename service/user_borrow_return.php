<?php
	//导入数据库连接文件
	require_once('mysqli_connect.php');

	//接受android端以GET方式提交的数据
	$book_id=$_GET['book_id'];
	$a='';
	$b=0;

	//更新数据表book中书籍状态的值
	$q="update book set book_status='$b',user_name='$a',user_id='$a'  where book_id='$book_id'";
	$r=mysqli_query($dbc,$q);
	if($r){
	 	$response["success"]=1;
	}else{
		$response["success"]=0;
	}

	//更新结果以JSON格式封装
	echo json_encode($response);
	mysqli_close($dbc);
  
?>