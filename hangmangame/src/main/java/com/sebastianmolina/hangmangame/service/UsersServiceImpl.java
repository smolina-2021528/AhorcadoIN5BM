package com.sebastianmolina.hangmangame.service;

import com.sebastianmolina.hangmangame.model.Users;
import com.sebastianmolina.hangmangame.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUserById(Integer id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public Users saveUser(Users user) {
        // Validaciones de unicidad antes de persistir
        if (usersRepository.existsByUserName(user.getUserName())) {
            String msg = "El nombre de usuario ya existe";
            System.out.println("Error al ingresar: " + msg);
            throw new IllegalArgumentException(msg);
        }
        if (usersRepository.existsByUserPassword(user.getUserPassword())) {
            String msg = "La contraseña ya existe";
            System.out.println("Error al ingresar: " + msg);
            throw new IllegalArgumentException(msg);
        }
        return usersRepository.save(user);
    }

    @Override
    public Users updateUser(Integer id, Users user) {
        Users existingUser = usersRepository.findById(id).orElse(null);
        if (existingUser != null) {
            // Validaciones de unicidad que excluyen el registro actual
            if (usersRepository.existsByUserNameAndUserCodeNot(user.getUserName(), id)) {
                String msg = "El nombre de usuario ya esta registrado con otro id";
                System.out.println("Error: " + msg);
                throw new IllegalArgumentException(msg);
            }
            if (usersRepository.existsByUserPasswordAndUserCodeNot(user.getUserPassword(), id)) {
                String msg = " La contraseña ya esta en uso por otro usuario";
                System.out.println("Error  " + msg);
                throw new IllegalArgumentException(msg);
            }

            existingUser.setUserName(user.getUserName());
            existingUser.setUserPassword(user.getUserPassword());
            return usersRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        usersRepository.deleteById(id);
    }
}