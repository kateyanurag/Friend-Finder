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
		$flag=0;


		//$query = "SELECT  phno FROM tbl_login ";
		//$query1 = "INSERT INTO tbl_login (name,phno,flagbit,lattitude,longitude) VALUES ('". $name . "','" . $phno . "'," . $flagbit . "," . $latt . "," . $longt . ");"; 
		mysqlStart('localhost','fbuddy_database');
		//$result = searchDatabase($query,array('phno'));
		//for($count=0;$count<sizeof($result);$count++)
		//{
		//  if($result[$count]['phno']==$phno)
		//  {
		//    flag=1; break;
		//  }
		 // echo $result[$count]['msg'] ;
		//}
		//echo $flag;
		//$query = "UPDATE tbl_login SET longitude=$longt,lattitude=$latt,flagbit=1 WHERE phno=$phno;";
		//$insert_result = executeQuery($query);
	
		
			$query1 = "INSERT INTO tbl_login (name,phno,flagbit,lattitude,longitude) VALUES ('". $name . "','" . $phno . "'," . $flagbit . "," . $latt . "," . $longt . ");"; 
			$insert_result = executeQuery($query1);
			if($insert_result != -1)
				echo "Inserted Successfully via PHP";
			else
				echo "Cannot insert to database";
		
			
		//close the connection
		mysqlEnd();
	}
?>

