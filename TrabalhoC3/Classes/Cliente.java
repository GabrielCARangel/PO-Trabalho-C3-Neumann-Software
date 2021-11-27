package TrabalhoC3.Classes;

public class Cliente {

    private String nome;
    private long CPF;

    public Cliente (String nome, long CPF) {

        this.nome = nome;
        this.CPF = CPF;
    }

    public String getNome () {

        return nome;
    }

    public void setNome (String novoNome) {

        this.nome = novoNome;
    }

    public long getCPF () {

        return CPF;
    }

    public void setCPF (long novoCPF) {

        this.CPF = novoCPF;
    }

}