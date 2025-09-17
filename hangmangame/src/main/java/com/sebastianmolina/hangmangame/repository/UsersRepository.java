package com.sebastianmolina.hangmangame.repository;

import com.sebastianmolina.hangmangame.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    // esto es para la validacion de unico
    boolean existsByUserName(String userName);
    boolean existsByUserPassword(String userPassword);

    // esto es para la validacion de unico al actualizar
    boolean existsByUserNameAndUserCodeNot(String userName, Integer userCode);
    boolean existsByUserPasswordAndUserCodeNot(String userPassword, Integer userCode);
}