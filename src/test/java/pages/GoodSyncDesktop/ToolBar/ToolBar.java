package pages.GoodSyncDesktop.ToolBar;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class ToolBar {
    private Screen sikuli;

    @FindBy(image = "", similarity = 70.0f)
    private SikuliElement toolBar;

    @FindBy(image = "", similarity = 70.0f)
    private SikuliElement toolsMenu;

    @FindBy(image = "", similarity = 70.0f)
    SikuliElement jobMenu;

    @FindBy(image = "", similarity = 70.0f)
    SikuliElement allMenu;

    @FindBy(image = "", similarity = 70.0f)
    SikuliElement viewMenu;

    @FindBy(image = "", similarity = 70.0f)
    SikuliElement actionMenu;

    @FindBy(image = "", similarity = 70.0f)
    SikuliElement helpMenu;

    public ToolBar(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickToolsInToolBar(){
        toolsMenu.click();
    }

    public void clickJobInToolBar(){
        jobMenu.click();
    }

    public void clickAllInToolBar(){
        allMenu.click();
    }

    public void clickViewInToolBar(){
        viewMenu.click();
    }

    public void clickActionsInToolBar(){
        actionMenu.click();
    }

    public void clickHelpInToolBar(){
        helpMenu.click();
    }
}
