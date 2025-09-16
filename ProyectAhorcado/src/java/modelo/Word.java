package modelo;
/**
 *
 * @author informatica
 */
public class Word {
    private int word_code;
    private String word_text;

    public Word() {}

    public Word(int wordCode, String wordText) {
        this.word_code = wordCode;
        this.word_text = wordText;
    }

    public int getWord_code() {
        return word_code;
    }

    public void setWord_code(int word_code) {
        this.word_code = word_code;
    }

    public String getWord_text() {
        return word_text;
    }

    public void setWord_text(String word_text) {
        this.word_text = word_text;
    }

 


}