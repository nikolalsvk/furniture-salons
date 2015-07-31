package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.FurnitureItem;
import model.FurnitureItems;
import model.ShoppingCart;
import model.ShoppingCartItem;

/**
 * Servlet implementation class DeleteShoppingCartItemServlet
 */
public class DeleteShoppingCartItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShoppingCart shoppingCart;
	private FurnitureItems furnitureItems;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteShoppingCartItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		shoppingCart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
		furnitureItems = (FurnitureItems) getServletContext().getAttribute("furnitureItems");
		FurnitureItem item = furnitureItems.getFurnitureItem(id);
		
		ArrayList<ShoppingCartItem> list = shoppingCart.getShoppingCart();
		for (ShoppingCartItem shoppingCartItem : list) {
			if(shoppingCartItem.getFurnitureItem().getId().equals(id)) {
				item.setInStock(item.getInStock() + shoppingCartItem.getCount());
				shoppingCart.getShoppingCart().remove(shoppingCartItem);
				break;
			}
		}
		
		ArrayList<ShoppingCartItem> itemsToSend = shoppingCart.getShoppingCart();
		request.getSession().setAttribute("shoppingCart", shoppingCart);
		getServletContext().setAttribute("furnitureItems", furnitureItems);
		
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
