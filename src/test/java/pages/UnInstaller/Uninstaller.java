package pages.UnInstaller;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

import java.io.IOException;

public class Uninstaller {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/UnInstaller/img/rootWnd.png", similarity = 70.0f)
    private SikuliElement rootWnd;

    @FindBy(image = "src/test/java/pages/UnInstaller/img/uninstallBtn.png", similarity = 70.0f)
    private SikuliElement uninstallBtn;

    @FindBy(image = "src/test/java/pages/UnInstaller/img/cancelBtn.png", similarity = 70.0f)
    private SikuliElement cancelBtn;

    @FindBy(image = "src/test/java/pages/UnInstaller/img/closeWnd.png", similarity = 70.0f)
    private SikuliElement closeWnd;

    @FindBy(image = "src/test/java/pages/UnInstaller/img/modalWndRoot.png", similarity = 70.0f)
    private SikuliElement modalWndRoot;

    @FindBy(image = "src/test/java/pages/UnInstaller/img/modalYesBtn.png", similarity = 70.0f)
    private SikuliElement modalYesBtn;

    @FindBy(image = "src/test/java/pages/UnInstaller/img/modalNoBtn.png", similarity = 70.0f)
    private SikuliElement modalNoBtn;

    @FindBy(image = "src/test/java/pages/UnInstaller/img/uninstallProgressRootWnd.png", similarity = 70.0f)
    private SikuliElement UninstallProgressRootWnd;

    @FindBy(image = "src/test/java/pages/UnInstaller/img/deleteAll.png", similarity = 70.0f)
    private SikuliElement deleteAllCheckBox;

    public Uninstaller(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void runDeinstallation(){
        try {
            Runtime.getRuntime().exec("C:\\Program Files\\Siber Systems\\GoodSync\\" +
                    "gs-runner.exe /stop-remove-services");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean waitForUninstallWzdAppear(int sec){
        for(int i = 0; i < sec; i++){
            if(!rootWnd.exists()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                return true;
            }
        }
        return false;

    }

    public void clickUninstallBtn(){
        uninstallBtn.click();
    }

    public void clickCancelBtn(){
        cancelBtn.click();
    }

    public boolean isUninstallWzdOpened(){
        return rootWnd.exists();
    }

    public boolean waitForUninstallProgressDisappear(int sec){
        boolean result = false;
        if(!UninstallProgressRootWnd.exists()){
            return true;
        } else {
            for(int i = 0; i < sec; i++){
                if(UninstallProgressRootWnd.exists()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                } else {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public void clickDeleteAllCheckBox(){
        deleteAllCheckBox.click();
    }

    public boolean waitForModalWndOpened(int sec){
        for(int i = 0; i < sec; i++){
            if(!modalWndRoot.exists()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public void clickYesBtnModalWnd(){
        modalYesBtn.click();
    }

    public void clickNoBtnModalWnd(){
        modalNoBtn.click();
    }
}
