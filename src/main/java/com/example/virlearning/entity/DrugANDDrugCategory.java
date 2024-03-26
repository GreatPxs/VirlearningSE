package com.example.virlearning.entity;


/**
 * 药品和药品类别实体类
 * @author PHP
 *
 */
public class DrugANDDrugCategory{
	  private Integer id;//药品id
	  private String drugName;//药品名
	  private String barCode;//条形码
	  private String referred;//简称
	  private String specifications;//规格
	  private String unit;//单位
	  private String origin;//产地
	  private String approvalNumber;//批准文号
	  private Double pleasedTo;//进货价
	  private Double salesPrice;//售货价
	  private Integer inventory;//库存
	  private Integer totalSales;//销售总量
	  private String drugNote;//药品备注
	  private String categoryName;//药品类别名
	  private Integer isDelete;//是否删除，0-未删除，1-已删除
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getReferred() {
		return referred;
	}
	public void setReferred(String referred) {
		this.referred = referred;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getApprovalNumber() {
		return approvalNumber;
	}
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	public Double getPleasedTo() {
		return pleasedTo;
	}
	public void setPleasedTo(Double pleasedTo) {
		this.pleasedTo = pleasedTo;
	}
	public Double getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public Integer getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(Integer totalSales) {
		this.totalSales = totalSales;
	}
	public String getDrugNote() {
		return drugNote;
	}
	public void setDrugNote(String drugNote) {
		this.drugNote = drugNote;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DrugANDDrugCategory [id=" + id + ", drugName=" + drugName + ", barCode=" + barCode + ", referred="
				+ referred + ", specifications=" + specifications + ", unit=" + unit + ", origin=" + origin
				+ ", approvalNumber=" + approvalNumber + ", pleasedTo=" + pleasedTo + ", salesPrice=" + salesPrice
				+ ", inventory=" + inventory + ", totalSales=" + totalSales + ", drugNote=" + drugNote
				+ ", categoryName=" + categoryName + ", isDelete=" + isDelete + "]";
	}
	
	  
}