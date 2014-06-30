/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.scenarios;

import com.etlx.mapper.Mapping;

/**
 *
 * @author kamilersz
 */
public class GenerateMappingScript {
    
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Insufficent parameter");
        } else {
                    Mapping m = new Mapping();
        m.loadMapping(args[0]);
        m.saveMapping(args[1]);

        }
    }
}
