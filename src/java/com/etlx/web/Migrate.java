/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.web;

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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kamilersz
 */
public class Migrate extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        try {
            EtlExecutor.newExecutor(new File(request.getParameter("script"))).execute();
        } catch (Exception ex) {
            System.out.println("ETL Execution error: " + ex.getMessage());
        }
    }
}
