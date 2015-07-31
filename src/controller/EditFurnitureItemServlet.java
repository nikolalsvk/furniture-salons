package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FurnitureItem;
import model.FurnitureItems;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class EditFurnitureItemServlet
 */
public class EditFurnitureItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FurnitureItems furnitureItems;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditFurnitureItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		furnitureItems = (FurnitureItems) getServletContext().getAttribute(
				"furnitureItems");
		FurnitureItem itemToSend = furnitureItems.getFurnitureItem(id);

		if (furnitureItems == null || id == null || itemToSend == null) {
			return;
		}

		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(itemToSend);
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
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String originCountry = request.getParameter("originCountry");
		String manufacturerName = request.getParameter("manufacturerName");
		Double price = Double.parseDouble(request.getParameter("price"));
		Integer inStock = Integer.parseInt(request.getParameter("inStock"));
		String category = request.getParameter("category");
		Integer yearOfProduction = Integer.parseInt(request
				.getParameter("yearOfProduction"));
		String salon = request.getParameter("salon");
		String image = request.getParameter("imagedata");

		furnitureItems = (FurnitureItems) getServletContext().getAttribute(
				"furnitureItems");

		if (id == null || furnitureItems == null) {
			response.sendRedirect("AddFurnitureItemServlet");
			return;
		}

		FurnitureItem item = furnitureItems.getFurnitureItem(id);
		item.setName(name);
		item.setColor(color);
		item.setOriginCountry(originCountry);
		item.setManufacturerName(manufacturerName);
		item.setPrice(price);
		item.setInStock(inStock);
		item.setCategory(category);
		item.setYearOfProduction(yearOfProduction);
		item.setSalon(salon);
		item.setImage(image);
		
		// slanje svih namestaja da bi se iscrtala tabela ponovo
		Collection<FurnitureItem> itemsToSend = furnitureItems.getValues();
		if (itemsToSend == null) {
			return;
		}
		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(itemsToSend);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(sendItems);

		getServletContext().setAttribute("furnitureItems", furnitureItems);
	}

}
