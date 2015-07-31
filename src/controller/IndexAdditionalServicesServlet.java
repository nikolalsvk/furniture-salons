package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdditionalServices;

/**
 * Servlet implementation class IndexAdditionalServicesServlet
 */
public class IndexAdditionalServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdditionalServices additionalServices;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexAdditionalServicesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (IndexSalonServlet.checkIfLoggedIn(request, response)) {
			response.sendRedirect("index_additional_services.jsp");
		} else {
			response.sendRedirect("IndexServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		additionalServices = (AdditionalServices) getServletContext().getAttribute("additionalServices");
		additionalServices.saveFurnitureCategories(getServletContext().getRealPath(""));
	}

}
