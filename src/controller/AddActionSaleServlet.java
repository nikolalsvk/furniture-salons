package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.ActionSale;
import model.ActionSales;
import model.FurnitureCategories;
import model.FurnitureItem;
import model.FurnitureItems;

/**
 * Servlet implementation class AddActionSaleServlet
 */
public class AddActionSaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddActionSaleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String salon = request.getParameter("salon");
		String entityOnSale = request.getParameter("entityOnSale");
		String entity = request.getParameter("entity");
		Double discount = Double.parseDouble(request.getParameter("discount"));
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		ServletContext context = getServletContext();

		ActionSales actionSales = (ActionSales) context.getAttribute("actionSales");
		FurnitureItems furnitureItems = (FurnitureItems) context
				.getAttribute("furnitureItems");
		FurnitureCategories furnitureCategories = (FurnitureCategories) context
				.getAttribute("furnitureCategories");
		
		ArrayList<ActionSale> listActions = actionSales.getActionSales();
		for (ActionSale actionSale : listActions) {
			if(actionSale.getSalon().equals(salon) && actionSale.getEntity().equals(entity)
					&& actionSale.getEntityOnSale().equals(entityOnSale)) {
				listActions.remove(actionSale);
				break;
			}
		}

		// dodavanje nove akcije
		ActionSale sale = null;
		try {
			sale = new ActionSale(salon, entityOnSale, entity, discount, startDate,
					endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		actionSales.addActionSale(sale);
		if (sale.isCurrent()) {
			if (entity.equals("komad")) {
				FurnitureItem item = furnitureItems
						.getFurnitureItem(entityOnSale);
				item.setDiscount(discount);
				item.setDiscounted(true);
			} else {
				ArrayList<FurnitureItem> list = furnitureItems
						.getFurnitureItemsFromSalon("", "", "", "", "", "", "",
								salon, entityOnSale);

				for (FurnitureItem furnitureItem : list) {
					furnitureItem.setDiscount(discount);
					furnitureItem.setDiscounted(true);
				}

				furnitureCategories.getCategoryByName(entityOnSale)
						.setDiscount(discount);
			}
		}
		actionSales.saveActionSales(getServletContext().getRealPath(""));
		context.setAttribute("furnitureCategories", furnitureCategories);
		context.setAttribute("furnitureItems", furnitureItems);
		context.setAttribute("actionSales", actionSales);

		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(actionSales
				.getActionSales());
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(sendItems);
	}
}
