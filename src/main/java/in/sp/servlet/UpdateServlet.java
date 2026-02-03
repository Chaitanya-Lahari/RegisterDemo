package in.sp.servlet;
import java.io.IOException;

import java.io.PrintWriter;
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
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
  
        // 🔴 NEVER print before redirect
        int id = Integer.parseInt(req.getParameter("id"));

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("pass");
        String gender = req.getParameter("gender");
        String city = req.getParameter("city");

        String[] subjectsArr = req.getParameterValues("subjects");
        String subjects = "";
        if (subjectsArr != null) {
            subjects = String.join(",", subjectsArr);
        }

        String hobies = req.getParameter("hobies");
        String address = req.getParameter("address");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/JSP", "root", "root");

            PreparedStatement ps = con.prepareStatement(
                "update students set Name=?, Email=?, Password=?, Gender=?, City=?, Subjects=?, Hobies=?, Address=? where S_No=?");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, gender);
            ps.setString(5, city);
            ps.setString(6, subjects);
            ps.setString(7, hobies);
            ps.setString(8, address);
            ps.setInt(9, id);

            ps.executeUpdate();

            // ✅ GO BACK TO VIEW PAGE
            res.sendRedirect(req.getContextPath() + "/view");

           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
