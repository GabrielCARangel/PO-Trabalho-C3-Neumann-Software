package TrabalhoC3.Classes;

import java.util.ArrayList;

public class ArvoreABB {

    private NoABB raiz;
    private int numeroElementos;

    public ArvoreABB() {

        this.numeroElementos = 0;
    }

    public boolean eVazia() {

        if (this.raiz == null) {

            return true;

        } else {

            return false;
        }
    }

    public NoABB getRaiz() {

        return raiz;
    }

    public int getNumeroElementos() {

        return numeroElementos;
    }

    public NoABB pesquisarABB(long CPF) {

        return fazerPesquisa(CPF, this.raiz);
    }
    
    private NoABB fazerPesquisa(long CPF, NoABB no) {

        if (no == null) {

            return null;

        } else if (CPF < no.getCPF()) {

            no = fazerPesquisa(CPF, no.getEsquerda());

        } else if (CPF > no.getCPF()) {

            no = fazerPesquisa(CPF, no.getDireita());
        }

        return no;
    }

    public boolean inserir(Compra elemento) {

        this.raiz = fazerInsercao(elemento, this.raiz);
        return true;
    }

    private NoABB fazerInsercao(Compra elemento, NoABB no) {

        if (no == null) {

            NoABB novo = new NoABB(elemento.getCliente().getCPF());
            novo.adicionar(elemento);
            this.numeroElementos++;
            return novo;

        } else {

            if (elemento.getCliente().getCPF() > no.getCPF()) {

                no.setDireita(fazerInsercao(elemento, no.getDireita()));

            } else if (elemento.getCliente().getCPF() < no.getCPF()) {

                no.setEsquerda(fazerInsercao(elemento, no.getEsquerda()));

            } else {

                no.adicionar(elemento);
            }
        }

        return no;
    }

    public ArrayList<NoABB> emOrdem() {

        ArrayList<NoABB> compra = new ArrayList<>();
        return (fazEmOrdem(compra, this.raiz));
    }

    private ArrayList<NoABB> fazEmOrdem(ArrayList<NoABB> vetor, NoABB no) {

        if (no != null) {

            vetor = fazEmOrdem(vetor, no.getEsquerda());
            vetor.add(no);
            vetor = fazEmOrdem(vetor, no.getDireita());
        }

        return vetor;
    }

    public ArvoreABB balancear(ArrayList<NoABB> vetor) {

        ArvoreABB arvore = new ArvoreABB();
        fazerBalanceamento(vetor, arvore, 0, vetor.size() - 1);
        return arvore;
    }

    private void fazerBalanceamento(ArrayList<NoABB> vetor, ArvoreABB arvore, int inicio, int fim) {
        
        int meio;
        if (fim >= inicio) {

            meio = fim + inicio / 2;
            vetor.get(meio).getInformacao().forEach((compra) -> arvore.inserir(compra));
            fazerBalanceamento(vetor, arvore, inicio, meio - 1);
            fazerBalanceamento(vetor, arvore, meio + 1, fim);
        }
    }

    public void pegarTodos(ArrayList<Compra> vetor, NoABB no) {

        for (int contador = 0; contador < no.getInformacao().size(); contador++) {

            vetor.add(no.getInformacao().get(contador));
        }
    }

}