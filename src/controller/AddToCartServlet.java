package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FurnitureItem;
import model.FurnitureItems;
import model.ShoppingCart;
import model.ShoppingCartItem;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class AddToCartServlet
 */
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCartServlet() {
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
		Integer number = Integer.parseInt(request.getParameter("number"));

		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute(
				"shoppingCart");
		FurnitureItems furnitureItems = (FurnitureItems) getServletContext().getAttribute(
				"furnitureItems");
		FurnitureItem itemToCart = furnitureItems.getFurnitureItem(id);

		if (id == null || itemToCart == null) {
			return;
		}

		if (itemToCart.getInStock() >= number) {
			itemToCart.setInStock(itemToCart.getInStock() - number);
			
			// ako je na popustu
			ArrayList<ShoppingCartItem> list = shoppingCart.getShoppingCart();

			boolean inCart = false;
			// provera da li je namestaj vec dodat u korpu
			for (ShoppingCartItem shoppingCartItem : list) {
				if (shoppingCartItem.getFurnitureItem().getId().equals(id)) {
					shoppingCartItem.setCount(shoppingCartItem.getCount()
							+ number);
					inCart = true;
					break;
				}
			}
			if (!inCart) {
				shoppingCart.addItem(new ShoppingCartItem(itemToCart, number));
			}

			request.getSession().setAttribute("shoppingCart", shoppingCart);
			furnitureItems.saveFurnitureItems(getServletContext().getRealPath(""));

			ObjectMapper mapper = new ObjectMapper();

			String sendItems = mapper.writeValueAsString(itemToCart);
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(sendItems);
		} else {
			ObjectMapper mapper = new ObjectMapper();

			String sendItems = mapper.writeValueAsString("false");
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(sendItems);
		}
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
