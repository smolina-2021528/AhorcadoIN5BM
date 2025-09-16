package modelo;

/**
 *
 * @author informatica
 */
public class User {
    
    private int user_code;
    private String user_name;
    private String user_password;

    public User() {}

    public User(int userCode, String userName, String userPassword) {
        this.user_code = userCode;
        this.user_name = userName;
        this.user_password = userPassword;
    }

    public int getUser_code() {
        return user_code;
    }

    public void setUser_code(int user_code) {
        this.user_code = user_code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    

   
}
