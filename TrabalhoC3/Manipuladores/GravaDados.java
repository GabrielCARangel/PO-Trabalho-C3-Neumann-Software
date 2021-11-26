package TrabalhoC3.Manipuladores;

import java.io.*;

public class GravaDados {

	private FileWriter auxiliarDados;
	private PrintWriter inserirDados;

	public GravaDados (String nomeArquivo, boolean inserirNoFinal) throws IOException {

		try {
			/*
			Para somente inserir novos dados no final do arquivo passe TRUE;
			Caso seja para sobrescrever o arquivo passe FALSE.
			Quando não existir um arquivo com o nome passado, um novo será criado.
			*/

			auxiliarDados = new FileWriter(new File(nomeArquivo), inserirNoFinal);
			inserirDados = new PrintWriter(auxiliarDados);
		}

		catch (IOException erro) {

			throw new IOException ("O arquivo não pode ser aberto para a gravação.");
		}
	}

	public void gravar (String novoDado) {

			this.inserirDados.print(novoDado);
	}

	public void fechar () throws IOException {

		try {

			this.inserirDados.close();
			this.auxiliarDados.close();
		}

		catch (IOException erro) {

			throw new IOException ("Ocorreu um erro ao fechar o arquivo.");
		}
	}

}