<?php
	if(isset($_GET["txtID"]) && isset($_GET["txtName"]) && isset($_GET["txtAge"]) && isset($_GET["txtAddress"]) && isset($_GET["txtContact"]) && isset($_GET["txtOtherInformation"]))
	{
		//include the PHP-MySQL Library
		include_once("mysqllibrary.php");
		
		//now insert the record in the database
		$id = $_GET["txtID"];
		$name = $_GET["txtName"];
		$age = $_GET["txtAge"];
		$address = $_GET["txtAddress"];
		$contact = $_GET["txtContact"];
		$other_information = $_GET["txtOtherInformation"];
		
		$query = "UPDATE tblpersonalinformation SET name='$name', age=$age, address='$address', contact='$contact', other_information='$other_information' WHERE id=$id;";
		//echo $query;
		mysqlStart('localhost','dbfStudents');
		$insert_result = executeQuery($query);
		
		if($insert_result != -1)
			echo "Updated Successfully";
		else
			echo "Cannot update the database";
			
		//close the connection
		mysqlEnd();
	}
?>