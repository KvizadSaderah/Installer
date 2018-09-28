package pages.GoodSyncDesktop.ToolBar.JobMenu;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class JobMenu {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/wholeMenu.png", similarity = 50.0f)
    private SikuliElement wholeMenu;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/analyze.png", similarity = 99.0f)
    private SikuliElement analyze;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/synchronize.png", similarity = 99.0f)
    private SikuliElement synchronize;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/analyzeAndSync.png", similarity = 99.0f)
    private SikuliElement analyzeAndSync;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/clear.png", similarity = 99.0f)
    private SikuliElement clear;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/stop.png", similarity = 99.0f)
    private SikuliElement stop;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/pause.png", similarity = 99.0f)
    private SikuliElement pause;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/resume.png", similarity = 99.0f)
    private SikuliElement resume;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/options.png", similarity = 99.0f)
    private SikuliElement options;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/logsLeft.png", similarity = 99.0f)
    private SikuliElement logsOnLeftSide;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/logsRight.png", similarity = 99.0f)
    private SikuliElement logsOnRightSide;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/new.png", similarity = 99.0f)
    private SikuliElement newJob;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/delete.png", similarity = 99.0f)
    private SikuliElement delete;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/rename.png", similarity = 99.0f)
    private SikuliElement rename;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/clone.png", similarity = 99.0f)
    private SikuliElement clone;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/advanced.png", similarity = 99.0f)
    private SikuliElement advanced;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/JobMenu/img/exit.png", similarity = 99.0f)
    private SikuliElement exit;

    public JobMenu(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickAnalyze(){
        wholeMenu.click(analyze);
    }

    public void press_F5(){
        sikuli.type(Key.F5);
    }

    public void press_Alt_A(){
        sikuli.type("A", Key.ALT);
    }

    public void clickSynchronize(){
        wholeMenu.click(synchronize);
    }

    public void press_F7(){
        sikuli.type(Key.F7);
    }

    public void press_Alt_S(){
        sikuli.type("S", Key.ALT);
    }

    public void clickAnalyzeAndSync(){
        wholeMenu.click(analyzeAndSync);
    }

    public void press_F9(){
        sikuli.type(Key.F9);
    }

    public void press_Alt_Y(){
        sikuli.type("Y", Key.ALT);
    }

    public void clickClear(){
        wholeMenu.click(clear);
    }

    public void press_ESC(){
        sikuli.type(Key.ESC);
    }

    public void clickPause(){
        wholeMenu.click(pause);
    }

    public void press_Alt_Z(){
        sikuli.type("Z", Key.ALT);
    }

    public void clickOptions(){
        wholeMenu.click(options);
    }

    public void press_Alt_O(){
        sikuli.type("O", Key.ALT);
    }

    public void clickLogsOnLeftSide(){
        wholeMenu.click(logsOnLeftSide);
    }

    public void press_Alt_L(){
        sikuli.type("L", Key.ALT);
    }

    public void clickLogsOnRightSide(){
        wholeMenu.click(logsOnRightSide);
    }

    public void press_Alt_R(){
        sikuli.type("R", Key.ALT);
    }

    public void clickNew(){
        wholeMenu.click(newJob);
    }

    public void press_Alt_N(){
        sikuli.type("N", Key.ALT);
    }

    public void clickDelete(){
        wholeMenu.click(delete);
    }

    public void press_DEL(){
        sikuli.type(Key.DELETE);
    }

    public void press_Alt_D(){
        sikuli.type("D", Key.ALT);
    }

    public void clickRename(){
        wholeMenu.click(rename);
    }

    public void press_F2(){
        sikuli.type(Key.F2);
    }

    public void clickClone(){
        wholeMenu.click(clone);
    }

    public void press_Alt_C(){
        sikuli.type("C", Key.ALT);
    }

    public void moveMouseToAdvanced(){
        advanced.hover();
    }

    public void clickExit(){
        wholeMenu.click(exit);
    }

    public void press_Alt_X(){
        sikuli.type("X", Key.ALT);
    }
}
