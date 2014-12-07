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
public class Venda {
    public Cliente comprador;
    public Item item_vendido;
    public int quantidade_vendida;
    public float total_venda;


    public int getQuantidade_vendida() {
        return quantidade_vendida;
    }

    public void setQuantidade_vendida(int quantidade_vendida) {
        this.quantidade_vendida = quantidade_vendida;
    }

    public float getTotal_venda() {
        return total_venda;
    }

    public Cliente getComprador() {
        return comprador;
    }

    public void setComprador(Cliente comprador) {
        this.comprador = comprador;
    }

    public Item getItem_vendido() {
        return item_vendido;
    }

    public void setItem_vendido(Item item_vendido) {
        this.item_vendido = item_vendido;
    }

    public void setTotal_venda(float total_venda) {
        this.total_venda = total_venda;
    }
    
    
    @Override
    public String toString() {
        return (comprador + "  " + item_vendido + "  " + quantidade_vendida + "  R$" + total_venda );
    }
   
}