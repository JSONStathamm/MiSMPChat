package ru.team4.mismpm.user;

public class UserData {

    private String nickname;
    private String fullName;
    private String email;

    public UserData(String nickname, String fullName, String email) {
        this.nickname = nickname;
        this.fullName = fullName;
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFullName() {
        return fullName != null ? fullName : email;
    }

    public String getEmail() {
        return email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
