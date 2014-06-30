/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.scenarios;

import com.etlx.mapper.MetaDataReader;
import com.etlx.mapper.Ontology;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 *
 * @author kamilersz
 */
public class DBSchemaToOnto {
    
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Insufficent parameter");
        } else {
                    MetaDataReader mr = new MetaDataReader(args[0]);
        //mr.toJSON();
        Ontology o = new Ontology();
        OWLOntology ox = o.dbSchemaToOnto(mr.getTables(),mr.getPrimaryKeys(),mr.getForeignKeys());
        o.saveOntology(ox, args[1]);

        }
    }
}
