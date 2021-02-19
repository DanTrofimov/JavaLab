package ru.itis.trofimoff.todoapp.dto;

public class SignInFormDto {
    private String email;
    private String password; // need to replace by hashPassword

    public SignInFormDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
