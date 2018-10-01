package pages.NewInstaller.ModalWnds.CloseInstallerWnd;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class CloseInstallerWnd {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/NewInstaller/ModalWnds/CloseInstallerWnd/img/firstStepWnd.png", similarity = 70.0f)
    private SikuliElement rootWnd;

    @FindBy(image = "src/test/java/pages/NewInstaller/ModalWnds/CloseInstallerWnd/img/yesBtn.png", similarity = 70.0f)
    private SikuliElement yesBtn;

    @FindBy(image = "src/test/java/pages/NewInstaller/ModalWnds/CloseInstallerWnd/img/noBtn.png", similarity = 70.0f)
    private SikuliElement noBtn;

    public CloseInstallerWnd(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public boolean isModalWndOpened(){
        boolean bool = false;
        if(rootWnd.exists()){
            bool = true;
        }
        return bool;
    }

    public void clickYesBtn(){
        yesBtn.click();
    }

    public void clickNoBtn(){
        noBtn.click();
    }
}
