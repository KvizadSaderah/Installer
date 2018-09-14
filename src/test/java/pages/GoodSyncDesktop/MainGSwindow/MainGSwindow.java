package pages.GoodSyncDesktop.MainGSwindow;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class MainGSwindow {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/MainGSwindow/img/toolBar.png", similarity = 70.0f)
    private SikuliElement toolBar;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/MainGSwindow/img/tools.png", similarity = 70.0f)
    private SikuliElement toolsMenu;

    public MainGSwindow(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickTools(){
        toolsMenu.click();
    }

}
