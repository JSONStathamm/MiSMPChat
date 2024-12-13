package ru.team4.mismpm.authentication;

import ru.team4.mismpm.storage.DatabaseService;
import ru.team4.mismpm.user.User;
import ru.team4.mismpm.user.UserData;

public class AuthenticationService {

    private final DatabaseService databaseService;
    private long currentUserId = 0;

    public AuthenticationService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public User register(String email, String password, String fullName) {
        if (email != null && password != null) {
            User user = new User(++currentUserId, new UserData(null, fullName, email));
            databaseService.getUserStorage().save(currentUserId, user);
            databaseService.getAuthenticationStorage().save(new AuthenticationPair(email, password), currentUserId);
            return user;
        }
        return null;
    }

    public boolean authenticate(AuthenticationPair pair) {
        return databaseService.getAuthenticationStorage().contains(pair);
    }

    public boolean resetPassword(AuthenticationPair pair, String newPassword) {
        long userId = databaseService.getAuthenticationStorage().get(pair);
        if (userId != -1) {
            databaseService.getAuthenticationStorage().remove(pair);
            databaseService.getAuthenticationStorage().save(new AuthenticationPair(pair.getEmail(), newPassword), userId);
            return true;
        }
        return false;
    }

}
