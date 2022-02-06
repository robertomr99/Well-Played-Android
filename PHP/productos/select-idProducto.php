<?php
        require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";
                 


                }else{
                    
                    $sql = "SELECT iIdProducto FROM PRODUCTO WHERE sNombre = '". $_GET["txtProducto"]."'";
                    
                    
                    $result = $conn->query($sql);
                    
                    $json = json_encode ($result->fetch_assoc());
                    
                    echo $json;
                    mysqli_close($conn);


                }





?>