package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Salon;
import model.Salons;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class GetSalonsIndexServlet
 */
public class GetSalonsIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Salons salons;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetSalonsIndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("");
		if (context.getAttribute("salons") == null) {
			salons = new Salons(path);
			context.setAttribute("salons", salons);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		salons = (Salons) getServletContext().getAttribute("salons");
		Collection<Salon> itemsToSend = salons.getValues();

		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(itemsToSend);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(sendItems);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
