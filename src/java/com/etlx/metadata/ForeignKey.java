package com.etlx.metadata;

import java.util.ArrayList;
import java.util.List;

public class ForeignKey {

    private String name;
    private String tableFrom;
    private List<String> colFrom;
    private String tableTo;
    private List<String> colTo;

    public ForeignKey(final String name) {
        this.name = name;
    }

    public void setTableFrom(String tableFrom) {
        this.tableFrom = tableFrom;
    }

    public void setTableTo(String tableTo) {
        this.tableTo = tableTo;
    }

    public void setColFrom(List<String> colFrom) {
        this.colFrom = colFrom;
    }

    public void setColTo(List<String> colTo) {
        this.colTo = colTo;
    }

    public String getName() {
        return name;
    }

    public String getTableFrom() {
        return tableFrom;
    }

    public String getTableTo() {
        return tableTo;
    }

    public List<String> getColFrom() {
        return colTo;
    }

    public List<String> getColTo() {
        return colTo;
    }

    @Override
    public String toString() {
        return name + " " + tableFrom + "." + colFrom + "->" + tableTo + "." + colTo;
    }
}