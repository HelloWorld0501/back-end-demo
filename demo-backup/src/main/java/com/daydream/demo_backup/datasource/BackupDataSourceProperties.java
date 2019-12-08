package com.daydream.demo_backup.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/19
 * \* Time: 1:07
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
@ConfigurationProperties(prefix = "backenddemo.backup.datasource")
public class BackupDataSourceProperties {
    private String host;
    private String userName;
    private String password;
    private String database;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}