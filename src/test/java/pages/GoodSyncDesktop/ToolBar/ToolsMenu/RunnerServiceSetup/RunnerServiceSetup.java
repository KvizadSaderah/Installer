package pages.GoodSyncDesktop.ToolBar.ToolsMenu.RunnerServiceSetup;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class RunnerServiceSetup {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/RunnerServiceSetup/img/userName.png"
            , similarity = 50.0f)
    private SikuliElement userNameField;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/RunnerServiceSetup/img/passwd.png"
            , similarity = 50.0f)
    private SikuliElement sysPasswordField;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/RunnerServiceSetup/img/applyBtn.png"
            , similarity = 90.0f)
    private SikuliElement applyBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/RunnerServiceSetup/img/cancelBtn.png"
            , similarity = 90.0f)
    private SikuliElement cancelBtn;

    public RunnerServiceSetup(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void inputUserName(String name){
        userNameField.type(name);
    }

    public void inputPassword(String pwd){
        sysPasswordField.type(pwd);
    }

    public void clickApplyBtn(){
        applyBtn.click();
    }

    public void clickCancelBtn(){
        cancelBtn.click();
    }
}
