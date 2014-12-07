/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Debora
 */
public class Cliente {
    private String nome;
    private int telefone;
    private String end_residencial;

   public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEnd_residencial() {
        return end_residencial;
    }

    public void setEnd_residencial(String end_residencial) {
        this.end_residencial = end_residencial;
    }

    @Override
    public String toString() {
        return nome + "\t" ;
    }

     
}
