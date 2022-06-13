package com.example.VKR.service.excel.bean;

public class ExcelBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "ExcelBean{" +
                "'name'" + name + '\'' +
                '}';
    }
}
