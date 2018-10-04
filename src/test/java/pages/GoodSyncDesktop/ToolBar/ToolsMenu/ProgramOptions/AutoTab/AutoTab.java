package pages.GoodSyncDesktop.ToolBar.ToolsMenu.ProgramOptions.AutoTab;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class AutoTab {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/AutoTab/img/rootWnd.png", similarity = 50.0f)
    private SikuliElement rootWnd;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/AutoTab/img/checkboxSelected.png", similarity = 90.0f)
    private SikuliElement checkboxSelected;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/AutoTab/img/autoHide.png", similarity = 90.0f)
    private SikuliElement autoHideMiniWnd;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/AutoTab/img/jobsParallel.png", similarity = 90.0f)
    private SikuliElement jobsInParallelField;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/AutoTab/img/quant.png", similarity = 90.0f)
    private SikuliElement autoJobsQuantField;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/AutoTab/img/keepHistory.png", similarity = 90.0f)
    private SikuliElement keepHistoryField;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/AutoTab/img/checkVer.png", similarity = 90.0f)
    private SikuliElement checkNewVerDaily;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/AutoTab/img/installVer.png", similarity = 90.0f)
    private SikuliElement installNewVer;

    public AutoTab(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickAutoHideCheckbox(){
        autoHideMiniWnd.click();
    }

    public boolean isAutoHideWindowSelected(){
        try {
            sikuli.find(autoHideMiniWnd.getImage()).left().exists(checkboxSelected.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void setRunJobsInParallel(String jobsInParallel){
        jobsInParallelField.doubleClick();
        jobsInParallelField.type(Key.DELETE);
        jobsInParallelField.type(jobsInParallel);
    }

    public void setAutoQuant(String quant){
        autoJobsQuantField.doubleClick();
        autoJobsQuantField.type(Key.DELETE);
        autoJobsQuantField.type(quant);
    }

    public void setHistoryFiled(String months){
        keepHistoryField.doubleClick();
        keepHistoryField.type(Key.DELETE);
        keepHistoryField.type(months);
    }

    public void clickOnCheckNewVerDaily(){
        checkNewVerDaily.click();
    }

    public boolean isCheckNewVerSelected(){
        try {
            sikuli.find(checkNewVerDaily.getImage()).left().exists(checkboxSelected.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void clickOnAutoInstallNewVer(){
        installNewVer.click();
    }

    public boolean isAutoInstallNewVerSelected(){
        try {
            sikuli.find(installNewVer.getImage()).left().exists(checkboxSelected.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }
}
