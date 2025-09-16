package controlador;

import config.Conexion;
import modelo.Usuario;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "Validar", urlPatterns = {"/Validar"})
public class Validar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if ("Ingresar".equalsIgnoreCase(accion)) {
            String nombre = request.getParameter("txtCorreo");
            String pass   = request.getParameter("txtPassword");

            Usuario usuario = validarUsuario(nombre, pass);

            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("codigo_usuario", usuario.getCodigo_usuario());
                session.setAttribute("nombre_usuario", usuario.getNombre_usuario());
    request.getRequestDispatcher("Index/ahorcado.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("Index/ahorcado.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("Index/ahorcado.jsp").forward(request, response);
        }
    }

    private Usuario validarUsuario(String nombre, String contrasena) {
        Usuario usuario = null;
        String sql = "SELECT codigo_usuario, nombre_usuario, contraseña_usuario " +
                     "FROM Usuarios WHERE nombre_usuario = ? AND contraseña_usuario = ?";

        try (Connection con = new Conexion().Conexion();
             PreparedStatement ps = con != null ? con.prepareStatement(sql) : null) {

            if (ps == null) return null;

            ps.setString(1, nombre);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setCodigo_usuario(rs.getInt("codigo_usuario"));
                    usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                    usuario.setContraseña_usuario(rs.getString("contraseña_usuario"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
