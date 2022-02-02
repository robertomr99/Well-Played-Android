<?php
            require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";

                }else{
                   
                    $sql = "SELECT sNombre FROM JUEGO WHERE iIdJuego in (SELECT iIdJuego FROM EQUIPO_JUEGO WHERE iIdEquipo = ".$_GET["txtEquipo"].")";

                    $result = $conn->query($sql);   
                    
                    $juegos= array();

             while($fila = mysqli_fetch_assoc($result)){ // Coge todas las filas mientras que haya y las guarda en cada iteracion en la variable fila
           $juegos[] = array ('sNombre' => $fila["sNombre"]); 
            }
              $json =  json_encode ($juegos);
              echo $json;
              mysqli_close($conn);
              }
?>