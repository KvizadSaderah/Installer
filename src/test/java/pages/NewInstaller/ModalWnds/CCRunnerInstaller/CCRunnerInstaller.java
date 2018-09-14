package pages.NewInstaller.ModalWnds.CCRunnerInstaller;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class CCRunnerInstaller {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/NewInstaller/ModalWnds/CCRunnerInstaller/img/companyId.png", similarity = 70.0f)
    private SikuliElement badCompanyId;

    @FindBy(image = "src/test/java/pages/NewInstaller/ModalWnds/CCRunnerInstaller/img/okBtn.png", similarity = 70.0f)
    private SikuliElement okBtn;

    @FindBy(image = "src/test/java/pages/NewInstaller/ModalWnds/CCRunnerInstaller/img/coidError.png", similarity = 90.0f)
    private SikuliElement coidError;

    //TODO all error modals must be moved to separate class
    @FindBy(images = {"src/test/java/pages/NewInstaller/ModalWnds/CCRunnerInstaller/img/sysUserError.png",
    "src/test/java/pages/NewInstaller/ModalWnds/CCRunnerInstaller/img/sysUserErrorWrongValue.png",
    "src/test/java/pages/NewInstaller/ModalWnds/CCRunnerInstaller/img/sysUserErrorWrongSysUser.png",
    "src/test/java/pages/NewInstaller/ModalWnds/CCRunnerInstaller/img/sysUserErrorUserNotSpecified.png"}, similarity = 90.0f)
    private SikuliElement sysUserError;

    public CCRunnerInstaller(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public boolean isCompanyIdFormatErrorMsgOpened(){
        return coidError.exists();
    }

    public boolean waitForCompanyIdFormatErrorMsgAppears(int sec){
        for(int i = 0; i < sec; i++){
            if(!coidError.exists()){
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

    public boolean waitForCompanyIdFormatErrorMsgDisappear(int sec){
        return coidError.waitVanish(sec);
    }

    public boolean isBadCompanyIdErrorMsgOpened(){
        return badCompanyId.exists();
    }

    public boolean waitForBadCompanyIdErrorMsgAppears(int sec){
        for(int i = 0; i < sec; i++){
            if(!badCompanyId.exists()){
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

    public boolean waitForBadCompanyIdErrMsgDisappear(int sec){
        return badCompanyId.waitVanish(sec);
    }

}
