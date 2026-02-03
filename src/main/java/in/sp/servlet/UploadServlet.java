package in.sp.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig   // Enables file upload
public class UploadServlet extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse res)
	            throws IOException, ServletException {
	        doPost(req, res);
	    }

    // Folder name where files will be stored
    private static final String UPLOAD_DIR = "files";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        // Get student id from hidden field
        int studentId = Integer.parseInt(req.getParameter("id"));

        // Create absolute upload path inside project
        String uploadPath = getServletContext().getRealPath("/")
                + File.separator + UPLOAD_DIR;

        // Create directory if it doesn't exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/JSP", "root", "root");

            // SQL to store file info
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO student_files(student_id, file_name) VALUES (?, ?)");

            // Loop through all uploaded parts
            for (Part part : req.getParts()) {

                // Process only file inputs
                if (part.getName().equals("file") && part.getSize() > 0) {

                    // Extract file name
                    String fileName = Paths.get(
                            part.getSubmittedFileName()).getFileName().toString();

                    // Save file to server folder
                    part.write(uploadPath + File.separator + fileName);

                    // Save file details to DB
                    ps.setInt(1, studentId);
                    ps.setString(2, fileName);
                    ps.executeUpdate();
                }
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect back to view page
        res.sendRedirect(req.getContextPath() + "/view");
    }
}

