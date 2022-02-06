<?php
require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";
                }else{
                        
                $sql = "SELECT sNombre,sFoto,iPrecio from PRODUCTOS WHERE iIdCategoria = '".$_GET["txtCategoria"]."'"; 
                $result = $conn->query($sql);
                
                $productos = array();
                
                        while($fila = mysqli_fetch_assoc($result)){
                       $productos[] = array('sNombre' => $fila["sNombre"],
                       'sFoto' => $fila["sFoto"],
                               'iPrecio' => $fila["iPrecio"]);
                       
                        }
                }
                
                             $json = json_encode($productos);
                             echo $json;
                             mysqli_close($conn);



?>