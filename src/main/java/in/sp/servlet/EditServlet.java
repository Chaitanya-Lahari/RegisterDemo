package in.sp.servlet;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            // 🔴 THIS LINE WAS MISSING EARLIER
            int id = Integer.parseInt(req.getParameter("id"));

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/JSP", "root", "root");

            PreparedStatement ps =
                    con.prepareStatement("select * from students where S_No=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                req.setAttribute("rs", rs);
                RequestDispatcher rd = req.getRequestDispatcher("edit.jsp");
                rd.forward(req, res);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
