package pages.GoodSyncDesktop.ToolBar.ToolsMenu.GSConnectSetup.ConfigureUser;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class ConfigureUser {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/ConfigureUser/img/user.png"
            , similarity = 50.0f)
    private SikuliElement userNameField;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/ConfigureUser/img/passwd.png"
            , similarity = 50.0f)
    private SikuliElement sysPasswordField;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/ConfigureUser/img/cancelBtn.png"
            , similarity = 90.0f)
    private SikuliElement cancelBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/ConfigureUser/img/backBtn.png"
            , similarity = 90.0f)
    private SikuliElement backBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/ConfigureUser/img/nextBtn.png"
            , similarity = 90.0f)
    private SikuliElement nextBtn;

    public ConfigureUser(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void inputUserName(String userName){
        userNameField.type(userName);
    }

    public void inputSysPassword(String password){
        sysPasswordField.type(password);
    }

    public void clickCancelBtn(){
        cancelBtn.click();
    }

    public void clickBackBtn(){
        backBtn.click();
    }

    public void clickNextBtn(){
        nextBtn.click();
    }
}
