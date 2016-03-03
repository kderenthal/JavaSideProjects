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
public class AddButtonListener implements ActionListener
{
    JTextField text;
    JComboBox combo;
    List<Stock> stockList;
    Stock currentStock;
    public AddButtonListener(ArrayList<Stock> list, JTextField t, JComboBox box, Stock stock)
    {
        stockList = list;
        combo = box;
        text = t;
        currentStock = stock;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Stock s = new Stock(text.getText(), 0, new ArrayList<DoublePoint>(), -1.0, -1.0, -1.0, -1.0);
           stockList.add(stockList.size(), s);
        combo.addItem(stockList.get(stockList.size()-1));
        text.setText("");
        if(currentStock.name=="")
            currentStock.name = stockList.get(stockList.size()-1).name;
    }
}
