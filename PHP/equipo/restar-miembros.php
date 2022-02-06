<?php
            require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";

                }else{
                   
                    $select = "SELECT iMiembros FROM EQUIPO WHERE iIdEquipo = (SELECT iIdEquipo WHERE sNombre = '". $_GET["txtEquipo"]."'))"

                    $resultSelect = $conn->query($select);
                    
                    $resultSelect = $resultSelect - 1;
                    
                    $update = "UPDATE EQUIPO SET iMiembros =  $resultSelect";

                    $conn->query($update);


                    $json = json_encode ($resultSelect->fetch_assoc());
                    echo $json;
                    mysqli_close($conn);
              
                }        
?>