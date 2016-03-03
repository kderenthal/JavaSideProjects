/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockQuote;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Kevin Derenthal
 */
public class Stock 
{
    double price;
    String name;
    List<DoublePoint> list = new ArrayList<DoublePoint>();
    double minX, minY, maxX, maxY;

    public Stock(String initName, double initPrice, List<DoublePoint> initList, double xMin, double yMin, double xMax, double yMax)
    {
        name = initName.toUpperCase();
        price = initPrice;
        list = initList;
        minX = xMin;
        minY = yMin;
        maxX = xMax;
        maxY = yMax;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}
