package pills.models;

public class CategoryModel {
	private Integer categoryId;
	private String categoryName;
	
	public Integer getCategoryId(){
		return categoryId;
	}
	public String getCategoryName(){
		return categoryName;
	}
	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}
}
