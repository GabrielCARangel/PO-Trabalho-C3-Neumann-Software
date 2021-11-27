package TrabalhoC3.Classes;

public class ClienteEspecial extends Cliente {

    private double valeCompra;

    public ClienteEspecial (String nome, long cpf, double valeCompra) {

        super(nome, cpf);
        this.valeCompra = valeCompra;
    }

    public double getValeCompra () {

        return valeCompra;
    }

    public void setValeCompra (double novoValeCompra) {

        this.valeCompra = novoValeCompra;
    }    

}