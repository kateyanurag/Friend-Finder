<?php
	if(isset($_GET["txtID"]) && isset($_GET["txtMessage"]))
	{
		//include the PHP-MySQL Library
		include_once("mysqllibrary.php");
		
		//now Update the record in the database
		$id = $_GET["txtID"];
		$message = $_GET["txtMessage"];
		
		$query = "UPDATE tblwallpost SET message='$message' id=$id;";
		//echo $query;
		mysqlStart('localhost','dbfasynctask');
		$insert_result = executeQuery($query);
		
		if($insert_result != -1)
		{
			echo "Updated Successfully";
			//Also update the Flag to true
			$query = "UPDATE tblflag SET flag=1";
			executeQuery($query);
		}
		else
			echo "Cannot update the database";
			
		//close the connection
		mysqlEnd();
	}
?>