package TrabalhoC3.Classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Compra {

    private Cliente cliente;
    private Calendar data;
    private double valor;

    public Compra (Cliente cliente, Calendar data, double valor) {

        this.cliente = cliente;
        this.data = data;
        this.valor = valor;
    }

    public Cliente getCliente () {

        return cliente;
    }

    public void setCliente (Cliente novoCliente) {

        this.cliente = novoCliente;
    }

    public Calendar getData () {

        return data;
    }

    public void setData (Calendar novaData) {

        this.data = novaData;
    }

    public double getValor () {

        return valor;
    }

    public void setValor (double novoValor) {

        this.valor = novoValor;
    }

    @Override
    public String toString () {

        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

        if (cliente instanceof ClienteEspecial) {

            return this.cliente.getNome()+";"
                    +this.cliente.getCPF()+";"
                    +((ClienteEspecial) cliente).getValeCompra()
                    +";"+date.format(data.getTime())+";"
                    +this.valor+"\n";
        }

        return this.cliente.getNome()+";"
                +this.cliente.getCPF()+";"
                +date.format(data.getTime())+";"
                +this.valor+"\n";
    }
    
}