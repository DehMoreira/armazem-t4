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
public class TotalItem {
    ArrayList<Item> itens = new ArrayList();
    
    public void Add(Item it) {
      itens.add(it);
   }
    
    public String MostraItem() {
      String result = "";
      for (int i = 0; i < itens.size(); i++) {
         result += itens.get(i).toString() + '\n';
      }
      return ("".equals(result)) ? ("Não foram encontrados itens no estoque!") : (result);
   }
   
    public Item BuscaItem(String palavra) {
      LeArquivo();
      Item encontrado = null;
      for (int i = 0; i < itens.size(); i++) {
         if (palavra.equals(itens.get(i).getArtigo())) {
            encontrado = itens.get(i);
            return encontrado;
         }
      }
      return encontrado;
}
    
    public void SalvaArquivo(Item item) {
        File dir = new File(".");
        File arq = new File(dir, "itens.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(arq, true);
        } catch (IOException ex) {
            Logger.getLogger(TotalItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(item.getArtigo() + "|" + item.getPreco());
        printWriter.flush();
    }
    
    public void LeArquivo() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("itens.txt"))
                    .useDelimiter("\\||\\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TotalItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (scanner.hasNext()) {
            String artigo = scanner.next();
            String preco = scanner.next();
            String precoVenda = preco.substring(0, preco.length() - 1);
            
            float p = Float.parseFloat(precoVenda);
            Item item = new Item();       
            item.setArtigo(artigo);
            item.setPreco(p);
            itens.add(item);
        }
    }
  
}