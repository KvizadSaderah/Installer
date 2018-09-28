package tests;

import Helper.GoodSyncHelper;
import Helper.SysHelper;
import Helper.WinRegistryHelper;
import LogUtils.GsServerLogReader;
import com.codeborne.selenide.Configuration;
import data.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.sikuli.script.App;
import org.sikuli.script.Screen;
import org.sikuli.vnc.VNCScreen;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.Jobs.GoodSync.ControlCenter;
import pages.NewInstaller.CCinstallerPage.CCInstallerPage;
import pages.NewInstaller.LanguageSelectionModule.LanguageSelectionModule;
import pages.NewInstaller.ModalWnds.CCRunnerInstaller.CCRunnerInstaller;
import tests.Listeners.MyListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@Listeners({MyListener.class})
public class RunnerInstallationTests extends BaseTest {
    private String runnerName = "test@DESKTOP-90PJ87A";
    boolean needsJobsCleanAfter = false;
    private String userName = System.getProperty("user.name");
    private String computerName;
    {
        try {
            computerName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void beforeClass(){
        /*FileDownloader.downloadFile(Links.httpUrl + Links.goodsync_v10_CC_Runner_setup_pvt,
                "C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);*/
        // delete previous test results
        if(SysHelper.isFolderOrFileExist("C:\\TestResults")){
            SysHelper.deleteDirOrFile("C:\\TestResults");
        }
        GoodSyncHelper.hardDeleteGoodSync();
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        // we need to be sure that user is deleted on jobs.gs.com from previous iterations
        new ControlCenter().deleteUser("test", true);
    }

    @AfterMethod
    public void afterMethod(){
        if(needsJobsCleanAfter){
            new ControlCenter().deleteUser("test", true);
        }
        GoodSyncHelper.hardDeleteGoodSync();

    }

    @Test
    @Description("The test checks that new user installs first time CC-Runner.\n" +
            "Steps:\n" +
            "1. Run installer and input 131 company id\n" +
            "2. Click install button\n" +
            "3. Wait until mini progress wnd appears\n" +
            "4. Go to jobs.goodsync.com\n" +
            "5. Go to Runners page and click show inactive\n" +
            "6. Check that runner exist in table\n" +
            "7. Check that runner is Active\n" +
            "8. Check that Runner is not authorized\n")
    public void installRunnerFirstTime(){
        needsJobsCleanAfter = true;
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Installer main wnd is not appeared in 10 sec");
        installer.setCompanyIdFieldToValue("131");
        installer.clickInstallBtn();
        Assert.assertTrue(installer.waitForMiniProgressAppear(10), "Progress wnd did not appear in 10 sec");
        ControlCenter cc = new ControlCenter();
        cc.openPage();
        cc.loginToControlCenter();
        cc.goToRunnersPage();
        cc.clickShowInactive();
        Assert.assertEquals(cc.getRunnerActiveStatus(runnerName),
                "true", "Runner status is not equal to Active == true");
        Assert.assertFalse(cc.isRunnerAuthorized(runnerName),
                "Runner should be unauthorized but found authorized");
        Assert.assertTrue(GsServerLogReader
                        .findStringInLog("Enterprise Runner: Registering New User=",
                                "C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                                "Runner", true),
                "The string 'Enterprise Runner: Registering New User=' not found in runner log");
        Map<String, String> runnerRegKeys = WinRegistryHelper.readRegValues(WinRegConst.HKLM,
                "SOFTWARE\\Policies\\Siber Systems\\GoodSync");
        String enterpRunnerConfig = runnerRegKeys.get("EnterpriseRunnerConfig");
        String runnerServiceUserId = runnerRegKeys.get("RunnerServiceUserId");
        String miniProgress = runnerRegKeys.get("MiniProgress");
        Assert.assertEquals(enterpRunnerConfig, "x",
                "Enterprise runner config is not set to 'x'. Real value is: " + enterpRunnerConfig);
        Assert.assertEquals(runnerServiceUserId, "",
                "Runner service user id is not empty. Actual value: " + runnerServiceUserId);
        Assert.assertEquals(miniProgress, "0x1", "Mini progress expected to be 0x1 but found: " + miniProgress);
    }

    @Test
    @Issue("#165414")
    @Description("The test checks that company id field can not be left empty on first installation\n" +
            "Steps:\n" +
            "1. Run installer\n" +
            "2. Leave company id field empty\n" +
            "3. Click install button\n" +
            "4. Check that error modal wnd opened\n")
    public void companyIdCanNotBeEmpty(){
        needsJobsCleanAfter = false;
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Installer main wnd is not appeared in 10 sec");
        installer.clickInstallBtn();
        CCRunnerInstaller errorModalWnd = new CCRunnerInstaller(new Screen());
        Assert.assertTrue(errorModalWnd.isBadCompanyIdErrorMsgOpened(), "Error modal wnd is not opened");
    }

    @Test
    @Description("The test checks that company id value can only be digit value\n" +
            "Steps:\n" +
            "1. Run installer\n" +
            "2. Input 'dddd' to companyId field\n" +
            "3. Click install button\n" +
            "4. Check if error modal wnd appears\n")
    public void companyIdCanNotBeNonDigitValue(){
        needsJobsCleanAfter = false;
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Installer main wnd is not appeared in 10 sec");
        installer.setCompanyIdFieldToValue("dddd");
        installer.clickInstallBtn();
        CCRunnerInstaller errorModalWnd = new CCRunnerInstaller(new Screen());
        Assert.assertTrue(errorModalWnd.isBadCompanyIdErrorMsgOpened(), "Error modal wnd is not opened");
    }

    @Test
    @Description("The test checks that install over must load company id from registry.\n" +
            "Steps:\n" +
            "1. Install CC runner first time to company id 131\n" +
            "2. Run installer again\n" +
            "3. Check that company id field is set to 131\n" +
            "4. Compete installation over\n" +
            "5. Check that runner didn't send new user API request\n")
    public void installOverMustLoadDefaultValuesFromRegistry(){
        needsJobsCleanAfter = true;
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10),
                "Installer main wnd is not appeared in 10 sec on first install");
        installer.setCompanyIdFieldToValue("131");
        installer.clickInstallBtn();
        Assert.assertTrue(installer.waitForMiniProgressAppear(10), "Progress wnd did not appear in 10 sec");
        // install over here
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        Assert.assertTrue(installer.waitForMainWndAppear(15), "Installer main wnd is not appeared in 15 sec");
        Assert.assertTrue(installer.isCompanyIdSetTo131(), "Company id field is not set to 131 value but should be");
        installer.clickInstallBtn();
        Assert.assertTrue(installer.waitForMiniProgressWndDisappear(10),
                "Mini progress didn't disappear in 10 sec on install over procedure");
        Assert.assertTrue(installer.waitForMiniProgressAppear(10), "Progress wnd did not appear in 10 sec");
        Assert.assertTrue(GsServerLogReader
                        .findStringInLog("bRunnerUpdate=1",
                                "C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                                "InstallUser", true),
                "The string 'bRunnerUpdate=1' not found in runner log, but should be on install-over");
    }

    @Test
    @Description("The test checks that install over must keep previously selected installation language\n" +
            "Steps:\n" +
            "1. Install CC runner with German language\n" +
            "2. Run installer again\n" +
            "3. Check that installer has selected language German\n")
    public void installOverMustKeepPreviousSelectedLanguage(){
        needsJobsCleanAfter = true;
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Installer main wnd is not appeared in 10 sec");
        LanguageSelectionModule language = new LanguageSelectionModule(new Screen());
        language.clickOnLangSelectionBtn();
        language.selectLanguage(Languages.GERMAN.getId());
        installer.setCompanyIdFieldToValue("131");
        installer.clickInstallBtn();
        Assert.assertTrue(installer.waitForMiniProgressAppear(15), "Progress wnd did not appear in 15 sec");
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        Assert.assertTrue(installer.waitForMainWndAppear(15), "Installer main wnd is not appeared in 15 sec");
        Assert.assertTrue(language.isLanguageSelected(Languages.GERMAN.getId()),
                "Installer Language expected to be german but not");
        Assert.assertTrue(GsServerLogReader
                        .findStringInLog("bRunnerUpdate=1",
                                "C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                                "InstallUser", true),
                "The string 'bRunnerUpdate=1' not found in runner log, but should be on install-over");
    }

    //TODO do we really need that case?
    /*@Test
    public void installOverOfAuthRunnerMustNotChangeItsStatusOnWebPage(){
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(15), "Installer main wnd is not appeared in 15 sec");
        installer.setCompanyIdFieldToValue("131");
        installer.clickInstallBtn();
        Assert.assertTrue(installer.waitForMiniProgressAppear(15), "Progress wnd did not appear in 15 sec");
        ControlCenter cc = new ControlCenter();
        cc.openPage();
        cc.loginToControlCenter();
        cc.goToRunnersPage();
        cc.clickShowInactive();
        cc.clickAuthCheckBox(runnerName);
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        Assert.assertTrue(installer.waitForMainWndAppear(15), "Installer main wnd is not appeared in 15 sec");
        installer.setCompanyIdFieldToValue("131");
        installer.clickInstallBtn();
        Assert.assertTrue(installer.waitForMiniProgressWndDisappear(15),
                "Mini progress didn't disappear in 15 sec on install over procedure");
        Assert.assertTrue(installer.waitForMiniProgressAppear(15), "Progress wnd did not appear in 15 sec");
        Assert.assertFalse(GsServerLogReader
                        .findStringInLog("Enterprise Runner: Registering New User=",
                                "C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                                "Runner", true),
                "The string 'Enterprise Runner: Registering New User=' not found in runner log");
        cc.openPage();
        cc.goToRunnersPage();
        cc.clickShowInactive();
        Assert.assertTrue(cc.isRunnerAuthorized(runnerName), "Runner is not authorized but should be");
        Assert.assertEquals(cc.getRunnerActiveStatus(runnerName), "true", "Runner should be active but found not");
    }*/

    @Test
    @Description("The test checks that cc-runner can be installed silently from cmd line as User\n" +
            "Steps:\n" +
            "1. Run cmd cc-installer.exe /cc-runner=User /cc-coid=131\n" +
            "2. Check that runner is present on jobs.gs.com\n" +
            "3. Check that registry keys is correct\n" +
            "4. Check that runner mini progress wnd appears\n")
    public void runnerCanBeInstalledFromCMDasNonService(){
        needsJobsCleanAfter = true;
        new GoodSyncHelper().buildRunnerCMD().setCCRunner("user").setCCCoid("131").executeCMD();
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMiniProgressAppear(10),
                "Mini progress wnd didn't appear in 10 sec after cmd command execution called");
        ControlCenter cc = new ControlCenter();
        cc.openPage();
        cc.loginToControlCenter();
        cc.goToRunnersPage();
        cc.clickShowInactive();
        softAssert.assertFalse(cc.isRunnerAuthorized(runnerName),
                "runner should not be authorized on jobs.gs.com or runner is not found");
        softAssert.assertTrue(SysHelper.isAppStatusRunning("gs-runner"),
                "gs-runner.exe is not in running state or not found");
        softAssert.assertTrue(GsServerLogReader
                        .findStringInLog("Enterprise Runner: Registering New User=",
                                "C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                                "Runner", true),
                "The string 'Enterprise Runner: Registering New User=' not found in runner log");
        Map<String, String> registry = WinRegistryHelper.readRegValues(WinRegConst.HKLM,
                WinRegConst.SOFTWARE_POLICIES_SIBER_GS);
        softAssert.assertEquals(registry.get("EnterpriseRunnerConfig"), "x",
                "EnterpriseRunnerConfig expected to be 'x' but found: " + registry.get("EnterpriseRunnerConfig"));
        softAssert.assertEquals(registry.get("JobServerUrl"), "https://jobs.goodsync.com",
                "JobServerUrl key expected to be https://jobs.goodsync.com but found: " + registry.get("JobServerUrl"));
        softAssert.assertEquals(registry.get("RunnerServiceUserId"), "0x1",
                "RunnerServiceUserId expected to be empty but found: " + registry.get("RunnerServiceUserId"));
        softAssert.assertAll();
    }

    @Test
    @Description("The test checks that cc-runner can be installed as service\n" +
            "Steps:\n" +
            "1. Run cmd line with params: /cc-runner=Service /cc-coid=131 /sysUsrId=UserId /sysPasswd=syspwd\n" +
            "2. Wait for installation completes\n" +
            "3. Check registry keys for correct values\n" +
            "4. Check that runner send new-user API request\n" +
            "5. Check that gs-runner.exe has RUNNING status")
    public void runnerCanBeInstalledAsServiceFromCMD(){
        needsJobsCleanAfter = true;
        new GoodSyncHelper().buildRunnerCMD().setCCCoid("131").setCCRunner("service").setSysUserId("test")
                .setSysPasswd("123456").setSilent().executeCMD();
        // TODO wait for 10 sec must be replaced with conditional
        wait(10);
        softAssert.assertTrue(SysHelper.isServiceStatusRunning("GsRunner " + userName),
                "gs-runner.exe is not in running state or not found");
        softAssert.assertTrue(GsServerLogReader
                        .findStringInLog("Enterprise Runner: Registering New User=",
                                "C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                                "Runner", true),
                "The string 'Enterprise Runner: Registering New User=' not found in runner log");
        Map<String, String> registry = WinRegistryHelper.readRegValues(WinRegConst.HKLM,
                WinRegConst.SOFTWARE_POLICIES_SIBER_GS);
        softAssert.assertEquals(registry.get("EnterpriseRunnerConfig"), "x",
                "EnterpriseRunnerConfig expected to be 'x' but found: " + registry.get("EnterpriseRunnerConfig"));
        softAssert.assertEquals(registry.get("RunnerServiceUserId"), "test",
                "RunnerServiceUserId key expected to be test but found: " + registry.get("RunnerServiceUserId"));
        softAssert.assertEquals(registry.get("MiniProgress"), "0x0",
                "MiniProgress key expected to be 0x0 but found: " + registry.get("MiniProgress"));
        softAssert.assertEquals(registry.get("UserClose"), "0x0",
                "UserClose key expected to be 0x0 but found: " + registry.get("UserClose"));
        softAssert.assertEquals(registry.get("CompanyId"), "131",
                "CompanyId expected to be '131' but found: " + registry.get("CompanyId"));
        softAssert.assertAll();
    }

    @Test
    @Description("The test checks that if /cc-runner= key is empty installer consider it as installation as User\n" +
            "Steps:\n" +
            "1. Run cmd with empty /cc-runner=\n" +
            "2. Check that mini progress wnd appears\n" +
            "3. Check registry key values\n")
    public void installRunnerWithEmptyCC_runnerParam(){
        needsJobsCleanAfter = true;
        new GoodSyncHelper().buildRunnerCMD().setCCRunner("").setCCCoid("131").setSilent().executeCMD();
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMiniProgressAppear(10), "Mini progress wnd didn't appear in 10 sec");
        Map<String, String> registry = WinRegistryHelper.readRegValues(WinRegConst.HKLM,
                WinRegConst.SOFTWARE_POLICIES_SIBER_GS);
        Assert.assertEquals(registry.get("RunnerServiceUserId"), "",
                "RunnerServiceUserId key expected to be empty but found: " + registry.get("RunnerServiceUserId"));
    }

    @Test
    @Description("The test checks that if runner installed from CMD and no company id were entered - modal wnd must appear\n" +
            "Steps:\n" +
            "1. Run CMD installation with no coid param value\n" +
            "2. Check that GS installed in Program Files/Siber Systems/Goodsync\n" +
            "3. Check that gs-runner.exe is not in process list\n")
    public void installRunnerAsUserWithEmptyCC_coidParam(){
        needsJobsCleanAfter = false;
        new GoodSyncHelper().buildRunnerCMD().setCCRunner("user").setCCCoid("").setSilent().executeCMD();
        CCRunnerInstaller installer = new CCRunnerInstaller(new Screen());
        Assert.assertTrue(installer.waitForCompanyIdFormatErrorMsgAppears(10),
                "Modal with error company id param error didn't appear or not found in 10 sec");
        softAssert.assertTrue(SysHelper.isFolderOrFileExist("C:\\Program Files\\Siber Systems\\GoodSync"),
                "GoodSync directory is not found at sysDisk\\Program Files\\Siber System");
        softAssert.assertFalse(SysHelper.isServiceStatusRunning("gs-runner.exe"),
                "gs-runner is running but should be not cuz after companyid error it should be closed");
        softAssert.assertAll();
    }

    @Test
    @Description("The test checks that if runner installed from CMD and no digit company id were entered - modal wnd must appear\n" +
            "Steps:\n" +
            "1. Run CMD installation with no digit coid param value\n" +
            "2. Check that GS installed in Program Files/Siber Systems/Goodsync\n" +
            "3. Check that gs-runner.exe is not in process list\n")
    public void installRunnerAsUserWithNonDigitCC_coidParam(){
        needsJobsCleanAfter = false;
        new GoodSyncHelper().buildRunnerCMD().setCCRunner("user").setCCCoid("qqqq").setSilent().executeCMD();
        CCRunnerInstaller installer = new CCRunnerInstaller(new Screen());
        Assert.assertTrue(installer.waitForCompanyIdFormatErrorMsgAppears(10),
                "Modal with error company id param error didn't appear or not found in 10 sec");
        Assert.assertTrue(SysHelper.isFolderOrFileExist("C:\\Program Files\\Siber Systems\\GoodSync"),
                "GoodSync directory is not found at sysDisk\\Program Files\\Siber System");
        Assert.assertFalse(SysHelper.isServiceStatusRunning("gs-runner.exe"),
                "gs-runner is running but should be not cuz after companyid error it should be closed");
    }

    @Test
    @Description("The test checks that if cc-runner installing as Service with no system user id installation must be reverted\n" +
            "Steps:\n" +
            "1. Run cmd installer as a service with no system user id value\n" +
            "2. Wait until modal wnd with error explanation appears\n" +
            "3. Check that folder Program Files/Siber Systems was not created\n" +
            "4. Check that gs-runner.exe is not in process list\n" +
            "5. Check that no keys were added to registry HKLM/SOFTWARE/Policies\n")
    public void installRunnerAsServiceWithNoSystemUserParam(){
        needsJobsCleanAfter = false;
        new GoodSyncHelper().buildRunnerCMD().setCCCoid("131").setCCRunner("service").setSysUserId("")
                .setSysPasswd("123456").setSilent().executeCMD();
        CCRunnerInstaller installer = new CCRunnerInstaller(new Screen());
        Assert.assertTrue(installer.waitForSysUserIdEmptyErrorAppears(10),
                "Modal wnd with error of wrong Sys User id expected but not found in 10 sec");
        Assert.assertFalse(SysHelper.isAppStatusRunning("gs-runner.exe"),
                "gs-runner.exe should not be in the process list due to critical installation error");
        Assert.assertFalse(SysHelper.isFolderOrFileExist("C:\\Program Files\\Siber Systems"),
                "Due to critical error installation no files should be under Program Files/Siber Systems");
        Map<String, String> registry = WinRegistryHelper.readRegValues(WinRegConst.HKLM,
                WinRegConst.SOFTWARE_POLICIES_SIBER_GS);
        Assert.assertTrue(registry.isEmpty(),
                "Registry directory HKLM/SOFTWARE/POLICIES/Siber System should not be created bit found");
    }

    @Test
    @Description("The test checks that cc-runner installed as service with wrong system user id\n" +
            "Steps:\n" +
            "1. Run installer in cmd with incorrect system user id\n" +
            "2. Check modal wnd with error msg appears\n" +
            "3. Check that folder was created under Program Files/Siber Systems/GoodSync\n" +
            "4. Check that gs-runner.exe is not in the process list\n" +
            "5. Check that appropriate msg was printed in log file\n")
    public void installRunnerAsServiceWithIncorrectSystemUserId(){
        needsJobsCleanAfter = false;
        new GoodSyncHelper().buildRunnerCMD().setCCRunner("service").setCCCoid("131").setSysUserId("blabla")
                .setSysPasswd("123456").setSilent().executeCMD();
        CCRunnerInstaller installer = new CCRunnerInstaller(new Screen());
        Assert.assertTrue(installer.waitForIncorrectSysUserErrorAppears(10),
                "Modal wnd with error of wrong system user id expected but not found in 10 sec");
        Assert.assertTrue(SysHelper.isFolderOrFileExist("C:\\Program Files\\Siber Systems\\GoodSync"),
                "Folder Program Files/Siber Systems/GoodSync expected to be existent bot not found");
        Assert.assertFalse(SysHelper.isServiceStatusRunning("GsRunner " + userName),
                "gs-runner.exe service should not be in the process list but found in RUNNING state");
        softAssert.assertTrue(GsServerLogReader
                        .findStringInLog("Gs-Runner Terminal ERROR: Creating Service:" +
                                        " Cannot get Profile Dir for User:",
                                "C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                                "InstallUser", true),
                "The string: 'Gs-Runner Terminal ERROR: Creating Service: Cannot get Profile Dir for User:' " +
                        "is not found in log");
        softAssert.assertAll();
    }

    @Test
    @Description("The test checks that cc-runner installed as service with no system pwd\n" +
            "Steps:\n" +
            "1. Run installer in cmd with no system pwd id\n" +
            "2. Check modal wnd with error msg appears\n" +
            "3. Check that folder was created under Program Files/Siber Systems/GoodSync\n" +
            "4. Check that gs-runner.exe is not in the process list\n" +
            "5. Check that appropriate msg was printed in log file\n")
    public void installRunnerAsServiceWithNoSystemPassword(){
        needsJobsCleanAfter = false;
        new GoodSyncHelper().buildRunnerCMD().setCCRunner("service").setCCCoid("131")
                .setSysUserId("test").setSysPasswd("").setSilent().executeCMD();
        CCRunnerInstaller installer = new CCRunnerInstaller(new Screen());
        Assert.assertTrue(installer.waitForIncorrectSysPasswdErrorAppears(10),
                "Modal wnd with error of wrong system password expected but not found in 10 sec");
        Assert.assertTrue(SysHelper.isFolderOrFileExist("C:\\Program Files\\Siber Systems\\GoodSync"),
                "Folder Program Files/Siber Systems/GoodSync expected to be existent bot not found");
        Assert.assertFalse(SysHelper.isServiceStatusRunning("gs-runner.exe"),
                "gs-runner.exe service should not be in the process list but found in RUNNING state");
        Assert.assertTrue(GsServerLogReader
                        .findStringInLog("Gs-Runner Terminal ERROR: " +
                                        "Cannot start GsRunner Service: " +
                                        "The service did not start due to a logon failure.  (error 1069)",
                                "C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                                "InstallUser", true),
                "The string: 'Gs-Runner Terminal ERROR: Cannot start GsRunner Service: " +
                        "The specified service does not exist as an installed service.  (error 1060)' is not found in log");
    }

    @Test
    @Description("The test checks that cc-runner installed as service with wrong system pwd\n" +
            "Steps:\n" +
            "1. Run installer in cmd with no system pwd id\n" +
            "2. Check modal wnd with error msg appears\n" +
            "3. Check that folder was created under Program Files/Siber Systems/GoodSync\n" +
            "4. Check that gs-runner.exe is not in the process list\n" +
            "5. Check that appropriate msg was printed in log file\n")
    public void runnerInstalledAsServiceWithIncorrectSystemPassword(){
        needsJobsCleanAfter = false;
        new GoodSyncHelper().buildRunnerCMD().setCCRunner("service").setCCCoid("131")
                .setSysUserId("test").setSysPasswd("1234567890").setSilent().executeCMD();
        CCRunnerInstaller installer = new CCRunnerInstaller(new Screen());
        Assert.assertTrue(installer.waitForIncorrectSysPasswdErrorAppears(10),
                "Modal wnd with error of wrong system password expected but not found in 10 sec");
        Assert.assertTrue(SysHelper.isFolderOrFileExist("C:\\Program Files\\Siber Systems\\GoodSync"),
                "Folder Program Files/Siber Systems/GoodSync expected to be existent bot not found");
        Assert.assertFalse(SysHelper.isServiceStatusRunning("gs-runner.exe"),
                "gs-runner.exe service should not be in the process list but found in RUNNING state");
        Assert.assertTrue(GsServerLogReader
                        .findStringInLog("Gs-Runner Terminal ERROR: " +
                                        "Cannot start GsRunner Service: " +
                                        "The specified service does not exist as an installed service.  (error 1060)",
                                "C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                                "InstallUser", true),
                "The string: 'Gs-Runner Terminal ERROR: Cannot start GsRunner Service: " +
                        "The specified service does not exist as an installed service.  (error 1060)' is not found in log");
    }

    @Test
    @Description("The test checks that cc-runner can be installed as service with GUI\n" +
            "Steps:\n" +
            "1. Run cmd with cc-runner=Service\n" +
            "2. Check that GUI installer appeared\n" +
            "3. Complete installation via GUI\n" +
            "4. Check that service is up\n" +
            "5. Check that registry key RunnerServiceUserId is correct\n" +
            "")
    public void installRunnerAsServiceViaGUI(){
        needsJobsCleanAfter = true;
        new GoodSyncHelper().buildRunnerCMD().setCCRunner("service").setCCCoid("131").setSysUserId("test")
                .setSysPasswd("123456").executeCMD();
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Main installer wnd didn't show up in 10 sec");
        installer.clickInstallBtn();
        softAssert.assertTrue(installer.waitForInstallProgressWndAppear(10),
                "Install progress wnd didn't appear in 10 sec");
        softAssert.assertTrue(installer.waitForInstallProgressDisappear(15),
                "Install progress didn't disappear in 15 sec");
        Map<String, String> registry = WinRegistryHelper.readRegValues(WinRegConst.HKLM,
                WinRegConst.SOFTWARE_POLICIES_SIBER_GS);
        softAssert.assertEquals(registry.get("RunnerServiceUserId"), computerName + "\\" + userName,
                "RunnerServiceUserId key expected to be test but found: " + registry.get("RunnerServiceUserId"));
        softAssert.assertTrue(SysHelper.isServiceStatusRunning("GsRunner " + userName),
                "gs-runner.exe is not in Running state or was not found in proc list");
        softAssert.assertAll();
    }

    @Test
    @Issue("#165505")
    @Description("The test checks that deselection show mini progress wnd in installer should not show it\n" +
            "Steps:\n" +
            "1. Install cc-runner with show mini progress wnd option enabled\n" +
            "2. wait until installation finishes\n" +
            "3. Run installer again\n" +
            "4. Deselect show mini progress wnd checkbox\n" +
            "5. Complete installation\n" +
            "6. Check registry key value MiniProgress\n" +
            "7. Check that miniProgressWnd didn't appear\n")
    public void installOverWithNoMiniProgressWndShouldOverwriteMiniProgressSetting(){
        needsJobsCleanAfter = true;
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(15), "Installer main wnd is not appeared in 15 sec");
        installer.setCompanyIdFieldToValue("131");
        installer.clickInstallBtn();
        Assert.assertTrue(installer.waitForMiniProgressAppear(15), "Progress wnd did not appear in 15 sec");
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        Assert.assertTrue(installer.waitForMainWndAppear(15), "Installer main wnd is not appeared in 15 sec");
        installer.clickShowMiniProgressCheckBox();
        wait(1);
        installer.clickInstallBtn();
        Assert.assertTrue(installer.waitForInstallProgressDisappear(15),
                "Install progress did not disappear in 15 sec");
        Map<String, String> registry = WinRegistryHelper.readRegValues(WinRegConst.HKLM,
                WinRegConst.SOFTWARE_POLICIES_SIBER_GS);
        Assert.assertFalse(installer.isMiniProgressWndOpened(),
                "Mini progress wnd should not be opened as we disabled it on installation");
        Assert.assertEquals(registry.get("MiniProgress"), "0x0",
                "MiniProgress registry value expected to be 0x0 but found: " + registry.get("MiniProgress"));
    }

    @Test
    @Issue("#165413")
    @Description("The test checks that cmd line runner installation must accept any keys order")
    public void installCMDLineOrderMustBeAny(){
        needsJobsCleanAfter = true;
        new GoodSyncHelper().buildRunnerCMD().setMiniProgress(true).setCCallowBadCert(true)
                .setCCRunner("user").setUserClose(true).setCCCoid("131").setSilent().executeCMD();
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMiniProgressAppear(10),
                "Mini progress wnd didn't appear in 10 sec");
        String[] str = GsServerLogReader.getLogErrors("C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                "InstallUser", false);
        Assert.assertEquals(str.length, 5, "InstallUser logs contains errors: " + GoodSyncFiles.join(str));
        str = GsServerLogReader.getLogErrors("C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                "Runner", false);
        Assert.assertEquals(str, new String[0], "Runner logs contains errors: " + GoodSyncFiles.join(str));
    }

    @Test
    @Description("The test checks that if we set userClose = false it will propagated to registry correctly\n" +
            "Steps:\n" +
            "1. Run installer from CMD line with /user-close=no\n" +
            "3. Wait until installation completes\n" +
            "4. Check UserClose value in registry\n")
    public void installCMDLineWithUserCloseFalse(){
        needsJobsCleanAfter = true;
        new GoodSyncHelper().buildRunnerCMD().setCCCoid("131").setCCRunner("user")
                .setUserClose(false).setSilent().executeCMD();
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMiniProgressAppear(10),
                "Mini progress wnd didn't appear in 10 sec");
        Map<String, String> registry = WinRegistryHelper.readRegValues(WinRegConst.HKLM,
                WinRegConst.SOFTWARE_POLICIES_SIBER_GS);
        Assert.assertEquals(registry.get("UserClose"), "0x0",
                "UserClose registry value expected to be 0x0 but found: " + registry.get("UserClose"));
        String[] str = GsServerLogReader.getLogErrors("C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                "InstallUser", false);
        Assert.assertEquals(str.length, 5, "InstallUser logs contains errors: " + GoodSyncFiles.join(str));
        str = GsServerLogReader.getLogErrors("C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                "Runner", false);
        Assert.assertEquals(str, new String[0], "Runner logs contains errors: " + GoodSyncFiles.join(str));
    }

    @Test
    @Description
    public void installRunnerWithCompanyPinViaGUI(){
        needsJobsCleanAfter = true;
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Installer main wnd is not appeared in 10 sec");
        installer.setCompanyIdFieldToValue("131");
        installer.setCompanyPinToValue("12345");
        installer.clickInstallBtn();
        Assert.assertTrue(installer.waitForMiniProgressAppear(10), "Progress wnd did not appear in 10 sec");
        Map<String, String> registry = WinRegistryHelper.readRegValues(WinRegConst.HKLM,
                WinRegConst.SOFTWARE_POLICIES_SIBER_GS);
        Assert.assertEquals(registry.get("CompanyPin"), "12345",
                "CompanyPin registry value expected to be 12345 but found: " + registry.get("CompanyPin"));
    }

    @Test
    @Description
    public void installRunnerWithCompanyPinViaCMD(){
        needsJobsCleanAfter = true;
        new GoodSyncHelper().buildRunnerCMD().setCCRunner("user").setCCCoid("131").setCCcopin("123456")
                .setSilent().executeCMD();
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMiniProgressAppear(10),
                "Mini progress wnd didn't appear in 10 sec after install btn was clicked");
        Map<String, String> registry = WinRegistryHelper.readRegValues(WinRegConst.HKLM,
                WinRegConst.SOFTWARE_POLICIES_SIBER_GS);
        Assert.assertEquals(registry.get("CompanyPin"), "123456",
                "CompanyPin registry value expected to be 123456 but found: " + registry.get("CompanyPin"));
    }

    @Test
    @Description
    public void installRunnerAccountPwdViaGUI() {
        needsJobsCleanAfter = true;
        App.open("C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Installer main wnd is not appeared in 10 sec");
        installer.setCompanyIdFieldToValue("131");
        installer.setAccountPasswordFieldToValue("1234567");
        installer.clickInstallBtn();
        Assert.assertTrue(installer.waitForMiniProgressAppear(10),
                "Mini progress wnd didn't appear in 10 sec after install btn was clicked");
        Map<String, String> registry = WinRegistryHelper.readRegValues(WinRegConst.HKLM,
                WinRegConst.SOFTWARE_POLICIES_SIBER_GS);
        Assert.assertTrue(!registry.get("AccountsEncryptPassword").isEmpty(),
                "AccountsEncryptPassword registry value expected to be not empty");
    }


    @Test
    @Description
    public void installRunnerAccountsPasswordViaCMD(){
        needsJobsCleanAfter = true;
        new GoodSyncHelper().buildRunnerCMD().setCCCoid("131").setCCRunner("user").setCCacctPwd("12345678")
                .setSilent().executeCMD();
        CCInstallerPage installer = new CCInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMiniProgressAppear(15), "Installer main wnd is not appeared in 15 sec");
        Map<String, String> registry = WinRegistryHelper.readRegValues(WinRegConst.HKLM,
                WinRegConst.SOFTWARE_POLICIES_SIBER_GS);
        Assert.assertTrue(!registry.get("AccountsEncryptPassword").isEmpty(),
                "AccountsEncryptPassword registry value expected to be not empty");
    }

}
