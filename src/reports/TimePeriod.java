package reports;

import java.util.Date;

public class TimePeriod {
  
  public Date startDate;
  public Date endDate;
  public long timeLength;
  
  public TimePeriod() {
    startDate = new Date();
    endDate = new Date();
    timeLength = 0;
  }
  
  public void setStartDate(Date startDate) {
    assert ( startDate != null ) : "Passed start date is null";
    this.startDate = startDate;
  }
  
  public Date getStartDate() {
    return startDate;
  }
  
  public void setEndDate(Date endDate) {
    assert ( endDate != null ) : "Passed end date is null";
    this.endDate = endDate;
  }
  
  public Date getEndDate() {
    return endDate;
  }
  
  public void setTimeLength(long timeLength) {
    this.timeLength = timeLength;
  }
  
  public long getTimeLength(){
    return this.timeLength;
  }
}
