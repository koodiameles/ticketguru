package fi.ohjelmistoprojekti1.TicketGuru.web;

import javax.validation.constraints.NotNull;

public class TickettypeDTO {
  @NotNull
  private long event;
  @NotNull
  private String name;
  @NotNull
  private double price;

  public TickettypeDTO(long event, String name, double price) {
    this.event = event;
    this.name = name;
    this.price = price;
  }

  public long getEvent() {
    return this.event;
  }

  public void setEvent(long event) {
    this.event = event;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
