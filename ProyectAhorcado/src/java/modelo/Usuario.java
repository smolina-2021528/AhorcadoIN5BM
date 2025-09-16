package modelo;

public class Usuario {
    private int codigo_usuario;
    private String nombre_usuario;
    private String contraseña_usuario;

    public Usuario() {}

    public Usuario(int codigoUsuario, String nombreUsuario, String contraseñaUsuario) {
        this.codigo_usuario = codigoUsuario;
        this.nombre_usuario = nombreUsuario;
        this.contraseña_usuario = contraseñaUsuario;
    }

    public int getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(int codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContraseña_usuario() {
        return contraseña_usuario;
    }

    public void setContraseña_usuario(String contraseña_usuario) {
        this.contraseña_usuario = contraseña_usuario;
    }

   
}

