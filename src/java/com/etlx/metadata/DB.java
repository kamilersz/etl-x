/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.metadata;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kamilersz
 */
public class DB {

    private List<Table> tables;
    private List<ForeignKey> foreignKeys;
    private List<PrimaryKey> primaryKeys;
    
    public DB(List<Table> ts,List<PrimaryKey> pks,List<ForeignKey> fks) {
        tables = ts;
        foreignKeys = fks;
        primaryKeys = pks;
    }
    
    public List<Column> getAllColumns() {
        List<Column> lc = new ArrayList();
        for (Table t : getTables()) {
            lc.addAll(t.getColumns());
        }
        return lc;
    }

    /**
     * @return the tables
     */
    public List<Table> getTables() {
        return tables;
    }

    /**
     * @param tables the tables to set
     */
    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    /**
     * @return the foreignKeys
     */
    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    /**
     * @param foreignKeys the foreignKeys to set
     */
    public void setForeignKeys(List<ForeignKey> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    /**
     * @return the primaryKeys
     */
    public List<PrimaryKey> getPrimaryKeys() {
        return primaryKeys;
    }

    /**
     * @param primaryKeys the primaryKeys to set
     */
    public void setPrimaryKeys(List<PrimaryKey> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

}
