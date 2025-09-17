package com.sebastianmolina.hangmangame.controller;

import com.sebastianmolina.hangmangame.model.Words;
import com.sebastianmolina.hangmangame.service.WordsService;
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
@RequestMapping("/api/words")
public class WordsController {
    private final WordsService wordsService;

    public WordsController(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    @GetMapping
    public List<Words> getAllWords() {
        return wordsService.getAllWords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWordById(@PathVariable Integer id) {
        Words word = wordsService.getWordById(id);
        if (word == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No se encontró la palabra"));
        return ResponseEntity.ok(word);
    }

    @PostMapping
    public ResponseEntity<?> createWord(@Valid @RequestBody Words word) {
        return ResponseEntity.ok(wordsService.saveWord(word));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWord(@PathVariable Integer id, @Valid @RequestBody Words word) {
        Words updated = wordsService.updateWord(id, word);
        if (updated == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No se encontró la palabra"));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteWord(@PathVariable Integer id) {
        wordsService.deleteWord(id);
    }

    // errores de validaciones
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
            System.out.println("Houston tenemos un problema en el campo " + error.getField() + " : " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    // errores de argumentos invalidos
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> errors = new HashMap<>();
        String msg = ex.getMessage();
        if (msg != null && msg.contains(":")) {
            String[] parts = msg.split(":", 2);
            String field = parts[0].trim();
            String message = parts[1].trim();
            errors.put(field, message);
            System.out.println("Houston tenemos un problema en el campo " + field + " : " + message);
        } else {
            errors.put("error", msg == null ? "Error en la operacion " : msg);
            System.out.println("Problema : " + msg);
        }
        return ResponseEntity.badRequest().body(errors);
    }

    // errores de integridad de datos o duplicaciones
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityViolationException ex) {
        String detail = ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage();
        System.out.println("Problema SQL : " + detail);
        Map<String, String> err = new HashMap<>();
        err.put("error", "Valor duplicado o alguna restricción " + (detail != null ? detail : ""));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }
}