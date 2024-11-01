package services;

import repository.MesaRepository;

import java.util.Map;

public class MesaService {

    private final MesaRepository mesaRepository = new MesaRepository();
    public Map<Integer, Double[]> obtenerPosicionesMesas(){
        return mesaRepository.obtenerPosicionesMesas();
    }
}
