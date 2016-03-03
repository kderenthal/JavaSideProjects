/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockQuote;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin Derenthal
 */
public class StockQuote 
{
    public static void main(String[] args) 
    {
        ArrayList<Stock> stockList= new ArrayList<Stock>();
        Stock currentStock = new Stock("", 0, new ArrayList<DoublePoint>(), -1.0, -1.0, -1.0, -1.0);
        //SET UP FRAME
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Container pane = frame.getContentPane();
        pane.setLayout(null);
        pane.setBackground(Color.WHITE);
        pane.setPreferredSize(new Dimension(500, 500));
        frame.addWindowListener(new ListenerWindow());  
        //SET UP LABEL
        JLabel label = new JLabel("Add Symbol:");
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setBackground(Color.WHITE);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setOpaque(true);
        label.setBounds(0, 0, 110, 30);
        pane.add(label);
        //SET UP ERROR LABEL
        JLabel label2 = new JLabel();
        label2.setFont(new Font("Serif", Font.PLAIN, 15));
        label2.setBackground(Color.WHITE);
        label2.setOpaque(true);
        label2.setBounds(0, label.getY()+label.getHeight()+1, label.getWidth(), label.getHeight());
        pane.add(label2);
        //SET UP TEXT BOX
        JTextField text = new JTextField();
        text.setFont(new Font("Serif", Font.BOLD, 20));
        text.setForeground(Color.BLUE);
        text.setBounds(label.getX()+label.getWidth(), 0, 60, 30);
        pane.add(text);
        //SET UP ADD BUTTON
        JButton button1 = new JButton("Add");
        button1.setFont(new Font("Serif", Font.BOLD, 20));
        button1.setBounds(text.getX()+text.getWidth(), 0, 80, 30);
        pane.add(button1);
        //SET UP COMBO BOX
        JComboBox combo = new JComboBox();
        combo.setFont(new Font("Serif", Font.PLAIN, 20));
        combo.setBounds(label2.getX(), label2.getY()+label2.getHeight(), 80, 30);
        pane.add(combo);
        //SET UP SELECT BUTTON
        JButton button2 = new JButton("Select");
        button2.setFont(new Font("Serif", Font.BOLD, 20));
        button2.setBounds(combo.getX()+combo.getWidth(), combo.getY(), 90, 30);
        button2.addActionListener(new SelectButtonListener(currentStock, combo));
        pane.add(button2);
        //SET UP PRICE LABEL
        JLabel label3 = new JLabel();
        label3.setFont(new Font("Serif", Font.PLAIN, 22));
        label3.setBackground(Color.WHITE);
        label3.setForeground(Color.GREEN);
        label3.setOpaque(true);
        label3.setBorder(BorderFactory.createLineBorder(Color.black));
        label3.setBounds(0, combo.getY()+combo.getHeight()*2, 70, combo.getHeight());
        pane.add(label3);
        //PACK FRAME AND SET VISIBLE
        frame.pack();
        frame.setVisible(true);
        DrawGraph graph = new DrawGraph(currentStock, stockList);
        graph.setBounds(100, 100, 300, 300);
        frame.add(graph);
        Graphics g = frame.getGraphics();
        graph.paintComponent(g);        
        button1.addActionListener(new AddButtonListener(stockList, text, combo, currentStock));
        while(true)
        {
            synchronized(stockList)
            {
                for(int i=0; i<stockList.size(); i++)
                {
                    Stock updateStock = stockList.get(i);
                    try 
                    {
                       new StockTask(label2, stockList, i, combo, currentStock, updateStock).run();
                       label3.setText(Double.toString(currentStock.price));
                       graph.updateStock(currentStock);
                       if(stockList.size()>=1 || currentStock.list.size()>1)
                       {
                           graph.revalidate();
                           graph.repaint();
                       }
                    }
                    catch(Exception e)
                    { 
                    }
                }
            }
        }
    }
}