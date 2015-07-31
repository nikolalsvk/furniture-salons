package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FurnitureItem;
import model.FurnitureItems;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class GetFurnitureIndexServlet
 */
public class GetFurnitureIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetFurnitureIndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FurnitureItems furnitureItems = (FurnitureItems) getServletContext()
				.getAttribute("furnitureItems");

		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String originCountry = request.getParameter("originCountry");
		String manufacturerName = request.getParameter("manufacturerName");
		String price = request.getParameter("price");
		String inStock = request.getParameter("inStock");
		String category = request.getParameter("category");
		String yearOfProduction = request.getParameter("yearOfProduction");
		String salonName = request.getParameter("salon");
		
		if (salonName.equals("default"))
			salonName = "";
		if (category.equals("default"))
			category = "";
		ArrayList<FurnitureItem> itemsToSend = furnitureItems
				.getFurnitureItemsFromSalon(name, color, originCountry,
						manufacturerName, price, inStock, yearOfProduction,
						salonName, category);

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
