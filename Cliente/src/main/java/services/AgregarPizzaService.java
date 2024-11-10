package services;

import repository.agregarPizzaRepositorio;


public class AgregarPizzaService {

    private agregarPizzaRepositorio pizzas=new agregarPizzaRepositorio();

    public int getPrecios(String pizza){

       return pizzas.getPrecios(pizza);
    }

}
