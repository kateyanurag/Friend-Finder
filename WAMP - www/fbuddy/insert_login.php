<?php
	if(isset($_GET["txt_name"]) && isset($_GET["txt_phno"]) && isset($_GET["flagbit"]) && isset($_GET["lattitude"]) && isset($_GET["longitude"]))
	{
		//include the PHP-MySQL Library
		include_once("mysqllibrary.php");
		
		//now insert the record in the database
                $name = $_GET["txt_name"];
		$phno = $_GET["txt_phno"];
		$flagbit= $_GET["flagbit"];
                $latt =$_GET["lattitude"];
		$longt=$_GET["longitude"];



		$query = "INSERT INTO tbllogin (name,phno,flagbit,lattitude,longitude) VALUES ('". $name . "','" . $phno . "'," . $flagbit . "," . $latt . "," . $longt . ");"; 
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

