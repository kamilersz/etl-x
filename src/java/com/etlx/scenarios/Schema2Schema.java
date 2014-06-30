/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.scenarios;

import com.etlx.matcher.Matcher;
import com.etlx.mapper.MetaDataReader;
import com.etlx.mapper.Ontology;
import com.etlx.matcher.SchemaMatcher;
import com.etlx.metadata.DB;
import fr.inrialpes.exmo.align.impl.BasicParameters;
import java.io.*;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 *
 * @author kamilersz
 */
public class Schema2Schema {

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("Insufficent parameter");
        } else {
            MetaDataReader mr = new MetaDataReader(args[0]);
            //mr.toJSON();
            DB db1 = new DB(mr.getTables(), mr.getPrimaryKeys(), mr.getForeignKeys());
            mr.toJSON("db_1");
            mr = new MetaDataReader(args[1]);
            mr.toJSON("db_2");
            DB db2 = new DB(mr.getTables(), mr.getPrimaryKeys(), mr.getForeignKeys());
            Matcher m = new SchemaMatcher(db1, db2, .65);
            Properties params = new BasicParameters();
            params.setProperty("classNameWeight", "0.5");
            params.setProperty("classPropertyWeight", "0.5");
            params.setProperty("attNameWeight", "0.7");
            params.setProperty("attTypeWeight", "0");
            m.match(params);
//        System.out.println(m.toJson());

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
