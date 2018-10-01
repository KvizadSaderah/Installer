package pages.GoodSyncDesktop.ToolBar.ToolsMenu.GSConnectSetup.GlobalDisco;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class GlobalDisco {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/GlobalDisco/img/useExistant.png"
            , similarity = 60.0f)
    private SikuliElement useExistentAccount;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/GlobalDisco/img/crtNewAcc.png"
            , similarity = 60.0f)
    private SikuliElement createNewAccount;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/GlobalDisco/img/compId.png"
            , similarity = 50.0f)
    private SikuliElement computerIdField;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/GlobalDisco/img/userId.png"
            , similarity = 50.0f)
    private SikuliElement userIdField;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/GlobalDisco/img/password.png"
            , similarity = 50.0f)
    private SikuliElement passwordField;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/GlobalDisco/img/retypePwd.png"
            , similarity = 50.0f)
    private SikuliElement retypePasswordField;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/GlobalDisco/img/name.png"
            , similarity = 50.0f)
    private SikuliElement userNameField;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/GlobalDisco/img/backBtn.png"
            , similarity = 90.0f)
    private SikuliElement backBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/GlobalDisco/img/nextBtn.png"
            , similarity = 90.0f)
    private SikuliElement nextBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolBar/ToolsMenu/GSConnectSetup/GlobalDisco/img/cancelBtn.png"
            , similarity = 90.0f)
    private SikuliElement cancelBtn;

    public GlobalDisco(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickUseExistentAccount(){
        useExistentAccount.click();
    }

    public void clickCrtNewAccount(){
        createNewAccount.click();
    }

    public void inputComputerName(String computerName){
        computerIdField.type(computerName);
    }

    public void inputUserName(String userName){
        userNameField.type(userName);
    }

    public void inputUserId(String userId){
        userIdField.type(userId);
    }

    public void inputPassword(String pwd){
        passwordField.type(pwd);
    }

    public void retypePassword(String re_pwd){
        retypePasswordField.type(re_pwd);
    }

    public void clickBackBtn(){
        backBtn.click();
    }

    public void clickNextBtn(){
        nextBtn.click();
    }

    public void clickCancelBtn(){
        cancelBtn.click();
    }
}
