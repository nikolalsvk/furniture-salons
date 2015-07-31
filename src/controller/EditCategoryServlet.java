package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FurnitureCategories;
import model.FurnitureCategory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class EditCategoryServlet
 */
public class EditCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FurnitureCategories furnitureCategories;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditCategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getParameter("name");
		furnitureCategories = (FurnitureCategories) getServletContext()
				.getAttribute("furnitureCategories");
		FurnitureCategory itemToSend = furnitureCategories
				.getCategoryByName(name);

		if (furnitureCategories == null || name == null || itemToSend == null) {
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
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String subCategory = request.getParameter("subCategory");
		
		furnitureCategories = (FurnitureCategories) getServletContext()
				.getAttribute("furnitureCategories");
		
		if (furnitureCategories == null || name == null) {
			return;
		}
		
		FurnitureCategory cat = furnitureCategories
				.getCategoryByName(name);
		cat.setName(name);
		cat.setDescription(description);
		cat.setSubCategory(subCategory);
		
		Collection<FurnitureCategory> itemsToSend = furnitureCategories.getValues();

		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(itemsToSend);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(sendItems);
		
		getServletContext().setAttribute("furnitureCategories", furnitureCategories);
	}

}
