package TrabalhoC3.Classes;

import java.util.ArrayList;

public class NoAVL {
     
    private ArrayList<Compra> informacao;
    private long CPF;
    private NoAVL esquerda, direita; 
    private byte fatorBalanceamento; 
    
    public NoAVL() {}

    NoAVL (long CPF) {

        this.informacao = new ArrayList<>();
        this.CPF = CPF;
        this.fatorBalanceamento = 0; 
    } 

    public Long getCPF(){
        
        return this.CPF;
    }
    public NoAVL getItemDireita () {

        return this.direita;
    }

    public void setItemDireita (NoAVL novoNoDireito) {

        this.direita = novoNoDireito;
    } 

    public NoAVL getItemEsquerdo () {

        return this.esquerda;
    } 

    public void setItemEsquerdo (NoAVL novoNoEsquerdo) {

        this.esquerda = novoNoEsquerdo;
    } 

    public byte getFatorBalanceamento () {

        return this.fatorBalanceamento;
    } 
    
    protected void setFatorBalanceamento (byte novoFatorBalanceamento) {

        this.fatorBalanceamento = novoFatorBalanceamento;
    } 

    public ArrayList<Compra> getInformacao () {

        return this.informacao;
    }

    public void adicionar (Compra compra) {

        informacao.add(compra);
    }
    
}