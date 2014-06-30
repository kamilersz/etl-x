/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.mapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
import com.etlx.web.JSON;
import java.io.*;
import java.util.Map;

/**
 *
 * @author kamilersz
 */
public class Mapping {

    private HashMap source;
    private HashMap destination;
    private HashMap[] mapping;

    public Mapping() {
    }

    public void setMapping() {
    }

    public void saveMapping(String filename) {
        File file = new File(filename);
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(filename);
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot write file!");
        }
        OutputStreamWriter osw = new OutputStreamWriter(os);
        Writer w = new BufferedWriter(osw);
        try {
            w.write("<!DOCTYPE etl SYSTEM \"http://scriptella.javaforge.com/dtd/etl.dtd\">\n");
            w.write("<etl>\n");
            w.write("    <description>ETL-X Migration Script</description>\n");
            w.write("    <properties>\n");
            w.write("        driverOracle=oracle.jdbc.driver.OracleDriver\n");
            w.write("        driverMYSQL=com.mysql.jdbc.Driver\n");
            w.write("    </properties>\n");
            w.write("    <connection id=\"db1\" driver=\"$driver" + source.get("db_type") + "\" url=\"" + source.get("url") + "\" user=\"" + source.get("username") + "\" password=\"" + source.get("password") + "\">\n");
            w.write("    </connection>\n");
            w.write("    <connection id=\"db2\" driver=\"$driver" + destination.get("db_type") + "\" url=\"" + destination.get("url") + "\" user=\"" + destination.get("username") + "\" password=\"" + destination.get("password") + "\">\n");
            w.write("    </connection>\n");
            for (int i = 0; i < mapping.length; i++) {
                String[] tableSrc = (String[]) mapping[i].get("source");
                String tableDest = (String) mapping[i].get("destination");
                HashMap<String, Object> maps = (HashMap<String, Object>) mapping[i].get("map");
                w.write("        <query connection-id=\"db1\">\n");
                if (tableSrc.length == 1) {
                    w.write("            SELECT * FROM " + tableSrc[0] + ";\n");
                } else {
                    StringBuilder builder = new StringBuilder();
                    builder.append(tableSrc[0]);
                    for (int j = 1; j < tableSrc.length; j++) {
                        builder.append(" NATURAL JOIN ");
                        builder.append(tableSrc[j]);
                    }
                    w.write("            SELECT * FROM " + builder.toString() + ";\n");
                }
                w.write("            <script connection-id=\"db2\">\n");
                StringBuilder column = new StringBuilder();
                StringBuilder values = new StringBuilder();

                for (Map.Entry<String, Object> entry : maps.entrySet()) {
                    if (column.length() == 0)
                        column.append(entry.getKey());
                    else
                        column.append(", ").append(entry.getKey());
                    if (values.length() == 0)
                        values.append("?{").append(entry.getValue()).append("}");
                    else
                        values.append(", ?{").append(entry.getValue()).append("}");
                }
                w.write("                INSERT INTO "+tableDest+"("+column.toString()+") VALUES ("+values.toString()+");\n");
                w.write("            </script>\n");
                w.write("        </query>\n");

            }
            w.write("</etl>\n");
            w.close();

        } catch (IOException ex) {
            System.out.println("Writing error");
        }

    }

    public void saveMapping(Writer w) {
        try {
            w.write("<!DOCTYPE etl SYSTEM \"http://scriptella.javaforge.com/dtd/etl.dtd\">\n");
            w.write("<etl>\n");
            w.write("    <description>ETL-X Migration Script</description>\n");
            w.write("    <properties>\n");
            w.write("        driverOracle=oracle.jdbc.driver.OracleDriver\n");
            w.write("        driverMYSQL=com.mysql.jdbc.Driver\n");
            w.write("    </properties>\n");
            w.write("    <connection id=\"db1\" driver=\"$driver" + source.get("db_type") + "\" url=\"" + source.get("url") + "\" user=\"" + source.get("username") + "\" password=\"" + source.get("password") + "\">\n");
            w.write("    </connection>\n");
            w.write("    <connection id=\"db2\" driver=\"$driver" + destination.get("db_type") + "\" url=\"" + destination.get("url") + "\" user=\"" + destination.get("username") + "\" password=\"" + destination.get("password") + "\">\n");
            w.write("    </connection>\n");
            for (int i = 0; i < mapping.length; i++) {
                String[] tableSrc = (String[]) mapping[i].get("source");
                String tableDest = (String) mapping[i].get("destination");
                HashMap<String, Object> maps = (HashMap<String, Object>) mapping[i].get("map");
                w.write("        <query connection-id=\"db1\">\n");
                if (tableSrc.length == 1) {
                    w.write("            SELECT * FROM " + tableSrc[0] + ";\n");
                } else {
                    StringBuilder builder = new StringBuilder();
                    builder.append(tableSrc[0]);
                    for (int j = 1; j < tableSrc.length; j++) {
                        builder.append(" NATURAL JOIN ");
                        builder.append(tableSrc[j]);
                    }
                    w.write("            SELECT * FROM " + builder.toString() + ";\n");
                }
                w.write("            <script connection-id=\"db2\">\n");
                StringBuilder column = new StringBuilder();
                StringBuilder values = new StringBuilder();

                for (Map.Entry<String, Object> entry : maps.entrySet()) {
                    if (column.length() == 0)
                        column.append(entry.getKey());
                    else
                        column.append(", ").append(entry.getKey());
                    if (values.length() == 0)
                        values.append("?{").append(entry.getValue()).append("}");
                    else
                        values.append(", ?{").append(entry.getValue()).append("}");
                }
                w.write("                INSERT INTO "+tableDest+"("+column.toString()+") VALUES ("+values.toString()+");\n");
                w.write("            </script>\n");
                w.write("        </query>\n");

            }
            w.write("</etl>\n");
            w.close();

        } catch (IOException ex) {
            System.out.println("Writing error");
        }

    }

    public void loadMapping(String filename) throws IOException {
        JSONObject mappingJSON = null;
        String fileContent;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            fileContent = sb.toString();
        } finally {
            br.close();
        }

        try {
            mappingJSON = new JSONObject(fileContent);
        } catch (JSONException ex) {
            Logger.getLogger(Mapping.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            source = new HashMap<String, Object>();
            source.put("url", mappingJSON.getJSONObject("source").getString("url"));
            source.put("db_type", mappingJSON.getJSONObject("source").getString("db_type"));
            source.put("username", mappingJSON.getJSONObject("source").getString("username"));
            source.put("password", mappingJSON.getJSONObject("source").getString("password"));
            destination = new HashMap<String, Object>();
            destination.put("url", mappingJSON.getJSONObject("destination").getString("url"));
            destination.put("db_type", mappingJSON.getJSONObject("destination").getString("db_type"));
            destination.put("username", mappingJSON.getJSONObject("destination").getString("username"));
            destination.put("password", mappingJSON.getJSONObject("destination").getString("password"));
            JSONArray mappings = mappingJSON.getJSONArray("mapping");
            mapping = new HashMap[mappings.length()];
            for (int j = 0; j < mappings.length(); j++) {
                mapping[j] = new HashMap<String, Object>();
                JSONArray src = mappings.getJSONObject(j).getJSONArray("source");
                String[] srcx = new String[src.length()];
                for (int i = 0; i < src.length(); i++) {
                    srcx[i] = src.getString(i);
                }
                mapping[j].put("source", srcx);
                String dst = mappings.getJSONObject(j).getString("destination");
                mapping[j].put("destination", dst);
                JSONObject map = mappings.getJSONObject(j).getJSONObject("map");
                HashMap<String, String> mapx = new HashMap<String, String>();
                for (Iterator it = map.keys(); it.hasNext();) {
                    String key = (String) it.next();
                    mapx.put(key, map.getString(key));
                }
                mapping[j].put("map", mapx);
            }

        } catch (JSONException ex) {
            Logger.getLogger(Mapping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void parseMapping(String fileContent) throws IOException {
        JSONObject mappingJSON = null;
        try {
            mappingJSON = new JSONObject(fileContent);
        } catch (JSONException ex) {
            Logger.getLogger(Mapping.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            source = new HashMap<String, Object>();
            source.put("url", mappingJSON.getJSONObject("source").getString("url"));
            source.put("db_type", mappingJSON.getJSONObject("source").getString("db_type"));
            source.put("username", mappingJSON.getJSONObject("source").getString("username"));
            source.put("password", mappingJSON.getJSONObject("source").getString("password"));
            destination = new HashMap<String, Object>();
            destination.put("url", mappingJSON.getJSONObject("destination").getString("url"));
            destination.put("db_type", mappingJSON.getJSONObject("destination").getString("db_type"));
            destination.put("username", mappingJSON.getJSONObject("destination").getString("username"));
            destination.put("password", mappingJSON.getJSONObject("destination").getString("password"));
            JSONArray mappings = mappingJSON.getJSONArray("mapping");
            mapping = new HashMap[mappings.length()];
            for (int j = 0; j < mappings.length(); j++) {
                mapping[j] = new HashMap<String, Object>();
                JSONArray src = mappings.getJSONObject(j).getJSONArray("source");
                String[] srcx = new String[src.length()];
                for (int i = 0; i < src.length(); i++) {
                    srcx[i] = src.getString(i);
                }
                mapping[j].put("source", srcx);
                String dst = mappings.getJSONObject(j).getString("destination");
                mapping[j].put("destination", dst);
                JSONObject map = mappings.getJSONObject(j).getJSONObject("map");
                HashMap<String, String> mapx = new HashMap<String, String>();
                for (Iterator it = map.keys(); it.hasNext();) {
                    String key = (String) it.next();
                    mapx.put(key, map.getString(key));
                }
                mapping[j].put("map", mapx);
            }

        } catch (JSONException ex) {
            Logger.getLogger(Mapping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        Mapping m = new Mapping();
        m.loadMapping("mapping.json");
        m.saveMapping("test");
    }

    /**
     * @return the source
     */
    public HashMap getSource() {
        return source;
    }

    /**
     * @return the destination
     */
    public HashMap getDestination() {
        return destination;
    }

    /**
     * @return the mapping
     */
    public HashMap[] getMapping() {
        return mapping;
    }
}
