package data_providers;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass {
    @DataProvider(name = "singleValue")
    public Object[][] storeSingleValue(){
        return new Object[][]{
                {"Mamun"},
                {"Rifat"},
                {"Mohammad"}
        };
    }
    @DataProvider(name = "MultipleValues")
    public Object[][] storeMultipleValues(){
        return new Object[][]{
            {"Mamun",11435,"Jamaica"},
            {"Farid",11432,"Forest Hill"},
            {"Kamal",11436,"Jackson Heights"},
            {"Babul",11436,"Ronkonkoma"}
        };
    }
    @DataProvider(name = "RealAprRates")
    public Object[][] storeRealAprRates(){
        return new Object[][]{
                {"200000","15000","3","3.130%"}
        };
    }

    @Test(dataProvider = "singleValue")
    public void run(String name){
       System.out.println("[Single value] name is: " + name);
    }
    @Test(dataProvider = "MultipleValues")
    public void run(String name, int zipCode, String city){
        System.out.println("[Multiple value] name is: " + name);
        System.out.println("[Multiple value] city is: " + city);
        System.out.println("[Multiple value] zipCode is: " + zipCode);
    }

}
