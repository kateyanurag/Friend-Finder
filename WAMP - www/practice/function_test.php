<?php
	function sum_avg($a,$b,&$sum,&$avg)
	{
		$sum = $a + $b;
		$avg = $sum / 2;
	}
	
	sum_avg(10,20,$sum,$avg);
	echo "Sum:$sum, Average:$avg";
	
	function fun($arg=10)
	{
		echo "<br/>" . $arg;
	}
	
	fun(55);
	
	function max_value()
	{
		$max_val = 0;
		//get all the args passed to the function
		$arr = func_get_args();
		for($i=0;$i<sizeof($arr);$i++)
		{
			if($arr[$i] > $max_val)
			{
				$max_val = $arr[$i];
			}
		}
		return $max_val;
	}
	
	$max = max_value(9,8,8,6,12,23,56,100,123,21);
	echo "<br/>Max Value:" . $max;
?>