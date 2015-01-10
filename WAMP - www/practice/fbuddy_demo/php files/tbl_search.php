<?php
	if(isset($_GET["txtRadius"]) )
	{
		//include the PHP-MySQL Library
		include_once("mysqllibrary.php");
		
		//now insert the record in the database
		$rad = $_GET["txtRadius"];
		
		
		$query = "INSERT INTO tbl_search (radius) VALUES (" . $rad .  ");";
		mysqlStart('localhost','fbuddy_database');
		$insert_result = executeQuery($query);
		
		if($insert_result != -1)
			echo "Inserted Successfully via PHP yippeeeeeeeeee";
		else
			echo "Cannot insert to database";
			
		//close the connection
		mysqlEnd();
	}
?>