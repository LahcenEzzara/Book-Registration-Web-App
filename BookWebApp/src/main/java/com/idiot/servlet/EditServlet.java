package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final String query = "UPDATE bookdata SET bookname = ?, bookedition = ?, bookprice = ? WHERE id = ?;";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = resp.getWriter();
		
		// Set content type
		resp.setContentType("text/html");
		
		// Get the id of record
		int id = Integer.parseInt(req.getParameter("id"));
		
		// Get the edit data that we want to edit
		String bookName = req.getParameter("bookName");
		String bookEdition = req.getParameter("bookEdition");
		float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
		
		//Load the JDBC Driver
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
		cnf.printStackTrace();
	}
		// Generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "lahcenadmin");){
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			
			ps.setInt(4, id);
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				pw.println("""
						<!DOCTYPE html>
<html data-bs-theme='light' lang='en'>

<head>
  <meta charset='utf-8' />
  <meta name='viewport' content='width=device-width, initial-scale=1.0, shrink-to-fit=no' />
  <title>Record has been edtited successfully ! - Book Registration Web App</title>
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

  <section class='py-5'>
    <div class='container'>

      <div class='alert alert-success d-flex align-items-center' role='alert'>

        <div>
          Record has been edtited successfully !
        </div>
      </div>

    </div>
  </section>
</body>

</html>
						""");
			}else {
				pw.println("""
						<!DOCTYPE html>
<html data-bs-theme='light' lang='en'>

<head>
  <meta charset='utf-8' />
  <meta name='viewport' content='width=device-width, initial-scale=1.0, shrink-to-fit=no' />
  <title>Record is not edtited ! - Book Registration Web App</title>
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

  <section class='py-5'>
    <div class='container'>

      <div class='alert alert-danger d-flex align-items-center' role='alert'>

        <div>
          Record is not edtited !
        </div>
      </div>

    </div>
  </section>
</body>

</html>
						""");
			}

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
