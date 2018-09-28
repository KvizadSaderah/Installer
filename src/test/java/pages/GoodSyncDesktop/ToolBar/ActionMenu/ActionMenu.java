package pages.GoodSyncDesktop.ToolBar.ActionMenu;

import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class ActionMenu {
    private Screen sikuli;

    public ActionMenu(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }
}
