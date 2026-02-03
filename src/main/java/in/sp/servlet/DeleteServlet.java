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
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse res)
	            throws IOException, ServletException {
	        doPost(req, res);
	    }

	
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
                PrintWriter out=res.getWriter();
                out.println("delete page");
	        try {
	        
	            int id = Integer.parseInt(req.getParameter("id"));

	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/JSP", "root", "root");

	            PreparedStatement ps =
	                    con.prepareStatement("delete from students where S_No=?");
	            ps.setInt(1, id);

	            ps.executeUpdate();
	            res.sendRedirect(req.getContextPath() + "/view");
         }catch(Exception e) {
	e.printStackTrace();
    }
	    }
}
