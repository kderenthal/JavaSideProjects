/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockQuote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
public class SelectButtonListener implements ActionListener
{
    Stock stock;
    JComboBox combo;
    public SelectButtonListener(Stock initStock, JComboBox box)
    {
        stock = initStock;
        combo = box;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Stock temp = (Stock)combo.getSelectedItem();
        stock.name = temp.name;
        stock.price = temp.price;
        stock.list = temp.list;
    }
}