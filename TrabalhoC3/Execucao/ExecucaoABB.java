package TrabalhoC3.Execucao;

import java.util.ArrayList;
import TrabalhoC3.Classes.*;
import TrabalhoC3.Manipuladores.*;
import java.text.SimpleDateFormat;

public class ExecucaoABB {

    public static void executarABB (String nomeArquivo) {

        ArvoreABB arvoreBalanceada;
        ArrayList<NoABB> vetorOrdenado = new ArrayList<>();
        double[] resultadoTempos = new double[5];
        double tempoInicial = 0, tempoFinal = 0;

            for (byte contador = 0; contador < resultadoTempos.length; contador++) {

                tempoInicial = System.nanoTime();
                arvoreBalanceada = new ArvoreABB();
                
                for (int contadorInsercao = 0; contadorInsercao < AplicativoTeste.compra.getVetorCompra().size(); contadorInsercao++) {

                    arvoreBalanceada.inserir(AplicativoTeste.compra.getVetorCompra().get(contadorInsercao));
                }

                vetorOrdenado = arvoreBalanceada.emOrdem();
                arvoreBalanceada = arvoreBalanceada.balancear(vetorOrdenado);
                pesquisarABB(nomeArquivo, arvoreBalanceada);

                tempoFinal = System.nanoTime();
                resultadoTempos[contador] = tempoFinal - tempoInicial;
            }

            AplicativoTeste.gravarTempoPesquisa("Árvore ABB", nomeArquivo, resultadoTempos, true);     
    }

    private static void pesquisarABB (String nomeArquivo, ArvoreABB arvoreBalanceada) {

        NoABB noPesquisa;
        GravaDados gravarArquivo = null;
        double totalCompras = 0;
        SimpleDateFormat dataSimples = new SimpleDateFormat("dd/MM/yyyy");

        try {
        
            gravarArquivo = new GravaDados("TrabalhoC3/ResultadoCompras/ArvoreABB_" +nomeArquivo, false);

        } catch (Exception erro) {

            System.err.print(erro);
        }

        for (int contadorCPF = 0; contadorCPF < AplicativoTeste.listaCPFs.size(); contadorCPF++) {

            noPesquisa = arvoreBalanceada.pesquisarABB(AplicativoTeste.listaCPFs.get(contadorCPF));

            if (noPesquisa != null) {

                gravarArquivo.gravar("Cliente: " +(noPesquisa.getInformacao().get(0).getCliente().getNome()) +" | CPF: " +(noPesquisa.getInformacao().get(0).getCliente().getCPF()) +"\n");

                for (int contadorLista = 0; contadorLista < noPesquisa.getInformacao().size(); contadorLista++) {
                    
					gravarArquivo.gravar("\n	Data: " +dataSimples.format((noPesquisa.getInformacao().get(contadorLista).getData().getTime())) +"	Valor: R$ " +(noPesquisa.getInformacao().get(contadorLista).getValor()));
					totalCompras += noPesquisa.getInformacao().get(contadorLista).getValor();
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