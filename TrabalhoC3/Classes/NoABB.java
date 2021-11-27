package TrabalhoC3.Classes;

import java.util.ArrayList;

public class NoABB {

    private ArrayList<Compra> infomacaoCompra;
    private long CPF;
    private NoABB direita, esquerda;

    public NoABB() {}

    public NoABB (long CPF) {

        this.CPF = CPF;
        this.infomacaoCompra = new ArrayList<>();
    }

    public Long getCPF() {
        
        return this.CPF;
    }

    public NoABB getDireita () {

        return direita;
    }

    public void setDireita (NoABB direita) {

        this.direita = direita;
    }

    public NoABB getEsquerda () {

        return esquerda;
    }

    public void setEsquerda (NoABB esquerda) {

        this.esquerda = esquerda;
    }

    public ArrayList<Compra> getInformacao () {

        return infomacaoCompra;
    }
    
    public void setInformacao (ArrayList<Compra> novaInformacaoCompra) {
        
        this.infomacaoCompra = novaInformacaoCompra;
    }

    public void adicionar (Compra compra) {
        
        infomacaoCompra.add(compra);
    }

}