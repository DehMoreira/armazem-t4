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
import javax.swing.JOptionPane;

/**
 *
 * @author Debora
 */
public class TotalCliente {
    ArrayList<Cliente> clientes = new ArrayList();
    
    public void Add(Cliente cliente) {
      clientes.add(cliente);
   }
      
    public Cliente BuscaCliente(String cli) {
      LeArquivo();
      Cliente achou = null;
      for (int i = 0; i < clientes.size(); i++) {
         if (cli.equals(clientes.get(i).getNome())) {
            achou = clientes.get(i);
         }
               }
      return achou;
      }
    
    public void SalvaArquivo (Cliente cliente){
        File dir = new File(".");
        File arq = new File(dir, "clientes.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(arq, true);
        } catch (IOException ex) {
            Logger.getLogger(TotalCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(cliente.getNome() + "|" + cliente.getTelefone() + "|" +  cliente.getEnd_residencial());
            printWriter.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente ja cadastrado");
        }
    }
    
    public void LeArquivo() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("clientes.txt"))
                    .useDelimiter("\\||\\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TotalCliente.class.getName()).log(Level.SEVERE, null, ex);
             }
        while (scanner.hasNext()) {
            String nome = scanner.next();
            String telefone = scanner.next();
            String aux = scanner.next();
            String endereco = aux.substring(0, aux.length() - 1);
            int i = Integer.parseInt(telefone);
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.getEnd_residencial();
            cliente.setTelefone(i);
            clientes.add(cliente);
        }
    }
}


    