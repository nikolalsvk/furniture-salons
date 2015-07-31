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

import model.AdditionalService;
import model.AdditionalServices;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class GetAdditionalServicesIndexServlet
 */
public class GetAdditionalServicesIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdditionalServices additionalServices;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAdditionalServicesIndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("");
		if(context.getAttribute("additionalServices") == null) {
			additionalServices = new AdditionalServices(path);
			context.setAttribute("additionalServices", additionalServices);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		additionalServices = (AdditionalServices) getServletContext()
				.getAttribute("additionalServices");
		Collection<AdditionalService> itemsToSend = additionalServices.getValues();

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
