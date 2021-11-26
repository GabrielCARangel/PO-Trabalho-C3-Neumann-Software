package TrabalhoC3.Execucao;

import TrabalhoC3.Classes.*;
import TrabalhoC3.Manipuladores.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ExecucaoHash {
    
    public static void executarHash (String nomeArquivo) {
        
        double[] resultadoTempos = new double[5];
        double tempoInicial = 0, tempoFinal = 0;

        for (byte contador = 0; contador < resultadoTempos.length; contador++) {

            tempoInicial = System.nanoTime();
            ListaHash listaHash = new ListaHash((int) ((AplicativoTeste.compra.getVetorCompra().size())*1.1)+1);

            for (int contadorInserir = 0; contadorInserir < AplicativoTeste.compra.getVetorCompra().size(); contadorInserir++) {

                listaHash.adicionarListaHash(AplicativoTeste.compra.get(contadorInserir));
            }

            pesquisarHash(nomeArquivo, listaHash);
            tempoFinal = System.nanoTime();
            resultadoTempos[contador] = tempoFinal - tempoInicial;
        }

        AplicativoTeste.gravarTempoPesquisa("Lista Hash", nomeArquivo, resultadoTempos, true);
    }

    private static void pesquisarHash (String nomeArquivo, ListaHash listaHash) {

        GravaDados gravarArquivo = null;
        double totalCompras = 0;
        SimpleDateFormat dataSimples = new SimpleDateFormat("dd/MM/yyyy");

        try {
        
            gravarArquivo = new GravaDados("TrabalhoC3/ResultadoCompras/ListaHash_" +nomeArquivo, false);

        } catch (Exception erro) {

            System.err.print(erro);
        }

        for (int contadorCPF = 0; contadorCPF < AplicativoTeste.listaCPFs.size(); contadorCPF++) {

            ArrayList <Compra> lista = listaHash.pesquisarCPF(AplicativoTeste.listaCPFs.get(contadorCPF));

                if (lista.isEmpty() == false) {

                    gravarArquivo.gravar("Cliente: " +(lista.get(0).getCliente().getNome()) +" | CPF: " +(lista.get(0).getCliente().getCPF()) +"\n");

                    for (int contadorLista = 0; contadorLista < lista.size(); contadorLista++) {
                        
                        gravarArquivo.gravar("\n	Data: " +dataSimples.format((lista.get(contadorLista).getData().getTime())) +"	Valor: R$ " +(lista.get(contadorLista).getValor()));
                        totalCompras += lista.get(contadorLista).getValor();
                    }
                    
                    gravarArquivo.gravar("\n\nTotal Geral: R$ " +totalCompras);
                    totalCompras = 0;
                    gravarArquivo.gravar(AplicativoTeste.quebraDeLinha);

            } else {

				gravarArquivo.gravar("Não há nenhuma compra com o CPF: " + AplicativoTeste.listaCPFs.get(contadorCPF));
				gravarArquivo.gravar(AplicativoTeste.quebraDeLinha);
            }
        }
    }

}