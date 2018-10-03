package pages.NewInstaller.ModalWnds.RunNowDlgWnd;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class RunNowDlgWnd {
    private Screen sikuli;

    @FindBy(images = {"src/test/java/pages/NewInstaller/ModalWnds/RunNowDlgWnd/img/rootWnd.png",
            "src/test/java/pages/NewInstaller/ModalWnds/RunNowDlgWnd/img/rootWnd1.png"}, similarity = 70.0f)
    private SikuliElement rootWnd;

    @FindBy(images = {"src/test/java/pages/NewInstaller/ModalWnds/RunNowDlgWnd/img/closeWnd.png",
            "src/test/java/pages/NewInstaller/ModalWnds/RunNowDlgWnd/img/closeWnd1.png"}, similarity = 70.0f)
    private SikuliElement closeWndBtn;

    @FindBy(images = {"src/test/java/pages/NewInstaller/ModalWnds/RunNowDlgWnd/img/yesBtn.png",
            "src/test/java/pages/NewInstaller/ModalWnds/RunNowDlgWnd/img/yesBtn1.png"}, similarity = 70.0f)
    private SikuliElement yesBtn;

    @FindBy(images = {"src/test/java/pages/NewInstaller/ModalWnds/RunNowDlgWnd/img/noBtn.png",
            "src/test/java/pages/NewInstaller/ModalWnds/RunNowDlgWnd/img/noBtn1.png"}, similarity = 70.0f)
    private SikuliElement noBtn;

    public RunNowDlgWnd(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickYesBtn(){
        yesBtn.click();
    }

    public void clickNoBtn(){
        noBtn.click();
    }

    public void clickCloseWndBtn(){
        closeWndBtn.click();
    }

    public boolean waitForModalToBeOpened(int sec){
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

