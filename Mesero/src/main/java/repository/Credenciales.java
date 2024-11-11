package repository;

public class Credenciales {
    private static final String URL = "jdbc:h2:file:C:/Users/lenovo1/Documents/GitHub/Proyecto_Fundamentos_Ing_soft/db/Cliente";

    private static final String User="sa";
    private static final String password="";



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
