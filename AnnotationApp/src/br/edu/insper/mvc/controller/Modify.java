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
 * Servlet implementation class Modify
 */
@WebServlet("/Modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = null;
		String notes = "notes";
		String filter = "";
		boolean loginStatus = request.getSession().getAttribute("loginStatus") != null;
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
		if(loginStatus != false) {
			request.setAttribute("notes", notesList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Modify.jsp");
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
		String[] ids = request.getParameterValues("id");
		String[] notes = request.getParameterValues("note");
		String[] types = request.getParameterValues("type");
		String[] deadlines = request.getParameterValues("deadline");
		boolean loginStatus = true;
		User user = (User) request.getSession().getAttribute("userLogedIn");
		request.setAttribute("loginStatus", loginStatus);
		DAO dao = null;
		try {
			dao = new DAO();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		for (int i = 0; i < ids.length; i++) {
			try {
				dao.atualiza(ids[i], notes[i], types[i], deadlines[i], user.getFirstname());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

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
