package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ActionSales;
import model.AdditionalServices;
import model.FurnitureCategories;
import model.FurnitureItems;
import model.Receipts;
import model.Salons;
import model.Users;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
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
		Users users = new Users(path);
		context.setAttribute("users", users);
		
		FurnitureCategories furnitureCategories = null;
		if (context.getAttribute("furnitureCategories") == null) {
			furnitureCategories = new FurnitureCategories(path);
			context.setAttribute("furnitureCategories", furnitureCategories);
		}

		FurnitureItems furnitureItems = null;
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
			Salons salons = new Salons(path);
			context.setAttribute("salons", salons);
		}

		if (context.getAttribute("additionalServices") == null) {
			Object additionalServices = new AdditionalServices(path);
			context.setAttribute("additionalServices", additionalServices);
		}

		if (context.getAttribute("receipts") == null) {
			Receipts receipts = null;
			try {
				receipts = new Receipts(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
//			ArrayList<Receipt> list = receipts.getReceipts();
//			for (Receipt receipt : list) {
//				receipt.setDateUsual("00:00:00 01/07/2015");
//			}
			context.setAttribute("receipts", receipts);
		}

		if (context.getAttribute("actionSales") == null) {
			ActionSales actionSales = null;
			try {
				actionSales = new ActionSales(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();	
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// setovanje popusta
//			ArrayList<ActionSale> list = actionSales.getActionSales();
//			for (ActionSale sale : list) {
//				if (sale.isCurrent()) {
//					if (sale.getEntity().equals("komad")) {
//						FurnitureItem item = furnitureItems
//								.getFurnitureItem(sale.getEntityOnSale());
//						item.setDiscount(sale.getDiscount());
//						item.setDiscounted(true);
//					} else {
//						ArrayList<FurnitureItem> list_items = furnitureItems
//								.getFurnitureItemsFromSalon("", "", "", "", "", "", "",
//										sale.getSalon(), sale.getEntityOnSale());
//
//						for (FurnitureItem furnitureItem : list_items) {
//							furnitureItem.setDiscount(sale.getDiscount());
//							furnitureItem.setDiscounted(true);
//						}
//
//						furnitureCategories.getCategoryByName(sale.getEntityOnSale())
//								.setDiscount(sale.getDiscount());
//					}
//				}
//			}
			context.setAttribute("actionSales", actionSales);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
