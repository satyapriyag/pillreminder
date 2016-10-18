package pills.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  //@Column(columnDefinition = "serial")
  private long id;
  
  @NotNull
  @Size(min = 2, max = 80)
  private String name;

  public Category() { }

  public Category(long id) { 
    this.id = id;
  }

  public Category(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long value) {
    this.id = value;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }
  
  @Override
  public String toString() {
      return String.format("Category[id=%d, name='%s']", id, name);
  }
} // class Category
