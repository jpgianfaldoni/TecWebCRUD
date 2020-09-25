package br.edu.insper.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.mvc.model.DAO;
import br.edu.insper.mvc.model.Note;
/**
 * Servlet implementation class Create
 */
@WebServlet("/Create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Create() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean loginStatus = request.getSession().getAttribute("loginStatus") != null;
		String username = request.getParameter("username");
		request.setAttribute("username", username);
		if(loginStatus != false) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Create.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("AcessoNegado.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = null;
		boolean loginStatus = true;
		request.setAttribute("loginStatus", loginStatus);
		try {
			dao = new DAO();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Note note = new Note();
		String creationString = request.getParameter("creation");
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		String username = request.getParameter("username");
		java.sql.Date creationDate = null;
		try {
			creationDate = new java.sql.Date(fmt.parse(creationString).getTime());
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String deadlineString = request.getParameter("deadline");
		java.sql.Date deadlineDate = null;
		try {
			deadlineDate = new java.sql.Date(fmt.parse(deadlineString).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		note.setNotes(request.getParameter("note"));
		note.setType(request.getParameter("type"));
		note.setCreation(creationDate);
		note.setDeadline(deadlineDate);
		note.setUserid(username);
		
		
		try {
			dao.adiciona(note);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("Visualize").forward(request, response);
	}

}
