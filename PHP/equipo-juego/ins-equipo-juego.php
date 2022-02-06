<?php
            require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";
                 
                }else{
              
                    
                    $sql = "INSERT INTO EQUIPO_JUEGO (iIdEquipo, iIdJuego, iVictorias,iDerrotas,fWinRate) VALUES ('".$_GET["txtEquipo"]."', '".$_GET["txtJuego"]."', ".$_GET["txtVictorias"]." ,".$_GET["txtDerrotas"]." , ".$_GET["txtWinRate"].")";
            
                    echo $sql;

                    if(mysqli_query($conn, $sql)){
                        
                        echo "OK.";

                    } else {
                        echo "ERROR: " . mysqli_error($conn);
                    }
                    
                    mysqli_close($conn);

                }

?>