drop database if exists DB_Ahorcado;
create database DB_Ahorcado;
use DB_Ahorcado;

Create table Users ( 
user_code int auto_increment, 
user_name varchar(32) unique, 
user_password varchar (32), 
constraint pk_user_code primary key (user_code)); 

Create table Words ( 
word_code int auto_increment, 
word_text varchar(20) unique, 
constraint pk_word_code primary key (word_code));

 Delimiter $$
 Create procedure sp_AddUser ( 
	in usnam varchar(32), 
	in uspass varchar (32)) 
	begin
		 insert into Users (user_name, user_password) 
			values (usnam, uspass);
	end$$ 
 Delimiter ; 
 
 call sp_AddUser("joaquingonzales", "jocaco7852"); 
 call sp_AddUser("diegomendez", "diegoLuhe980"); 
 call sp_AddUser("pablosanta cruz", "sdjfjf4141"); 
 
Delimiter $$
Create procedure sp_AddWord (in wtext varchar (32))
begin
	insert into Words (word_text)
		values (wtext);
end $$
Delimiter ;
 
 call sp_AddWord ("CENICIENTA");
 call sp_AddWord ("ANIMALISTA");
 call sp_AddWord ("OBSTACULO"); 
 call sp_AddWord ("DESBORDAR"); 
 call sp_AddWord ("CARRUAJE"); 
 call sp_AddWord ("DESAGRADABLE"); 
 call sp_AddWord ("DEPENDER"); 
 call sp_AddWord ("CUALQUIER"); 
 call sp_AddWord ("ESCUCHAR"); 
 call sp_AddWord ("IRREGULAR"); 
 call sp_AddWord ("CONVERTIRSE"); 
 call sp_AddWord ("BRUTALMENTE");
 call sp_AddWord ("EDUCACION"); 
 call sp_AddWord ("CEPILLARSE"); 
 call sp_AddWord ("COMPORTAMIENTO"); 
 call sp_AddWord ("PROFESOR"); 
 call sp_AddWord ("PASAJERO"); 
 /*
 call sp_AddWord ("TRANSMITIR"); 
 call sp_AddWord ("REALMADRID");
 call sp_AddWord ("AGRADABLE"); 
 call sp_AddWord ("DESPIERTO"); 
 call sp_AddWord ("ILUMINADO"); 
 call sp_AddWord ("SURREALISTA");
 call sp_AddWord ("COMANDOS"); 
 call sp_AddWord ("ATROCIDADES"); 
 call sp_AddWord ("IMPLICAR");
 call sp_AddWord ("AFICIONES");
 call sp_AddWord ("TECNOLOGIA");
 call sp_AddWord ("PRESENTACION"); 
 call sp_AddWord ("COMBATIENTE");
 call sp_AddWord ("INTERCAMBIAR");
 call sp_AddWord ("BARBILLA");
 call sp_AddWord ("REPRODUCTOR"); 
 call sp_AddWord ("LIQUIDOS");
 call sp_AddWord ("MISERABLE"); 
 call sp_AddWord ("NUMEROSO"); 
 call sp_AddWord ("SABIENDO"); 
 call sp_AddWord ("REBOBINAR"); 
 call sp_AddWord ("DESCOLORIDO");
 */
 
 Delimiter $$
Create procedure sp_ValidateUser(
    in usnam varchar(32),
    in uspass varchar(32)
)
begin
    select * from Users
    where user_name = usnam
    and user_password = uspass;
end$$
Delimiter ;

Delimiter $$
Create procedure sp_RandomWord()
begin
    select word_text from Words
    order by rand()
    limit 1;
end$$
Delimiter ;

call sp_RandomWord();
select * from Users;
select * from Words;