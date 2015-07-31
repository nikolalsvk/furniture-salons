package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ActionSale;
import model.ActionSales;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class GetActionSalesServlet
 */
public class GetActionSalesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActionSales actionSales;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetActionSalesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionSales = (ActionSales) getServletContext().getAttribute("actionSales");
		
		ArrayList<ActionSale> list = actionSales.getActionSales();
		
		ObjectMapper mapper = new ObjectMapper();

		String sendItems = mapper.writeValueAsString(list);
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
