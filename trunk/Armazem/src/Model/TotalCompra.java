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
public class TotalCompra {
    ArrayList<Compra> compras = new ArrayList();
    TotalItem totalItem = new TotalItem();
    
    public void Add (Compra compra){
    compras.add(compra);
        }
    
    public int BuscaCompra(String palavra) {
          int achados = 0;
          int total = 0;
      for (int i = 0; i < compras.size(); i++) {
         if (palavra.equals(compras.get(i).getItem_comprado())){
            achados = compras.get(i).getQuantidade_comprada();
            total = total + achados;
            }
         }
       return (total);
   }
    
    public float Saidas() {
          float achados = 0;
          float total = 0;
      for (int i = 0; i < compras.size(); i++) {
            achados = compras.get(i).getTotal();
            total = total + achados;
         }
       return (total);
   }

    public String MostraCompras() {
      String result = "";
      for (int i = 0; i < compras.size(); i++) {
         result += compras.get(i).toString() + '\n';
      }
   
      return ("".equals(result)) ? ("Ainda não foi realizada nenhuma Compra!") : (result);
   }
    
    public void SalvaArquivo(Compra compra){
        File dir = new File(".");
        File arq = new File(dir, "compras.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(arq, true);
        } catch (IOException ex) {
            Logger.getLogger(TotalCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(compra.Item_comprado + "|" + compra.quantidade_comprada + "|" + compra.getTotal());
            printWriter.flush();
        } catch (Exception e) {
            
        }
    }
    
    public void LeArquivo(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("compras.txt"))
                    .useDelimiter("\\||\\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TotalCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (scanner.hasNext()) {
            String item = scanner.next();
            String quantidade = scanner.next();
            String aux= scanner.next();
            String total = aux.substring(0, aux.length() - 1);
            
            Item item1 = new Item();
            item1=totalItem.BuscaItem(item);
            int q = Integer.parseInt(quantidade);
            float t = Float.parseFloat(total);
            
            Compra compra = new Compra();
            compra.setItem_comprado(item1);
            compra.setQuantidade_comprada(q);
            compra.setTotal(t);
            compras.add(compra);
            
            
        }
    }
       
   
}
