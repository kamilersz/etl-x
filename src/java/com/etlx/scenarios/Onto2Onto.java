/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.scenarios;

import com.etlx.mapper.MetaDataReader;
import com.etlx.mapper.Ontology;
import com.etlx.matcher.OntoMatcher;
import fr.inrialpes.exmo.align.impl.BasicParameters;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 *
 * @author kamilersz
 */
public class Onto2Onto {

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("Insufficent parameter");
        } else {
            com.etlx.matcher.Matcher m = new OntoMatcher(new File(args[0]).toURI(), new File(args[1]).toURI(), .65);
            Properties params = new BasicParameters();
            params.setProperty("stringFunction", "ngram");
            m.match(params);

            File file = new File(args[2]);
            FileOutputStream os = null;
            try {
                os = new FileOutputStream(args[2]);
            } catch (FileNotFoundException ex) {
                System.out.println("Cannot write file!");
            }
            OutputStreamWriter osw = new OutputStreamWriter(os);
            Writer w = new BufferedWriter(osw);
            try {
                w.write("var mapping = ");
                w.write(m.toJson().toString(1));
                w.close();
            } catch (IOException ex) {
                Logger.getLogger(MetaDataReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
