package br.edu.insper.mvc.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.mvc.model.DAO;
import br.edu.insper.mvc.model.Note;
import br.edu.insper.mvc.model.User;


/**
 * Servlet implementation class Visualize
 */
@WebServlet("/Visualize")
public class Visualize extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Visualize() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String notes = request.getParameter("ordenacao");
		DAO dao = null;
		boolean loginStatus = request.getSession().getAttribute("loginStatus") != null;
		String loginStatus2 = request.getParameter("ordenacao");
		String filter = request.getParameter("filter");
		User user = (User) request.getSession().getAttribute("userLogedIn");
		if(filter == null) {
			filter = "";
		}
		if(loginStatus != false || loginStatus2 != null) {
			try {
				dao = new DAO();
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			List<Note> notesList = null;
			try {
				notesList = dao.getLista(notes, filter);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			request.setAttribute("notes", notesList);
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Visualize.jsp");
			dispatcher.forward(request, response);
			try {
				dao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("AcessoNegado.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
