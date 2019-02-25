package basicframework.utility;

import java.io.FileInputStream;
import java.util.LinkedHashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelDataReadWrite {

	public String rootfolder;

	public ExcelDataReadWrite(String sourceFolder){
		this.rootfolder=sourceFolder;
	}

	LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String,String>>> getFileData;

	public void readExcelData() throws Exception {

		String ExcelFilePath=rootfolder+"/TestData/InputData.xlsx";

		// Create the input stream for Excel file 
		FileInputStream excelfile = new FileInputStream(ExcelFilePath);

		XSSFWorkbook workbook= new XSSFWorkbook(excelfile);

		int noOfSheets= workbook.getNumberOfSheets();

		getFileData=
				new LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String,String>>>();


		for(int i=0;i<noOfSheets;i++){
			String sheetName=workbook.getSheetAt(i).getSheetName();
			System.out.println(sheetName);

			//if(sheetName.equalsIgnoreCase("Login")){
			XSSFSheet st = workbook.getSheetAt(i);
			int rowcount= st.getPhysicalNumberOfRows();

			System.out.println(rowcount);
			XSSFRow headarObj = st.getRow(0);  /// created hearder obj

			//identify the executionflag column
			int executionFlag=0;
			for(int l=0;l<st.getRow(0).getLastCellNum();l++){
				if(st.getRow(0).getCell(l).toString().equalsIgnoreCase("ExecutionFlag")){
					executionFlag=l;
					break;
				}
			}


			if(executionFlag !=0){
				LinkedHashMap<String,LinkedHashMap<String,String>> sheetMap= 
						new LinkedHashMap<String,LinkedHashMap<String,String>>();

				for(int j=1;j<rowcount;j++){  /// change counter 0 to 1
					XSSFRow rowObj=st.getRow(j);
					int cellCount=rowObj.getLastCellNum();
					System.out.println(cellCount);

					if(st.getRow(j).getCell(executionFlag).toString().equalsIgnoreCase("Y")){
						LinkedHashMap<String,String> rowMap = new LinkedHashMap<String,String>();
						for (int k=0; k<cellCount;k++)
						{
							XSSFCell headerCellObj=headarObj.getCell(k);
							String headerValue=headerCellObj.toString();

							XSSFCell cellObj=rowObj.getCell(k);
							String ColValue=cellObj.toString();

							rowMap.put(headerValue, ColValue);
						}
						String TC_ID=st.getRow(j).getCell(0).toString();
						sheetMap.put(TC_ID, rowMap);
						System.out.println(rowMap);
					}
				}
				getFileData.put(sheetName, sheetMap);
			}

		}
		//}
		System.out.println(getFileData);
	}


	public LinkedHashMap<String, LinkedHashMap<String, String>> getsheetData(String SheetName){
		return getFileData.get(SheetName);
	}

	public LinkedHashMap<String, String> getTestCaseData(String SheetName,String TC_ID){
		return getFileData.get(SheetName).get(TC_ID);
	}

	public String getColumnData(String SheetName,String TC_ID,String colname){
		return getFileData.get(SheetName).get(TC_ID).get(colname);
	}

}
