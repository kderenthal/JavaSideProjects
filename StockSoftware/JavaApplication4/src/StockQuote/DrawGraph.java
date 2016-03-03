/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockQuote;

/**
 *
 * @author Kevin Derenthal
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import java.awt.geom.Line2D;
@SuppressWarnings("serial")
public class DrawGraph extends JPanel 
{
   private Stock currentStock;
   List<Stock> stockList;
   public DrawGraph(Stock initStock, List<Stock> list)
   {
      currentStock = initStock;
      stockList = list;
   }

    @Override
    protected void paintComponent(Graphics g) 
    {
        int height = 3;
        int width = 3;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for(int j=0; j<currentStock.list.size()-1; j++)
        {
            double x1temp = currentStock.list.get(j).x-currentStock.minX;
            double x2temp = currentStock.list.get(j+1).x-currentStock.minX;
            double y1temp = currentStock.list.get(j).y-currentStock.minY;
            double y2temp = currentStock.list.get(j+1).y-currentStock.minY;
            g2.draw(new Line2D.Double(x1temp,y1temp,x2temp,y2temp));
        }
   }
    public void updateStock(Stock initStock)
    {
        currentStock = initStock;
    }
}