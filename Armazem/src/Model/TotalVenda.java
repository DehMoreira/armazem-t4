/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Debora
 */
public class TotalVenda {
    
    ArrayList<Venda> vendas = new ArrayList();
    TotalCliente totalCliente = new TotalCliente();
    TotalItem totalItem = new TotalItem();
    
    public void Add(Venda vend) {
      vendas.add(vend);
   }
    
    public String MostraVendas() {
      String result = "";
      for (int i = 0; i < vendas.size(); i++) {
         result += vendas.get(i).toString() + '\n';
      }
      return ("".equals(result)) ? ("Ainda não foi realizada nenhuma venda!") : (result);
   }
   
    public String BuscaPorItem(Item item) {
      LeArquivo();
      String achados = "";
      
      for (int i = 0; i < vendas.size(); i++) {
         if (item.equals(vendas.get(i).getItem_vendido())){
            achados += vendas.get(i).toString() + "\n";
            }
         }
      return ("".equals(achados)) ? ("Não foram encontradas vendas relacionadas a este item") : (achados);
   }
   
    public String BuscaPorCliente(Cliente palavra) {
      LeArquivo();
      String achados = "";
      for (int i = 0; i < vendas.size(); i++) {
         if (palavra.equals(vendas.get(i).getComprador())){
            achados += vendas.get(i).toString() + "\n";
            }
      }
      return ("".equals(achados)) ? ("Não foram encontradas vendas relacionadas a este cliente") : (achados);
   }
    
    public float EncontraTotal(Item palavra){
       LeArquivo();
       float total=0;
       
       for (int i = 0; i < vendas.size(); i++) {
         if (palavra.equals(vendas.get(i).getItem_vendido()))
            total = vendas.get(i).getTotal_venda() + total;
        }
         return total;
   }
    
    public float EncontraTotalCliente(Cliente palavra){
       LeArquivo();
       float total=0;
       
       for (int i = 0; i < vendas.size(); i++) {
         if (palavra.equals(vendas.get(i).getComprador()))
            total = vendas.get(i).getTotal_venda() + total;
        }
         return total;
   }
          
    public float Entrada() {
          float achados = 0;
          float total = 0;
      for (int i = 0; i < vendas.size(); i++) {
            achados = vendas.get(i).total_venda;
            total = total + achados;
         }
       return (total);
   }

    public void SalvaArquivo(Venda venda){
        File dir = new File(".");
        File arq = new File(dir, "vendas.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(arq, true);
        } catch (IOException ex) {
            Logger.getLogger(TotalVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(venda.comprador + "|" + venda.item_vendido + "|" + venda.quantidade_vendida+ "|" + venda.total_venda);
            printWriter.flush();
        } catch (Exception e) {

        }
    }
    
    public void LeArquivo(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("vendas.txt"))
                    .useDelimiter("\\||\\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TotalVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (scanner.hasNext()) {
            String cliente =scanner.next();
            String item = scanner.next();
            String quantia = scanner.next();
            String aux = scanner.next();
            String total = aux.substring(0, aux.length() - 1);
            
            int q = Integer.parseInt(quantia);
            float t = Float.parseFloat(total);
            
            Cliente cliente1 = new Cliente();
            totalCliente.LeArquivo();
            cliente1 = totalCliente.BuscaCliente(cliente);
            
            Item item1 = new Item();
            totalItem.LeArquivo();
            item1 = totalItem.BuscaItem(item);
            
            Venda venda = new Venda();
            venda.setComprador(cliente1);
            venda.setItem_vendido(item1);
            venda.setQuantidade_vendida(q);
            venda.setTotal_venda(t);
            vendas.add(venda);
        }
    }
}