package com.driver.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

final public class DataProviderClass {
//    ReadConfig readConfig;
    @DataProvider(name="LoginData")
    public Object[][] getLoginData(){
        return new Object[][]
            {
                { "Guru99", "India" },
                { "Krishna", "UK" },
                { "Bhupesh", "USA" }
            };

    }
    @DataProvider(name="dataDimension")
    public Object[][] getDataDimension() throws IOException {
//        readConfig=new ReadConfig();
        Object dataDimension[][]=new Object[ExcelReader.RowCount()][1];

        for (int rowCnt = 0; rowCnt < ExcelReader.RowCount(); rowCnt++){
            dataDimension[rowCnt][0]=Integer.toString(rowCnt);
        }
        return dataDimension;
    }
}
