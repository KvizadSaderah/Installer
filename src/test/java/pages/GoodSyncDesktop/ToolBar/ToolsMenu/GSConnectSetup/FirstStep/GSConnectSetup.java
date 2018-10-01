package pages.GoodSyncDesktop.ToolBar.ToolsMenu.GSConnectSetup.FirstStep;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class GSConnectSetup {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/FirstStep/img/firstStepWnd.png"
            , similarity = 50.0f)
    private SikuliElement firstStepWnd;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/FirstStep/img/doNotSetup.png"
            , similarity = 99.0f)
    private SikuliElement doNotSetupGS;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/FirstStep/img/yesSetup.png"
            , similarity = 90.0f)
    private SikuliElement yesSetupGS;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/FirstStep/img/noDisco.png"
            , similarity = 90.0f)
    private SikuliElement noDiscoCheckbox;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/FirstStep/img/nextBtn.png"
            , similarity = 90.0f)
    private SikuliElement nextBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/FirstStep/img/cancelBtn.png"
            , similarity = 90.0f)
    private SikuliElement cancelBtn;

    public GSConnectSetup(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickDoNotSetupGSConnect(){
        doNotSetupGS.click();
    }

    public void clickYesSetupGSConnect(){
        yesSetupGS.click();
    }

    public void clickDoNotDiscoCheckBox(){
        noDiscoCheckbox.click();
    }

    public void clickNextBtn(){
        nextBtn.click();
    }

    public void clickCancelBtn(){
        cancelBtn.click();
    }

}
