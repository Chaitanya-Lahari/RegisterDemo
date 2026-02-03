package in.sp.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {
	 @Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse res)
	            throws IOException, ServletException {
	        doPost(req, res);
	    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/JSP", "root", "root");

            /* =======================
               1️⃣ FETCH STUDENTS
               ======================= */
            PreparedStatement ps1 =
                con.prepareStatement("SELECT * FROM students");

            ResultSet rs = ps1.executeQuery();
            req.setAttribute("Result", rs);

            /* =======================
               2️⃣ FETCH FILES
               ======================= */
            PreparedStatement ps2 =
                con.prepareStatement("SELECT student_id, file_name FROM student_files");

            ResultSet frs = ps2.executeQuery();

            // studentId -> list of files
            Map<Integer, List<String>> fileMap = new HashMap<>();

            while (frs.next()) {
                int sid = frs.getInt("student_id");
                String fname = frs.getString("file_name");

                // Create list if not exists
                fileMap.computeIfAbsent(sid, k -> new ArrayList<>())
                       .add(fname);
            }

            // Send map to JSP
            req.setAttribute("fileMap", fileMap);

            // Forward to JSP
            req.getRequestDispatcher("view.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
