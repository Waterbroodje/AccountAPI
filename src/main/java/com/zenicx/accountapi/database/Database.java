package com.zenicx.accountapi.database;

import com.zaxxer.hikari.HikariDataSource;

public class Database {
    private HikariDataSource hikari;
    private final String address, name, username, password, port;

    public Database(String address, String name, String username, String password, String port) {
        this.address = address;
        this.name = name;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public void start() {
        hikari = new HikariDataSource();
        hikari.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", address);
        hikari.addDataSourceProperty("port", port);
        hikari.addDataSourceProperty("databaseName", name);
        hikari.addDataSourceProperty("user", username);
        hikari.addDataSourceProperty("password", password);
    }

    public void shutdown() {
        hikari.close();
    }

    public HikariDataSource getHikari() {
        return hikari;
    }
}