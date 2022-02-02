<?php
            require_once("conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";
                 


                }else{
                   $txtCodigo = $_GET["txtCodigo"];
                   $txtPassword = $_GET["txtPassword"];
                   $caracteres='ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz';
                   $longpalabra=8;
                   
                    $sql = "UPDATE USUARIO SET sPassword = '$txtPassword' WHERE sCodigo = '". $_GET["txtCodigo"]."'";
                    
                    for($pass='', $n=strlen($caracteres)-1; strlen($pass) < $longpalabra ; ) {
                    $x = rand(0,$n);
                    $pass.= $caracteres[$x];
                    
                    }          
                    
                   
                    if(mysqli_query($conn, $sql)){
                    $extraerCodigo = mysqli_query($conn,"SELECT sCodigo AS codigo FROM USUARIO WHERE sPassword = '$txtPassword'");
                    $result = mysqli_fetch_assoc($extraerCodigo);
                    
                    $row = $result["codigo"];
                 
                    
                    if($row != $txtCodigo){
                        echo "El token es incorrecto, por favor";
                    }else{
                    $sql2 = "UPDATE USUARIO SET sCodigo = '$pass' WHERE sCodigo = '$txtCodigo'";
                     if(mysqli_query($conn, $sql2)){
                         
                            echo "Se ha actualizado la contraseña correctamente";
                         }     
                    }
                    
                    
                    
                            
                   
                        
    
                          

                    } else {
                        echo "ERROR: " . mysqli_error($conn);
                    }
                    
                
                    mysqli_close($conn);

                }
                

?>