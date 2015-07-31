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
 * Servlet implementation class DeleteCategoryServlet
 */
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FurnitureCategories furnitureCategories;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("id");
		
		furnitureCategories = (FurnitureCategories) getServletContext().getAttribute(
				"furnitureCategories");
		if (furnitureCategories == null || name == null) {
			return;
		}

		furnitureCategories.deleteCategory(name);

		Collection<FurnitureCategory> itemsToSend = furnitureCategories.getValues();
		if (itemsToSend == null) {
			return;
		}
		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(itemsToSend);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(sendItems);

		getServletContext().setAttribute("furnitureCategories", furnitureCategories);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
