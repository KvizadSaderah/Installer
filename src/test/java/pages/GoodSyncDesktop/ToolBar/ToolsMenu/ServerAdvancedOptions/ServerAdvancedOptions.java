package pages.GoodSyncDesktop.ToolBar.ToolsMenu.ServerAdvancedOptions;

import com.codeborne.selenide.Condition;
import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.openqa.selenium.By;
import org.sikuli.script.Screen;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class ServerAdvancedOptions {
    private Screen sikuli;
    private String localHostURL = "http://localhost:11000";

    @FindBy(image = "", similarity = 60.0f)
    private SikuliElement loginForm;

    @FindBy(image = "", similarity = 60.0f)
    private SikuliElement loginField;

    @FindBy(image = "", similarity = 60.0f)
    private SikuliElement passwdField;

    @FindBy(image = "", similarity = 50.0f)
    private SikuliElement enterBtn;

    @FindBy(image = "", similarity = 50.0f)
    private SikuliElement cancelBtn;

    public ServerAdvancedOptions(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void openLoginPage(){
        open(localHostURL);
    }

    public void goToUsersPage(){
        open(localHostURL + "/access");
    }

    public void goToSettingsPage(){
        open(localHostURL + "/settings");
    }

    public void goToSessionsPage(){
        open(localHostURL + "/connect_status");
    }

    public void goToLogsPage(){
        open(localHostURL + "/logs");
    }

    public void goToLicensePage(){
        open(localHostURL + "/license");
    }

    public void goToHelpPage(){
        open(localHostURL + "/help");
    }

    public void logOut(){
        $(By.id("logout")).click();
    }

    public void inputLogin(String login){
        loginField.type(login);
    }

    public void inputPasswd(String passwd){
        passwdField.type(passwd);
    }

    public void clickEnterBtn(){
        enterBtn.click();
    }

    public void clickCancelBtn(){
        cancelBtn.click();
    }

    public boolean isLoginPromtOpenned(){
        return loginForm.exists();
    }

    public boolean waitForLoginFormAppear(int sec){
        for(int i = 0; i < sec; i++){
            if(!loginForm.exists()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public void clikcAddNewUser(){
        $(By.id("add_user")).find(By.tagName("a")).click();
    }

    public void clickEditUser(String userId){
        $(By.id("user_list")).find(By.tagName("tbody")).find(By.linkText(userId)).parent()
                .find(By.partialLinkText("edit_user")).click();
    }

    public void clickDeleteUser(String userId){
        $(By.id("user_list")).find(By.tagName("tbody")).find(By.linkText(userId)).parent()
                .find(By.partialLinkText("delete_user")).click();
    }

    public boolean isAccountGSConnectType(String userId){
        return $(By.id("user_list")).find(By.tagName("tbody")).find(By.linkText(userId)).parent()
                .find(By.xpath("/td[2]")).getText().contains("GoodSync Connect");
    }

    public String getUserEmail(String userId){
        return $(By.id("user_list")).find(By.tagName("tbody")).find(By.linkText(userId)).parent()
                .find(By.xpath("/td[3]")).getText();
    }

    public boolean isAdmin(String userId){
        String result = $(By.id("user_list")).find(By.tagName("tbody")).find(By.linkText(userId)).parent()
                .find(By.xpath("/td[4]")).attr("alt");
        if(result.contains("yes")){
            return true;
        } else {
            return false;
        }
    }

}
