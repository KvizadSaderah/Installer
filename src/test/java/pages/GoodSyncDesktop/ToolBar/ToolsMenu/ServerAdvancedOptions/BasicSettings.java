package pages.GoodSyncDesktop.ToolBar.ToolsMenu.ServerAdvancedOptions;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BasicSettings {

    //TODO re-write xpath to more reliable. remove exact pointer to place in DOM like []

    public String getUserId(){
        return $(By.id("contents")).find(By.xpath("//tbody/tr[1]/td[2]")).getText().trim();
    }

    public void setComputerId(String id){
        $(By.id("contents")).find(By.name("computerid")).clear();
        $(By.id("contents")).find(By.name("computerid")).click();
    }

    public String getComputerIdValue(){
        return $(By.id("contents")).find(By.name("computerid")).getValue();
    }

    public void clickAutomaticUPnP(){
        $(By.id("contents")).find(By.xpath("//tbody/tr[4]/td[2]/input")).click();
    }

    public boolean isAutomaticUPnPSelected(){
        String result = $(By.id("contents")).find(By.xpath("//tbody/tr[4]/td[2]/input")).getValue();
        if(result.contains("yes")){
            return true;
        } else {
            return false;
        }
    }

    public void clickManualMapExternalPort(){
        $(By.id("contents")).find(By.xpath("//tbody/tr[5]/td[2]/input[1]")).click();
    }

    public boolean isManualMapExternalPortSelected(){
        String result = $(By.id("contents")).find(By.xpath("//tbody/tr[5]/td[2]/input[1]")).getValue();
        if(result.contains("yes")){
            return true;
        } else {
            return false;
        }
    }

    public void setManualPort(String port){
        $(By.id("contents")).find(By.name("externalportmanualno")).clear();
        $(By.id("contents")).find(By.name("externalportmanualno")).val(port);
    }

    public boolean isDiscoverableSelected(){
        String result = $(By.id("contents")).find(By.name("globaldisco")).getValue();
        if(result.contains("yes")){
            return true;
        } else {
            return false;
        }
    }

    public void clickOnDiscoveryModeCheckbox(){
        $(By.id("contents")).find(By.name("globaldisco")).click();
    }

    public boolean isFileServerSelected(){
       String result = $(By.id("contents")).find(By.name("fileserver")).getValue();
       if(result.contains("yes")){
           return true;
       } else {
           return false;
       }
    }

    public void clickOnFileServerCheckbox(){
        $(By.id("contents")).find(By.name("fileserver")).click();
    }

    public void setFileServerPort(String port){
        $(By.id("contents")).find(By.name("fileserverport")).clear();
        $(By.id("contents")).find(By.name("fileserverport")).val(port);
    }

    public String getFileServerPortValue(){
        return $(By.id("contents")).find(By.name("fileserverport")).getValue();
    }

    public boolean isFileServerLocalOnlySelected(){
        String result = $(By.id("contents")).find(By.name("localonly")).getValue();
        if(result.contains("yes")){
            return true;
        } else {
            return false;
        }
    }

    public void clickOnFileServerLocalOnlyCheckbox(){
        $(By.id("contents")).find(By.name("localonly")).click();
    }

    public boolean isForwarderServerSelected(){
        String result = $(By.id("contents")).find(By.name("forwarderserver")).getValue();
        if(result.contains("yes")){
            return true;
        } else {
            return false;
        }
    }

    public void clickOnForwarderServerCheckbox(){
        $(By.id("contents")).find(By.name("forwarderserver")).click();
    }

    public boolean isWebInterfaceSelected(){
        String result = $(By.id("contents")).find(By.name("webui")).getValue();
        if(result.contains("yes")){
            return true;
        } else {
            return false;
        }
    }

    public void clickOnWebInterfaceCheckbox(){
        $(By.id("contents")).find(By.name("webui")).click();
    }

    public void setWebInterfacePort(String port){
        $(By.id("contents")).find(By.name("webuiport")).clear();
        $(By.id("contents")).find(By.name("webuiport")).val(port);
    }

    public boolean isWebInterfaceLocalOnlySelected(){
        String result = $(By.id("contents")).find(By.name("webuilocalonly")).getValue();
        if(result.contains("yes")){
            return true;
        } else {
            return false;
        }
    }

    public void clickOnWebInterfaceLocalOnlyCheckbox(){
        $(By.id("contents")).find(By.name("webuilocalonly")).click();
    }

    public void clickApplyBtn(){
        $(By.id("action_buttons")).find(By.name("apply")).click();
    }

    public ExpertSettings switchToExpertSettings(){
        $(By.id("settings_switch")).find(By.partialLinkText("expert_settings")).click();
        return new ExpertSettings();
    }
}
