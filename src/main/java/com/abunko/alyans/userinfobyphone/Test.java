package com.abunko.alyans.userinfobyphone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class Test {
    public static void main(String[] args) {
        // https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html#package.description
        // auto java.sql.Driver discovery -- no longer need to load a java.sql.Driver class via Class.forName

        // register JDBC driver, optional since java 1.6
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Oracle SID = orcl , find yours in tnsname.ora
        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.1.50.14:1521/test03.alliancebank.local", "ESB", "Q12345678")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
                String call = "{? = call sr_bank.pkg_sr_ib.client_authentication(?) }";
                CallableStatement callableStatement = conn.prepareCall(call);
                callableStatement.registerOutParameter(1, Types.CLOB);
                callableStatement.setString(2, "<?xml version=\"1.0\"?>\n" +
                        "<ClientAuthenticationRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><PhoneNumber>" + "+380506855457" + "</PhoneNumber></ClientAuthenticationRequest>");
                callableStatement.execute();
                Clob clob = callableStatement.getClob(1);
                System.out.println(OraUtil.clobToString(clob));
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

