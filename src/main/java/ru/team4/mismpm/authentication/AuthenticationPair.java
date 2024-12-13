package ru.team4.mismpm.authentication;

import java.util.Objects;

public class AuthenticationPair {

    private final String email;
    private final String password;

    public AuthenticationPair(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AuthenticationPair pair) {
            return email.equals(pair.email) && password.equals(pair.password);
        }
        return false;
    }
}
