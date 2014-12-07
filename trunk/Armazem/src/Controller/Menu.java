package Controller;


import Model.Cliente;
import Model.Item;
import Model.OpMenu;

public class Menu {
    
    OpMenu model = new OpMenu();  

    public void CadastroCliente (String nome, String endereco, String telefone){
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setEnd_residencial(endereco);
            int n = Integer.parseInt(telefone);
            cliente.setTelefone(n);
            model.Cadastro_Cliente(cliente);
            
    }
    
    public void CadastroItem (String artigo, String preco){
            Item item = new Item();
            item.setArtigo(artigo);
            float n = Float.parseFloat(preco);
            item.setPreco(n);
            model.Cadastro_Item(item);
            
    }
    
    public void Compra (String item, String quantidade, String total) {
            int h = Integer.parseInt(quantidade);
            float f = Float.parseFloat(total);
            model.Compra(item, h, f);

    }
    
    public void Venda (String cliente,String item, String quantidade)  {
            int h = Integer.parseInt(quantidade);
            model.Venda(cliente, item, h);

    }
   
    public void Relatorio1 (){
            //itens em estoque
            model.Item_estoque();
    }
    
    public void Relatorio2(String item){
            //vendas por itens
            model.Venda_item(item);
    }
    
    public void Relatorio3 (String cliente){
            //vendas por cliente
            model.Venda_Cliente(cliente);
    }
    
    public void Relatorio4 (){
            model.Lucros_Prejuizo();
    } 
    
    public void Relatorio5 (){
            model.RelatorioEntrada();
    }
    
    public void Relatorio6 (){
            model.RelatorioSaida();
    }
    
    public void GeraGrafico1(){
            model.GraficoProduto();            
    }  
  
    public void GeraGrafico2(){
            model.GraficoCliente();
            
    }
    
}
    


