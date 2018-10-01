package pages.NewInstaller.DesktopInstallerPage;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;
import pages.NewInstaller.ModalWnds.RunNowDlgWnd.RunNowDlgWnd;

public class DesktopInstallerPage {
    private Screen sikuli;

    @FindBy(images = {"src/test/java/pages/NewInstaller/DesktopInstallerPage/img/firstStepWnd.png",
    "src/test/java/pages/NewInstaller/DesktopInstallerPage/img/rootWnd1.png"}, similarity = 70.0f)
    private SikuliElement rootWnd;

    @FindBy(images = {"src/test/java/pages/NewInstaller/DesktopInstallerPage/img/languageSelectBtn.png",
    "src/test/java/pages/NewInstaller/DesktopInstallerPage/img/languageSelectBtn1.png"}, similarity = 70.0f)
    private SikuliElement languageSelectionBtn;

    @FindBy(images = {"src/test/java/pages/NewInstaller/DesktopInstallerPage/img/closeWnd.png",
    "src/test/java/pages/NewInstaller/DesktopInstallerPage/img/closeWnd1.png"}, similarity = 70.0f)
    private SikuliElement closeWndBtn;

    @FindBy(images = {"src/test/java/pages/NewInstaller/DesktopInstallerPage/img/InstallBtn.png",
    "src/test/java/pages/NewInstaller/DesktopInstallerPage/img/InstallBtn1.png"}, similarity = 70.0f)
    private SikuliElement installBtn;

    @FindBy(images = {"src/test/java/pages/NewInstaller/DesktopInstallerPage/img/progressHeader.png"}, similarity = 70.0f)
    private SikuliElement installProgressHeader;

    public DesktopInstallerPage(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public DesktopInstallerPage clickSelectLanguageBtn(){
        rootWnd.click(languageSelectionBtn);
        return this;
    }

    public RunNowDlgWnd clickCloseWndBtn(){
        closeWndBtn.click();
        return new RunNowDlgWnd(sikuli);
    }

    public void clickInstallBtn(){
        installBtn.click();
    }

    public String getSelectedLanguageName(){
        return languageSelectionBtn.find().getText();
    }

    public boolean isWindowOpened(){
        return rootWnd.exists();
    }

    public boolean isInstallBtnPresent(){
        return installBtn.exists();
    }

    public boolean isProgressWndOpened(){
        return installProgressHeader.exists();
    }

    public boolean waitForWindowDisappear(int sec){
        return rootWnd.waitVanish(sec);
    }

    public boolean waitForProgressWindowDisappear(int sec){
        return installProgressHeader.waitVanish(sec);
    }

    public boolean waitForMainWndAppear(int sec){
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
