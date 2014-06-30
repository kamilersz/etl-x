package com.etlx.mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author kamilersz
 */
public class Query {

    private Connection con = null;
    private int dbtype;
    private String url;
    private String user;
    private String pass;

    public Query(String dbtype, String url, String user, String pass) {
        if ("MYSQL".compareToIgnoreCase(dbtype) == 0) {
            this.dbtype = 1;
        } else if ("Oracle".compareToIgnoreCase(dbtype) == 0) {
            this.dbtype = 2;
        }
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public List all(String q, String[] w) {
        List result = new ArrayList<ResultSet>();
        // connecting to database
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            switch (dbtype) {
                case 1:
                    Class.forName("com.mysql.jdbc.Driver");
                    break;
                case 2:
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    break;
            }
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.prepareStatement(q);
            if (w != null) {
                for (int i = 0; i < w.length; i++) {
                    stmt.setString(i + 1, w[i]);
                }
            }
            rs = stmt.executeQuery();
            result = convertResultSetToList(rs);
        } catch (SQLException e) {
            throw new Exception("Servlet Could not display records.", e);
        } catch (ClassNotFoundException e) {
            throw new Exception("JDBC Driver not found.", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            return result;
        }
    }

    public PreparedStatement makeStatement(String q) {
        PreparedStatement stmt = null;
        try {
            switch (dbtype) {
                case 1:
                    Class.forName("com.mysql.jdbc.Driver");
                    break;
                case 2:
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    break;
            }
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.prepareStatement(q);
        } catch (SQLException e) {
            throw new Exception("Servlet Could not display records.", e);
        } catch (ClassNotFoundException e) {
            throw new Exception("JDBC Driver not found.", e);
        } finally {
            return stmt;
        }
    }

    public List execStatement(PreparedStatement stmt) {
        List result = new ArrayList<ResultSet>();
        ResultSet rs = null;
        try {
            stmt.executeQuery();
            result = convertResultSetToList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
            return result;
        }
    }

    public HashMap<String, Object> one(String q, String[] w) {
        HashMap<String, Object> result = null;
        // connecting to database
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            switch (dbtype) {
                case 1:
                    Class.forName("com.mysql.jdbc.Driver");
                    break;
                case 2:
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    break;
            }
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.prepareStatement(q);
            if (w != null) {
                for (int i = 0; i < w.length; i++) {
                    stmt.setString(i + 1, w[i]);
                }
            }
            rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            if (rs.next()) {
                HashMap row = new HashMap(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                result = row;
            } else {
                result = null;
            }
        } catch (SQLException e) {
            throw new Exception("Servlet Could not display records.", e);
        } catch (ClassNotFoundException e) {
            throw new Exception("JDBC Driver not found.", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            return result;
        }
    }

    public Integer nid(String q, String[] w) {
        Integer result = 0;
        // connecting to database
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            switch (dbtype) {
                case 1:
                    Class.forName("com.mysql.jdbc.Driver");
                    break;
                case 2:
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    break;
            }
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            if (w != null) {
                for (int i = 0; i < w.length; i++) {
                    stmt.setString(i + 1, w[i]);
                }
            }
            result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
        } catch (ClassNotFoundException e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    }

    public void n(String q, String[] w) {
        // connecting to database
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            switch (dbtype) {
                case 1:
                    Class.forName("com.mysql.jdbc.Driver");
                    break;
                case 2:
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    break;
            }
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.prepareStatement(q);
            if (w != null) {
                for (int i = 0; i < w.length; i++) {
                    stmt.setString(i + 1, w[i]);
                }
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    private static List<HashMap<String, Object>> convertResultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList list = new ArrayList(50);
        while (rs.next()) {
            HashMap row = new HashMap(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }

        return list;
    }

//    public static void main(String args[]) throws SQLException {
//        String tag = "satu,dua,tiga";
//        String[] tags = tag.split("\\s+");
//        for (int i = 0; i < tags.length; i++) {
//            System.out.println(tags[i]);
//        }
//        String[] param = {"2"};
//        Query q = new Query("jdbc:mysql://localhost:3306/ta", "root", "");
//        List<HashMap<String, Object>> r = q.all("select * from task where task_id = ?", param);
//        if (r.isEmpty()) {
//            System.out.println("error");
//        } else {
//            for (Entry<String, Object> e : r.get(0).entrySet()) {
//                System.out.println(e.getKey());
//                System.out.println(e.getValue());
//            }
//        }
//    }
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        Query q = new Query("Oracle", "jdbc:oracle:thin:@localhost:1521:xe", "inameta", "inameta");
        String[] param = {"2", "Irfan"};
        //q.n("insert into aas (ID_NM,NAMA) values (?,?)",param);
        String[] param2 = {"2"};
        List<HashMap<String, Object>> r = q.all("select * from aas where ID_NM = ?", param2);
        if (r.isEmpty()) {
            System.out.println("error");
        } else {
            for (Entry<String, Object> e : r.get(0).entrySet()) {
                System.out.println(e.getKey());
                System.out.println(e.getValue());
            }
        }
    }
}
