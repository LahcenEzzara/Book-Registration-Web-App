package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editScreen")
public class EditScreen extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final String query = "SELECT * FROM bookdata WHERE id = ?;";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = resp.getWriter();
		
		// Set content type
		resp.setContentType("text/html");
		
		// Get the id of record
		int id = Integer.parseInt(req.getParameter("id"));
		
		//Load the JDBC Driver
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
		cnf.printStackTrace();
	}
		// Generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "lahcenadmin");){
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			pw.println("""
					<!DOCTYPE html>
<html data-bs-theme='light' lang='en'>

<head>
  <meta charset='utf-8' />
  <meta name='viewport' content='width=device-width, initial-scale=1.0, shrink-to-fit=no' />
  <title>Edit Record - Book Registration Web App</title>
  <link rel='stylesheet' href='assets/bootstrap/css/bootstrap.min.css' />
  <link rel='stylesheet'
    href='https://fonts.googleapis.com/css?family=Inter:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&amp;display=swap' />
  <link rel='stylesheet' href='assets/css/Table-With-Search-search-table.css' />
  <link rel='shortcut icon' href='https://img.icons8.com/fluency/48/course-assign.png' type='image/png' />

  <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css'>
</head>

<body>
  <nav class='navbar navbar-expand-md sticky-top navbar-shrink py-3 navbar-light' id='mainNav'>
    <div class='container'>
      <a class='navbar-brand d-flex align-items-center' href='home.html'>

        <img src='https://img.icons8.com/fluency/48/course-assign.png' alt='icon'>

        <span>Book Registration</span></a>

      <button data-bs-toggle='collapse' class='navbar-toggler' data-bs-target='#navcol-1'>
        <span class='visually-hidden'>Toggle navigation</span><span class='navbar-toggler-icon'></span>
      </button>

      <div class='collapse navbar-collapse' id='navcol-1'>
        <ul class='navbar-nav mx-auto'>
          <li class='nav-item'>
            <a class='nav-link' href='home.html'>Home</a>
          </li>
          <li class='nav-item'></li>
          <li class='nav-item'>
            <a class='nav-link' href='bookList'>Book List</a>
          </li>
        </ul>

      </div>
    </div>
  </nav>
  <header class='bg-primary-gradient'>
    <div class='container pt-4'>
      <div class='row '>
        <div class='col-md-8 col-lg-8 col-xl-6 text-center text-md-start mx-auto'>
          <div class='text-center'>
            <h1 class='fw-bold'>Edit Record</h1>
          </div>
        </div>
      </div>
    </div>
  </header>
  <section class='py-5'>
    <div class='container profile profile-view' id='profile'>

      <form action='editurl?id=
					""");
			
			pw.println(id);
			
			pw.println("""
					' method='post'>

        <div class='row profile-row'>
          <div class='col-md-8 col-lg-8 offset-lg-2 text-start'>
            <div class='form-group mb-3'>
              <div class='col-sm-12 col-md-6'>
                <div class='form-group mb-3'>
                  <label class='form-label'><strong>Book Name</strong></label><input class='form-control' type='text'
                    name='bookName' value='
					""");
			
			pw.println(rs.getString(2));
			
			pw.println("""
					' required />
                </div>
              </div>
              <div class='form-group mb-3'>
                <div class='col-sm-12 col-md-6'>
                  <div class='form-group mb-3'>
                    <label class='form-label'><strong>Book Edition</strong></label><input class='form-control'
                      type='text' name='bookEdition' value='
					""");
			
			pw.println(rs.getString(3));
			
			pw.println("""
					' required />
                  </div>
                </div>
              </div>
              <div class='form-group mb-3'>
                <div class='col-sm-12 col-md-6'>
                  <div class='form-group mb-3'>
                    <label class='form-label'><strong>Book Price</strong></label><input class='form-control' type='text'
                      name='bookPrice' value='
					""");
			
			pw.println(rs.getString(4));
			
			pw.println("""
					' required />
                  </div>
                </div>
              </div>
            </div>
            <div class='row'>
              <div class='col-md-12 col-lg-6 offset-lg-0 content-right'>

                <button class='btn btn-primary form-btn' type='submit' style='margin-right: 20px'>EDIT</button>

                <button class='btn btn-danger form-btn' type='reset'>CANCEL</button>

              </div>
            </div>
          </div>
      </form>
    </div>
  </section>
</body>
</html>""");
			
					

		}catch(SQLException se){
			se.printStackTrace();
			pw.println("<h2>"+se.getMessage()+"</h2>");
		
		} catch(Exception e) {
			e.printStackTrace();
			pw.println("<h2>"+e.getMessage()+"</h2>");

		}
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
