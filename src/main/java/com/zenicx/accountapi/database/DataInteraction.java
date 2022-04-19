package com.zenicx.accountapi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public record DataInteraction(Database database) {

    public void createAccount(String user, String password) {
        contains(user).thenAcceptAsync(b -> {
            if (!b) {
                try (Connection connection = database.getHikari().getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement("INSERT IGNORE INTO accounts (USER, PASSWORD) VALUES (?,?)")) {
                    preparedStatement.setString(1, user);
                    preparedStatement.setString(2, password);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CompletableFuture<Boolean> contains(String user) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = database.getHikari().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE USER=?")) {
                preparedStatement.setString(1, user);

                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    public CompletableFuture<String> getPassword(String user) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = database.getHikari().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT PASSWORD FROM accounts WHERE USER=?")) {
                preparedStatement.setString(1, user);

                ResultSet rs = preparedStatement.executeQuery();
                return rs.getString("PASSWORD");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
