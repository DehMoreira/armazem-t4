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


/**
 *
 * @author Debora
 */
public class TotalVendasCliente {
        ArrayList <VendasCliente> vc = new ArrayList();
        
    public void ADD( VendasCliente v, boolean pertence){
        if (pertence != true)  {
            vc.add(v);
      }
        else {
            for (int i = 0; i < vc.size(); i++) {
                if(v.cliente.equals(vc.get(i).getCliente())) {
                    float total = vc.get(i).getTotal() + v.getTotal();
                    vc.get(i).setTotal(total);
         }
      }
    }
    }
        
    public int Menor(){
            VendasCliente a = vc.get(0);
            int menor = 0;
            for(int i = 1 ; i < 5 ; i++){
                 if(a.getTotal() > vc.get(i).getTotal()){
                    a = vc.get(i);
                    menor = i;
            }
        }
        return menor;
    }
        
    public void Encontra5() {
        LeArquivo();
        if(vc.size() > 5){
            for( int i = 5 ; i < vc.size() ; i = i){
                int menor = Menor();
                if(vc.get(i).getTotal() > vc.get(menor).getTotal()){
                    vc.set(menor, vc.get(i));
                    vc.remove(i);
                }
                }
            }
        }
    
    public CategoryDataset  GeraDataSet(float total) {
        LeArquivo();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        Encontra5();
        for(int i = 0; i< vc.size();i++){         
            float aux = vc.get(i).getTotal()*100;
            float tot = aux/total;
            dataset.addValue(tot,vc.get(i).getCliente(), "Clientes");
        }
        return dataset;
    }
    
    public boolean Pertence(String palavra){
        boolean ok = false;
        for (int i = 0; i < vc.size(); i++) {
             if (palavra.equals(vc.get(i).getCliente())) {
                ok = true;
         }   
      }
        return ok;
    } 
    
    public void SalvaArquivo(VendasCliente vcliente){
        File dir = new File(".");
        File arq = new File(dir, "vendascliente.txt");
        FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(arq, true);
            } catch (IOException ex) {
                Logger.getLogger(TotalVendasCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println( vcliente.cliente + "|" + vcliente.total);
            printWriter.flush();
        } catch (Exception e) {
            
        }
    }
    
    public void LeArquivo(){
        Scanner scanner = null;
            try {
                scanner = new Scanner(new FileReader("vendascliente.txt"))
                        .useDelimiter("\\||\\n");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TotalEstoque.class.getName()).log(Level.SEVERE, null, ex);
            }
        while (scanner.hasNext()) {
            String cliente = scanner.next();
            String aux = scanner.next();
            String total = aux.substring(0,aux.length()-1);
            
            float t= Float.parseFloat(total);
            
            VendasCliente vendasCliente = new VendasCliente();
            vendasCliente.setCliente(cliente);
            vendasCliente.setTotal(t);
            boolean pArq = Pertence(cliente);
            ADD(vendasCliente,pArq);
        }
    }
    
    
    
}
