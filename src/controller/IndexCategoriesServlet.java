package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FurnitureCategories;

/**
 * Servlet zaduzen za prikaz svih kategorija namestaja
 */
public class IndexCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FurnitureCategories furnitureCategories;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexCategoriesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (IndexSalonServlet.checkIfLoggedIn(request, response)) {
			response.sendRedirect("index_categories.jsp");
		} else {
			response.sendRedirect("IndexServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		furnitureCategories = (FurnitureCategories) getServletContext().getAttribute("furnitureCategories");
		furnitureCategories.saveFurnitureCategories(getServletContext().getRealPath(""));
	}

}
