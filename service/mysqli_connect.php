<?php

//设置本地的主机名和密码，连接mysql数据库
define('hostname','localhost');
define('username','root');
define('password','');
define('db_name','liberary');
$dbc=@mysqli_connect(hostname,username,password,db_name);


?>