/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.web;

import com.etlx.mapper.Mapping;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kamilersz
 */
public class GenerateMappingScript extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
                    Mapping m = new Mapping();
        m.parseMapping(request.getParameter("mapping"));
        m.saveMapping(response.getWriter());

    }
}
