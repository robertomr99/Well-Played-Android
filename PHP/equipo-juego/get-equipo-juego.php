<?php
            require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";

                }else{
                   
                    $sql = "SELECT iVictorias, iDerrotas, fWinRate FROM EQUIPO_JUEGO WHERE 
                    iIdEquipo = ".$_GET["txtEquipo"]." AND iIdJuego = ".$_GET["txtJuego"]."";

                    $result = $conn->query($sql);   
                    $json = json_encode ($result ->fetch_assoc());
                    echo $json;
                    mysqli_close($conn);
                }
                    
                  
    
          
?>