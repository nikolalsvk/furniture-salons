package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Receipt;
import model.Receipts;
import model.ReportCategory;
import model.ShoppingCartItem;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class GetReportCategoryServlet
 */
public class GetReportCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetReportCategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String category = request.getParameter("category");

		Receipts receipts = null;
		try {
			receipts = new Receipts(getServletContext().getRealPath(""));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList<Receipt> receiptsList = receipts.getReceipts();

		ArrayList<ReportCategory> reports = new ArrayList<ReportCategory>();

		for (Receipt receipt : receiptsList) {
			try {
				if (receipt.isBetweenDates(startDate, endDate)) {
					ArrayList<ShoppingCartItem> list = receipt
							.getItemWithCategory(category);

					for (ShoppingCartItem shoppingCartItem : list) {
						String name = shoppingCartItem.getFurnitureItem()
								.getName();
						Double total = shoppingCartItem.getTotal();
						Integer quantity = shoppingCartItem.getCount();
						boolean uListi = false;
						for (ReportCategory report : reports) {
							if (report.getName().equals(name)) {
								report.setTotal(report.getTotal() + total);
								report.setQuantity(report.getQuantity() + quantity);
								uListi = true;
								break;
							}
						}

						if (!uListi) {
							reports.add(new ReportCategory(name, quantity, total));
						}
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(reports);
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
