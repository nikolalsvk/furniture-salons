package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionSale implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String salon;
	private String entityOnSale;
	private String entity;
	private Double discount;
	private String startDate;
	private String endDate;
	private Boolean isCurrent;
	
	public ActionSale(String salon, String entityOnSale, String entity,
			Double discount, String startDate, String endDate) throws ParseException {
		super();
		this.salon = salon;
		this.entityOnSale = entityOnSale;
		this.entity = entity;
		this.discount = discount;
		this.startDate = startDate;
		this.endDate = endDate;
		
		Date startDate_conv = convertDate(startDate);
		Date endDate_conv = convertDate(endDate);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date currentDate = df.parse(df.format(new Date()));
		
		if(endDate_conv.before(currentDate) || startDate_conv.after(currentDate)) {
			isCurrent = false;
		} else {
			isCurrent = true;
		}
	}

	public ActionSale() {
		
	}
	
	public String getSalon() {
		return salon;
	}
	public void setSalon(String salon) {
		this.salon = salon;
	}
	public String getEntityOnSale() {
		return entityOnSale;
	}
	public void setEntityOnSale(String entityOnSale) {
		this.entityOnSale = entityOnSale;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public Date convertDate(String date) throws ParseException {
	    DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
	    Date convertedDate;
	    convertedDate = df.parse(date);
	    
	    return convertedDate;
	}

	public boolean isCurrent() {
		return isCurrent;
	}

	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
}
