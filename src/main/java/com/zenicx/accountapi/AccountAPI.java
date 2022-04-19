package com.zenicx.accountapi;

import com.zenicx.accountapi.database.DataInteraction;
import com.zenicx.accountapi.database.Database;

public class AccountAPI {
    private Database database;
    private DataInteraction dataInteraction;
    public static AccountAPI accountAPI;

    public void start(Database database) {
        this.database = database;
        this.dataInteraction = new DataInteraction(database);

        this.database.start();
        accountAPI = this;
    }

    public void end() {
        database.shutdown();

        dataInteraction = null;
        database = null;
    }

    public Database getDatabase() {
        return database;
    }

    public DataInteraction getDataInteraction() {
        return dataInteraction;
    }

    public static AccountAPI getAccountAPI() {
        return accountAPI;
    }
}
