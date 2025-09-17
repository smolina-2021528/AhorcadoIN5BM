package com.sebastianmolina.hangmangame.service;

import com.sebastianmolina.hangmangame.model.Words;
import com.sebastianmolina.hangmangame.repository.WordsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordsServiceImpl implements WordsService {

    private final WordsRepository wordsRepository;

    public WordsServiceImpl(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }

    @Override
    public List<Words> getAllWords() {
        return wordsRepository.findAll();
    }

    @Override
    public Words getWordById(Integer id) {
        return wordsRepository.findById(id).orElse(null);
    }

    @Override
    public Words saveWord(Words word) {
        // Validación de unicidad antes de persistir
        if (wordsRepository.existsByWordText(word.getWordText())) {
            String msg = "Esta palabra ya existe man";
            System.out.println("Error:  " + msg);
            throw new IllegalArgumentException(msg);
        }
        return wordsRepository.save(word);
    }

    @Override
    public Words updateWord(Integer id, Words word) {
        Words existingWord = wordsRepository.findById(id).orElse(null);
        if (existingWord != null) {
            // Validación de unicidad que excluye el registro actual
            if (wordsRepository.existsByWordTextAndWordCodeNot(word.getWordText(), id)) {
                String msg = "La palabra ya existe con otro id";
                System.out.println("Error: " + msg);
                throw new IllegalArgumentException(msg);
            }

            existingWord.setWordText(word.getWordText());
            return wordsRepository.save(existingWord);
        }
        return null;
    }

    @Override
    public void deleteWord(Integer id) {
        wordsRepository.deleteById(id);
    }
}