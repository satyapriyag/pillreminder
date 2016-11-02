package pills.models;

public class AddAlternativeModel {
  private Integer pillId;
  private Integer alternatePillId;

  public Integer getPillId() {
    return pillId;
  }

  public void setPillId(Integer pillId) {
    this.pillId = pillId;
  }

  public Integer getAlternatePillId() {
    return alternatePillId;
  }

  public void setAlternatePillId(Integer alternatePillId) {
    this.alternatePillId = alternatePillId;
  }
}
