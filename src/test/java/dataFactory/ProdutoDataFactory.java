package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComOValorIgualA(double valorProduto){
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Produto teste X");
        produto.setProdutoValor(valorProduto);

        List<String> cores = new ArrayList<>();
        cores.add("Branco");
        produto.setProdutoCores(cores);

        produto.setProdutoUrlMock("");

        ComponentePojo componente_1 = new ComponentePojo();
        componente_1.setComponenteNome("Componente YY");
        componente_1.setComponenteQuantidade(2);

        ComponentePojo componente_2 = new ComponentePojo();
        componente_2.setComponenteNome("Componente ZZ");
        componente_2.setComponenteQuantidade(1);

        List<ComponentePojo> componentes = new ArrayList<>();
        componentes.add(componente_1);
        componentes.add(componente_2);

        produto.setComponentes(componentes);

        return produto;
    }
}
