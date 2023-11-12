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


@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	private static final String query = "SELECT * FROM bookdata;";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = resp.getWriter();
		
		resp.setContentType("text/html");
		
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
		cnf.printStackTrace();
	}
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "lahcenadmin");){
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			String bookListStatic1 = """
					<!DOCTYPE html>
						<html data-bs-theme="light" lang="en">
						
						<head>
						  <meta charset="utf-8" />
						  <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
						  <title>Book List - Book Registration Web App</title>
						  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css" />
						  <link rel="stylesheet"
						    href="https://fonts.googleapis.com/css?family=Inter:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&amp;display=swap" />
						  <link rel="stylesheet" href="assets/css/Table-With-Search-search-table.css" />
						  <link rel="shortcut icon" href="https://img.icons8.com/fluency/48/course-assign.png" type="image/png" />
						
						  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
						</head>
						
						<body>
						  <nav class="navbar navbar-expand-md sticky-top navbar-shrink py-3 navbar-light" id="mainNav">
						    <div class="container">
						      <a class="navbar-brand d-flex align-items-center" href="home.html">
						
						        <img src="https://img.icons8.com/fluency/48/course-assign.png" alt="icon">
						
						        <span>Book List</span></a>
						
						      <button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1">
						        <span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span>
						      </button>
						
						      <div class="collapse navbar-collapse" id="navcol-1">
						        <ul class="navbar-nav mx-auto">
						          <li class="nav-item">
						            <a class="nav-link" href="home.html">Home</a>
						          </li>
						          <li class="nav-item"></li>
						          <li class="nav-item">
						            <a class="nav-link active" href="bookList">Book List</a>
						          </li>
						        </ul>
						
						      </div>
						    </div>
						  </nav>
						  <header class="bg-primary-gradient">
						    <div class="container pt-4">
						      <div class="row ">
						        <div class="col-md-8 col-lg-8 col-xl-6 text-center text-md-start mx-auto">
						          <div class="text-center">
						            <h1 class="fw-bold">Book Registration Web App</h1>
						          </div>
						        </div>
						      </div>
						    </div>
						  </header>
						  <section class="py-5">
						    <div class="container">
						      <div>
						        <div>
						          <table class="table table-hover table-bordered table-responsive">
						            <thead>
						              <tr>
						                <th id="trs-hd-1" class="text-center align-middle">Book Id</th>
						                <th id="trs-hd-2" class="text-center align-middle">Book Name</th>
						                <th id="trs-hd-3" class="text-center align-middle">Book Edition</th>
						                <th id="trs-hd-4" class="text-center align-middle">Book Price</th>
						                <th id="trs-hd-5" class="text-center align-middle">Edit</th>
						                <th id="trs-hd-6" class="text-center align-middle">Delete</th>
						              </tr>
						            </thead>
						            <tbody>
						            """;
			
			pw.println(bookListStatic1);
			
			while(rs.next()) {
				pw.println("<tr class='text-start'><td class='text-center align-middle'>"+ rs.getInt(1) +"</td><td class='text-center align-middle'>"+rs.getString(2)+"</td><td class='text-center align-middle'>"+rs.getString(3)+"</td><td class='text-center align-middle'>"+rs.getFloat(4)+"</td><td class='text-center align-middle'><a class='btn btn-success btn-sm text-start' href='editScreen?id="+rs.getInt(1)+"'><i class='bi bi-pencil-fill'></i></a></td><td class='text-center align-middle'><a class='btn btn-danger btn-sm' href='deleteurl?id="+rs.getInt(1)+"'><i class='bi bi-trash-fill'></i></a></td></tr>");
			}
			
			String bookListStatic2 = """
					</tbody>
				          </table>
				        </div>
				      </div>
				    </div>
				  </section>
				</body>
				
				</html>
					""";
			pw.println(bookListStatic2);
			
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
