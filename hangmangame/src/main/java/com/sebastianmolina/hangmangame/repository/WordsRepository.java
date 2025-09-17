package com.sebastianmolina.hangmangame.repository;

import com.sebastianmolina.hangmangame.model.Words;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordsRepository extends JpaRepository<Words, Integer> {

    // esto es para que las palabras sean unicas
    boolean existsByWordText(String wordText);

    // esto es para que las palabras sean unicas al actualizar
    boolean existsByWordTextAndWordCodeNot(String wordText, Integer wordCode);
}