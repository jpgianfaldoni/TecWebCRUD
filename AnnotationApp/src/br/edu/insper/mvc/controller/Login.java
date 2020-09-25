package br.edu.insper.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.mvc.model.DAO;
import br.edu.insper.mvc.model.User;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = null;
		User user =  new User();
		boolean loginStatus = false;
		try {
			 dao = new DAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] loginInfo = request.getParameterValues("login");
		user.setFirstname(loginInfo[0]);
		user.setPassword(loginInfo[1]);
		if(loginInfo[2].equals("Sign up")) {
			try {
				dao.adicionaLogin(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		request.getSession().setAttribute("loginStatus", loginStatus);
		request.getSession().setAttribute("userLogedIn", user);
		request.getRequestDispatcher("Visualize").forward(request, response);
		}else {
			try {
				if(dao.valida(loginInfo[0], loginInfo[1]) == null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("LoginInvalido.jsp");
					dispatcher.forward(request, response);
				}else {
					loginStatus = true;
					request.getSession().setAttribute("loginStatus", loginStatus);
					request.getSession().setAttribute("userLogedIn", user);
					request.getRequestDispatcher("Visualize").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
