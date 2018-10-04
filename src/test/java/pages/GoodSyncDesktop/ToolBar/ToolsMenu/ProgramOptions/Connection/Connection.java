package pages.GoodSyncDesktop.ToolBar.ToolsMenu.ProgramOptions.Connection;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class Connection {
    private Screen sikuli;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement useIEproxy;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement useThisProxy;

    @FindBy(image = "", similarity = 50.0f)
    private SikuliElement proxyServerField;

    public Connection(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }
}
