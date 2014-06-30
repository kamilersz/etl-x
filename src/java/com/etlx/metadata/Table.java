package com.etlx.metadata;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private List<Column> columns;
 
    public Table(final String name, List<Column> cols) {
        this.name = name.toUpperCase();
        //Collections.sort(cols);
        this.columns = cols;
    }
    
    @Override
    public String toString() {
        return getName() + ": " + getColumns();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the columns
     */
    public List<Column> getColumns() {
        return columns;
    }
}