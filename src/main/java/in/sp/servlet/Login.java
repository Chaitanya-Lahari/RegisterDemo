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
import jakarta.servlet.http.HttpSession;
@WebServlet("/loginForm")
public class Login extends HttpServlet {
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String name = req.getParameter("name");
        String password = req.getParameter("pass");
       
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "root");
        	PreparedStatement ps=con.prepareStatement("select *from customers where name=? and password=?");
        	ps.setString(1, name);
        	ps.setString(2, password);                                                       
        	ResultSet rs=ps.executeQuery();
        	
        	if(rs.next()) {
        		 HttpSession session=req.getSession();
              	session.setAttribute("gender_key", rs.getString("gender"));
        		out.println("<H3 style='color:'red'>login succefully</h3>");
        		RequestDispatcher rd=req.getRequestDispatcher("/Profile.jsp");
        		rd.include(req, res);
        	}
        	else {
        		res.setContentType("text/html");
        		out.println("<H3 style='color:'red'>user not login succefully</h3>");
        		RequestDispatcher rd= req.getRequestDispatcher("/Login.jsp");
        		rd.include(req, res);       		
        	}
        					
        }
        catch(Exception e) {
        	e.printStackTrace();
        	res.setContentType("text/html");
        	out.println("<H3 style='color:'red'>"+e.getMessage()+"</h3>");
    		RequestDispatcher rd= req.getRequestDispatcher("/Login.jsp");
    		rd.include(req, res);       		
        }
        

}
}
