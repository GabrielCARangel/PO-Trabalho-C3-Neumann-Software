package TrabalhoC3.Classes;

import java.util.ArrayList;
import java.util.Calendar;
import TrabalhoC3.Interfaces.*;

public class CadastraCompra implements Vetor, Ordernacao {

    private ArrayList<Compra> vetorCompra;

    public CadastraCompra () {

        this.vetorCompra = new ArrayList<>();
    }

    public ArrayList<Compra> getVetorCompra () {

        return vetorCompra;
    }

    public void setVetorCompra (ArrayList<Compra> novoVetorCompra) {

        this.vetorCompra = novoVetorCompra;
    }

    @Override
    public Compra get (int posicao) {

        return vetorCompra.get(posicao);
    }

    @Override
    public void inserir (Compra compra) {

        vetorCompra.add(compra);
    }

    @Override
    public void remover (long CPF, Calendar data) {

        for (int contador = 0; contador < vetorCompra.size(); contador++) {

            if ((vetorCompra.get(contador).getCliente().getCPF() == CPF) && (vetorCompra.get(contador).getData() == data)) {

                vetorCompra.remove(contador);
                contador--;
            }
        }
    }

    @Override
    public void insercaoDireta () {

        int auxiliar01, auxiliar02;
        Compra temporario;

        for (auxiliar01 = 1; auxiliar01 < this.vetorCompra.size(); auxiliar01++) {

            temporario = this.vetorCompra.get(auxiliar01);
            auxiliar02 = auxiliar01 - 1;
            
            while ((auxiliar02 >= 0) && (verificarMaior(auxiliar02, temporario) == 1)) {
            
                this.vetorCompra.set(auxiliar02 + 1, this.vetorCompra.get(auxiliar02));
                auxiliar02--;
            }
            
            this.vetorCompra.set(auxiliar02 + 1, temporario);
        }
    }

    @Override
    public void shellSort () {

        int auxiliar01, auxiliar02, auxiliar03 = 1;
        Compra temporario;

        do {
            auxiliar03 = 3 * auxiliar03 + 1;

        } while (auxiliar03 < this.vetorCompra.size());
        
        do {
            auxiliar03 = auxiliar03 / 3;
            
            for (auxiliar01 = auxiliar03; auxiliar01 < this.vetorCompra.size(); auxiliar01++) {
            
                temporario = this.vetorCompra.get(auxiliar01);
                auxiliar02 = auxiliar01;
            
                while (verificarMaior((auxiliar02 - 1), temporario) == 1) {
            
                    this.vetorCompra.set(auxiliar02, this.vetorCompra.get(auxiliar02 - auxiliar03));
                    auxiliar02 -= auxiliar03;
            
                    if (auxiliar02 < auxiliar03) {
                        break;
                    }
                }
            
                this.vetorCompra.set(auxiliar02, temporario);
            }
        } while (auxiliar03 != 1);
    
    }

    @Override
    public void quickSort () {

        fazerQuickSort(0, this.vetorCompra.size() - 1);
    }

    private void fazerQuickSort (int esquerda, int direita) {

        int auxiliar01 = esquerda, auxiliar02 = direita;
        Compra temporario, pivo;
        pivo = this.vetorCompra.get((auxiliar01 + auxiliar02) / 2);
        
        do {
            
            while (verificarMaior(auxiliar01, pivo) == -1) {
                auxiliar01++;
            }

            while (verificarMaior(auxiliar02, pivo) == 1) {
                auxiliar02--;
            }

            if (auxiliar01 <= auxiliar02) {
            
                temporario = this.vetorCompra.get(auxiliar01);
                this.vetorCompra.set(auxiliar01, this.vetorCompra.get(auxiliar02));
                this.vetorCompra.set(auxiliar02, temporario);
                auxiliar01++;
                auxiliar02--;
            }

        } while (auxiliar01 <= auxiliar02);
        
        if (esquerda < auxiliar02) {
            fazerQuickSort(esquerda, auxiliar02);
        }
        
        if (direita > auxiliar01) {
            fazerQuickSort(auxiliar01, direita);
        }

    }

    @Override
    public void quickComInsercao () {

        fazerQuickInsercao(0, this.vetorCompra.size() - 1);
    }

    private void fazerQuickInsercao (int esquerda, int direita) {

        int auxiliar01 = esquerda, auxiliar02 = direita;
        Compra temporario, pivo;
        pivo = this.vetorCompra.get((auxiliar01 + auxiliar02) / 2);
        
        do {

            while (verificarMaior(auxiliar01, pivo) == -1) {
                auxiliar01++;
            }

            while (verificarMaior(auxiliar02, pivo) == 1) {
                auxiliar02--;
            }

            if (auxiliar01 <= auxiliar02) {
            
                temporario = this.vetorCompra.get(auxiliar01);
                this.vetorCompra.set(auxiliar01, this.vetorCompra.get(auxiliar02));
                this.vetorCompra.set(auxiliar02, temporario);
                auxiliar01++;
                auxiliar02--;
            }

        } while (auxiliar01 <= auxiliar02);
        
        if (esquerda < auxiliar02) {
        
            if ((auxiliar02 - esquerda) <= 20) {
            
                insercaoDiretaModificada(esquerda, auxiliar02);
        
            } else {
            
                fazerQuickInsercao(esquerda, auxiliar02);
            }
        }
        
        if (direita > auxiliar01) {
        
            if ((direita - auxiliar01) <= 20) {
            
                insercaoDiretaModificada(auxiliar01, direita);
        
            } else {
            
                fazerQuickInsercao(auxiliar01, direita);
            }
        }
    
    }

    private void insercaoDiretaModificada (int inicio, int fim) {

        int auxiliar;
        Compra temporario;

        for (int contador = inicio; contador <= fim; contador++) {

            temporario = this.vetorCompra.get(contador);
            auxiliar = contador - 1;
            
            while ((auxiliar >= 0) && (verificarMaior(auxiliar, temporario) == 1)) {
            
                this.vetorCompra.set(auxiliar + 1, this.vetorCompra.get(auxiliar));
                auxiliar--;
            }
            
            this.vetorCompra.set(auxiliar + 1, temporario);
        }
    }

    public int verificarMaior (int auxiliar, Compra temporario) {

        if (this.vetorCompra.get(auxiliar).getCliente().getCPF() > temporario.getCliente().getCPF()) {
            
            return 1;

        } else if (this.vetorCompra.get(auxiliar).getCliente().getCPF() < temporario.getCliente().getCPF()) {
            
            return -1;

        } else {
        
            if (this.vetorCompra.get(auxiliar).getData().compareTo(temporario.getData()) > 0) {
            
                return 1;
        
            } else if (this.vetorCompra.get(auxiliar).getData().compareTo(temporario.getData()) < 0) {
            
                return -1;
        
            } else {
            
                return 0;
            }
        }
    }

}