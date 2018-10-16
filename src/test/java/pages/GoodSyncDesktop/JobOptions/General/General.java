package pages.GoodSyncDesktop.JobOptions.General;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.*;

public class General {
    private Screen sikuli;

    @FindBy(image = "", similarity = 50.0f)
    private SikuliElement rootWnd;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/jobTypeRoot.png", similarity = 50.0f)
    private SikuliElement jobTypeRoot;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/dropDownBtn.png", similarity = 90.0f)
    private SikuliElement dropDownBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/2way.png", similarity = 90.0f)
    private SikuliElement twoWaySync;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/ltoR.png", similarity = 90.0f)
    private SikuliElement leftToRight;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/RtoL.png", similarity = 90.0f)
    private SikuliElement rightToLeft;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/checkboxSelected.png", similarity = 70.0f)
    private SikuliElement checkboxSelected;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/propagateDel.png", similarity = 60.0f)
    private SikuliElement propagateDel;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/crtIfNotFound.png", similarity = 70.0f)
    private SikuliElement crtIfNotFound;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/lastVerOnly.png", similarity = 70.0f)
    private SikuliElement lastVerOnly;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/clnUpSaved.png", similarity = 70.0f)
    private SikuliElement cleanup_saved_folder;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/multipleVer.png", similarity = 70.0f)
    private SikuliElement multipleVer;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/clnUpHistory.png", similarity = 70.0f)
    private SikuliElement cleanup_history_folder;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/runParallellThreads.png", similarity = 70.0f)
    private SikuliElement parallelThreads;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/maxTimeToRun.png", similarity = 70.0f)
    private SikuliElement maxTimeToRun;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/saveBtn.png", similarity = 90.0f)
    private SikuliElement saveBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/cancelBtn.png", similarity = 90.0f)
    private SikuliElement cancelBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/JobOptions/General/img/threadsInputFld.png", similarity = 40.0f)
    private SikuliElement threadsInputFld;

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
            Match c = sikuli.find(propagateDel.getImage()).left().exists(checkboxSelected.getImage());
            if(c != null)
                return true;
        } catch (FindFailed findFailed){
            return false;
        }
        return false;
    }

    public void clickOnCrtIfNotFound(){
        crtIfNotFound.click();
    }

    public boolean isCrtIfNotFoundSelected(){
        try {
            Match c = sikuli.find(crtIfNotFound.getImage()).left().exists(checkboxSelected.getImage());
            if(c != null)
                return true;
        } catch (FindFailed findFailed){
            return false;
        }
        return false;
    }

    public void setRunParallelThreads(String threads){
        try {
            Region reg = sikuli.find(parallelThreads.getImage()).right();
            Region desiredReg = reg.find(threadsInputFld.getImage());
            desiredReg.doubleClick();
            desiredReg.type(Key.DELETE);
            desiredReg.type(threads);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void clickOnLastVerOnly(){
        lastVerOnly.click();
    }

    public void clickOnRunParallelThreads(){
        parallelThreads.click();
    }

    public boolean isLastVerOnlySelected(){
        try {
            Match c = sikuli.find(lastVerOnly.getImage()).left().exists(checkboxSelected.getImage());
            if(c != null)
                return true;
        } catch (FindFailed findFailed){
            return false;
        }
        return false;
    }

    public void clickOnCln_Saved_Folder(){
        cleanup_saved_folder.click();
    }

    public boolean isCln_saved_folderSelected(){
        try {
            Match c = sikuli.find(cleanup_saved_folder.getImage()).left().exists(checkboxSelected.getImage());
            if(c != null)
                return true;
        } catch (FindFailed findFailed){
            return false;
        }
        return false;
    }

    public void setCln_saved_folder(String days){
    }

    public void clickOnMultipleVer(){
        multipleVer.click();
    }

    public boolean isMultipleVerSelected(){
        try {
            Match c = sikuli.find(multipleVer.getImage()).left().exists(checkboxSelected.getImage());
            if(c != null)
                return true;
        } catch (FindFailed findFailed){
            return false;
        }
        return false;
    }

    public void clickOnCln_history_folder(){
        cleanup_history_folder.click();
    }

    public boolean isCln_history_folderSelected(){
        try {
            Region m = sikuli.find(cleanup_history_folder.getImage()).left();
            Match c = m.find(checkboxSelected.getImage());
            if(c != null){
                return true;
            }
        } catch (FindFailed findFailed){
            System.err.println("Image was not found in isCln_history_folderSelected() method");
        }
        return false;
    }

    public void setCln_history_folder(String days){

    }

    public void setTotalSecReconnect(String sec){

    }
}
