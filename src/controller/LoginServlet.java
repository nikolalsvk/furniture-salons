package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.ShoppingCart;
import model.User;
import model.Users;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Users users;
	private ShoppingCart shoppingCart;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		users = (Users) getServletContext().getAttribute("users");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username == null || password == null || users == null) {
			return;
		}
		PrintWriter out = response.getWriter();
		User user = users.getUser(username);
		if (user != null && user.getPassword().equals(password)
				&& !user.getRole().equals("admin")) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("login", true);

			if (user.getRole().equals("user")) {
				shoppingCart = new ShoppingCart();
				session.setAttribute("shoppingCart", shoppingCart);
			}
		} else {
			String redirect = "IndexServlet";

			out.write(redirect);
			return;
		}

		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(user);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(sendItems);
	}
}
