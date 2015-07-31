package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Receipt;
import model.Receipts;
import model.Report;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class GetReportServlet
 */
public class GetReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		Receipts receipts = (Receipts) getServletContext().getAttribute(
				"receipts");

		ArrayList<Receipt> receiptsList = receipts.getReceipts();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

		DateFormat df_other = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		ArrayList<Report> reports = new ArrayList<Report>();

		for (Receipt receipt : receiptsList) {
			try {
				if (receipt.isBetweenDates(startDate, endDate)) {
					String date = df.format(df_other.parse(receipt.getDate()));

					boolean uListi = false;
					for (Report report : reports) {
						if (report.getDate().equals(date)) {
							report.setTotal(report.getTotal()
									+ receipt.getFinalPrice());
							uListi = true;
							break;
						}
					}

					if (!uListi) {
						reports.add(new Report(date, receipt.getFinalPrice()));
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
