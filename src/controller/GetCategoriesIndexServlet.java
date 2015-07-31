package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FurnitureCategories;
import model.FurnitureCategory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class GetCategoriesIndexServlet
 */
public class GetCategoriesIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FurnitureCategories furnitureCategories;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCategoriesIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("");
		if(context.getAttribute("furnitureCategories") == null) {
			furnitureCategories = new FurnitureCategories(path);
			context.setAttribute("furnitureCategories", furnitureCategories);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String opis = request.getParameter("description");
		furnitureCategories = (FurnitureCategories) getServletContext().getAttribute("furnitureCategories");
		
		ArrayList<FurnitureCategory> itemsToSend = furnitureCategories.getFurnitureCategoriesForSearch(name, opis);

		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(itemsToSend);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(sendItems);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
