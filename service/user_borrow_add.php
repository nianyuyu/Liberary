<?php
	//导入数据库连接文件
	require_once('mysqli_connect.php');
	
	//接受android端以GET方式提交的值
	$book_id=$_GET['book_id'];
	

	//更新book表中的归还日期的值
	$q="update book set return_date=DATE_ADD(return_date, INTERVAL 1 MONTH)  where book_id='$book_id' ";
	$r=mysqli_query($dbc,$q);
	if($r){
        $response["success"]=1;
    }else{
        $response["success"]=0;
    }

    //将更新结果以JSON格式封装
	echo json_encode($response);
	mysqli_close($dbc);
   
?>