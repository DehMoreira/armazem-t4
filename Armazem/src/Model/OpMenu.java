/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;



import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;


/**
 *
 * @author Debora
 */


public class OpMenu {
        TotalCliente client = new TotalCliente();
        TotalItem it = new TotalItem();
        TotalVenda tv = new TotalVenda();
        TotalCompra tc = new TotalCompra();
        TotalEstoque te = new TotalEstoque();
        TotalVendasCliente vc = new TotalVendasCliente();

        
        
    public void Cadastro_Cliente (Cliente cliente) {                
                client.LeArquivo();
                if (client.BuscaCliente(cliente.getNome())== null){
                    client.Add(cliente);                  
                    client.SalvaArquivo(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso");
                }    
                else
                    JOptionPane.showMessageDialog(null, "Cliente já cadastrado");
}

    public void Cadastro_Item(Item item){
        it.LeArquivo();
        if(it.BuscaItem(item.getArtigo())== null){
            it.Add(item);
            Estoque estoque = new Estoque();
            estoque.setItem(item.artigo);
            estoque.setQuantidade(0);
            estoque.setQuant (0);
            te.Add(estoque, false);         
            te.SalvaArquivo(estoque);
            it.SalvaArquivo(item);
            JOptionPane.showMessageDialog(null, "Item Cadastrado com sucesso");
        } 
        else
            JOptionPane.showMessageDialog(null, "Item ja cadastrado");
    }
    
    public void Compra(String item, int quantidade, float total) { 
                it.LeArquivo();
                te.LeArquivo();
                
                Item aux = new Item();
                Compra compra = new Compra();  

                aux = it.BuscaItem(item);
                compra.setItem_comprado(aux);               
                Estoque estoque_aux = new Estoque();
                estoque_aux = te.BuscaIt(item);
                
                if (aux != null){
                    it.LeArquivo();
                    compra.setQuantidade_comprada(quantidade);
                    estoque_aux.setQuantidade(compra.quantidade_comprada + estoque_aux.quantidade);
                    estoque_aux.setQuant(compra.quantidade_comprada + estoque_aux.quant);
                    compra.setTotal(total);
                    String item_comprado;
                    item_comprado = aux.getArtigo();
                    tc.SalvaArquivo(compra);
                    tc.Add(compra);
                    te.SalvaArquivo(estoque_aux);
                    JOptionPane.showMessageDialog(null, "Item estocado com sucesso");
                    }
                    
                else 
                    JOptionPane.showMessageDialog(null, "Compra mal sucedida: Item nao cadastrado");
            
    } 
    
    public void Venda(String cliente,String item, int quantidade) {
            client.LeArquivo();
            it.LeArquivo();
            te.LeArquivo();
            Venda vendendo = new Venda();
            Cliente cliente_aux = new Cliente();
            
            cliente_aux = client.BuscaCliente(cliente);
            vendendo.setComprador(cliente_aux);
                   
            if (cliente_aux != null){
                Estoque estoque_aux = new Estoque();
                estoque_aux = te.BuscaIt(item);
                
                if (estoque_aux != null){              
                    vendendo.setQuantidade_vendida(quantidade);
                
                    if (vendendo.quantidade_vendida > estoque_aux.quantidade) {
                        JOptionPane.showMessageDialog(null, "Quantidade requerida, é maior do que há em estoque");
                        }
                    
                    else {
                        VendasCliente vendasc = new VendasCliente();
                        Item item_aux = new Item();
                        item_aux = it.BuscaItem(item);
                        vendendo.setItem_vendido(item_aux);
                        vendendo.setTotal_venda(item_aux.preco * vendendo.quantidade_vendida);
                        estoque_aux.setQuantidade(estoque_aux.getQuantidade() - vendendo.getQuantidade_vendida()); //atualiza quantidade no estoque
                                             
                        JOptionPane.showMessageDialog(null, "Total: R$"+vendendo.total_venda);
                        vendasc.setCliente(cliente_aux.getNome());
                        vendasc.setTotal(vendendo.getTotal_venda());
                        
                        vc.ADD(vendasc,vc.Pertence(vendasc.cliente));
                        vc.SalvaArquivo(vendasc);
                        tv.Add(vendendo);
                        te.SalvaArquivo(estoque_aux);
                        tv.SalvaArquivo(vendendo);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Item nao encontrado");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Cliente nao cadastrado");
                }
    }
    
    public void Item_estoque(){     
            te.LeArquivo();
            // Mostra os itens em estoque
            JOptionPane.showMessageDialog(null, te.MostraEstoque());
        }
    
    public void Venda_item(String aux){     
            tv.LeArquivo();
            // Mostra as vendas de um item específico
            Item item = new Item();
            item = it.BuscaItem(aux);
            JOptionPane.showMessageDialog(null, "Comprador    Item     Quantidade    Total da Compra \n" + tv.BuscaPorItem(item)+"\n Total R$: "+tv.EncontraTotal(item));
            
    }

    public void Venda_Cliente(String aux){
            tv.LeArquivo();
            client.LeArquivo();
            // Mostra as vendas de um cliente específico
            Cliente cliente = new Cliente();
            cliente = client.BuscaCliente(aux);
            JOptionPane.showMessageDialog(null, "Comprador    Item     Quantidade    Total da Compra \n" + tv.EncontraTotalCliente(cliente)+"\n Total R$: "+ tv.EncontraTotalCliente(cliente));

    }
    
    public void Lucros_Prejuizo(){
            TotalVenda tv1 = new TotalVenda();
            TotalCompra tc1 = new TotalCompra();
            tv1.LeArquivo();
            tc1.LeArquivo();
            float tot1 = 0.00f,tot2=0.00f;
            tot1 = tv1.Entrada();
            tot2 = tc1.Saidas();
          
            if (tot1 < tot2)
                JOptionPane.showMessageDialog(null,"Total de Entrada: R$"+tot1+"\n"+"Total de Saida: R$"+tot2+"\nPrejuizo é de: R$"+(tot2-tot1));
            else if (tot1 > tot2)
                JOptionPane.showMessageDialog(null,"Total de Entrada: R$"+tot1+"\n"+"Total de Saida: R$"+tot2+"\nLucro é de: R$"+(tot1-tot2));
            else
                JOptionPane.showMessageDialog(null,"Total de Entrada: R$"+tot1+"\n"+"Total de Saida: R$"+tot2+"\nNao houve lucro e nem prejuizo");
           
    }
    
    public JFrame GraficoProduto() {
        te.LeArquivo();
        CategoryDataset cds = te.GeraDataset();
     
        JFreeChart grafico = ChartFactory.createBarChart3D("Venda por produto", "Legenda:","Quantidade vendida", cds, PlotOrientation.VERTICAL, true, true, true);
        
        JFrame janela = new JFrame(" ");
	Painel painel = new Painel();
        ChartPanel myChartPanel = new ChartPanel(grafico, true);
        myChartPanel.setSize(painel.getWidth(), painel.getHeight());
        myChartPanel.setVisible(true);
        painel.removeAll();
        painel.add(myChartPanel);
        painel.revalidate();
        painel.repaint();     
        
        janela.add(painel);
	janela.setSize(690,465);
	janela.setVisible(true);
        
        return janela;
    }
    
    public JFrame GraficoCliente() {
        tv.LeArquivo();
        float t = tv.Entrada();

        CategoryDataset cds = vc.GeraDataSet(t);
        
        JFreeChart grafico = ChartFactory.createBarChart3D("Cinco clientes que mais compraram/gastaram", "Legenda: ", "Porcentagem (%)", cds, PlotOrientation.VERTICAL, true, true, true);
        
        JFrame janela = new JFrame(" ");
	Painel panel = new Painel();
        ChartPanel myChartPanel = new ChartPanel(grafico, true);
        myChartPanel.setSize(panel.getWidth(), panel.getHeight());
        myChartPanel.setVisible(true);
        panel.removeAll();
        panel.add(myChartPanel);
        panel.revalidate();
        panel.repaint();     
        
        
	janela.add(panel);
	janela.setSize(690,465);
	janela.setVisible(true);
        
        return janela;
        
    }
    
    public void RelatorioEntrada(){
            TotalVenda tv1 = new TotalVenda();
            tv1.LeArquivo();
            float total = 0.00f;
            total = tv1.Entrada();
            JOptionPane.showMessageDialog(null,"Total de Entrada: R$" + total);}
    
    public void RelatorioSaida(){
            TotalCompra tc1 = new TotalCompra();
            tc1.LeArquivo();
            float total=0.00f;
            total = tc1.Saidas();
            JOptionPane.showMessageDialog(null,"Total de Saida: R$" + total);
    }
}

