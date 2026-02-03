







package in.sp.servlet;

import java.io.IOException;



import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.Connection;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regForm")
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
       
        String Name = req.getParameter("name");
        String Email=req.getParameter("email");
        String Password = req.getParameter("pass");
        String Gender = req.getParameter("gender");
        String City = req.getParameter("city");
        String[] Subjects = req.getParameterValues("subjects");
        String subjectStr="";
        if(Subjects!=null) {
        	subjectStr=String.join(",", Subjects);
        }
        String Hobies = req.getParameter("hobies");
        String Address= req.getParameter("address");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/JSP", "root", "root");

            PreparedStatement ps = con.prepareStatement("insert into students(Name,Email,Password,Gender,City,Subjects,Hobies,Address) values(?,?,?,?,?,?,?,?)");

            ps.setString(1, Name);
            ps.setString(2,Email);
            ps.setString(3, Password);
            ps.setString(4, Gender);
            ps.setString(5, City);
            ps.setString(6, subjectStr);
            ps.setString(7, Hobies);
            ps.setString(8, Address);
       

            int count = ps.executeUpdate();

            if (count > 0) {
                out.print("<h3 style='color:green'>Registered Successfully</h3>");
            } else {
                out.print("<h3 style='color:red'>Registration Failed</h3>");
            }

            RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
            rd.include(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            res.setContentType("text/html");
            out.print("<h3 style='color:red'>"+e.getMessage()+"</h3>");
            RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
            rd.include(req, res);
        }
    }
}
