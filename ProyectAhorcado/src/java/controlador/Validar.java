package controlador;

import config.Conexion;
import modelo.User;
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
        String name = request.getParameter("txtCorreo");
        String pass = request.getParameter("txtPassword");

        User user = validarUsuario(name, pass);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user_code", user.getUser_code());
            session.setAttribute("user_name", user.getUser_name());
            request.getRequestDispatcher("Index/ahorcado.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    } else {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

    private User validarUsuario(String name, String contrasena) {
        User user = null;
        String sql = "SELECT user_code, user_name, user_password " +
                     "FROM Users WHERE user_name = ? AND user_password = ?";

        try (Connection con = new Conexion().Conexion();
             PreparedStatement ps = con != null ? con.prepareStatement(sql) : null) {

            if (ps == null) return null;

            ps.setString(1, name);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setUser_code(rs.getInt("user_code"));
                    user.setUser_name(rs.getString("user_name"));
                    user.setUser_password(rs.getString("user_password"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
