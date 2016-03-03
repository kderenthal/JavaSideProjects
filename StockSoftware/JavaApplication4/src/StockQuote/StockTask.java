/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockQuote;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.util.Date;
/**
 *
 * @author Kevin Derenthal
 */
public class StockTask 
{
    List<Stock> list;
    int count;
    JComboBox combo;
    JLabel label1;
    Stock currentStock;
    Stock updateStock;
    String baseUrl = "http://finance.yahoo.com/q?s=";
    String ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.33 (KHTML, like Gecko) Chrome/27.0.1438.7 Safari/537.33";
    public StockTask(JLabel label, List<Stock> initList, int initCount, JComboBox box, Stock initStock, Stock initUpdate)
    {
        label1 = label;
        list = initList;
        count = initCount;
        combo = box;
        currentStock = initStock;
        updateStock = initUpdate;
    }    

    public void run()
    {
            String code = updateStock.name;
            if(code!=null)
            {
                String url = baseUrl + code;
                Document doc = null;
                String price = null, name = null;
                try
                {   
                    Connection.Response response  = Jsoup.connect(url).userAgent(ua).timeout(10*1000).ignoreHttpErrors(true).execute();
                    int status = response.statusCode();
                    if(status==200)
                    {
                        doc = Jsoup.connect(url).get();
                    }
                     price = doc.select(".time_rtq_ticker").first().text();
                     name = doc.select(".title h2").first().text();
                }
                catch(Exception e)
                {
                }        
                if(name==null)
                {
                    list.remove(count);
                    label1.setText("Symbol Not Found");
                    combo.removeItemAt(combo.getItemCount()-1);
                }
                else
                {
                    int a = updateStock.list.size();
                    double y = Double.parseDouble(price);
                    double x = (new Date().getTime()/1000);
                    if(y < updateStock.minY || updateStock.minY == -1)
                        updateStock.minY = y;
                    if(x < updateStock.minX || updateStock.minX == -1)
                        updateStock.minX = x;
                    if(y > updateStock.maxY)
                        updateStock.maxY = y;
                    if(x > updateStock.maxX)
                        updateStock.maxX = x;
                    DoublePoint p = new DoublePoint(x,y);
                    updateStock.list.add(updateStock.list.size(), p);
                    //System.out.println(String.format("%s [%s] is trading at %s", name, code, price));
                    updateStock.price = y;
                    label1.setText("");
                    if(currentStock.name.equals(updateStock.name))
                    {
                        currentStock.price = Double.parseDouble(price);
                        currentStock.list.add(currentStock.list.size(), p);
                        currentStock.minX = updateStock.minX;
                        currentStock.minY = updateStock.minY;
                        currentStock.maxX = updateStock.maxX;
                        currentStock.maxY = updateStock.maxY;

                    }
               }
            }   
    }
}