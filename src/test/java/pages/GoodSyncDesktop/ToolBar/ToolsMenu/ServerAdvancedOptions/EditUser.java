package pages.GoodSyncDesktop.ToolBar.ToolsMenu.ServerAdvancedOptions;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class EditUser {
    public void setHomeFolder(String path){
        $(By.id("contents")).find(By.name("newuserhome")).clear();
        $(By.id("contents")).find(By.name("newuserhome")).val(path);
    }

    public String getHomeFolderPath(){
        return $(By.id("contents")).find(By.name("newuserhome")).getValue();
    }

    public void setDiskQuota(String quota){
        $(By.id("contents")).find(By.name("newdiskquota")).clear();
        $(By.id("contents")).find(By.name("newdiskquota")).val(quota);
    }

    public String getDiskQuota(){
        return $(By.id("contents")).find(By.name("newdiskquota")).getValue();
    }

    public void setSysUserId(String sysUserId){
        $(By.id("contents")).find(By.name("newusersysid")).clear();
        $(By.id("contents")).find(By.name("newusersysid")).val(sysUserId);
    }

    public String getSysUserId(){
        return $(By.id("contents")).find(By.name("newusersysid")).getValue();
    }

    public void setSystemPasswd(String passwd){
        $(By.id("contents")).find(By.name("newusersyspwd")).clear();
        $(By.id("contents")).find(By.name("newusersyspwd")).val(passwd);
    }

    public boolean isServerAdminChecked(){
        String result = $(By.id("contents")).find(By.name("newadmin")).getValue();
        if(result.contains("yes")){
            return true;
        } else {
            return false;
        }
    }

    public void clickOnServerAdminCheckbox(){
        $(By.id("contents")).find(By.name("newadmin")).click();
    }

    public boolean isImpersonatedChecked(){
        String result = $(By.id("contents")).find(By.name("newimpersonate")).getValue();
        if(result.contains("yes")){
            return true;
        } else {
            return false;
        }
    }

    public void clickOnImpersonated(){
        $(By.id("contents")).find(By.name("newimpersonate")).click();
    }

    public boolean isReadOnlyChecked(){
        String result = $(By.id("contents")).find(By.name("newreadonly")).getValue();
        if(result.contains("yes")){
            return true;
        } else {
            return false;
        }
    }

    public void clickReadOnlyCheckbox(){
        $(By.id("contents")).find(By.name("newreadonly")).click();
    }

    public boolean isSysProtectedChecked(){
        String result = $(By.id("contents")).find(By.name("newsysfileprotect")).getValue();
        if(result.contains("yes")){
            return true;
        } else {
            return false;
        }
    }

    public void clickOnSysProtectedCheckbox(){
        $(By.id("contents")).find(By.name("newsysfileprotect")).click();
    }

    public void clickSaveBtn(){
        $(By.id("contents")).find(By.name("save")).click();
    }

    public void clickCancelBtn(){
        $(By.id("contents")).find(By.name("cancel")).click();
    }

    public ChangePassword clickChangePasswdLink(){
        $(By.id("change_password")).find(By.tagName("a")).click();
        return new ChangePassword();
    }
}
