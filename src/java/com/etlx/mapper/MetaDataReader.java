/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.mapper;

import com.etlx.metadata.Column;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import com.etlx.metadata.ForeignKey;
import com.etlx.metadata.PrimaryKey;
import com.etlx.metadata.Table;
import com.etlx.metadataparser.CreateTableLexer;
import com.etlx.metadataparser.CreateTableParser;
import com.etlx.web.JSON;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

/**
 *
 * @author kamilersz
 */
public class MetaDataReader {
    private List tables;
    private List foreignKeys;
    private List primaryKeys;

    public MetaDataReader(String filename) {

        try {
            InputStream in = new FileInputStream(filename);
            ANTLRInputStream input = new ANTLRInputStream(in);
            CreateTableLexer lexer = new CreateTableLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CreateTableParser parser = new CreateTableParser(tokens);
            CreateTableParser.Table_listContext ctx = parser.table_list();
            tables =  ctx.tables;
            foreignKeys =  ctx.fk;
            primaryKeys =  ctx.pk;
        } catch (IOException ex) {
            Logger.getLogger(MetaDataReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void toJSON(String filename) throws JSONException {
        JSONObject jsonDb = null;
        JSONArray jsonTables = new JSONArray();
        JSONArray jsonPks = new JSONArray();
        JSONArray jsonFks = new JSONArray();
        jsonDb = new JSONObject();
        for(Object o : this.tables) {
            Table t = (Table) o;
            JSONObject tab = new JSONObject();
            JSONArray columns = new JSONArray();
            tab.put("name",t.getName().toUpperCase());
            for (Object p : t.getColumns()) {
                Column c = (Column) p;
                JSONObject col = new JSONObject();
                col.put("name",c.getName().toUpperCase());
                col.put("constraint",c.getConstraint());
                col.put("data_type",c.getDataType());
                col.put("length",c.getLength());
                col.put("precision",c.getPrecision());
                columns.put(col);
            }
            tab.put("columns",columns);
            jsonTables.put(tab);
        }
        jsonDb.put("tables",jsonTables);
        for (Object o : primaryKeys) {
            PrimaryKey pk = (PrimaryKey) o;
            JSONObject jsonPk = new JSONObject();
            JSONArray columns = new JSONArray();
            jsonPk.put("name",pk.getName().toUpperCase());
            jsonPk.put("table",pk.getTableName().toUpperCase());
            for (String cn : pk.getPrimaryColumns()) {
                columns.put(cn.toUpperCase());
            }
            jsonPk.put("columns",columns);
            jsonPks.put(jsonPk);
        }
        jsonDb.put("primary_keys",jsonPks);
        for (Object o : foreignKeys) {
            ForeignKey fk = (ForeignKey) o;
            JSONObject jsonFk = new JSONObject();
            JSONArray column_from = new JSONArray();
            JSONArray column_to = new JSONArray();
            jsonFk.put("name",fk.getName().toUpperCase());
            jsonFk.put("table_from",fk.getTableFrom().toUpperCase());
            jsonFk.put("table_to",fk.getTableTo().toUpperCase());
            for (String cn : fk.getColFrom()) {
                column_from.put(cn.toUpperCase());
            }
            jsonFk.put("column_from",column_from);
            for (String cn : fk.getColTo()) {
                column_to.put(cn.toUpperCase());
            }
            jsonFk.put("column_to",column_to);
            jsonFks.put(jsonFk);
        }
        jsonDb.put("foreign_keys",jsonFks);
        
        //System.out.println(jsonDb.toString(1));
        File file = new File("web/" + filename + ".js");
        FileOutputStream os = null;
        try {
            os = new FileOutputStream("web/" + filename + ".js");
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot write file!");
        }
        OutputStreamWriter osw = new OutputStreamWriter(os);
        Writer w = new BufferedWriter(osw);
        try {
            w.write("var " + filename + " = ");
            w.write(jsonDb.toString(1));
            w.close();
        } catch (IOException ex) {
            Logger.getLogger(MetaDataReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public JSONObject getJSON() throws JSONException {
        JSONObject jsonDb = null;
        JSONArray jsonTables = new JSONArray();
        JSONArray jsonPks = new JSONArray();
        JSONArray jsonFks = new JSONArray();
        jsonDb = new JSONObject();
        for(Object o : this.tables) {
            Table t = (Table) o;
            JSONObject tab = new JSONObject();
            JSONArray columns = new JSONArray();
            tab.put("name",t.getName().toUpperCase());
            for (Object p : t.getColumns()) {
                Column c = (Column) p;
                JSONObject col = new JSONObject();
                col.put("name",c.getName().toUpperCase());
                col.put("constraint",c.getConstraint());
                col.put("data_type",c.getDataType());
                col.put("length",c.getLength());
                col.put("precision",c.getPrecision());
                columns.put(col);
            }
            tab.put("columns",columns);
            jsonTables.put(tab);
        }
        jsonDb.put("tables",jsonTables);
        for (Object o : primaryKeys) {
            PrimaryKey pk = (PrimaryKey) o;
            JSONObject jsonPk = new JSONObject();
            JSONArray columns = new JSONArray();
            jsonPk.put("name",pk.getName().toUpperCase());
            jsonPk.put("table",pk.getTableName().toUpperCase());
            for (String cn : pk.getPrimaryColumns()) {
                columns.put(cn.toUpperCase());
            }
            jsonPk.put("columns",columns);
            jsonPks.put(jsonPk);
        }
        jsonDb.put("primary_keys",jsonPks);
        for (Object o : foreignKeys) {
            ForeignKey fk = (ForeignKey) o;
            JSONObject jsonFk = new JSONObject();
            JSONArray column_from = new JSONArray();
            JSONArray column_to = new JSONArray();
            jsonFk.put("name",fk.getName().toUpperCase());
            jsonFk.put("table_from",fk.getTableFrom().toUpperCase());
            jsonFk.put("table_to",fk.getTableTo().toUpperCase());
            for (String cn : fk.getColFrom()) {
                column_from.put(cn.toUpperCase());
            }
            jsonFk.put("column_from",column_from);
            for (String cn : fk.getColTo()) {
                column_to.put(cn.toUpperCase());
            }
            jsonFk.put("column_to",column_to);
            jsonFks.put(jsonFk);
        }
        jsonDb.put("foreign_keys",jsonFks);
        return jsonDb;
    }

    /**
     * @return the tables
     */
    public List getTables() {
        return tables;
    }

    /**
     * @return the foreignKeys
     */
    public List getForeignKeys() {
        return foreignKeys;
    }

    /**
     * @return the primaryKeys
     */
    public List getPrimaryKeys() {
        return primaryKeys;
    }
}
