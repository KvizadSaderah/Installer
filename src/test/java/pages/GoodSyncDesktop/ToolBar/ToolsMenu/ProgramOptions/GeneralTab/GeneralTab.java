package pages.GoodSyncDesktop.ToolBar.ToolsMenu.ProgramOptions.GeneralTab;

import data.Languages;
import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class GeneralTab {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/checkboxSelected.png"
            , similarity = 90.0f)
    private SikuliElement selectedCheckbox;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/rootWnd.png"
            , similarity = 30.0f)
    private SikuliElement rootWnd;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/noAnimation.png"
            , similarity = 90.0f)
    private SikuliElement noAnimation;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/sortByAlphabet.png"
            , similarity = 90.0f)
    private SikuliElement sortJobByAlphabet;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/showGroups.png"
            , similarity = 90.0f)
    private SikuliElement showGroups;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/showInTray.png"
            , similarity = 90.0f)
    private SikuliElement showInSysTray;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/hideWhenClosed.png"
            , similarity = 90.0f)
    private SikuliElement hideToTrayWhenClosed;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/startWinStarts.png"
            , similarity = 90.0f)
    private SikuliElement startWhenWinStarts;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/addShortcuts.png"
            , similarity = 90.0f)
    private SikuliElement addShortcuts;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/storeLogs.png"
            , similarity = 90.0f)
    private SikuliElement storeLogsInFolderField;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/retainLogs.png"
            , similarity = 90.0f)
    private SikuliElement retainLogsField;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/saveBtn.png"
            , similarity = 90.0f)
    private SikuliElement saveBtn;

    @FindBy(image = "src/test/java/pages/ToolBar/ToolsMenu/ProgramOptions/GeneralTab/img/cancelBtn.png"
            , similarity = 90.0f)
    private SikuliElement cancelBtn;

    public GeneralTab(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void clickOnNoAnimationOnAnalyze(){
        noAnimation.click();
    }

    public boolean isNoAnimationOnAnalyzeSelected(){
        try {
            sikuli.find(noAnimation.getImage()).left().exists(selectedCheckbox.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void clickOnSortJobByAlphabet(){
        sortJobByAlphabet.click();
    }

    public boolean isSortByAlphabetSelected(){
        try {
            sikuli.find(sortJobByAlphabet.getImage()).left().exists(selectedCheckbox.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void clickShowGroups(){
        showGroups.click();
    }

    public boolean isShowGroupsSelected(){
        try {
            sikuli.find(showGroups.getImage()).left().exists(selectedCheckbox.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void clickOnShowIconInSysTray(){
        showInSysTray.click();
    }

    public boolean isShowIconInSysTraySelected(){
        try {
            sikuli.find(showInSysTray.getImage()).left().exists(selectedCheckbox.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void clickOnHideToTrayWhenClosed(){
        hideToTrayWhenClosed.click();
    }

    public boolean isHideToTrayOnCloseSelected(){
        try {
            sikuli.find(hideToTrayWhenClosed.getImage()).left().exists(selectedCheckbox.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void clickOnStartWhenWindowsStarts(){
        startWhenWinStarts.click();
    }

    public boolean isStartOnWinStartsSelected(){
        try {
            sikuli.find(startWhenWinStarts.getImage()).left().exists(selectedCheckbox.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void clickOnAddShortCuts(){
        addShortcuts.click();
    }

    public boolean isAddShortcutsSelected(){
        try {
            sikuli.find(addShortcuts.getImage()).left().exists(selectedCheckbox.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void setLogsFolder(String path){
        storeLogsInFolderField.doubleClick();
        storeLogsInFolderField.type(Key.DELETE);
        storeLogsInFolderField.type(path);
    }

    public void setRetainLogs(String retainDays){
        retainLogsField.doubleClick();
        retainLogsField.type(Key.DELETE);
        retainLogsField.type(retainDays);
    }

    public void clickSaveBtn(){
        saveBtn.click();
    }

    public void clickCancelBtn(){
        cancelBtn.click();
    }


}
