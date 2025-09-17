package com.sebastianmolina.hangmangame.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Words")
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_code")
    private Integer wordCode;

    @NotBlank(message = "El texto no debe estar vacio")
    @Size(min = 8, max = 20, message = "El texto no debe tener menos de 8 caracteres ni mas de 20")
    @Column(name = "word_text", unique = true)
    private String wordText;

    public Integer getWordCode() {
        return wordCode;
    }

    public void setWordCode(Integer wordCode) {
        this.wordCode = wordCode;
    }

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }
}