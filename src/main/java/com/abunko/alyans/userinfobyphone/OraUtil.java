package com.abunko.alyans.userinfobyphone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

public final class OraUtil {
    private OraUtil(){}
    public static String clobToString(java.sql.Clob data) {
        final StringBuilder sb = new StringBuilder();

        try {
            final Reader reader = data.getCharacterStream();
            final BufferedReader br = new BufferedReader(reader);

            int b;
            while (-1 != (b = br.read())) {
                sb.append((char) b);
            }

            br.close();
        } catch (SQLException e) {
            System.out.println("SQL. Could not convert CLOB to string" + e);
            return e.toString();
        } catch (IOException e) {
            System.out.println("IO. Could not convert CLOB to string" + e);
            return e.toString();
        }

        return sb.toString();
    }
}
