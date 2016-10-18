package pills.models;

public class CategoryModel {
	private long categoryId;
	private String categoryName;
	
	public long getCategoryId(){
		return categoryId;
	}
	public String getCategoryName(){
		return categoryName;
	}
	public void setCategoryId(long categoryId){
		this.categoryId = categoryId;
	}
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}
}
