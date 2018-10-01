package pages.NewInstaller.GoodSyncConnectSetupWzd;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;


public class GoodSyncConnectSetupWzd {
    private Screen sikuli;

    @FindBy(images = {"src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/firstStepWnd.png",
    "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/rootWnd1.png",
    "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/rootWndGerman.png"}, similarity = 70.0f)
    private SikuliElement rootWnd;

    @FindBy(images = {"src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/cancelBtn.png",
    "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/cancelBtnFrench.png",
    "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/cancelBtnGerman.png",
    "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/cancelBtnJapanese.png",
    "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/cancelBtnRussian.png"}, similarity = 70.0f)
    private SikuliElement cancelBtn;

    @FindBy(image = "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/closeWnd.png", similarity = 70.0f)
    private SikuliElement closeWndBtn;

    @FindBy(images = {"src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/nextBtn.png",
    "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/nextBtnGerman.png"}, similarity = 70.0f)
    private SikuliElement nextBtn;

    @FindBy(image = "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/noDiscoveryCheckbox.png", similarity = 70.0f)
    private SikuliElement noDiscoveryCheckbox;

    @FindBy(image = "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/yesSetupRadioBtn.png", similarity = 70.0f)
    private SikuliElement yesSetupRadioBtn;

    @FindBy(image = "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/noSetupRadioBtn.png", similarity = 70.0f)
    private SikuliElement noSetupRadioBtn;

    @FindBy(image = "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/yesSetupSelected.png", similarity = 90.0f)
    private SikuliElement yesSetupRadioBtnSelected;

    @FindBy(image = "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/noSetupSelected.png", similarity = 90.0f)
    private SikuliElement noSetupRadioBtnSelected;

    @FindBy(image = "src/test/java/pages/NewInstaller/GoodSyncConnectSetupWzd/img/noDiscoverySelected.png", similarity = 90.0f)
    private SikuliElement noDiscoverCheckBoxSelected;

    public GoodSyncConnectSetupWzd(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickCancelBtn(){
        rootWnd.click(cancelBtn);
    }

    public void clickNextBtn(){
        rootWnd.click(nextBtn);
    }

    public void clickNoDiscoveryModeCheckBox(){
        rootWnd.click(noDiscoveryCheckbox);
    }

    public void clickNoSetupRadioBtn(){
        rootWnd.click(noSetupRadioBtn);
    }

    public void clickYesSetupRadioBtn(){
        rootWnd.click(yesSetupRadioBtn);
    }

    public void clickCloseWnd(){
        rootWnd.click(closeWndBtn);
    }

    public boolean isYesSetupGSConnectSelected(){
        return yesSetupRadioBtnSelected.exists();
    }

    public boolean isNoSetupGSConnectSelected(){
        return noSetupRadioBtnSelected.exists();
    }

    public boolean isNoDiscoveryCheckBoxSelected(){
        return noDiscoverCheckBoxSelected.exists();
    }

    public boolean isGSConnectWindowAppeared(){
        return rootWnd.exists();
    }

    public boolean waitForGSConnectWndAppear(int sec){
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
}
