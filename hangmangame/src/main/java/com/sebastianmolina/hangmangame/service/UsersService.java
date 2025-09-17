package com.sebastianmolina.hangmangame.service;

import com.sebastianmolina.hangmangame.model.Users;
import java.util.List;

public interface UsersService {

    List<Users> getAllUsers();

    Users getUserById(Integer id);

    Users saveUser(Users user);

    Users updateUser(Integer id, Users user);

    void deleteUser(Integer id);

}