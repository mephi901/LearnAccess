/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnaccess;

import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;
import com.sun.prism.PixelFormat;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Женя
 */
public class AccessManipulator {
    public AccessManipulator(){}
    
    public void createSimpleDB() throws IOException{
        String DBname = "newDB.accdb";
        Database newDB = DatabaseBuilder.create(Database.FileFormat.V2010, new File(DBname));
        TableBuilder TB = new TableBuilder("Teremok");
        TB.addColumn(new ColumnBuilder("ID",DataType.INT));
        TB.addColumn(new ColumnBuilder("Dish",DataType.TEXT));
        TB.addColumn(new ColumnBuilder("Price",DataType.DOUBLE));
        TB.addColumn(new ColumnBuilder("Sudar",DataType.TEXT));
        Table teremok = TB.toTable(newDB);
        HashMap<String,Object> m = new HashMap<>();
        int i=1;
        double pr = 124;
        m.put("ID", i++);
        m.put("Dish","Kasha");
        m.put("Price", pr);
        m.put("Sudar","Pasha"); 
        teremok.addRowFromMap(m);
        
        pr = 500;
        m.put("ID", i++);
        m.put("Dish","Borsh");
        m.put("Price", pr);
        m.put("Sudar","Zhenia");
        m.put("Sudar","Yarik"); 
        teremok.addRowFromMap(m);
    }
    
    public String findBorshEater() throws IOException{
        String DBname = "newDB.accdb";
        Database DB = DatabaseBuilder.open(new File(DBname));
        Table teremok = DB.getTable("Teremok");
        HashMap rowPattern = new HashMap();
        rowPattern.put("Dish", "Borsh");
        
        Row borshrow = CursorBuilder.findRow(teremok,rowPattern);
        String BorshEaterName = borshrow.getString("Sudar");
        return BorshEaterName;
    }
}
