package modelo;

public class Palabra {
    private int codigoPalabra;
    private String textoPalabra;

    public Palabra() {}

    public Palabra(int codigoPalabra, String textoPalabra) {
        this.codigoPalabra = codigoPalabra;
        this.textoPalabra = textoPalabra;
    }

    public int getCodigoPalabra() {
        return codigoPalabra;
    }

    public void setCodigoPalabra(int codigoPalabra) {
        this.codigoPalabra = codigoPalabra;
    }

    public String getTextoPalabra() {
        return textoPalabra;
    }

    public void setTextoPalabra(String textoPalabra) {
        this.textoPalabra = textoPalabra;
    }
}
