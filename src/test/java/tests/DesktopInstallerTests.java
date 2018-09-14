package tests;

import Helper.SysHelper;
import Helper.GoodSyncHelper;
import Helper.WinRegistryHelper;
import LogUtils.GsServerLogReader;
import data.*;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.sikuli.script.App;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.NewInstaller.DesktopInstallerPage.DesktopInstallerPage;
import pages.NewInstaller.GoodSyncConnectSetupWzd.GoodSyncConnectSetupWzd;
import pages.NewInstaller.LanguageSelectionModule.LanguageSelectionModule;
import pages.NewInstaller.ModalWnds.CloseInstallerWnd.CloseInstallerWnd;
import pages.NewInstaller.ModalWnds.SecondInstanceWarning.SecondInstanceWarning;
import pages.NewInstaller.ModalWnds.RunNowDlgWnd.RunNowDlgWnd;
import tests.Listeners.MyListener;

@Listeners({MyListener.class})
public class DesktopInstallerTests extends BaseTest {

    @BeforeTest
    public void beforeTest(){
        GoodSyncHelper.hardDeleteGoodSync();
        FileDownloader.downloadFile(Links.httpUrl + Links.goodsync_v10_setup_pvt,
                "C:\\goodsync\\" + Links.goodsync_v10_setup_pvt);

    }


    @AfterMethod
    public void afterMethod(){
        GoodSyncHelper.hardDeleteGoodSync();
    }

