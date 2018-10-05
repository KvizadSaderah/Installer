package pages.GoodSyncDesktop.JobOptions.General;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class General {
    private Screen sikuli;

    @FindBy(image = "", similarity = 50.0f)
    private SikuliElement rootWnd;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement checkboxSelected;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement propagateDel;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement crtIfNotFound;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement lastVerOnly;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement cleanup_saved_folder;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement multipleVer;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement cleanup_history_folder;

    @FindBy(image = "", similarity = 50.0f)
    private SikuliElement cln_saved_field;

    @FindBy(image = "", similarity = 50.0f)
    private SikuliElement cln_history_field;

    @FindBy(image = "", similarity = 50.0f)
    private SikuliElement totalSecReconnField;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement parallelThreads;

    @FindBy(image = "", similarity = 50.0f)
    private SikuliElement parallelThreadsField;

    @FindBy(image = "", similarity = 50.0f)
    private SikuliElement speedLimitField;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement maxTimeToRun;

    @FindBy(image = "", similarity = 50.0f)
    private SikuliElement maxTimeToRunField;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement saveBtn;

    @FindBy(image = "", similarity = 90.0f)
    private SikuliElement cancelBtn;

    public General(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public boolean IsGeneralTabOpened(){
        return rootWnd.exists();
    }

    public void clickOnPropagateDeletions(){
        propagateDel.click();
    }

    public boolean isPropagateDelSelected(){
        try {
            sikuli.find(propagateDel.getImage()).left().exists(checkboxSelected.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void clickOnCrtIfNotFound(){
        crtIfNotFound.click();
    }

    public boolean isCrtIfNotFoundSelected(){
        try {
            sikuli.find(crtIfNotFound.getImage()).left().exists(checkboxSelected.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void clickOnLastVerOnly(){
        lastVerOnly.click();
    }

    public boolean isLastVerOnlySelected(){
        try {
            sikuli.find(lastVerOnly.getImage()).left().exists(checkboxSelected.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void clickOnCln_Saved_Folder(){
        cleanup_saved_folder.click();
    }

    public boolean isCln_saved_folderSelected(){
        try {
            sikuli.find(cleanup_saved_folder.getImage()).left().exists(checkboxSelected.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void setCln_saved_folder(String days){
        cln_saved_field.doubleClick();
        cln_saved_field.type(Key.DELETE);
        cln_saved_field.type(days);
    }

    public void clickOnMultipleVer(){
        multipleVer.click();
    }

    public boolean isMultipleVerSelected(){
        try {
            sikuli.find(multipleVer.getImage()).left().exists(checkboxSelected.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void clickOnCln_history_folder(){
        cleanup_history_folder.click();
    }

    public boolean isCln_history_folderSelected(){
        try {
            sikuli.find(cleanup_history_folder.getImage()).left().exists(checkboxSelected.getImage());
            return true;
        } catch (FindFailed findFailed){
            return false;
        }
    }

    public void setCln_history_folder(String days){
        cln_history_field.doubleClick();
        cln_history_field.type(Key.DELETE);
        cln_history_field.type(days);
    }

    public void setTotalSecReconnect(String sec){
        totalSecReconnField.doubleClick();
        totalSecReconnField.type(Key.DELETE);
        totalSecReconnField.type(sec);
    }
}
