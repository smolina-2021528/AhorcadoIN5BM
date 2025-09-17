package com.sebastianmolina.hangmangame.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_code")
    private Integer userCode;

    @NotBlank(message = "El usuario no debe estar vacio")
    @Size(max = 32, message = "El nombre de usuario puede tener como maximo 32 caracteres")
    @Column(name = "user_name", unique = true)
    private String userName;

    @NotBlank(message = "La contraseña no puede estar vacias")
    @Size(max = 32, message = "La contraseña no puede tener mas de 32 caracteres")
    @Column(name = "user_password")
    private String userPassword;

    public Integer getUserCode() {
        return userCode;
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}