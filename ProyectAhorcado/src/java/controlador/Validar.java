package controlador;

import config.Conexion;
import modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Validar")
public class Validar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if (accion != null && "Ingresar".equalsIgnoreCase(accion)) {
            String nombre = request.getParameter("txtCorreo");
            String pass   = request.getParameter("txtPassword");

            Usuario usuario = validarUsuario(nombre, pass);

            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("codigoUsuario", usuario.getCodigoUsuario());
                session.setAttribute("nombreUsuario", usuario.getNombreUsuario());

                request.getRequestDispatcher("ahorcado.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private Usuario validarUsuario(String nombre, String contrasena) {
        Usuario usuario = null;
        String sql = "SELECT codigoUsuario, nombreUsuario, contraseñaUsuario " +
                     "FROM Usuarios WHERE nombreUsuario = ? AND contraseñaUsuario = ?";


        try (Connection con = new Conexion().Conexion()) {

            if (con == null) {

                System.err.println("Validar: la conexión a la base de datos es nula.");
                return null;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.setString(2, contrasena);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        usuario = new Usuario();
                        usuario.setCodigoUsuario(rs.getInt("codigoUsuario"));
                        usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                        usuario.setContraseñaUsuario(rs.getString("contraseñaUsuario"));
                    }
                }
            }

        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return usuario;
    }
}
