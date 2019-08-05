package com.abunko.alyans.userinfobyphone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class UserDao {
    private static final Logger LOG = Logger.getLogger(UserDao.class);

    static {
        SystemOutToLog4j.enableForClass(UserDao.class);
    }

    @Autowired
    private DataSource dataSource;

    public String getUerinfoByPhone(String userPhone) {
        Clob clob = null;
        try {
            LOG.info("create connection");
            Connection connection = dataSource.getConnection();
            String call = "{? = call sr_bank.pkg_sr_ib.client_authentication(?) }";
            CallableStatement callableStatement = connection.prepareCall(call);
            callableStatement.registerOutParameter(1, Types.CLOB);
            callableStatement.setString(2, "<?xml version=\"1.0\"?>\n" +
                    "<ClientAuthenticationRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><PhoneNumber>" + "+380506855457" + "</PhoneNumber></ClientAuthenticationRequest>");
            LOG.info("execute callable statement");
            callableStatement.execute();
            LOG.info("get result");
            clob = callableStatement.getClob(1);
            LOG.info("result " + clob);
        } catch (SQLException e) {
            LOG.error(e.toString());
            e.printStackTrace();
        }
        String result = OraUtil.clobToString(clob);
        LOG.info("resunl: " + result);

        return result;
    }
}
