package Helper;

import data.Links;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class GoodSyncHelper {

    public BuildRunnerCMD buildRunnerCMD(){
        return new BuildRunnerCMD();
    }

    public static void exeCMD (String cmd){
        //Process process;
        //String response = "";
        //StringWriter sw = new StringWriter();

        try {
            Runtime.getRuntime().exec(cmd);
            /*InputStream reader = process.getInputStream();
            //process.waitFor();
            int b;
            while((b = reader.read()) != -1){
                sw.write(b);
            }
            response = sw.toString();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return response;

    }

    public static void hardDeleteGoodSync(){
        SysHelper.clnTempFolder();
        SysHelper.killProcess("GoodSync-v10.exe");
        SysHelper.killProcess("GoodSync-v10-Setup-pvt.exe");
        SysHelper.killProcess("GoodSync-v10-CC-Runner-Setup-pvt.exe");
        SysHelper.killProcess("chrome.exe");
        SysHelper.killProcess("gs-runner.exe");
        SysHelper.deleteGsRunnerService();
        SysHelper.deleteGsServerService();
        // Delete from start menu
        SysHelper.deleteDirOrFile("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\GoodSync");
        SysHelper.deleteDirOrFile("C:\\Users\\Public\\Desktop\\GoodSync Explorer.lnk");
        SysHelper.deleteDirOrFile("C:\\Users\\Public\\Desktop\\GoodSync.lnk");
        WinRegistryHelper.delAllAboutGoodSyncFromRegistry();
        SysHelper.deleteDirOrFile("C:\\Program Files\\Siber Systems\\GoodSync");
        SysHelper.deleteDirOrFile("C:\\Program Files\\Siber Systems");
        SysHelper.deleteDirOrFile("C:\\Users\\test\\AppData\\Roaming\\GoodSync");
        SysHelper.deleteDirOrFile("C:\\ProgramData\\GoodSync");
    }


}
