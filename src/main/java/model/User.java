package model;

public class User {
    String name;
    String lastname;
    String login;
    String password;

    public User(String name, String lastname, String login, String password) {
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public boolean logIN(String login, String password){
        boolean log=false;

        if(login.equals(this.login)){
            if(password.equals((this.password))){
                log=true;
            }else throw new IllegalArgumentException("Błędne dane logowania");
        }else throw new IllegalArgumentException("Błędne dane logowania");

        return log;
    }
}
