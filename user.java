package src.pingtuGames;

public class user {
//    用户的信息
    private String userName;
    private String userPassword;

    public user() {
    }

    public user(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 获取
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString() {
        return "user{userName = " + userName + ", userPassword = " + userPassword + "}";
    }
}
