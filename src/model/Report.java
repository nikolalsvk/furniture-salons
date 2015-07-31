package model;

public class Report {
	private String date;
	private Double total;

	public Report(String date, Double total) {
		super();
		this.date = date;
		this.total = total;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
