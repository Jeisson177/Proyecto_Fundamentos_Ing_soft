package services;

import repository.menu.UsuarioRepository;

import java.util.List;

public class loginService {

    private UsuarioRepository log=new UsuarioRepository();
    public int AutentificarUsuario(String e,String p){
        return log.AutentificarUsuario(e,p);

    }
    public int CrearUsuario(String e,String c,String n,String t){

        return log.CrearUsuario(e,c,n,t);
    }

    public List<String> ConsultarReservas(int id){
        return log.ConsultarReservas(id);

    }
}
