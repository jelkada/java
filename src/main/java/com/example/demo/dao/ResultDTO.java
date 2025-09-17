package com.example.demo.dao;

public class ResultDTO {

    private String colFromTable1;
    private String colFromTable21;
    private String colFromTable22;
    private String colFromTable3;

    public ResultDTO() {
    }

    public ResultDTO(String colFromTable1, String colFromTable21, String colFromTable22, String colFromTable3) {
        this.colFromTable1 = colFromTable1;
        this.colFromTable21 = colFromTable21;
        this.colFromTable22 = colFromTable22;
        this.colFromTable3 = colFromTable3;
    }

    public String getColFromTable1() {
        return colFromTable1;
    }

    public void setColFromTable1(String colFromTable1) {
        this.colFromTable1 = colFromTable1;
    }

    public String getColFromTable21() {
        return colFromTable21;
    }

    public String getColFromTable22() {
        return colFromTable22;
    }

    public void setColFromTable21(String colFromTable21) {
        this.colFromTable21 = colFromTable21;
    }

    public void setColFromTable22(String colFromTable22) {
        this.colFromTable22 = colFromTable22;
    }

    public String getColFromTable3() {
        return colFromTable3;
    }

    public void setColFromTable3(String colFromTable3) {
        this.colFromTable3 = colFromTable3;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "colFromTable1='" + colFromTable1 + '\'' +
                ", colFromTable21='" + colFromTable21 + '\'' +
                ", colFromTable22='" + colFromTable22 + '\'' +
                ", colFromTable3='" + colFromTable3 + '\'' +
                '}';
    }
}