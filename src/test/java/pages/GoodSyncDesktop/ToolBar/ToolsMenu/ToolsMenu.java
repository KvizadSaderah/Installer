package pages.GoodSyncDesktop.ToolBar.ToolsMenu;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class ToolsMenu {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/img/wholeMenu.png", similarity = 50.0f)
    private SikuliElement wholeMenu;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/img/progOptions.png", similarity = 99.0f)
    private SikuliElement programOptions;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/img/gsConnect.png", similarity = 99.0f)
    private SikuliElement gsConnectSetup;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/img/runnerSetup.png", similarity = 99.0f)
    private SikuliElement runnerServiceSetup;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/img/serverAdvanced.png", similarity = 99.0f)
    private SikuliElement serverAdvancedOptions;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/img/exportAll.png", similarity = 99.0f)
    private SikuliElement exportAllJobs;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/img/exportSelected.png", similarity = 99.0f)
    private SikuliElement exportSelectedJobs;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/img/import.png", similarity = 99.0f)
    private SikuliElement importJobList;

    public ToolsMenu(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickProgramOptions(){
        wholeMenu.click(programOptions);
    }

    public void press_Alt_P(){
        sikuli.type("P", Key.ALT);
    }

    public void clickGSConnectSetup(){
        wholeMenu.click(gsConnectSetup);
    }

    public void clickRunnerServiceSetup(){
        wholeMenu.click(runnerServiceSetup);
    }

    public void clickServerAdvancedOptions(){
        wholeMenu.click(serverAdvancedOptions);
    }

    public void clickExportAllJobs(){
        wholeMenu.click(exportAllJobs);
    }

    public void clickExportSelectedJobs(){
        wholeMenu.click(exportSelectedJobs);
    }

    public void clickImportJobList(){
        wholeMenu.click(importJobList);
    }

}
