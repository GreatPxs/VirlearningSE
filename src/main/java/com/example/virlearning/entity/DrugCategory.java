package com.example.virlearning.entity;

/**
 * 药品类别实体类
 * @author PHP
 *
 */
public class DrugCategory {
	  private Integer categoryId;//药品类别id
	  private String categoryName;//药品类别名
	  private String note;//备注
	  
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "DrugCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + ", note=" + note + "]";
	}
	  
}