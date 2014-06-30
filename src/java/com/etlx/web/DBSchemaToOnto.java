/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.web;

import com.etlx.mapper.MetaDataReader;
import com.etlx.mapper.Ontology;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.semanticweb.owlapi.model.OWLOntology;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 *
 * @author kamilersz
 */
public class DBSchemaToOnto extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        ServletContext servletContext = request.getSession().getServletContext();
        MetaDataReader mr = new MetaDataReader(servletContext.getRealPath("/")+"files/" + request.getParameter("db"));
        //mr.toJSON();
        Ontology o = new Ontology();
        OWLOntology ox;
        try {
            ox = o.dbSchemaToOnto(mr.getTables(), mr.getPrimaryKeys(), mr.getForeignKeys());
            o.saveOntology(ox, response.getOutputStream());
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(DBSchemaToOnto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OWLOntologyStorageException ex) {
            Logger.getLogger(DBSchemaToOnto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
