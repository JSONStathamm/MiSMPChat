package ru.team4.mismpm.user;

public class UserSettings {

    private boolean stealthMode;
    private boolean accountClosed;

    public UserSettings() {
        stealthMode = false;
        accountClosed = false;
    }

    public boolean isStealthMode() {
        return stealthMode;
    }

    public void setStealthMode(boolean stealthMode) {
        this.stealthMode = stealthMode;
    }

    public boolean isAccountClosed() {
        return accountClosed;
    }

    public void setAccountClosed(boolean accountClosed) {
        this.accountClosed = accountClosed;
    }
}
