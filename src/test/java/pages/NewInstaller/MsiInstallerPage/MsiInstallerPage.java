package pages.NewInstaller.MsiInstallerPage;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class MsiInstallerPage {
    Screen sikuli;

    @FindBy(images = {""}, similarity = 70.0f)
    SikuliElement cancelBtn;

    @FindBy(images = {""}, similarity = 70.0f)
    SikuliElement rootElement;

    public MsiInstallerPage(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }
}
