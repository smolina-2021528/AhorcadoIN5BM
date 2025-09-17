package com.sebastianmolina.hangmangame.service;

import com.sebastianmolina.hangmangame.model.Words;
import java.util.List;

public interface WordsService {

    List<Words> getAllWords();

    Words getWordById(Integer id);

    Words saveWord(Words word);

    Words updateWord(Integer id, Words word);

    void deleteWord(Integer id);

}