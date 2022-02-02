<?php
            require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";

                }else{
                   
                    $sql = "SELECT * FROM EQUIPO";

                    $result = $conn->query($sql);   
                    
                    $equipos = array();

             while($fila = mysqli_fetch_assoc($result)){ // Coge todas las filas mientras que haya y las guarda en cada iteracion en la variable fila
           $equipos[] = array ('sNombre' => $fila["sNombre"],
                            'sFoto' => $fila["sFoto"]),
                            'iMiembros' => $fila["iMiembros"]); 
    }
            }
            
              $json =  json_encode ($equipos);
              echo $json;
              mysqli_close($conn);
                       
?>