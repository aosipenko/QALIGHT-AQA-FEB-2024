package org.web.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.HashMap;

@Configuration
public class BeanConfig {

    private final static String ENV_TYPE = System.getProperty("driver.type", "CHROME");
    private final static String ENV_TYPE_JENKINS = "JENKINS";

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dmds =
                new DriverManagerDataSource(getSQLHost(), "user", "password");
        dmds.setDriverClassName("com.mysql.jdbc.Driver");
        return dmds;
    }

    private String getSQLHost() {
        if (ENV_TYPE_JENKINS.equalsIgnoreCase(ENV_TYPE)) {
            return "jdbc:mysql://mysql-db-1:3306/db";
        }
        return "jdbc:mysql://localhost:3306/db";
    }
}
