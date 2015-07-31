package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ActionSale;
import model.ActionSales;
import model.FurnitureItem;
import model.FurnitureItems;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class DeleteFurnitureItemServlet
 */
public class DeleteFurnitureItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FurnitureItems furnitureItems;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteFurnitureItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id == null) {
			return;
		}

		furnitureItems = (FurnitureItems) getServletContext().getAttribute(
				"furnitureItems");
		if (furnitureItems == null) {
			return;
		}
		FurnitureItem item = furnitureItems.getFurnitureItem(id);
		// ako je na snizenju izbaci ga iz akcija
		if (item.isDiscounted()) {
			ActionSales actionSales = (ActionSales) getServletContext()
					.getAttribute("actionSales");
			ArrayList<ActionSale> actionSale_list = actionSales.getActionSales();
			for (ActionSale actionSale : actionSale_list) {
				if(actionSale.getEntityOnSale().equals(id)) {
					actionSale_list.remove(actionSale);
					break;
				}
			}
		}

		furnitureItems.deleteFurnitureItem(id);

		Collection<FurnitureItem> itemsToSend = furnitureItems.getValues();
		if (itemsToSend == null) {
			return;
		}
		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(itemsToSend);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(sendItems);

		getServletContext().setAttribute("furnitureItems", furnitureItems);
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
