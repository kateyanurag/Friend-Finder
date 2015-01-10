<?php
	if(isset($_GET["txtMessage"]))
	{
		//include the PHP-MySQL Library
		include_once("mysqllibrary.php");
		
		//now insert the record in the database
		$message = $_GET["txtMessage"];
		
		$query = "INSERT INTO tblWallPost (message) VALUES ('" . $message . "');";
		mysqlStart('localhost','dbfasynctask');
		$insert_result = executeQuery($query);
		
		if($insert_result != -1)
		{
			echo "Inserted Successfully via PHP";
			
			//Also update the Flag to true
			$query = "UPDATE tblflag SET flag=1";
			executeQuery($query);
		}
		else
			echo "Cannot insert to database";
			
		//close the connection
		mysqlEnd();
	}
?>