package Lesson6DataFromExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven
{

    public ArrayList<String> getdata(String testcasename,String sheetName) throws Exception
    {
        ArrayList<String> d= new ArrayList<String>();
        FileInputStream fi = new FileInputStream("C:\\gaurav\\demoData.xlsx");

        XSSFWorkbook wb= new XSSFWorkbook(fi);
        int countSheets=wb.getNumberOfSheets();
        for(int i=0;i<wb.getNumberOfSheets();i++)
        {
            if(wb.getSheetName(i).equalsIgnoreCase("sheet1"))
            {
            XSSFSheet ws=wb.getSheetAt(i);
            Iterator<Row> row=ws.iterator();
            while(row.hasNext())
                {
                    Row r=row.next();
                    if(r.getCell(0).getStringCellValue().equalsIgnoreCase(testcasename))
                    {
                        Iterator<Cell> ce=r.cellIterator();
                        while(ce.hasNext())
                        {
                            Cell c=ce.next();
                            if(c.getCellType()== CellType.STRING)
                            {
                                d.add(c.getStringCellValue());
                            }
                            else
                            {
                                d.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }

                    }

                }
            }
        }
        return d;
    }
}

