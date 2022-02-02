<?php
            require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";

                }else{
                   
                    $sql = "SELECT sNombre, sFoto FROM PRODUCTO WHERE iIdProducto IN NOT (SELECT iIdProducto FROM USUARIO_PRODUCTOS WHERE iIdUsuario = ". $_GET["txtiIdUsuario"].") AND iIdCategoria = ". $_GET["txtCategoria"]."";

                    $result = $conn->query($sql);

                    $json =  json_encode ($result->fetch_assoc());
                
                    echo $json;
                    mysqli_close($conn);

                }

?>