<?php
    $connection=null;
    $db=null;
    
    function mysqlStart($ConnectionServer='localhost',$DatabaseName='dbfStudents')
    {
        global $connection;
        global $db;
        
        $connection=mysql_connect($ConnectionServer,"root");
        
        if(isset($connection))
        {
            $db=mysql_select_db($DatabaseName,$connection);
            
            if(isset($db))
            {
                return true;
            }
        }
        
        return false;
    }
    
    function executeQuery($QueryToFire)
    {
        global $connection;
        global $db;
        
        if(isset($connection) && isset($db))
        {
            mysql_query($QueryToFire,$connection);
            $result=mysql_insert_id($connection);
            return $result;
        }
        else
        {
            return -1;
        }
    }
    
    function searchDatabase($QueryToFire,$ColumnsToReturn)
    {
        global $connection;
        global $db;
        
        if(isset($connection) && isset($db))
        {
            $result=mysql_query($QueryToFire,$connection);
            
            $count=0;
            
            while($row=mysql_fetch_array($result))
            {
                foreach($ColumnsToReturn as $Column)
                {
                    $output[$count][$Column] = $row[$Column];
                }
                $count=$count+1;
            }
            
            return $output;
        }
        
        return false;
    }
    
    function mysqlEnd()
    {
        global $connection;
        global $db;
        
        unset($db);
        unset($connection);
    }
?>