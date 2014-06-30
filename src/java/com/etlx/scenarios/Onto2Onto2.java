/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.scenarios;

import com.etlx.mapper.MetaDataReader;
import com.etlx.mapper.Ontology;
import com.etlx.matcher.Matcher;
import com.etlx.matcher.OntoMatcher;
import com.etlx.metadata.DB;
import fr.inrialpes.exmo.align.impl.BasicParameters;
import java.io.File;
import java.util.Properties;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 *
 * @author kamilersz
 */
public class Onto2Onto2 {
        public static void main(String[] args) throws Exception {

        MetaDataReader mr = new MetaDataReader("input_inameta2.sql");
        //mr.toJSON();
        Ontology o = new Ontology();
        OWLOntology ox = o.dbSchemaToOnto(mr.getTables(),mr.getPrimaryKeys(),mr.getForeignKeys());
        o.saveOntology(ox, "out1.owl");
        mr = new MetaDataReader("input_ppdm2.sql");
        OWLOntology oy = o.dbSchemaToOnto(mr.getTables(),mr.getPrimaryKeys(),mr.getForeignKeys());
        o.saveOntology(oy, "out2.owl");
        Matcher m = new OntoMatcher(new File("out1.owl").toURI(),new File("out2.owl").toURI(), .65);
        Properties params = new BasicParameters();
        params.setProperty("stringFunction", "ngram");
        m.match(params);
        //System.out.println(m.toJson().toString(1));  
        }

}
