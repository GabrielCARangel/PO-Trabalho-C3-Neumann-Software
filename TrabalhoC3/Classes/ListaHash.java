package TrabalhoC3.Classes;

import java.util.ArrayList;

public class ListaHash {

    private NoHash[] listaHashing;

    public ListaHash (int tamanhoLista) {

        listaHashing = new NoHash [tamanhoLista];

        for (int contador = 0; contador < listaHashing.length; contador++) {

            listaHashing[contador] = new NoHash();
        }
    }

    public void adicionarListaHash (Compra novaCompra /*CadastraCompra novaCompra*/) {

        int valorHashing = (int) valorHash(novaCompra.getCliente().getCPF());
        listaHashing[valorHashing].add(novaCompra);
    }

    public ArrayList<Compra> pesquisarCPF (long CPF) {

        int hashingPesquisa = (int) valorHash(CPF);

        ArrayList<Compra> listaAuxiliar = listaHashing[hashingPesquisa].getLista();
        ArrayList<Compra> resultadoPesquisa = new ArrayList<>();

        if (listaAuxiliar == null) {

            return null;
        }

        for (int contador = 0; contador < listaAuxiliar.size(); contador++) {

            if (listaAuxiliar.get(contador).getCliente().getCPF() == CPF) {

                resultadoPesquisa.add(listaAuxiliar.get(contador));
            }
        }

        return resultadoPesquisa;
    }

    private long valorHash (long CPF) {

        return CPF % listaHashing.length;
    }

}