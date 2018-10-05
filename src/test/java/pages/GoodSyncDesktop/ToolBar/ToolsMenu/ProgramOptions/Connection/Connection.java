package pages.GoodSyncDesktop.ToolBar.ToolsMenu.ProgramOptions.Connection;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class Connection {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/Connection/img/rootWnd.png"
            , similarity = 50.0f)
    private SikuliElement rootWnd;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/Connection/img/checkboxSelected.png"
            , similarity = 90.0f)
    private SikuliElement checkboxSelected;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/Connection/img/IEproxy.png"
            , similarity = 90.0f)
    private SikuliElement useIEproxy;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/Connection/img/customProxy.png"
            , similarity = 90.0f)
    private SikuliElement useThisProxy;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/Connection/img/prxyAddr.png"
            , similarity = 50.0f)
    private SikuliElement proxyServerField;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/Connection/img/prxyPort.png"
            , similarity = 50.0f)
    private SikuliElement proxyPortField;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/Connection/img/prxyUserId.png"
            , similarity = 50.0f)
    private SikuliElement proxyUserIdField;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/Connection/img/passwd.png"
            , similarity = 50.0f)
    private SikuliElement passwd;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/Connection/img/saveBtn.png"
            , similarity = 90.0f)
    private SikuliElement saveBtn;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/Connection/img/cancelBtn.png"
            , similarity = 90.0f)
    private SikuliElement cancelBtn;

    public Connection(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public boolean isConnectionWndOpened(){
        return rootWnd.exists();
    }

    public void clickOnUseIEproxySettings(){
        useIEproxy.click();
    }

    public boolean isUseIEproxySelected(){
        try {
            sikuli.find(useIEproxy.getImage()).left().exists(checkboxSelected.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void clickUseThisProxy(){
        useThisProxy.click();
    }

    public boolean isUseThisProxySelected(){
        try {
            sikuli.find(useThisProxy.getImage()).left().exists(checkboxSelected.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void setProxyServerAddress(String prxyAddr){
        proxyServerField.doubleClick();
        proxyServerField.type(Key.DELETE);
        proxyServerField.type(prxyAddr);
    }

    public void setProxyPort(String prxyPort){
        proxyPortField.doubleClick();
        proxyPortField.type(Key.DELETE);
        proxyPortField.type(prxyPort);
    }

    public void setProxyUserId(String prxyUserId){
        proxyUserIdField.doubleClick();
        proxyUserIdField.type(Key.DELETE);
        proxyUserIdField.type(prxyUserId);
    }

    public void setProxyPasswd(String pwd){
        passwd.doubleClick();
        passwd.type(Key.DELETE);
        passwd.type(pwd);
    }

    public void clickSaveBtn(){
        saveBtn.click();
    }

    public void clickCancelBtn(){
        cancelBtn.click();
    }
}
