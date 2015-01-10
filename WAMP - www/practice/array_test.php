<?php

$arr = array("pankaj","sagar",33.22,98);

echo "$arr[0] $arr[1] $arr[2] $arr[3]<br/>";

$arr[4] = "pooja";

print_r($arr);

echo "<br/>";

echo sizeof($arr) . "<br/>";

for($count=0;$count<sizeof($arr);$count++)
{
	echo "Position $count, Value $arr[$count]<br/>";
}

//Custom arrays
$arr_cust = array("name1" => "Pankaj", "name2" => "Sagar", 0 => 33.22, 1 => 98, "name3" => "pooja");
print_r($arr_cust);

echo "<br/>";

while(list($key,$value) = each($arr_cust))
{
	echo "Index:$key – Value:$value <br/>";
}


?>