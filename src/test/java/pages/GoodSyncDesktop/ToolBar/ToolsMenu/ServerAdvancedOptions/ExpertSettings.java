package pages.GoodSyncDesktop.ToolBar.ToolsMenu.ServerAdvancedOptions;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ExpertSettings {
    public void setLogLevel(String loglevel){
        $(By.name("loglevel")).selectOption(loglevel);
    }

    public String getRetainLogsValue(){
        return $(By.id("contents")).find(By.name("logretain")).getValue();
    }

    public void setLogRetain(String value){
        $(By.id("contents")).find(By.name("logretain")).val(value);
    }

    public String getMaxConnValue(){
        return $(By.id("contents")).find(By.name("maxconn")).getValue();
    }

    public void setMaxConnect(String value){
        $(By.id("contents")).find(By.name("maxconn")).clear();
        $(By.id("contents")).find(By.name("maxconn")).val(value);
    }

    public String getLogFolderValue(){
        return $(By.id("contents")).find(By.name("logfolder")).getValue();
    }

    public void setLogFolder(String path){
        $(By.id("contents")).find(By.name("logfolder")).clear();
        $(By.id("contents")).find(By.name("logfolder")).val(path);
    }

    public void clickApplyBtn(){
        $(By.id("action_buttons")).find(By.name("apply")).click();
    }
}
