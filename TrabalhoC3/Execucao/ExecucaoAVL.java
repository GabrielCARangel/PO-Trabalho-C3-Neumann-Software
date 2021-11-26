package TrabalhoC3.Execucao;

import java.text.SimpleDateFormat;
import TrabalhoC3.Classes.*;
import TrabalhoC3.Manipuladores.GravaDados;

public class ExecucaoAVL {

    public static void executarAVL (String nomeArquivo) {

        double[] resultadoTempos = new double[5];
        double tempoInicial = 0, tempoFinal = 0;

        for (byte contador = 0; contador < resultadoTempos.length; contador++) {

            tempoInicial = System.nanoTime();
            ArvoreAVL arvoreAVL = new ArvoreAVL();
            
            for (int contadorInsercao = 0; contadorInsercao < AplicativoTeste.compra.getVetorCompra().size(); contadorInsercao++) {

                arvoreAVL.inserir(AplicativoTeste.compra.getVetorCompra().get(contadorInsercao));
            }

            pesquisarAVL(nomeArquivo, arvoreAVL);
            tempoFinal = System.nanoTime();
            resultadoTempos[contador] = tempoFinal - tempoInicial;
        }

        AplicativoTeste.gravarTempoPesquisa("Árvore AVL", nomeArquivo, resultadoTempos, true);
    }

    private static void pesquisarAVL (String nomeArquivo, ArvoreAVL arvoreAVL) {

        NoAVL noPesquisa;
        GravaDados gravarArquivo = null;
        double totalCompras = 0;
        SimpleDateFormat dataSimples = new SimpleDateFormat("dd/MM/yyyy");

        try {
        
            gravarArquivo = new GravaDados("TrabalhoC3/ResultadoCompras/ArvoreAVL_" +nomeArquivo, false);

        } catch (Exception erro) {

            System.err.print(erro);
        }

        for (int contadorCPF = 0; contadorCPF < AplicativoTeste.listaCPFs.size(); contadorCPF++) {

            noPesquisa = arvoreAVL.pesquisa(AplicativoTeste.listaCPFs.get(contadorCPF));

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