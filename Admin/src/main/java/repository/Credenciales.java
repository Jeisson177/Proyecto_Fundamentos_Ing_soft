package repository;

public class Credenciales {

    private String URL="jdbc:mysql://localhost:3306/proyecto ingesoft";
    private String User="root";
    private String password="cl";



    public String getUser() {
        return User;
    }

    public String getURL() {
        return URL;
    }

    public String getPassword() {
        return password;
    }
}
