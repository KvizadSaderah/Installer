package pages.GoodSyncDesktop.ToolBar.ToolsMenu.ServerAdvancedOptions;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ChangePassword {

    public void setOldPasswd(String oldPasswd){
        $(By.id("contents")).find(By.name("oldpassword")).val(oldPasswd);
    }

    public void setNewPasswd(String newPasswd){
        $(By.id("contents")).find(By.name("newpassword")).val(newPasswd);
    }

    public void setConfirmNewPasswd(String confirmPwd){
        $(By.id("contents")).find(By.name("newpassword2")).val(confirmPwd);
    }

    public void clickChangeBtn(){
        $(By.id("action_buttons")).find(By.name("change")).click();
    }

    public void clickCancelBtn(){
        $(By.id("action_buttons")).find(By.name("cancel")).click();
    }
}
