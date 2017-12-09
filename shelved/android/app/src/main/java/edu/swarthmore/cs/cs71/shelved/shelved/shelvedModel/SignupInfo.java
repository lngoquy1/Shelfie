package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

public class SignupInfo {
    private String name;
    private String email;
    private String password;

    public SignupInfo(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
