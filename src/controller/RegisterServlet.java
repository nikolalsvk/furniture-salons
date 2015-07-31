package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import model.Users;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String role = request.getParameter("role");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		Boolean registered = false;

		Users users = (Users) getServletContext().getAttribute("users");
		if (users == null) {
			return;
		}

		if (users.getUser(username) != null) {
			registered = false;
		} else {
			users.addUser(new User(username, password, name, lastName, role,
					phoneNumber, email));
			users.saveUsers(getServletContext().getRealPath(""));
			registered = true;
			getServletContext().setAttribute("users", users);
		}

		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(registered);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(sendItems);
	}

}
