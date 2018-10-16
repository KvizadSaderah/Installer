package tests;

import org.sikuli.script.Screen;
import org.testng.annotations.Test;
import pages.GoodSyncDesktop.JobOptions.General.General;

public class Debug {

    @Test
    public void test(){
        General gen = new General(new Screen());
        gen.clickOnRunParallelThreads();
        gen.setRunParallelThreads("6");
    }
}
