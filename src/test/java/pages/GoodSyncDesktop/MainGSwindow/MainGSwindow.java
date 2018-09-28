package pages.GoodSyncDesktop.MainGSwindow;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;
import pages.GoodSyncDesktop.ToolBar.ToolBar;

public class MainGSwindow {
    private Screen sikuli;
    private ToolBar toolBar;


    public MainGSwindow(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
        toolBar = new ToolBar(sikuli);
    }

}
