package pages.GoodSyncDesktop.ToolsMenu;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class ToolsMenu {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolsMenu/img/rootWnd.png", similarity = 70.0f)
    private SikuliElement rootWnd;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/ToolsMenu/img/progOptions.png", similarity = 70.0f)
    private SikuliElement programOptions;

    public ToolsMenu(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickProgramOptions(){
        rootWnd.click(programOptions);
    }
}
