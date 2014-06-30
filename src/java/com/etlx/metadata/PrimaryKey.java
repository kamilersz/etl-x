package com.etlx.metadata;

import java.util.ArrayList;
import java.util.List;

public class PrimaryKey {

    private String name;
    private String tableName;
    private List<String> primaryColumns;

    public PrimaryKey(final String name) {
        this.name = name;
    }

    public void setTable(String table) {
        this.tableName = table;
    }

    public void setCol(List<String> primaryColumns) {
        this.primaryColumns = primaryColumns;
    }

    @Override
    public String toString() {
        return getName() + " " + getTableName() + "." + getPrimaryColumns();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @return the primaryColumns
     */
    public List<String> getPrimaryColumns() {
        return primaryColumns;
    }
}