package pages.NewInstaller.ModalWnds.SecondInstanceWarning;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class SecondInstanceWarning {
    Screen sikuli;

    @FindBy(image = "src/test/java/pages/NewInstaller/ModalWnds/SecondInstanceWarning/img/rootWnd.png", similarity = 70.0f)
    private SikuliElement rootWnd;

    @FindBy(image = "src/test/java/pages/NewInstaller/ModalWnds/SecondInstanceWarning/img/okBtn.png", similarity = 70.0f)
    private SikuliElement okBtn;

    public SecondInstanceWarning(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public boolean isModalOpened(){
        return rootWnd.exists();
    }

    public void clickOkBtn(){
        okBtn.click();
    }

    public boolean waitForModalWndDisappear(int sec){
        boolean result = false;
        for(int i = 0; i < sec; i++){
            if(rootWnd.exists()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                result = true;
                break;
            }
        }
        return result;
    }

}
