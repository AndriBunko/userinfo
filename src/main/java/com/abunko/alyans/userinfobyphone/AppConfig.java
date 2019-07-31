package com.abunko.alyans.userinfobyphone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Hashtable;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource(){
        DataSource source = null;
        try {
            Hashtable environment = new Hashtable();
            environment.put("java.naming.factory.initial", "org.wso2.carbon.tomcat.jndi.CarbonJavaURLContextFactory");
            Context initContext = new InitialContext(environment);
            Object result = initContext.lookup("jdbc/OracleTestDataSource");
            if (result != null) {
                source = (DataSource) result;
            } else {
                System.out.println("Cannot find MyCarbonDataSource");
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return source;
    }

}