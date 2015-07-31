package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.FurnitureCategories;
import model.FurnitureCategory;

/**
 * Servlet implementation class AddCategoryServlet
 */
public class AddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FurnitureCategories furnitureCategories;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String subCategory = request.getParameter("subCategory");
		
		furnitureCategories = (FurnitureCategories) getServletContext()
				.getAttribute("furnitureCategories");
		
		if (furnitureCategories == null || name == null) {
			return;
		}
		
		furnitureCategories.addFurnitureCategory(new FurnitureCategory(name, description, subCategory));
		
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
