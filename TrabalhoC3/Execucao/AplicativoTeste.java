package TrabalhoC3.Execucao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import TrabalhoC3.Classes.CadastraCompra;
import TrabalhoC3.Manipuladores.*;

public class AplicativoTeste {

	static ArrayList <Long> listaCPFs = lerCPFs();
	static String quebraDeLinha = "\n\n---------------------------------------------------------------------------------\n\n";
	static CadastraCompra compra = new CadastraCompra();

    public static void main (String[] args) {

		executarPrograma();
		System.exit(0);
	}

	private static void executarPrograma () {
		
		execucaoLote();
	}

	private static void execucaoLote () {
		
		System.out.print("\n\nExecutando os métodos de pesquisa em 500 dados aleatórios.");	
        lerDados(compra, "TrabalhoC3/Dados/500_Aleatorios.txt");	
		execucaoTipoLote("500_Aleatorios.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 500 dados invertidos.");
        lerDados(compra, "TrabalhoC3/Dados/500_Invertidos.txt");	
		execucaoTipoLote("500_Invertidos.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 500 dados ordenados.");
        lerDados(compra, "TrabalhoC3/Dados/500_Ordenados.txt");
		execucaoTipoLote("500_Ordenados.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 1000 dados aleatórios.");
        lerDados(compra, "TrabalhoC3/Dados/1000_Aleatorios.txt");
		execucaoTipoLote("1000_Aleatorios.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 1000 dados invertidos.");
        lerDados(compra, "TrabalhoC3/Dados/1000_Invertidos.txt");
		execucaoTipoLote("1000_Invertidos.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 1000 dados ordenados.");
        lerDados(compra, "TrabalhoC3/Dados/1000_Ordenados.txt");
		execucaoTipoLote("1000_Ordenados.txt");
		
		System.out.print("\nExecutando os métodos de pesquisa em 5000 dados aleatórios.");
        lerDados(compra, "TrabalhoC3/Dados/5000_Aleatorios.txt");
		execucaoTipoLote("5000_Aleatorios.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 5000 dados invertidos.");
        lerDados(compra, "TrabalhoC3/Dados/5000_Invertidos.txt");
		execucaoTipoLote("5000_Invertidos.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 5000 dados ordenados.");
        lerDados(compra, "TrabalhoC3/Dados/5000_Ordenados.txt");
		execucaoTipoLote("5000_Ordenados.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 10000 dados aleatórios.");
        lerDados(compra, "TrabalhoC3/Dados/10000_Aleatorios.txt");
		execucaoTipoLote("10000_Aleatorios.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 10000 dados invertidos.");
        lerDados(compra, "TrabalhoC3/Dados/10000_Invertidos.txt");
		execucaoTipoLote("10000_Invertidos.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 10000 dados ordenados.");
        lerDados(compra, "TrabalhoC3/Dados/10000_Ordenados.txt");
		execucaoTipoLote("10000_Ordenados.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 50000 dados aleatórios.");
        lerDados(compra, "TrabalhoC3/Dados/50000_Aleatorios.txt");
		execucaoTipoLote("50000_Aleatorios.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 50000 dados invertidos.");
        lerDados(compra, "TrabalhoC3/Dados/50000_Invertidos.txt");
		execucaoTipoLote("50000_Invertidos.txt");

		System.out.print("\nExecutando os métodos de pesquisa em 50000 dados ordenados.");
        lerDados(compra, "TrabalhoC3/Dados/50000_Ordenados.txt");
		execucaoTipoLote("50000_Ordenados.txt");
		
		System.out.print("\n\nTodos os dados de clientes estão contidos na pasta 'ResultadoCompras'.");
		System.out.print("\nTodos os tempos e resultados finais estão contidos na pasta 'ResultadoTempos'.");
	}

	private static void execucaoTipoLote (String nomeArquivo) {

		double[] auxiliar = null;

		try {
		
			ExecucaoABB.executarABB(nomeArquivo);
		
		} catch (StackOverflowError erro) {

            AplicativoTeste.gravarTempoPesquisa("Árvore ABB", nomeArquivo, auxiliar, false);
        }
		
		ExecucaoAVL.executarAVL(nomeArquivo);
		ExecucaoHash.executarHash(nomeArquivo);
	}
	
	private static void lerDados (CadastraCompra compra, String nomeLeitura) {
		
		try {

			LeDados arquivo = new LeDados(nomeLeitura);
			compra.setVetorCompra(arquivo.ler());
			arquivo.fecha();
		
		} catch (FileNotFoundException erro) {
			
			System.err.print(erro);

		} catch (NumberFormatException erro) {
			
			System.err.print(erro);

		} catch (ArrayIndexOutOfBoundsException erro) {
			
			System.err.print(erro);
		}
	}

	private static ArrayList<Long> lerCPFs () {
		
		ArrayList<Long> listaCPFs = new ArrayList<>();

		try {

			LeDados arquivo = new LeDados("TrabalhoC3/Dados/ListaCPFs.txt");
			listaCPFs = arquivo.lerCPF();
		
		} catch (FileNotFoundException erro) {
			
			System.err.print(erro);
		}
	
		return listaCPFs;
	}

	public static void gravarTempoPesquisa (String tipoPesquisa, String nomeArquivo, double[] tempoResultados, boolean controle) {

		Double auxiliarMedia = 0.0;
		String arquivoResultados = "TrabalhoC3/ResultadoTempos/" +nomeArquivo;
		String quebraDeLinha = "\n\n---------------------------------------------------------------------------------\n\n";

		try {

			if (controle == true) {

				GravaDados gravarResultados = new GravaDados(arquivoResultados, true);
				gravarResultados.gravar("Resultados para a pesquisa em " +tipoPesquisa +" (os resultados estão em segundos):" +"\n\n");

				for (byte contador = 0; contador < tempoResultados.length; contador++) {

					gravarResultados.gravar("	Tempo da " +(contador + 1) +"° Execução: " +(Double.toString((tempoResultados[contador]) / 1e+9)) +"\n");
					auxiliarMedia += tempoResultados[contador];
				}
				
				auxiliarMedia = ((auxiliarMedia/tempoResultados.length) / 1e+9);
				gravarResultados.gravar("\n" +"O tempo médio final é: " +Double.toString(auxiliarMedia));
				gravarResultados.gravar(quebraDeLinha);
				gravarResultados.fechar();
			
			} else {

				GravaDados gravarResultados = new  GravaDados(arquivoResultados, true);
				gravarResultados.gravar("Não foi possível executar o método de pesquisa " +tipoPesquisa +".");
				gravarResultados.gravar(quebraDeLinha);
				gravarResultados.fechar();
			}

		} catch (IOException erro) {

			System.err.print(erro);
		}
	}

}