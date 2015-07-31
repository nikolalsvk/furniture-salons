package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdditionalServices;
import model.FurnitureCategories;
import model.FurnitureItems;
import model.Salons;

/**
 * Servlet implementation class IndexSalonServlet
 */
public class IndexSalonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Salons salons;
	private FurnitureItems furnitureItems;
	private FurnitureCategories furnitureCategories;
	private AdditionalServices additionalServices;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexSalonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		ServletContext context = getServletContext();
		String path = context.getRealPath("");
		if (context.getAttribute("furnitureCategories") == null) {
			furnitureCategories = new FurnitureCategories(path);
			context.setAttribute("furnitureCategories", furnitureCategories);
		}

		if (context.getAttribute("furnitureItems") == null) {
			try {
				furnitureItems = new FurnitureItems(path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			context.setAttribute("furnitureItems", furnitureItems);
		}

		if (context.getAttribute("salons") == null) {
			salons = new Salons(path);
			context.setAttribute("salons", salons);
		}

		if (context.getAttribute("additionalServices") == null) {
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
		if (checkIfLoggedIn(request, response)) {
			response.sendRedirect("index_salon.jsp");
		} else {
			response.sendRedirect("IndexServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) Cuvanje namestaja u fajl
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		furnitureItems = (FurnitureItems) getServletContext().getAttribute(
				"furnitureItems");
		furnitureItems.saveFurnitureItems(getServletContext().getRealPath(""));
	}

	public static boolean checkIfLoggedIn(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			return false;
		}

		return true;
	}
}
