package TrabalhoC3.Execucao;

import java.text.SimpleDateFormat;
import TrabalhoC3.Classes.*;
import TrabalhoC3.Manipuladores.GravaDados;

public class ExecucaoAVL {
    public static void executar (String nomeArquivo, CadastraCompra compra) {

        ArvoreAVL arvore;
        double[] resultadoTempos = new double[5];
        double tempoInicial = 0, tempoFinal = 0;

        for (byte contador = 0; contador < resultadoTempos.length; contador++) {

            tempoInicial = System.nanoTime();
            arvore  = new ArvoreAVL();
            for (int contadorInsercao = 0; contadorInsercao < compra.getVetorCompra().size(); contadorInsercao++) {

                arvore.inserir(AplicativoTeste.compra.getVetorCompra().get(contadorInsercao));
            }
            pesquisarAVL(nomeArquivo, arvore);

            tempoFinal = System.nanoTime();
            resultadoTempos[contador] = tempoFinal - tempoInicial;
        }

        AplicativoTeste.gravarTempoPesquisa("Árvore AVL", nomeArquivo, resultadoTempos);
        // AplicativoTeste.gravarTempoPesquisa(String tipoPesquisa, String nomeArquivo, double[] tempoResultados)
    }

    private static void pesquisarAVL (String nomeArquivo, ArvoreAVL arvore) {

        NoAVL noPesquisa = new NoAVL();
        GravaDados gravarArquivo = null;
        double totalCompras = 0;
        SimpleDateFormat dataSimples = new SimpleDateFormat("dd/MM/yyyy");

        try {
        
            gravarArquivo = new GravaDados("TrabalhoC3/ResultadoCompras/ArvoreAVL" +nomeArquivo, false);

        } catch (Exception erro) {

            System.err.print(erro);
        }

        for (int contadorCPF = 0; contadorCPF < AplicativoTeste.listaCPFs.size(); contadorCPF++) {

            noPesquisa = arvore.pesquisa(AplicativoTeste.listaCPFs.get(contadorCPF));

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