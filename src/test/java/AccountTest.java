import com.zenicx.accountapi.AccountAPI;
import com.zenicx.accountapi.database.Database;
import com.zenicx.accountapi.models.Account;
import com.zenicx.accountapi.models.User;
import com.zenicx.accountapi.types.UserType;

import java.util.concurrent.CompletableFuture;

public class AccountTest {
    private final AccountAPI accountAPI;
    private final Database database;

    public AccountTest() {
        this.accountAPI = new AccountAPI();
        this.database = new Database(
                "address",
                "name",
                "username",
                "password",
                "port"
        );

        this.accountAPI.start(database);
    }

    /**
     * Always run the code in this function, otherwise errors will occur.
     */
    public void endSession() {
        accountAPI.end();
    }

    public void acceptUserSignUp(String login, String password) {
        accountAPI.getDataInteraction().createAccount(login, password);
        System.out.println("Successfully created login details for " + login);
    }

    public CompletableFuture<Boolean> isValid(String login, UserType userType, String password) {
        User user = new User(userType, login);

        return Account.getAccountByUser(user).thenApply(acc -> password.equals(acc.getPassword()));
    }

    public void handleLogin(String login, UserType userType, String password) {
        isValid(login, userType, password).thenAcceptAsync(b -> {
            if (b) {
                System.out.println(login + " has successfully logged in.");
            } else {
                System.out.println(login + " tried to login, but " + login + " provided the wrong password.");
            }
        });
    }
}
