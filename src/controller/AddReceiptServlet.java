package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Receipt;
import model.Receipts;
import model.ShoppingCart;
import model.ShoppingCartItem;
import model.User;

/**
 * Servlet implementation class AddReceiptServlet
 */
public class AddReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReceiptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Double total = Double.parseDouble(request.getParameter("total"));
		String additionalService = request.getParameter("service");
		
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
		User user = (User) request.getSession().getAttribute("user");
		ArrayList<ShoppingCartItem> list = shoppingCart.getShoppingCart();
		
		Receipts receipts = (Receipts) getServletContext().getAttribute("receipts");
		
		Receipt receipt = new Receipt(list, 0.20, total, new Date(), user.getUsername());
		receipt.setAdditionalService(additionalService);
		receipts.addReceipt(receipt);
		receipts.saveReceipts(getServletContext().getRealPath(""));
		
		getServletContext().setAttribute("receipts", receipts);
		
		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(receipt);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(sendItems);
		
		shoppingCart.getShoppingCart().clear();
		request.getSession().setAttribute("shoppingCart", shoppingCart);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
