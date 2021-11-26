package TrabalhoC3.Interfaces;

import java.util.Calendar;

import TrabalhoC3.Classes.Compra;

public interface Vetor {
    
    public Compra get (int posicao);
    public void inserir (Compra compra);
    public void remover (long cpf, Calendar data);
}