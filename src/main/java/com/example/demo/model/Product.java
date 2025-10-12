package com.example.demo.model;

import java.util.Objects;

public class Product {
  private String code;
  private double price;

  public Product(String code, double price) {
    this.code = code;
    this.price = price;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Product{" +
        "code='" + code + '\'' +
        ", price=" + price +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(code, product.code);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(code);
  }
}
