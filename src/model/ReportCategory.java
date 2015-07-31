package model;

public class ReportCategory {
	private String name;
	private Integer quantity;
	private Double total;

	public ReportCategory(String name, Integer quantity, Double total) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
