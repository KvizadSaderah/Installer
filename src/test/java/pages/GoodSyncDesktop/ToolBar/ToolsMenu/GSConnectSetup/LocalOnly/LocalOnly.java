package pages.GoodSyncDesktop.ToolBar.ToolsMenu.GSConnectSetup.LocalOnly;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class LocalOnly {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/LocalOnly/img/backBtn.png"
            , similarity = 70.0f)
    private SikuliElement backBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/LocalOnly/img/applyBtn.png"
            , similarity = 70.0f)
    private SikuliElement applyBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/LocalOnly/img/cancelBtn.png"
            , similarity = 70.0f)
    private SikuliElement cancelBtn;

    public LocalOnly(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickBackBtn(){
        backBtn.click();
    }

    public void clickApplyBtn(){
        applyBtn.click();
    }

    public void clickCancelBtn(){
        cancelBtn.click();
    }
}
