package controller;

import view.menu.mon_compte.PanelCompte;

public class EspaceEtudiant {

    private static EspaceEtudiant instance;
    private final PanelCompte panelCompte;

    public EspaceEtudiant() {
        instance = this;
        panelCompte = new PanelCompte(Connexion.getConnectedStudent());
    }

    public static EspaceEtudiant getInstance() {
        return instance;
    }

    public PanelCompte getPanelCompte() {
        return panelCompte;
    }
}
