package controlador;

import config.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    Conexion cn = new Conexion();
    Connection con;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if ("Login".equals(menu)) {
            if ("Ingresar".equals(accion)) {
                String user = request.getParameter("txtCorreo");
                String password = request.getParameter("txtPassword");

                try {
                    con = cn.Conexion();
                    CallableStatement stmt = con.prepareCall("{call sp_ValidateUser(?,?)}");
                    stmt.setString(1, user);
                    stmt.setString(2, password);

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        HttpSession session = request.getSession();
                        session.setAttribute("usuarioLogueado", user);
                        request.getRequestDispatcher("ahorcado.jsp").forward(request, response);
                    } else {
                        request.setAttribute("error", "Usuario o contraseña incorrectos");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                } catch (SQLException e) {
                    throw new ServletException("Error en validación de usuario", e);
                }

            } else if ("CerrarSesion".equals(accion)) {
                HttpSession session = request.getSession();
                session.invalidate();
                request.getRequestDispatcher("Index/ahorcado.jsp").forward(request, response);
            }
        }

        else if ("Juego".equals(menu)) {
            if ("obtenerPalabra".equals(accion)) {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");

                try (PrintWriter out = response.getWriter()) {
                    con = cn.Conexion();
                    CallableStatement stmt = con.prepareCall("{call sp_RandomWord()}");
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        out.print(rs.getString("word_text"));
                    } else {
                        out.print("ERROR");
                    }
                } catch (SQLException e) {
                    throw new ServletException("Error al obtener palabra", e);
                }
            }
        }

        else {
            request.getRequestDispatcher("Index/ahorcado.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
