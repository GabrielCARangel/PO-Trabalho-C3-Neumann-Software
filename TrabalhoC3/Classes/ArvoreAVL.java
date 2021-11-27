package TrabalhoC3.Classes;

public class ArvoreAVL {

    private NoAVL raiz;
    private boolean controle;

    public ArvoreAVL() {

        this.raiz = null;
        this.controle = true;
    }

    public void inserir(Compra elemento) {

        NoAVL no = this.pesquisa(elemento.getCliente().getCPF());

        if (no != null) {
        
            no.adicionar(elemento);
        
        } else {
        
            this.raiz = this.inserirRaiz(elemento, this.raiz); 
        }
    }

    private NoAVL inserirRaiz(Compra elemento, NoAVL no) {

        if (no == null) {

            NoAVL novoNo = new NoAVL(elemento.getCliente().getCPF());
            novoNo.adicionar(elemento);
            this.controle = true;

            return novoNo;

        } else {

            if (elemento.getCliente().getCPF() < no.getCPF()) {

                no.setItemEsquerdo(this.inserirRaiz(elemento, no.getItemEsquerdo()));
                no = this.balancearDireita(no);

            } else if (elemento.getCliente().getCPF() > no.getCPF()) {

                no.setItemDireita(this.inserirRaiz(elemento, no.getItemDireita()));
                no = this.balancearEsquerda(no);

            }
        }

        return no;
    }

    public NoAVL pesquisa(long elemento) {

        return pesquisaAVL(elemento, this.raiz);
    }

    private NoAVL pesquisaAVL(long elemento, NoAVL no) {

        if (no == null) {

            return null;

        } else {

            if (elemento < no.getCPF()) {
                no = pesquisaAVL(elemento, no.getItemEsquerdo());

            } else if (elemento > no.getCPF()) {
                no = pesquisaAVL(elemento, no.getItemDireita());
            }
        }

        return no;
    }

    private NoAVL balancearDireita(NoAVL no) {

        if (this.controle) {

            switch (no.getFatorBalanceamento()) {

            case -1:
                    no = this.rotacaoDireita(no);
                break;

            case 0:
                    no.setFatorBalanceamento((byte) -1);
                break;

            case 1:
                    no.setFatorBalanceamento((byte) 0);
                    this.controle = false;
                break;

            }
        }

        return no;
    }

    private NoAVL balancearEsquerda(NoAVL no) {

        if (this.controle) {

            switch (no.getFatorBalanceamento()) {

                case -1:
                    no.setFatorBalanceamento((byte) 0);
                    this.controle = false;
                    break;

                case 0:
                    no.setFatorBalanceamento((byte) 1);
                    break;

                case 1:
                    no = this.rotacaoEsquerda(no);
                    break;
            }
        }

        return no;
    }

    private NoAVL rotacaoDireita(NoAVL no) {

        NoAVL auxiliar01, auxiliar02;
        auxiliar01 = no.getItemEsquerdo();

        if (auxiliar01.getFatorBalanceamento() == -1) {

            no.setItemEsquerdo(auxiliar01.getItemDireita());
            auxiliar01.setItemDireita(no);
            no.setFatorBalanceamento((byte) 0);
            no = auxiliar01;

        } else {

            auxiliar02 = auxiliar01.getItemDireita();
            auxiliar01.setItemDireita(auxiliar02.getItemEsquerdo());
            auxiliar02.setItemEsquerdo(auxiliar01);
            no.setItemEsquerdo(auxiliar02.getItemDireita());
            auxiliar02.setItemDireita(no);

            if (auxiliar02.getFatorBalanceamento() == -1) {

                no.setFatorBalanceamento((byte) 1);

            } else {

                no.setFatorBalanceamento((byte) 0);
            }

            if (auxiliar02.getFatorBalanceamento() == 1) {

                auxiliar01.setFatorBalanceamento((byte) -1);

            } else {

                auxiliar01.setFatorBalanceamento((byte) 0);
            }

            no = auxiliar02;
        }

        no.setFatorBalanceamento((byte) 0);
        this.controle = false;
        return no;
    }

    private NoAVL rotacaoEsquerda(NoAVL no) {

        NoAVL auxiliar01, auxiliar02;
        auxiliar01 = no.getItemDireita();

        if (auxiliar01.getFatorBalanceamento() == 1) {

            no.setItemDireita(auxiliar01.getItemEsquerdo());
            auxiliar01.setItemEsquerdo(no);
            no.setFatorBalanceamento((byte) 0);
            no = auxiliar01;

        } else {

            auxiliar02 = auxiliar01.getItemEsquerdo();
            auxiliar01.setItemEsquerdo(auxiliar02.getItemDireita());
            auxiliar02.setItemDireita(auxiliar01);
            no.setItemDireita(auxiliar02.getItemEsquerdo());
            auxiliar02.setItemEsquerdo(no);

            if (auxiliar02.getFatorBalanceamento() == 1) {

                no.setFatorBalanceamento((byte) -1);

            } else {

                no.setFatorBalanceamento((byte) 0);
            }

            if (auxiliar02.getFatorBalanceamento() == -1) {

                auxiliar01.setFatorBalanceamento((byte) 1);

            } else {

                auxiliar01.setFatorBalanceamento((byte) 0);
                
            }
            
            no = auxiliar02;
        }

        no.setFatorBalanceamento((byte) 0);
        controle = false;
        return no;
    }

}