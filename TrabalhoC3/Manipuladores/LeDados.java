package TrabalhoC3.Manipuladores;

import java.io.*;
import java.util.*;

import TrabalhoC3.Classes.Cliente;
import TrabalhoC3.Classes.ClienteEspecial;
import TrabalhoC3.Classes.Compra;

public class LeDados {

	private Scanner entrada;

	public LeDados (String nomeArquivo) throws FileNotFoundException {

		try {

			this.entrada = new Scanner(new File(nomeArquivo));
		}

		catch (FileNotFoundException excessao) {

			throw new FileNotFoundException("O arquivo não foi encontrado.");
		}
	}

	public ArrayList<Compra> ler() throws ArrayIndexOutOfBoundsException, NumberFormatException {

		ArrayList<Compra> compra = new ArrayList<>();
		String linha;

		while (this.entrada.hasNext()) {

			linha = this.entrada.nextLine();
			compra.add((separaDados(linha)));
		}

		return compra;
	}

	public ArrayList<Long> lerCPF () throws ArrayIndexOutOfBoundsException, NumberFormatException {

		ArrayList<Long> compraCPF = new ArrayList<>();
		String linha;
		long CPFLong;
	
		while (this.entrada.hasNext()){

			linha = this.entrada.nextLine();
			CPFLong = Long.parseLong(linha);
			compraCPF.add(CPFLong);
		}

		return compraCPF;
	}


	private Compra separaDados(String linha) throws ArrayIndexOutOfBoundsException, NumberFormatException {

		String[] dados = null;
		double vale, valor;
		Calendar data;
		long cpf;

		try {

			dados = linha.split(";");

			if (dados.length > 4) {

				data = ManipulaData.lerDataString(dados[3]);
				vale = Double.parseDouble(dados[2]);
				valor = Double.parseDouble(dados[4]);
				cpf = Long.parseLong(dados[1]);
				ClienteEspecial novoClienteEspecial = new ClienteEspecial(dados[0], cpf, vale);

				return (new Compra(novoClienteEspecial, data, valor));

			} else {

				data = ManipulaData.lerDataString(dados[2]);
				valor = Double.parseDouble(dados[3]);
				cpf = Long.parseLong(dados[1]);
				Cliente novoCliente = new Cliente(dados[0], cpf);

				return (new Compra(novoCliente, data, valor));
			}

		} catch (ArrayIndexOutOfBoundsException erro) {

			throw new ArrayIndexOutOfBoundsException("Registro tem " + dados.length + " informações.");
		
		} catch (NumberFormatException erro) {
		
			throw new NumberFormatException("O número da conta não é inteiro.");
		}
	}

	public void fecha() throws IllegalStateException {

		try {
			
			this.entrada.close();

		} catch (IllegalStateException erro) {

			throw new IllegalStateException("Erro ao fechar o arquivos.");
		}
	}
	
}