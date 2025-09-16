package modelo;

public class Palabra {
    private int codigo_Palabra;
    private String texto_Palabra;

    public Palabra() {}

    public Palabra(int codigoPalabra, String textoPalabra) {
        this.codigo_Palabra = codigoPalabra;
        this.texto_Palabra = textoPalabra;
    }

    public int getCodigo_Palabra() {
        return codigo_Palabra;
    }

    public void setCodigo_Palabra(int codigo_Palabra) {
        this.codigo_Palabra = codigo_Palabra;
    }

    public String getTexto_Palabra() {
        return texto_Palabra;
    }

    public void setTexto_Palabra(String texto_Palabra) {
        this.texto_Palabra = texto_Palabra;
    }


}
