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

public class Compra {
    public Item Item_comprado;
    public int quantidade_comprada;
    public float total;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Item getItem_comprado() {
        return Item_comprado;
    }

    public void setItem_comprado(Item Item_comprado) {
        this.Item_comprado = Item_comprado;
    }



    public int getQuantidade_comprada() {
        return quantidade_comprada;
    }

    public void setQuantidade_comprada(int quantidade_comprada) {
        this.quantidade_comprada = quantidade_comprada;
    }
    
    
}
