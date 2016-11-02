package pills.models;

public class AlternativeModel {
  private Integer altId;
  private Integer pillId;
  private Integer alternatePillId;

  public Integer getPillId() {
    return pillId;
  }

  public void setPillId(Integer pillId) {
    this.pillId = pillId;
  }

  public Integer getAltId() {
    return altId;
  }

  public void setAltId(Integer altId) {
    this.altId = altId;
  }

  public Integer getAlternatePillId() {
    return alternatePillId;
  }

  public void setAlternatePillId(Integer alternatePillId) {
    this.alternatePillId = alternatePillId;
  }
}
