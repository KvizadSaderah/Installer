package pages.GoodSyncDesktop.ToolBar.AllMenu;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class AllMenu {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/AllMenu/img/wholeMenu.png", similarity = 60.0f)
    private SikuliElement wholeMenu;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/AllMenu/img/autoRunSelector.png", similarity = 70.0f)
    private SikuliElement autoRunSelector;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/AllMenu/img/analyzeAll.png", similarity = 99.0f)
    private SikuliElement analyzeAll;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/AllMenu/img/syncAll.png", similarity = 99.0f)
    private SikuliElement synchronizeAll;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/AllMenu/img/analyzeAndSync.png", similarity = 99.0f)
    private SikuliElement analyzeAndSyncAll;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/AllMenu/img/stopAll.png", similarity = 99.0f)
    private SikuliElement stopAll;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/AllMenu/img/autoRunOFF.png", similarity = 99.0f)
    private SikuliElement autoRunOFF;

    public AllMenu(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickAnalyzeAll(){
        wholeMenu.click(analyzeAll);
    }

    public void press_F6(){
        sikuli.type(Key.F6);
    }

    public void clickSynchronizeAll(){
        wholeMenu.click(synchronizeAll);
    }

    public void press_F8(){
        sikuli.type(Key.F8);
    }

    public void clickAnalyzeAndSyncAll(){
       wholeMenu.click(analyzeAndSyncAll);
    }

    public void press_F10(){
        sikuli.type(Key.F10);
    }

    public void clickStopAll(){
        wholeMenu.click(stopAll);
    }

    public void clickAutoRunOFF(){
        wholeMenu.click(autoRunOFF);
    }

    public void press_F4(){
        sikuli.type(Key.F4);
    }

    public boolean isAnalyzeAllClickable(){
        return analyzeAll.exists();
    }

    public boolean isSynchronizeAllClickable(){
        return synchronizeAll.exists();
    }

    public boolean isAnalyzeAndSyncClickable(){
        return analyzeAndSyncAll.exists();
    }

    public boolean isStopAllClickable(){
        return stopAll.exists();
    }

    public boolean isAutoRunOffClickable(){
        return autoRunOFF.exists();
    }

    public boolean isAutoRunSelected(){
        return autoRunSelector.exists();

        // Possible way how to check smth near the element
        /*try {
            sikuli.find(wholeMenu.getImage())
                    .left().exists(autoRunSelector.getImage());
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }*/
    }

}
