package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FurnitureItem;
import model.FurnitureItems;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class AddFurnitureItemServlet
 */
public class AddFurnitureItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFurnitureItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		ServletContext context = getServletContext();
		FurnitureItems furnitureItems = (FurnitureItems) context
				.getAttribute("furnitureItems");
		if (furnitureItems == null) {
			try {
				furnitureItems = new FurnitureItems(context.getRealPath(""));
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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("add_furniture.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String originCountry = request.getParameter("originCountry");
		String manufacturerName = request.getParameter("manufacturerName");
		Double price = Double.parseDouble(request.getParameter("price"));
		Integer inStock = Integer.parseInt(request.getParameter("inStock"));
		String category = request.getParameter("category");
		Integer yearOfProduction = Integer.parseInt(request
				.getParameter("yearOfProduction"));
		String salon = request.getParameter("salon");
		String image = request.getParameter("imagedata");

		FurnitureItems furnitureItems = (FurnitureItems) getServletContext()
				.getAttribute("furnitureItems");

		if (id == null || furnitureItems == null) {
			return;
		}

		furnitureItems.addFurnitureItem(new FurnitureItem(id, name, color,
				originCountry, manufacturerName, price, inStock, category,
				yearOfProduction, salon, image));

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

}
