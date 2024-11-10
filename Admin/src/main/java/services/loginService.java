package services;

import repository.AdminRepository;




public class loginService {

    private AdminRepository log=new AdminRepository();
    public boolean AutentificarAdmin(String e,String p){
        return log.AutentificatAdmin(e,p);

    }

}
