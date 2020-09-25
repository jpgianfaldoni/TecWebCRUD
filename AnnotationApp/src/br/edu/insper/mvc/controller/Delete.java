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

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("Delete.jsp");
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
		String[] checkboxValues = request.getParameterValues("checkbox");
		DAO dao = null;
		boolean loginStatus = true;
		request.setAttribute("loginStatus", loginStatus);
		try {
			dao = new DAO();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		for(String i: checkboxValues) {
			String strNew = i.replace("/", "");
			try {
				dao.deleta(strNew);
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

	

