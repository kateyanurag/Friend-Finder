<?php
	if(isset($_GET["txtId"]) && isset($_GET["txtMsg"]))
	{
		//include the PHP-MySQL Library
		include_once("mysqllibrary.php");
		
		//now insert the record in the database
		//$id = $_GET["txtId"];
		$id = $_GET["txtId"];
		$msg = $_GET["txtMsg"];
		$mbit = 1;
		//$query = "INSERT INTO tbl_chat (msg) VALUES ('" . $msg. "') WHERE id=$id;";
		$query = "UPDATE tbl_chat SET msg='$msg',mbit=1 WHERE id=$id;";
		mysqlStart('localhost','fbuddy_database');
		$insert_result = executeQuery($query);
		
		if($insert_result != -1)
			echo "Message Sent";
		else
			echo "Cannot Send";
			
		//close the connection
		mysqlEnd();
	}

?>