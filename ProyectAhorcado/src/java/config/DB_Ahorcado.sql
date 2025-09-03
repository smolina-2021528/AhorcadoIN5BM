drop database if exists DB_Ahorcado;
create database DB_Ahorcado;
use DB_Ahorcado;

Create table Usuarios (
	codigoUsuario int auto_increment,
    nombreUsuario varchar(32) unique,
    contraseñaUsuario varchar (32),
    constraint pk_codigoUsuario primary key (codigoUsuario)
);

Create table Palabras (
	codigoPalabra int auto_increment,
    textoPalabra varchar(20) unique,
    constraint pk_codigoPalabra primary key (codigoPalabra)
);


Delimiter $$
	Create procedure sp_AgregarUsuario (
		in nomUsu varchar(32), 
		in contUsu varchar (32))
	begin 
		insert into Usuarios (nombreUsuario, contraseñaUsuario)
			values (nomUsu, contUsu);
	end$$
Delimiter ;

call sp_AgregarUsuario("joaquin gonzales", "jocaco7852$");
call sp_AgregarUsuario("diego mendez", "diegoLuhe980");
call sp_AgregarUsuario("pablo santa cruz", "sdjfjf4141");

Delimiter $$
	Create procedure sp_AgregarPalabra (
		in texto varchar(20))
	begin 
		insert into Palabras (textoPalabra)
			values (texto);
	end$$
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
call sp_AgregarPalabra ("ORDINARIO");
call sp_AgregarPalabra ("TECNOLOGIA");
call sp_AgregarPalabra ("PRESENTACION");
call sp_AgregarPalabra ("COMBATIENTE");
call sp_AgregarPalabra ("INTERCAMBIAR");
call sp_AgregarPalabra ("BARBILLA");
call sp_AgregarPalabra ("FRACCION");
call sp_AgregarPalabra ("REPRODUCTOR");
call sp_AgregarPalabra ("LIQUIDOS");
call sp_AgregarPalabra ("MISERABLE");
call sp_AgregarPalabra ("NUMEROSO");
call sp_AgregarPalabra ("SABIENDO");
call sp_AgregarPalabra ("REBOBINAR");
call sp_AgregarPalabra ("DESCOLORIDO");