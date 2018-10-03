package pages.GoodSyncDesktop.ToolBar.ToolsMenu.ServerAdvancedOptions;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DeleteUserConfirmation {

    public void clickDeleteBtn(){
        $(By.id("action_buttons")).find(By.name("delete")).click();
    }

    public void clickCancelBtn(){
        $(By.id("action_buttons")).find(By.name("cancel")).click();
    }
}
