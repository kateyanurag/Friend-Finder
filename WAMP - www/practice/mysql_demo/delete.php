<?php
	if(isset($_GET["txtID"]))
	{
		//include the PHP-MySQL Library
		include_once("mysqllibrary.php");
		
		//now insert the record in the database
		$id = $_GET["txtID"];
		
		$query = "DELETE FROM tblpersonalinformation WHERE id=$id;";
		//echo $query;
		mysqlStart('localhost','dbfStudents');
		$insert_result = executeQuery($query);
		
		if($insert_result != -1)
			echo "Deleted Successfully";
		else
			echo "Cannot delete from database";
			
		//close the connection
		mysqlEnd();
	}
?>