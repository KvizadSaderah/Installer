package tests;

import org.testng.asserts.SoftAssert;

public class BaseTest {
    SoftAssert softAssert;

    public BaseTest(){
        this.softAssert = new SoftAssert();
    }

    public void wait(int sec){
        try {
            Thread.sleep((sec * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
