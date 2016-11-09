package com.rym.libarary.KeywordFrame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rym.libarary.utils.Log;

public class ReadExcel2 extends ForTest{
	
	public static void main(String[] args)
	{
		try {
			List<List<String>> list=readXls("/Users/rymtest/Desktop/关键字驱动2.xls",0);
			for (List<String> list2 : list) {
				System.out.println(list2);
			}
			Log.logInfo("--------------------");
			List<List<String>> list3=readXls("/Users/rymtest/Desktop/关键字驱动2.xls",1);
			for (List<String> list4 : list3) {
				System.out.println(list4);
			}
			Log.logInfo("--------------------");
			List<List<String>> list5=readXls("/Users/rymtest/Desktop/关键字驱动2.xls",2);
			for (List<String> list6 : list5) {
				System.out.println(list6);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void aa(String d)
	{
		switch (d) {
		case "1":
			System.out.println("1");
			break;
		case "2":
			System.out.println("2");
			break;
		case "3":
			System.out.println("3");
			break;
		case "4":
			bb();
			break;
		}
	}
	
	public static void bb()
	{
		List<String> listaaa=new ArrayList<String>();
		for (int i = 1; i < 4; i++) {
			listaaa.add(i+"");
		}
		for (String string : listaaa) {
			aa(string);
		}
	}

	
    public static void readXml(String fileName){  
        boolean isE2007 = false;    //判断是否是excel2007格式  
        if(fileName.endsWith("xlsx"))  
            isE2007 = true;  
        try {  
            InputStream input = new FileInputStream(fileName);  //建立输入流  
            Workbook wb  = null;  
            //根据文件格式(2003或者2007)来初始化  
            if(isE2007)  
                wb = new XSSFWorkbook(new FileInputStream(fileName));  
            else  
                wb = new HSSFWorkbook(input);  
            Sheet sheet = wb.getSheetAt(0);     //获得第一个表单  
            Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器  
            while (rows.hasNext()) {  
                Row row = rows.next();  //获得行数据  
                System.out.println("Row #" + row.getRowNum());  //获得行号从0开始  
                Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器  
                while (cells.hasNext()) {  
                    Cell cell = cells.next();  
                    System.out.println("Cell #" + cell.getColumnIndex());  
                    switch (cell.getCellType()) {   //根据cell中的类型来输出数据  
                    case HSSFCell.CELL_TYPE_NUMERIC:  
                        System.out.println(cell.getNumericCellValue());  
                        break;  
                    case HSSFCell.CELL_TYPE_STRING:  
                        System.out.println(cell.getStringCellValue());  
                        break;  
                    case HSSFCell.CELL_TYPE_BOOLEAN:  
                        System.out.println(cell.getBooleanCellValue());  
                        break;  
                    case HSSFCell.CELL_TYPE_FORMULA:  
                        System.out.println(cell.getCellFormula());  
                        break;  
                    default:  
                        System.out.println("unsuported sell type");  
                    break;  
                    }  
                }  
            }  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }  

	
	 /** 
	 * 读取Excel测试，兼容 Excel 2003/2007/2010 
	 */  
	public static void readExcel()  
	{  
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");  
	    try {  
	        //同时支持Excel 2003、2007  
	        File excelFile = new File("/Users/rymtest/Desktop/关键字驱动.xlsx"); //创建文件对象  
	        FileInputStream is = new FileInputStream(excelFile); //文件流  
	        Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的  
	        int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量  
	        //遍历每个Sheet  
	        for (int s = 0; s < sheetCount; s++) {  
	            Sheet sheet = workbook.getSheetAt(s);  
	            int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数  
	            //遍历每一行  
	            for (int r = 1; r < rowCount; r++) {  
	                Row row = sheet.getRow(r);  
	                int cellCount = row.getPhysicalNumberOfCells(); //获取总列数  
	                //遍历每一列  
	                for (int c = 0; c < cellCount; c++) {  
	                    Cell cell = row.getCell(c);  
	                    int cellType = cell.getCellType();  
	                    String cellValue = null;  
	                    switch(cellType) {  
	                        case Cell.CELL_TYPE_STRING: //文本  
	                            cellValue = cell.getStringCellValue();  
	                            break;  
	                        case Cell.CELL_TYPE_NUMERIC: //数字、日期  
	                            if(DateUtil.isCellDateFormatted(cell)) {  
	                                cellValue = fmt.format(cell.getDateCellValue()); //日期型  
	                            }  
	                            else {  
	                                cellValue = String.valueOf(cell.getNumericCellValue()); //数字  
	                            }  
	                            break;  
	                        case Cell.CELL_TYPE_BOOLEAN: //布尔型  
	                            cellValue = String.valueOf(cell.getBooleanCellValue());  
	                            break;  
	                        case Cell.CELL_TYPE_BLANK: //空白  
	                            cellValue = cell.getStringCellValue();  
	                            break;  
	                        case Cell.CELL_TYPE_ERROR: //错误  
	                            cellValue = "错误";  
	                            break;  
	                        case Cell.CELL_TYPE_FORMULA: //公式  
	                            cellValue = "错误";  
	                            break;  
	                        default:  
	                            cellValue = "错误";  
	                    }  
	                    System.out.print(cellValue + "    ");  
	                }  
	                System.out.println();  
	            }  
	        }  
	  
	    }  
	    catch (Exception e) {  
	        e.printStackTrace();  
	    }  

	}  

	
	
	
	
	public static List<List<String>> readXls(String path,int numSheet)
	{
		InputStream is=null;
		try {
			is = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFWorkbook hssfWorkbook=null;
		try {
			hssfWorkbook = new HSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<List<String>> result=new ArrayList<List<String>>();
		//循环每一页，并处理当前循环页
//		for(int numSheet=0;numSheet < hssfWorkbook.getNumberOfSheets();numSheet++)
//		{
			HSSFSheet hssfSheet=hssfWorkbook.getSheetAt(numSheet);
//			if(hssfSheet==null)
//			{
//				continue;
//			}
			//处理当前页，循环读取每一行
			for(int rowNum=1;rowNum<=hssfSheet.getLastRowNum();rowNum++)
			{
				HSSFRow hssfRow=hssfSheet.getRow(rowNum);
				
//				int minColIx=hssfRow.getFirstCellNum();
				int MaxColIx=hssfRow.getLastCellNum();
//				Log.logInfo("最大烈属:"+MaxColIx);
				List<String> rowList=new ArrayList<String>();
				//遍历该行，获取处理每个cell元素
				if (MaxColIx>2)
				{
					if(hssfRow.getCell(0)==null&&hssfRow.getCell(1)==null)
					{
						break;
					}
					for(int colIx=0;colIx<MaxColIx;colIx++)
					{
						HSSFCell cell=hssfRow.getCell(colIx);
						if(cell==null)
						{
							rowList.add(null);
							continue;
						}
						rowList.add(getStringVal(cell));
					}
				}
//				if(rowList.get(0)==null&&rowList.get(1)==null)
//				{	result.remove(rowList);
//					break;}
				result.add(rowList);
			}
			
	//	}
		return result;
	}
	
	private static List<List<String>> readXlsx(String path) throws Exception
	{
		InputStream is=new FileInputStream(path);
		XSSFWorkbook xssfWorkbook=new XSSFWorkbook(is);
		List<List<String>> result=new ArrayList<List<String>>();
		//循环每一页，并处理当前循环页
		for(int numSheet=0;numSheet < xssfWorkbook.getNumberOfSheets();numSheet++)
		{
			XSSFSheet xssfSheet=xssfWorkbook.getSheetAt(numSheet);
			if(xssfSheet==null)
			{
				continue;
			}
			//处理当前页，循环读取每一行
			for(int rowNum=1;rowNum<=xssfSheet.getLastRowNum();rowNum++)
			{
				XSSFRow xssfRow=xssfSheet.getRow(rowNum);
				
				int minColIx=xssfRow.getFirstCellNum();
				int MaxColIx=xssfRow.getLastCellNum();
				List<String> rowList=new ArrayList<String>();
				//遍历该行，获取处理每个cell元素
				for(int colIx=minColIx;colIx<MaxColIx;colIx++)
				{
					XSSFCell cell=xssfRow.getCell(colIx);
					if(cell==null)
					{
						continue;
					}
					rowList.add(cell.toString());
				}
				result.add(rowList);
			}
			
		}
		return result;
	}
	
	public static String getStringVal(HSSFCell cell)
	{
		switch(cell.getCellType())
		{
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue() ? "TRUE" : "FLASE";
			case Cell.CELL_TYPE_FORMULA:
				return cell.getCellFormula();
			case Cell.CELL_TYPE_NUMERIC:
				cell.setCellType(Cell.CELL_TYPE_STRING);
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			default:
				return null;
		}
	}
}
