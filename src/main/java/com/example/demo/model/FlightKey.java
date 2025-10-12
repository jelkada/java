package com.example.demo.model;

import java.time.LocalDate;
import java.util.Objects;

public class FlightKey {
  private String Source;
  private String destination;
  private LocalDate date;

  public FlightKey(String source, String destination, LocalDate date) {
    Source = source;
    this.destination = destination;
    this.date = date;
  }

  public String getSource() {
    return Source;
  }

  public void setSource(String source) {
    Source = source;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "FlightKey{" +
        "Source='" + Source + '\'' +
        ", destination='" + destination + '\'' +
        ", date=" + date +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    FlightKey flightKey = (FlightKey) o;
    return Objects.equals(Source, flightKey.Source) && Objects.equals(destination, flightKey.destination) && Objects.equals(date, flightKey.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Source, destination, date);
  }
}
