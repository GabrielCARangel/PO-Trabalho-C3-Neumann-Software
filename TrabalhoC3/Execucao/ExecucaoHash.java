package TrabalhoC3.Execucao;

import TrabalhoC3.Classes.*;
import TrabalhoC3.Manipuladores.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ExecucaoHash {
    
    public static void executar() {
        
        CadastraCompra compra = new CadastraCompra();
        lerDados(compra, "TrabalhoC3/Dados/500_Aleatorios.txt");
        ListaHash lista = new ListaHash(compra.getVetorCompra().size());

        for (int contador = 0; contador < compra.getVetorCompra().size(); contador++) {

            lista.adicionarListaHash(compra.get(contador));
        }

        ArrayList <Compra> auxiliar = new ArrayList<>();

        for (int contadorCPF = 0; contadorCPF < AplicativoTeste.listaCPFs.size(); contadorCPF++) {

            auxiliar = lista.pesquisarCPF(AplicativoTeste.listaCPFs.get(contadorCPF));

            try {

                if (auxiliar.get(0).getCliente().getCPF() == AplicativoTeste.listaCPFs.get(contadorCPF)) {

                    for (int contadorImpressao = 0; contadorImpressao < auxiliar.size(); contadorImpressao++) {
                    
                        System.out.println("\nCPF: " +auxiliar.get(contadorImpressao).getCliente().getCPF() +" | Nome: " +auxiliar.get(contadorImpressao).getCliente().getNome() +" | Valor: " +auxiliar.get(contadorImpressao).getValor());
                    }
                }

            } catch (Exception erro) {

                System.out.println("\nO CPF " +AplicativoTeste.listaCPFs.get(contadorCPF) +" não possui compras.");
            }
        }
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
}