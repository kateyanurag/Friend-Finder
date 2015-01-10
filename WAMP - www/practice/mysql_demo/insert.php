<?php
	if(isset($_GET["txtName"]) && isset($_GET["txtAge"]) && isset($_GET["txtAddress"]) && isset($_GET["txtContact"]) && isset($_GET["txtOtherInformation"]))
	{
		//include the PHP-MySQL Library
		include_once("mysqllibrary.php");
		
		//now insert the record in the database
		$name = $_GET["txtName"];
		$age = $_GET["txtAge"];
		$address = $_GET["txtAddress"];
		$contact = $_GET["txtContact"];
		$other_information = $_GET["txtOtherInformation"];
		
		$query = "INSERT INTO tblpersonalinformation (name,age,address,contact,other_information) VALUES ('" . $name . "'," . $age . ",'" . $address . "','" . $contact . "','" . $other_information . "');";
		mysqlStart('localhost','dbfStudents');
		$insert_result = executeQuery($query);
		
		if($insert_result != -1)
			echo "Inserted Successfully via PHP";
		else
			echo "Cannot insert to database";
			
		//close the connection
		mysqlEnd();
	}
?>