<?php
	if(isset($_GET["txt_name"]) && isset($_GET["txt_phno"]) && isset($_GET["flagbit"]))
	{
		//include the PHP-MySQL Library
		include_once("mysqllibrary.php");
		
		//now insert the record in the database
                                   $name = $_GET["txt_name"];
		$phno = $_GET["txt_phno"];
		$flagbit= $_GET["flagbit"];


		$query = "INSERT INTO tbllogin (name,phno,flagbit) VALUES ('". $name . "','" . $phno . "'," . $flagbit . ");"; 
		mysqlStart('localhost','fbuddy_database');
		$insert_result = executeQuery($query);
		
		if($insert_result != -1)
			echo "Inserted Successfully via PHP";
		else
			echo "Cannot insert to database";
			
		//close the connection
		mysqlEnd();
	}
?>

