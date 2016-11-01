package pills.models;

public class FeedModel{
  private String title;
  private String description;
  
  public void setDescription(String description){
    this.description = description;
  }
  public String getDescription(){
    return this.description;
  }
  
  public void setTitle(String title){
    this.title = title;
  }
  public String getTitle(){
    return this.title;
  }
  
}