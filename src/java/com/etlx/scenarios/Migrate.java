/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.scenarios;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import scriptella.execution.EtlExecutor;
import scriptella.execution.EtlExecutorException;

/**
 *
 * @author kamilersz
 */
public class Migrate {

    public static void main(String[] args) throws IOException, SQLException, EtlExecutorException {
        if (args.length < 1) {
            System.err.println("Insufficent parameter");
        } else {
            try {
                EtlExecutor.newExecutor(new File(args[0])).execute();
            } catch (Exception ex) {
                System.out.println("ETL Execution error: " + ex.getMessage());
            }
        }
    }
}
