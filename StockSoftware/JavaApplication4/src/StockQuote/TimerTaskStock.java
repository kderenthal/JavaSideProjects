/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockQuote;
import java.util.TimerTask;
import java.awt.*;
/**
 *
 * @author Kevin Derenthal
 */
public class TimerTaskStock extends TimerTask
{
    DrawGraph graph;
    Graphics g;
    public TimerTaskStock(DrawGraph a, Graphics b)
    {
        graph = a;
        g = b;
    }
    @Override
    public void run()
    {
        graph.paintComponent(g);
    }
}
