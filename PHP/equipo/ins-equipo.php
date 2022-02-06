<?php
            require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";
                 
                }else{
              
                    
                    $sql = "INSERT INTO EQUIPO (sNombre,sFoto,iMiembros) VALUES ('".$_GET["txtNombre"]. "','".$_GET["txtFoto"]."',".$_GET["txtMiembros"].")";
            

                    echo $sql;

                    if(mysqli_query($conn, $sql)){
                        
                        echo "OK.";

                    } else {
                        echo "ERROR: " . mysqli_error($conn);
                    }
                    
                    mysqli_close($conn);

                }

?>