    @Test
    @Issue("#165191")
    @Description("The test checks that GS installed on new PC and GS Connect setup canceled " +
            "so server is in local mode and GS Connect wzd will be lunched on first start again" +
            "\nSteps:\n" +
            "1. Clean PC\n" +
            "2. Run installer\n" +
            "3. Click Cancel in GS Connect Setup\n" +
            "4. Click yes to run GS now in dlg wnd\n" +
            "5. Click Cancel in GS Connect wzd on GS start\n" +
            "6. Check that gsServer is in localMode\n" +
            "7. Check that Auto install and daily check is set to YES\n" +
            "8. Check that server is started from Program Files dir\n")
    public void installDesktopGSOnNewPC(){
        App.open("C:\\goodsync\\" + Links.goodsync_v10_setup_pvt);
        DesktopInstallerPage installer = new DesktopInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Main GS installer wnd has not appeared in 10 sec");
        installer.clickInstallBtn();
        GoodSyncConnectSetupWzd gsConnect = new GoodSyncConnectSetupWzd(new Screen());
        Assert.assertTrue(gsConnect.waitForGSConnectWndAppear(10), "GS Connect Wzd has not appeared in 10 sec");
        Assert.assertTrue(gsConnect.isNoSetupGSConnectSelected(), "No Setup GS Connect is not selected by default");
        gsConnect.clickCancelBtn();
        RunNowDlgWnd confirmDlg = new RunNowDlgWnd(new Screen());
        Assert.assertTrue(confirmDlg.waitForModalToBeOpened(10), "Confirm dlg wnd is not appeared in 10 sec");
        confirmDlg.clickYesBtn();
        Assert.assertTrue(gsConnect.waitForGSConnectWndAppear(10), "GS Connect wzd did not re-appeared on GS start");
        gsConnect.clickCancelBtn();
        Assert.assertTrue(SysHelper.checkShortcutsExistOnDesktop(), "Short cuts are not found on Desktop");
        Assert.assertTrue(SysHelper.isGoodSyncDesktopAppInProcessList(), "GoodSync is not found in Process list");
        Assert.assertTrue(SysHelper.isServiceStatusRunning("GsServer"), "Gs Server is not running or stopped");
        String[] filesInGSdir = SysHelper.listDir("C:\\Program Files\\Siber Systems\\GoodSync\\");
        String comparing = GoodSyncFiles.compareArrays(filesInGSdir);
        Assert.assertEquals(comparing.length(), 0, "Files differ: " + comparing);
        Assert.assertTrue(GsServerLogReader
                .findStringInLog("CheckNewVersion = Yes",
                        "C:\\ProgramData\\GoodSync\\server", "gserver", true),
                "Auto check new version is not set to YES");
        Assert.assertTrue(GsServerLogReader
                .findStringInLog("InstallNewVersion = Yes",
                        "C:\\ProgramData\\GoodSync\\server", "gserver", true),
                "Auto install new version is not set to YES");
        Assert.assertTrue(GsServerLogReader
                        .findStringInLog("GstpFileLocalOnly = Yes",
                                "C:\\ProgramData\\GoodSync\\server", "gserver", true),
                "Server must be in local mode cuz user clicked cancel in GS Connect wzd");
        Assert.assertTrue(GsServerLogReader
                        .findStringInLog("Arg0=C:\\Program Files\\Siber Systems\\GoodSync\\gs-server.exe",
                                "C:\\ProgramData\\GoodSync\\server", "gserver", true),
                "Server is not started from Program Files/Siber System folder");
        String[] str = GsServerLogReader.getLogErrors("C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                "GoodSync", false);
        Assert.assertEquals(str, new String[0], "GoodSync logs contains errors: " + GoodSyncFiles.join(str));
    }

    @Test
    @Description("The test checks that if user have installed GS with German language, running installation will " +
            "automatically set language to German.\n" +
            "Steps:\n" +
            "1. Install GS with German localization\n" +
            "2. Run installer again\n" +
            "3. Check that installer set language to German by auto\n" +
            "4. Check InstallUser log file for string sLanguage=de-german.rfs\n")
    public void installOverMustKeepPreviousSelectedLanguage(){
        App goodsync = App.open("C:\\goodsync\\" + Links.goodsync_v10_setup_pvt);
        //App.focus(goodsync.getName());
        DesktopInstallerPage installer = new DesktopInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(15), "Main GS installer wnd has not appeared in 15 sec");
        LanguageSelectionModule language = new LanguageSelectionModule(new Screen());
        language.clickOnLangSelectionBtn();
        language.selectLanguage(Languages.GERMAN.getId());
        installer.clickInstallBtn();
        GoodSyncConnectSetupWzd gsConnect = new GoodSyncConnectSetupWzd(new Screen());
        Assert.assertTrue(gsConnect.waitForGSConnectWndAppear(10), "GS Connect Wzd has not appeared in 10 sec");
        gsConnect.clickCancelBtn();
        RunNowDlgWnd confirmDlg = new RunNowDlgWnd(new Screen());
        Assert.assertTrue(confirmDlg.waitForModalToBeOpened(10), "Confirm dlg wnd is not appeared in 10 sec");
        confirmDlg.clickNoBtn();
        wait(1);
        goodsync.open();
        //App.focus(goodsync.getName());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Main GS installer wnd has not appeared in 10 sec");
        //App.open("C:\\goodsync\\" + Links.goodsync_v10_setup_pvt);
        Assert.assertTrue(language.isLanguageSelected(Languages.GERMAN.getId()),
                "Language selected on previous installation is not set in installation window");
        Assert.assertTrue(GsServerLogReader.findStringInLog("GsGetGuiLanguage: registry: sLanguage=de-german.rfs",
                "C:\\Users\\test\\AppData\\Roaming\\GoodSync", "InstallUser", true),
                "Language is expected to be German but not");
        String[] str = GsServerLogReader.getLogErrors("C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                "GoodSync", false);
        Assert.assertEquals(str, new String[0], "GoodSync logs contains errors: " + GoodSyncFiles.join(str));
    }

    @Test
    @Description("The test checks that if installer already run second instance of installer can not be run. Warning message" +
            " will be closed by auto in 30 seconds.\nSteps:\n" +
            "1. Run installer, wait until main GS installer wnd appears\n" +
            "2. Run Installer again\n" +
            "3. Observe Warning message\n" +
            "4. Wait 30 seconds to check that warning message close itself after 30 seconds\n")
    public void secondInstanceOfInstallerCanNotBeRunAutoCloseModalWarning(){
        App goodsync = App.open("C:\\goodsync\\" + Links.goodsync_v10_setup_pvt);
        DesktopInstallerPage installer = new DesktopInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Main GS installer wnd has not appeared in 10 sec");
        //App.focus(goodsync.getName());
        goodsync.open();
        SecondInstanceWarning warning = new SecondInstanceWarning(new Screen());
        Assert.assertTrue(warning.isModalOpened(), "Modal window with warning is not opened");
        Assert.assertTrue(warning.waitForModalWndDisappear(30), "Modal wnd has not disappeared in 30 seconds");
        Assert.assertTrue(installer.isWindowOpened(), "Installation window is not opened");
        String[] str = GsServerLogReader.getLogErrors("C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                "GoodSync", false);
        Assert.assertEquals(str, new String[0], "GoodSync logs contains errors: " + GoodSyncFiles.join(str));
    }

    @Test
    @Description("The test checks that if installer already run second instance of installer can not be run. Warning message" +
            " will be closed by auto in 30 seconds.\nSteps:\n" +
            "1. Run installer, wait until main GS installer wnd appears\n" +
            "2. Run Installer again\n" +
            "3. Observe Warning message\n" +
            "4. Click OK button in warning msg\n" +
            "5. Check that installation wnd is still opened\n")
    public void secondInstanceOfInstallerCanNotBeRunCloseWarningManually(){
        App goodsync = App.open("C:\\goodsync\\" + Links.goodsync_v10_setup_pvt);
        DesktopInstallerPage installer = new DesktopInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Main GS installer wnd has not appeared in 10 sec");
        //App.focus(goodsync.getName());
        goodsync.open();
        SecondInstanceWarning warning = new SecondInstanceWarning(new Screen());
        Assert.assertTrue(warning.isModalOpened(), "Modal window with warning is not opened");
        warning.clickOkBtn();
        // modal wnd goes away with delay, sikuli still catch it so wait 1 sec
        wait(1);
        Assert.assertFalse(warning.isModalOpened(),"Warning wnd is opened but should be not");
        Assert.assertTrue(installer.isWindowOpened(), "Installation window is not opened");
        String[] str = GsServerLogReader.getLogErrors("C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                "GoodSync", false);
        Assert.assertEquals(str, new String[0], "GoodSync logs contains errors: " + GoodSyncFiles.join(str));
    }

    @Test
    @Description("The test checks that installation process can be closed via close window btn.\nSteps:\n" +
            "1. Run installer\n" +
            "2. Click close window btn\n" +
            "3. Check that modal wnd has appeared\n" +
            "4. Click YES btn in modal wnd\n" +
            "5. Observe that installer is closed\n" +
            "6. Check that GS Synchronizer is not in sys.processes list\n")
    public void installationCanBeClosed(){
        App goodsync = App.open("C:\\goodsync\\" + Links.goodsync_v10_setup_pvt);
        DesktopInstallerPage installer = new DesktopInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Main GS installer wnd has not appeared in 10 sec");
        //App.focus(goodsync.getName());
        installer.clickCloseWndBtn();
        CloseInstallerWnd closeDlg = new CloseInstallerWnd(new Screen());
        Assert.assertTrue(closeDlg.isModalWndOpened(), "Close modal wnd is not opened");
        closeDlg.clickYesBtn();
        Assert.assertFalse(installer.isWindowOpened(), "Main installer wnd should be close but found opened");
        Assert.assertFalse(SysHelper.isGoodSyncDesktopAppInProcessList(),
                "GS should not be in process list after install canceled");
        String[] str = GsServerLogReader.getLogErrors("C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                "GoodSync", false);
        Assert.assertEquals(str, new String[0], "GoodSync logs contains errors: " + GoodSyncFiles.join(str));
    }

    @Test
    @Description("The test checks that installation process can be closed via close window btn and then reverted.\nSteps:\n" +
            "1. Run installer\n" +
            "2. Click close window btn\n" +
            "3. Check that modal wnd has appeared\n" +
            "4. Click NO btn in modal wnd\n" +
            "5. Observe that installer is opened\n" +
            "6. Check that GS Synchronizer is in sys.processes list\n")
    public void installationCloseProcedureCanBeReverted() {
        App goodsync = App.open("C:\\goodsync\\" + Links.goodsync_v10_setup_pvt);
        DesktopInstallerPage installer = new DesktopInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Main GS installer wnd has not appeared in 10 sec");
        //App.focus(goodsync.getName());
        installer.clickCloseWndBtn();
        CloseInstallerWnd closeDlg = new CloseInstallerWnd(new Screen());
        Assert.assertTrue(closeDlg.isModalWndOpened(), "Close modal wnd is not opened");
        closeDlg.clickNoBtn();
        //TODO find solution!
        // Disabled assert for mdl wnd. Cuz no matter do i place wait or not it sees it on screen
        //Assert.assertFalse(closeDlg.isModalWndOpened(), "Close modal wnd should be closed but found opened");
        Assert.assertTrue(installer.isWindowOpened(), "Installer window is not found but should be");
        Assert.assertTrue(SysHelper.isGoodSyncDesktopInstallerInProcessList(),
                "GS should be in process list after install canceled reverted");
        String[] str = GsServerLogReader.getLogErrors("C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                "GoodSync", false);
        Assert.assertEquals(str, new String[0], "GoodSync logs contains errors: " + GoodSyncFiles.join(str));
    }

    @Test
    @Issue("164207")
    @Description("The test checks that if user selected not to add shortcuts to desktop hence installing over must not adding them\n" +
            "Steps:\n" +
            "1. Install GS and disable 'add shortcuts to desktop'\n" +
            "2. Run installer and complete installation\n" +
            "3. Check that shortcuts has not been added\n" +
            "4. Check HKLM/SOFTWARE/Siber System/GoodSync/instDesktopShortCuts value\n")
    public void installOverWithNoDesktopShortCutsEnabled(){
        App.open("C:\\goodsync\\" + Links.goodsync_v10_setup_pvt);
        //App.focus(goodsync.getName());
        DesktopInstallerPage installer = new DesktopInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(15), "Main GS installer wnd has not appeared in 15 sec");
        installer.clickInstallBtn();
        GoodSyncConnectSetupWzd gsConnect = new GoodSyncConnectSetupWzd(new Screen());
        Assert.assertTrue(gsConnect.waitForGSConnectWndAppear(10), "GS Connect Wzd has not appeared in 10 sec");
        Assert.assertTrue(gsConnect.isNoSetupGSConnectSelected(), "No Setup GS Connect is not selected by default");
        gsConnect.clickCancelBtn();
        RunNowDlgWnd confirmDlg = new RunNowDlgWnd(new Screen());
        Assert.assertTrue(confirmDlg.waitForModalToBeOpened(10), "Confirm dlg wnd is not appeared in 10 sec");
        confirmDlg.clickNoBtn();
        SysHelper.killProcess("GoodSync-v10.exe");
        //deleting shortcuts from desktop before install it over
        SysHelper.deleteDirOrFile("C:\\Users\\Public\\Desktop\\GoodSync Explorer.lnk");
        SysHelper.deleteDirOrFile("C:\\Users\\Public\\Desktop\\GoodSync.lnk");
        Assert.assertFalse(SysHelper.isFolderOrFileExist("C:\\Users\\Public\\Desktop\\GoodSync Explorer.lnk"));
        Assert.assertFalse(SysHelper.isFolderOrFileExist("C:\\Users\\Public\\Desktop\\GoodSync.lnk"));
        Assert.assertTrue(WinRegistryHelper.addRegistryParamAndValue(WinRegConst.HKLM, WinRegConst.SOFTWARE_SIBER_GS,
                "instDesktopShortCut", "0", WinRegConst.REG_DWORD),
                "instDesktopShortCut was not added to registry");
        //install over
        App.open("C:\\goodsync\\" + Links.goodsync_v10_setup_pvt);
        //App.focus(goodsync.getName());
        installer = new DesktopInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Main GS installer wnd has not appeared in 10 sec");
        installer.clickInstallBtn();
        gsConnect = new GoodSyncConnectSetupWzd(new Screen());
        Assert.assertTrue(gsConnect.waitForGSConnectWndAppear(10), "GS Connect Wzd has not appeared in 10 sec");
        Assert.assertTrue(gsConnect.isNoSetupGSConnectSelected(), "No Setup GS Connect is not selected by default");
        gsConnect.clickCancelBtn();
        confirmDlg = new RunNowDlgWnd(new Screen());
        Assert.assertTrue(confirmDlg.waitForModalToBeOpened(10), "Confirm dlg wnd is not appeared in 10 sec");
        confirmDlg.clickNoBtn();
        Assert.assertFalse(SysHelper.isFolderOrFileExist("C:\\Users\\Public\\Desktop\\GoodSync Explorer.lnk"),
                "GS Explorer shortcut exists on desktop but should not");
        Assert.assertFalse(SysHelper.isFolderOrFileExist("C:\\Users\\Public\\Desktop\\GoodSync.lnk"),
                "GoodSync shortcut exists on desktop but should not");
        String[] str = GsServerLogReader.getLogErrors("C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                "GoodSync", false);
        Assert.assertEquals(str, new String[0], "GoodSync logs contains errors: " + GoodSyncFiles.join(str));
    }

    @Test
    @Description("The test checks that GS is not installed but directory Program Files/Siber System exists\n" +
            "Steps:\n" +
            "1. Create folder under Program Files/Siber Systems\n" +
            "2. Run installer and click install btn\n" +
            "3. ")
    public void installNewIfInstallationDirectoryAlreadyExists(){
        SysHelper.createFolder(System.getenv("SystemDrive") + "\\Program Files\\Siber Systems");
        App.open("C:\\goodsync\\" + Links.goodsync_v10_setup_pvt);
        DesktopInstallerPage installer = new DesktopInstallerPage(new Screen());
        Assert.assertTrue(installer.waitForMainWndAppear(10), "Main installer wnd didn't appear in 10 sec");
        installer.clickInstallBtn();
        GoodSyncConnectSetupWzd gsConnect = new GoodSyncConnectSetupWzd(new Screen());
        Assert.assertTrue(gsConnect.waitForGSConnectWndAppear(10), "GS Connect Wzd has not appeared in 10 sec");
        Assert.assertTrue(gsConnect.isNoSetupGSConnectSelected(), "No Setup GS Connect is not selected by default");
        gsConnect.clickCancelBtn();
        RunNowDlgWnd confirmDlg = new RunNowDlgWnd(new Screen());
        Assert.assertTrue(confirmDlg.waitForModalToBeOpened(10), "Confirm dlg wnd is not appeared in 10 sec");
        confirmDlg.clickYesBtn();
        Assert.assertTrue(gsConnect.waitForGSConnectWndAppear(10), "GS Connect wzd did not re-appeared on GS start");
        gsConnect.clickCancelBtn();
        Assert.assertTrue(SysHelper.checkShortcutsExistOnDesktop(), "Short cuts are not found on Desktop");
        Assert.assertTrue(SysHelper.isGoodSyncDesktopAppInProcessList(), "GoodSync is not found in Process list");
        Assert.assertTrue(SysHelper.isServiceStatusRunning("GsServer"), "Gs Server is not running or stopped");
        String[] filesInGSdir = SysHelper.listDir("C:\\Program Files\\Siber Systems\\GoodSync\\");
        String comparing = GoodSyncFiles.compareArrays(filesInGSdir);
        Assert.assertEquals(comparing.length(), 0, "Files differ: " + comparing);
        Assert.assertTrue(GsServerLogReader
                        .findStringInLog("CheckNewVersion = Yes",
                                "C:\\ProgramData\\GoodSync\\server", "gserver", true),
                "Auto check new version is not set to YES");
        Assert.assertTrue(GsServerLogReader
                        .findStringInLog("InstallNewVersion = Yes",
                                "C:\\ProgramData\\GoodSync\\server", "gserver", true),
                "Auto install new version is not set to YES");
        Assert.assertTrue(GsServerLogReader
                        .findStringInLog("GstpFileLocalOnly = Yes",
                                "C:\\ProgramData\\GoodSync\\server", "gserver", true),
                "Server must be in local mode cuz user clicked cancel in GS Connect wzd");
        Assert.assertTrue(GsServerLogReader
                        .findStringInLog("Arg0=C:\\Program Files\\Siber Systems\\GoodSync\\gs-server.exe",
                                "C:\\ProgramData\\GoodSync\\server", "gserver", true),
                "Server is not started from Program Files/Siber System folder");
        String[] str = GsServerLogReader.getLogErrors("C:\\Users\\test\\AppData\\Roaming\\GoodSync",
                "GoodSync", false);
        Assert.assertEquals(str, new String[0], "GoodSync logs contains errors: " + GoodSyncFiles.join(str));
    }

}
