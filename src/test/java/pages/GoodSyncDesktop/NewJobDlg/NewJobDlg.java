package pages.GoodSyncDesktop.NewJobDlg;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Screen;

public class NewJobDlg {
    private Screen sikuli;

    @FindBy(images = {"src/test/java/pages/GoodSyncDesktop/NewJobDlg/img/rootWnd.png",
    "src/test/java/pages/GoodSyncDesktop/NewJobDlg/img/rootWndFirstJob.png"}, similarity = 70.0f)
    private SikuliElement rootWnd;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/NewJobDlg/img/inputField.png", similarity = 70.0f)
    private SikuliElement jobNameInputField;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/NewJobDlg/img/syncRadioBtn.png", similarity = 70.0f)
    private SikuliElement synchronizeRadioBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/NewJobDlg/img/backupRadioBtn.png", similarity = 70.0f)
    private SikuliElement backupRadioBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/NewJobDlg/img/okBtnActive.png", similarity = 90.0f)
    private SikuliElement okBtnActive;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/NewJobDlg/img/okBtnInactive.png", similarity = 90.0f)
    private SikuliElement okBtnInactive;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/NewJobDlg/img/cancelBtn.png", similarity = 70.0f)
    private SikuliElement cancelBtn;

    @FindBy(image = "src/test/java/pages/GoodSyncDesktop/NewJobDlg/img/closeWnd.png", similarity = 70.0f)
    private SikuliElement closeWndBtn;

    public NewJobDlg(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, s);
    }

    public void clickOkBtn(){
        if (okBtnActive.exists()){
            okBtnActive.click();
        } else {
            okBtnInactive.click();
        }

    }

    public void clickCancelBtn(){
        cancelBtn.click();
    }

    public void inputJobName(String jobName){
        jobNameInputField.type(jobName);
    }

    public void selectSyncJobType(){
        synchronizeRadioBtn.click();
    }

    public void selectBackupJobType(){
        backupRadioBtn.click();
    }

    public void clickCloseWnd(){
        closeWndBtn.click();
    }

    public boolean isOkBtnActive(){
        return okBtnActive.exists();
    }
}
