package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdditionalService;
import model.AdditionalServices;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class AddAdditionalServiceServlet
 */
public class AddAdditionalServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdditionalServices additionalServices;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddAdditionalServiceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Double price = Double.parseDouble(request.getParameter("price"));

		additionalServices = (AdditionalServices) getServletContext()
				.getAttribute("additionalServices");

		if (additionalServices == null || name == null) {
			return;
		}

		additionalServices.addAdditionalService(new AdditionalService(name,
				description, price));

		Collection<AdditionalService> itemsToSend = additionalServices
				.getValues();

		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(itemsToSend);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(sendItems);

		getServletContext().setAttribute("additionalServices",
				additionalServices);
	}

}
