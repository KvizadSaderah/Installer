package pages.Jobs.GoodSync;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class ControlCenter {
    String baseUrl = "https://jobs.goodsync.com";

    public void openPage(){
        open(baseUrl);
        $(By.id("wrapper")).waitUntil(Condition.text("GoodSync Control Center"), 10);
        Assert.assertTrue($(By.id("wrapper")).exists(), "jobs.goodsync.com is not opened");
    }

    public void loginToControlCenter(){
        $(By.id("userid")).val("yurkov@siber.com");
        $(By.id("password")).val("123456");
        $(By.name("login")).click();
        $(By.id("wrapper")).waitUntil(Condition.text("TestGSCC"), 10);
    }

    public void goToRunnersPage(){
        open(baseUrl + "/ui/job-runners");
        $(By.id("tbl-job-runners")).waitUntil(Condition.appear, 10);
    }

    public void clickShowInactive(){
        $(By.id("cb-show-inactive")).parent()
                .find(By.className("toggle")).click();
    }

    public String getRunnerOnlineStatus(String runnerName){
        return $(By.id("tbl-job-runners")).find(By.tagName("tbody"))
                .find(By.linkText(runnerName)).parent().parent()
                .find(By.cssSelector("span.label.label-default")).getText();
    }

    public String getRunnerActiveStatus(String runnerName){
        return $(By.id("tbl-job-runners")).find(By.tagName("tbody"))
                .find(By.linkText(runnerName)).parent().parent()
                .find(By.cssSelector("span.label.label-success")).getText().trim();
    }

    public boolean isRunnerAuthorized(String runnerName){
        boolean result = false;
        String str =  $(By.id("tbl-job-runners")).find(By.tagName("tbody"))
                .find(By.linkText(runnerName)).parent().parent()
                .find(By.name("cb-is-auth")).getAttribute("checked");
        if(str != null){
            result = true;
        }
        return result;
    }

    public void clickAuthCheckBox(String runnerName){
        $(By.id("tbl-job-runners")).find(By.tagName("tbody"))
                .find(By.linkText(runnerName)).parent().parent()
                .find(By.name("cb-is-auth")).click();
        $(By.id("btn-save-changes")).click();
        $(By.className("modal-content")).find(By.className("modal-footer"))
                .find(By.className("btn btn-success")).click();
    }

    public void deleteUser(String userName, boolean cleanup){
        if(cleanup){
            close();
            open(baseUrl);
            loginToControlCenter();
        }
        open(baseUrl + "/ui/users");
        // if exists so delete it.
        // if block needs to be here in case of we try to delete user which is not exists on after method or on SetUp phase
        if($(By.id("tbl-users")).find(By.tagName("tbody"))
                .find(By.linkText(userName)).exists())
            {
                $(By.id("tbl-users")).find(By.tagName("tbody"))
                        .find(By.linkText(userName)).parent().parent()
                        .find(By.name("mass-action")).parent().find(By.className("check")).click();
                $(By.id("btn-remove-checked")).click();
                $(By.cssSelector(".modal-dialog .btn-danger")).click();

            }
        if(cleanup){
            close();
        }
    }



}
