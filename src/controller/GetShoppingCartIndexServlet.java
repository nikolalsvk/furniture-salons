package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ShoppingCart;
import model.ShoppingCartItem;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class GetShoppingCartIndexServlet
 */
public class GetShoppingCartIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShoppingCart shoppingCart;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetShoppingCartIndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		shoppingCart = (ShoppingCart) request.getSession().getAttribute(
				"shoppingCart");
		if (shoppingCart == null) {
			request.getSession().setAttribute("shoppingCart",
					new ShoppingCart());
		}
		
		ArrayList<ShoppingCartItem> itemsToSend = shoppingCart.getShoppingCart();
//		for (ShoppingCartItem item : itemsToSend) {
//			if(item.getFurnitureItem().isDiscounted()) {
//				item.getFurnitureItem().setPrice(item.getFurnitureItem().getDiscountedPrice());
//			}
//		}

		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(itemsToSend);
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
