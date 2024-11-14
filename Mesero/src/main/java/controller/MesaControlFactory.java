package controller;

import controller.menuMesero.MesaControl;

public class MesaControlFactory {
    public MesaControl createMesaControl() {
        return new MesaControl();
    }
}
