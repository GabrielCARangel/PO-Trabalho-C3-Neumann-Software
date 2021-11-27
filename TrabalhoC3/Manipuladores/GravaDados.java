package TrabalhoC3.Manipuladores;

import java.io.*;

public class GravaDados {

	private FileWriter auxiliarDados;
	private PrintWriter inserirDados;

	public GravaDados (String nomeArquivo, boolean inserirNoFinal) throws IOException {

		try {

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