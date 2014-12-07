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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeriesCollection;



/**
 *
 * @author Debora
 */
public class TotalEstoque {
        ArrayList<Estoque> est = new ArrayList();
    
    public void Add(Estoque estoq, boolean pertArray) {
      if (pertArray != true)  {
          est.add(estoq);
      }
      else {
      for (int i = 0; i < est.size(); i++) {
         if (estoq.getItem().equals(est.get(i).getItem())) {
            est.get(i).setQuantidade(estoq.quantidade);
            est.get(i).setQuant(estoq.quant);
         }
      }
    }
    }
    
    public void SalvaArquivo(Estoque estoque){
        File dir = new File(".");
        File arq = new File(dir, "estoque.txt");
        FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(arq, true);
            } catch (IOException ex) {
                Logger.getLogger(TotalEstoque.class.getName()).log(Level.SEVERE, null, ex);
            }
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(estoque.getItem() + "|" + estoque.quant+ "|" + estoque.getQuantidade());
            printWriter.flush();
        } catch (Exception e) {
            
        }
    }
    
    public void LeArquivo(){
        Scanner scanner = null;
            try {
                scanner = new Scanner(new FileReader("estoque.txt"))
                        .useDelimiter("\\||\\n");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TotalEstoque.class.getName()).log(Level.SEVERE, null, ex);
            }
        while (scanner.hasNext()) {
            String item = scanner.next();
            String quant = scanner.next();
            String aux = scanner.next();
            String quantidade = aux.substring(0,aux.length()-1);
            
            int q = Integer.parseInt(quant);
            int q2= Integer.parseInt(quantidade);
            
            Estoque estoque = new Estoque();
            estoque.setItem(item);
            estoque.setQuant(q);
            estoque.setQuantidade(q2);
            boolean pArq = Pertence(item);
            Add(estoque,pArq);
        }
    }
    
    public Estoque BuscaIt(String palavra) {
      Estoque encontrado = null;
      for (int i = 0; i < est.size(); i++) {
         if (palavra.equals(est.get(i).getItem())) {
            encontrado = est.get(i);
         }
      }
      return encontrado;
   
}
    
    public boolean Pertence(String palavra){
    boolean ok = false;
    for (int i = 0; i < est.size(); i++) {
         if (palavra.equals(est.get(i).getItem())) {
            ok = true;
         }   
      }
        return ok;
    }   
   
    public String MostraEstoque() {
      String result = "";
      for (int i = 0; i < est.size(); i++) {
         result += est.get(i).toString() + '\n';
      }
      return ("".equals(result)) ? ("Ainda não foi estocado nenhum item!") : (result);
    } 
        
    public CategoryDataset GeraDataset() {
       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < est.size(); i++)  {
            int total = est.get(i).quant - est.get(i).quantidade;
            dataset.addValue(total,est.get(i).getItem(), "Item");
        }
        return dataset;
  
    }  
 
}
    

