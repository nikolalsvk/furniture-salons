package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class DeleteActionSaleServlet
 */
public class DeleteActionSaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActionSales actionSales;
	private FurnitureCategories furnitureCategories;
	private FurnitureItems furnitureItems;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteActionSaleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String salon = request.getParameter("salon");
		String entityOnSale = request.getParameter("entityOnSale");
		String entity = request.getParameter("entity");

		ServletContext context = getServletContext();

		actionSales = (ActionSales) context.getAttribute("actionSales");
		furnitureItems = (FurnitureItems) context
				.getAttribute("furnitureItems");
		furnitureCategories = (FurnitureCategories) context
				.getAttribute("furnitureCategories");

		// pronalazenje akcije
		ActionSale sale = null;
		for (ActionSale actionSale : actionSales.getActionSales()) {
			if (actionSale.getSalon().equals(salon)
					&& actionSale.getEntityOnSale().equals(entityOnSale)) {
				sale = actionSale;
				break;
			}
		}

		// ako je trenutna akcija u toku
		if (sale.isCurrent()) {
			if (entity.equals("komad")) {
				FurnitureItem item = furnitureItems
						.getFurnitureItem(entityOnSale);
				item.setDiscount(0.0);
			} else {

				ArrayList<FurnitureItem> list = furnitureItems
						.getFurnitureItemsFromSalon("", "", "", "", "", "", "",
								salon, entityOnSale);

				for (FurnitureItem furnitureItem : list) {
					furnitureItem.setDiscount(0.0);
					furnitureItem.setDiscountedPrice(0.0);
					furnitureItem.setDiscounted(false);
				}

				furnitureCategories.getCategoryByName(entityOnSale)
						.setDiscount(0.0);
			}
		}

		// brisanje iz akcije iz svih akcija

		actionSales.getActionSales().remove(sale);

		context.setAttribute("furnitureCategories", furnitureCategories);
		context.setAttribute("furnitureItems", furnitureItems);
		context.setAttribute("actionSales", actionSales);

		ArrayList<ActionSale> list = actionSales.getActionSales();

		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(list);
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
		// TODO Auto-generated method stub
	}

}
