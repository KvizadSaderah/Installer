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

    @FindBy(image = "src/test/java/pages/NewInstaller/ModalWnds/CCRunnerInstaller/img/sysUserIdEmpty.png", similarity = 80.0f)
    private SikuliElement sysUserIdEmpty;

    @FindBy(image = "src/test/java/pages/NewInstaller/ModalWnds/CCRunnerInstaller/img/incorrectSysUser.png", similarity = 80.0f)
    private SikuliElement incorrectSysUser;

    @FindBy(image = "src/test/java/pages/NewInstaller/ModalWnds/CCRunnerInstaller/img/incorrectSysPasswd.png", similarity = 80.0f)
    private SikuliElement incorrectSysPasswd;

    public CCRunnerInstaller(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public boolean waitForIncorrectSysPasswdErrorDisappear(int sec){
        return incorrectSysPasswd.waitVanish(sec);
    }

    public boolean waitForIncorrectSysPasswdErrorAppears(int sec){
        for(int i = 0; i < sec; i++){
            if(!incorrectSysPasswd.exists()){
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

    public boolean isIncorrectSysPasswdErrorOpened(){
        return incorrectSysPasswd.exists();
    }

    public boolean waitForIncorrectSysUserErrorDisappear(int sec){
        return incorrectSysUser.waitVanish(sec);
    }

    public boolean waitForIncorrectSysUserErrorAppears(int sec){
        for(int i = 0; i < sec; i++){
            if(!incorrectSysUser.exists()){
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

    public boolean isIncorrectSysUserErrorOpened(){
        return incorrectSysUser.exists();
    }

    public boolean isSysUserIdErrorOpened(){
        return sysUserIdEmpty.exists();
    }

    public boolean waitForSysUserIdEmptyErrorAppears(int sec){
        for(int i = 0; i < sec; i++){
            if(!sysUserIdEmpty.exists()){
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

    public boolean waitForSysUserIdEmptyErrorDisappear(int sec){
        return sysUserIdEmpty.waitVanish(sec);
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
