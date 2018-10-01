package pages.NewInstaller.CCinstallerPage;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import pages.NewInstaller.ModalWnds.RunNowDlgWnd.RunNowDlgWnd;

public class CCInstallerPage{
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/NewInstaller/CCinstallerPage/img/firstStepWnd.png", similarity = 80.0f)
    private SikuliElement rootWnd;

    @FindBy(image = "src/test/java/pages/NewInstaller/CCinstallerPage/img/langSelectionBtn.png", similarity = 70.0f)
    private SikuliElement languageSelectionBtn;

    @FindBy(image = "src/test/java/pages/NewInstaller/CCinstallerPage/img/companyId.png", similarity = 99.0f)
    private SikuliElement company_id_inputField;

    @FindBy(image = "src/test/java/pages/NewInstaller/CCinstallerPage/img/companyPin.png", similarity = 99.0f)
    private SikuliElement company_pin_inputFiled;

    @FindBy(image = "src/test/java/pages/NewInstaller/CCinstallerPage/img/accPasswrd.png", similarity = 99.0f)
    private SikuliElement accountsInputField;

    @FindBy(image = "src/test/java/pages/NewInstaller/CCinstallerPage/img/showProgressCheckbox.png", similarity = 70.0f)
    private SikuliElement showMiniProgressCheckBox;

    @FindBy(image = "src/test/java/pages/NewInstaller/CCinstallerPage/img/installBtn.png", similarity = 70.0f)
    private SikuliElement installBtn;

    @FindBy(image = "src/test/java/pages/NewInstaller/CCinstallerPage/img/closeWnd.png", similarity = 70.0f)
    private SikuliElement closeWndBtn;

    @FindBy(images = {"src/test/java/pages/NewInstaller/CCinstallerPage/img/miniProgressWnd.png",
    "src/test/java/pages/NewInstaller/CCinstallerPage/img/miniProgressWnd1.png",
    "src/test/java/pages/NewInstaller/CCinstallerPage/img/miniProgressWnd2.png"}, similarity = 90.0f)
    SikuliElement progressWnd;

    @FindBy(image = "src/test/java/pages/NewInstaller/CCinstallerPage/img/compId131.png", similarity = 90.0f)
    SikuliElement companyId131;

    @FindBy(image = "src/test/java/pages/NewInstaller/CCinstallerPage/img/installProgress.png", similarity = 20.0f)
    SikuliElement installProgressWnd;

    @FindBy(image = "src/test/java/pages/NewInstaller/CCinstallerPage/img/closeMiniProgressWnd.png", similarity = 70.0f)
    SikuliElement closeMiniProgressWnd;

    public CCInstallerPage(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void closeMiniProgressWnd(){
        progressWnd.click(closeMiniProgressWnd);
    }

    public boolean isInstallProgressWndOpened(){
        return installProgressWnd.exists();
    }

    public boolean waitForInstallProgressDisappear(int sec){
        return installProgressWnd.waitVanish(sec);
    }

    public boolean isCompanyIdSetTo131(){
        return companyId131.exists();
    }

    public boolean isInstallerWndOpened(){
        return rootWnd.exists();
    }

    public boolean waitForInstallProgressWndAppear(int sec){
        for(int i = 0; i < sec; i++){
            if(!installProgressWnd.exists()){
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

    public CCInstallerPage clickLanguageSelectionBtn(){
        rootWnd.click(languageSelectionBtn);
        return this;
    }

    public RunNowDlgWnd clickCloseWndBtn(){
        rootWnd.click(closeWndBtn);
        return new RunNowDlgWnd(sikuli);
    }

    public CCInstallerPage clickShowMiniProgressCheckBox(){
        rootWnd.click(showMiniProgressCheckBox);
        return this;
    }

    public CCInstallerPage setAccountPasswordFieldToValue(String value){
        accountsInputField.type(value);
        return this;
    }

    public CCInstallerPage setCompanyPinToValue(String value){
        company_pin_inputFiled.type(value);
        return this;
    }

    public CCInstallerPage setCompanyIdFieldToValue(String value){
        company_id_inputField.type(value);
        return this;
    }

    public void clickInstallBtn(){
        installBtn.click();
    }

    public boolean waitForMiniProgressAppear(int sec){
        for(int i = 0; i < sec; i++){
            if(!progressWnd.exists()){
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

    public boolean waitForMiniProgressWndDisappear(int sec){
        return progressWnd.waitVanish(sec);
    }

    public boolean isMiniProgressWndOpened(){
        return progressWnd.exists();
    }

}
