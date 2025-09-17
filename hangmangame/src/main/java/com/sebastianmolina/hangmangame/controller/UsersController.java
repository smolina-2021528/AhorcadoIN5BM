package com.sebastianmolina.hangmangame.controller;

import com.sebastianmolina.hangmangame.model.Users;
import com.sebastianmolina.hangmangame.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        Users user = usersService.getUserById(id);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No se encontró el usuario"));
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody Users user) {
        return ResponseEntity.ok(usersService.saveUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody Users user) {
        Users updated = usersService.updateUser(id, user);
        if (updated == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No se encontró el usuario"));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        usersService.deleteUser(id);
    }

    // errores de validacion
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
            System.out.println("Houston, tenemos un problema en el campo " + error.getField() + " : " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    // errores de argumentos no validos
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> errors = new HashMap<>();
        String msg = ex.getMessage();
        if (msg != null && msg.contains(":")) {
            String[] parts = msg.split(":", 2);
            String field = parts[0].trim();
            String message = parts[1].trim();
            errors.put(field, message);
            System.out.println("Houston, tenemos un problema en el campo " + field + " : " + message);
        } else {
            errors.put("error", msg == null ? "Error en la operacion" : msg);
            System.out.println("Problema : " + msg);
        }
        return ResponseEntity.badRequest().body(errors);
    }

    // errores sql o valor duplciado
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityViolationException ex) {
        String detail = ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage();
        System.out.println("Problema SQL : " + detail);
        Map<String, String> err = new HashMap<>();
        err.put("error", "Valor duplicado o alguna restricción " + (detail != null ? detail : ""));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }
}