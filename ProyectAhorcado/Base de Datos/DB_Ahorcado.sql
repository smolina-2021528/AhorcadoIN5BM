drop database if exists DB_Ahorcado;
create database DB_Ahorcado;
use DB_Ahorcado;

Create table Usuarios ( 
codigo_usuario int auto_increment, 
nombre_usuario varchar(32) unique, 
contraseña_usuario varchar (32), 
constraint pk_codigoUsuario primary key (codigo_usuario)); 

Create table Palabras ( 
codigo_palabra int auto_increment, 
texto_palabra varchar(20) unique, 
constraint pk_codigoPalabra primary key (codigo_palabra) );

 Delimiter $$
 Create procedure sp_AgregarUsuario ( 
 in nomUsu varchar(32), 
 in contUsu varchar (32)) 
 begin
 insert into Usuarios (nombre_usuario, contraseña_usuario) 
 values (nomUsu, contUsu);
 end$$ 
 Delimiter ; 
 
 call sp_AgregarUsuario("joaquingonzales", "jocaco7852"); 
 call sp_AgregarUsuario("diegomendez", "diegoLuhe980"); 
 call sp_AgregarUsuario("pablosanta cruz", "sdjfjf4141"); 
 
Delimiter $$
Create procedure sp_AgregarPalabra (in texto varchar (32))
begin
	insert into Palabras (texto_palabra)
		values (texto);
end $$
Delimiter ;
 
 call sp_AgregarPalabra ("CENICIENTA");
 call sp_AgregarPalabra ("ANIMALISTA");
 call sp_AgregarPalabra ("OBSTACULO"); 
 call sp_AgregarPalabra ("DESBORDAR"); 
 call sp_AgregarPalabra ("CARRUAJE"); 
 call sp_AgregarPalabra ("DESAGRADABLE"); 
 call sp_AgregarPalabra ("DEPENDER"); 
 call sp_AgregarPalabra ("CUALQUIER"); 
 call sp_AgregarPalabra ("ESCUCHAR"); 
 call sp_AgregarPalabra ("IRREGULAR"); 
 call sp_AgregarPalabra ("CONVERTIRSE"); 
 call sp_AgregarPalabra ("BRUTALMENTE");
 call sp_AgregarPalabra ("EDUCACION"); 
 call sp_AgregarPalabra ("CEPILLARSE"); 
 call sp_AgregarPalabra ("COMPORTAMIENTO"); 
 call sp_AgregarPalabra ("PROFESOR"); 
 call sp_AgregarPalabra ("PASAJERO"); 
 /*
 call sp_AgregarPalabra ("TRANSMITIR"); 
 call sp_AgregarPalabra ("REALMADRID");
 call sp_AgregarPalabra ("AGRADABLE"); 
 call sp_AgregarPalabra ("DESPIERTO"); 
 call sp_AgregarPalabra ("ILUMINADO"); 
 call sp_AgregarPalabra ("SURREALISTA");
 call sp_AgregarPalabra ("COMANDOS"); 
 call sp_AgregarPalabra ("ATROCIDADES"); 
 call sp_AgregarPalabra ("IMPLICAR");
 call sp_AgregarPalabra ("AFICIONES");
 call sp_AgregarPalabra ("TECNOLOGIA");
 call sp_AgregarPalabra ("PRESENTACION"); 
 call sp_AgregarPalabra ("COMBATIENTE");
 call sp_AgregarPalabra ("INTERCAMBIAR");
 call sp_AgregarPalabra ("BARBILLA");
 call sp_AgregarPalabra ("REPRODUCTOR"); 
 call sp_AgregarPalabra ("LIQUIDOS");
 call sp_AgregarPalabra ("MISERABLE"); 
 call sp_AgregarPalabra ("NUMEROSO"); 
 call sp_AgregarPalabra ("SABIENDO"); 
 call sp_AgregarPalabra ("REBOBINAR"); 
 call sp_AgregarPalabra ("DESCOLORIDO");
 */
 
 Delimiter $$
Create procedure sp_ValidarUsuario(
    in nomUsu varchar(32),
    in contUsu varchar(32)
)
begin
    select * from Usuarios
    where nombre_usuario = nomUsu
    and contraseña_usuario = contUsu;
end$$
Delimiter ;

Delimiter $$
Create procedure sp_ObtenerPalabraAleatoria()
begin
    select textoPalabra from Palabras
    order by rand()
    limit 1;
end$$
Delimiter ;