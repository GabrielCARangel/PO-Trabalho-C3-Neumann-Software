package TrabalhoC3.Classes;

import java.util.ArrayList;

public class NoHash {
    
    private ArrayList<Compra> listaFilha;

    public NoHash () {

        listaFilha = new ArrayList<>();
    }

    public ArrayList<Compra> getLista () {

        return listaFilha;
    }

    public void setLista (ArrayList<Compra> novaLista) {

        this.listaFilha = novaLista;
    }
    
    public void add (Compra novaCompra) {

        this.listaFilha.add(novaCompra);
    }
    
}