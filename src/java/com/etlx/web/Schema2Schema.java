/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.web;

import com.etlx.matcher.Matcher;
import com.etlx.mapper.MetaDataReader;
import com.etlx.mapper.Ontology;
import com.etlx.matcher.SchemaMatcher;
import com.etlx.metadata.DB;
import java.io.*;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.semanticweb.owlapi.model.OWLOntology;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author kamilersz
 */
public class Schema2Schema extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        ServletContext servletContext = request.getSession().getServletContext();
        MetaDataReader mr = new MetaDataReader(servletContext.getRealPath("/")+"files/" + request.getParameter("db_1"));
        JSONObject result = new JSONObject();
        //mr.toJSON();
        DB db1 = new DB(mr.getTables(), mr.getPrimaryKeys(), mr.getForeignKeys());
        try {
            result.put("db_1", mr.getJSON());
            mr = new MetaDataReader(servletContext.getRealPath("/")+"files/" + request.getParameter("db_2"));
            result.put("db_2", mr.getJSON());
        } catch (JSONException ex) {
            response.getWriter().write("error");
        }
        DB db2 = new DB(mr.getTables(), mr.getPrimaryKeys(), mr.getForeignKeys());
        Matcher m = new SchemaMatcher(db1, db2, .65);
        Properties params = new Properties();
        params.setProperty("classNameWeight", "0.5");
        params.setProperty("classPropertyWeight", "0.5");
        params.setProperty("attNameWeight", "0.7");
        params.setProperty("attTypeWeight", "0");
        m.match(params);
//        System.out.println(m.toJson());

        try {
            result.put("mapping",m.toJson());
            response.getWriter().write(result.toString(1));
        } catch (JSONException ex) {
            response.getWriter().write("error");
        }
    }
}
