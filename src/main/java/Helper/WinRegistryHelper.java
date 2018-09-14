package Helper;

import com.github.sarxos.winreg.HKey;
import com.github.sarxos.winreg.RegistryException;
import com.github.sarxos.winreg.WindowsRegistry;
import data.WinRegConst;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class WinRegistryHelper {
    private static WindowsRegistry reg = WindowsRegistry.getInstance();

    public static Map<String, String> readRegValues(String root, String path) {
        Process process;
        StringWriter sw = new StringWriter();
        String response = null;
        try {
            process = Runtime.getRuntime()
                    .exec("reg QUERY " + "\"" + root + path + "\"");
            InputStream reader = process.getInputStream();
            process.waitFor();
            int b;
            while ((b = reader.read()) != -1) {
                sw.write(b);
            }
            response = sw.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return regStrToMap(response);
    }

    private static Map<String, String> regStrToMap(String response){
        Map<String, String> map = new HashMap<>();
        String[] strArr = response.split("\r\n");
        for(int i = 2; i < strArr.length; i++){
            String[] tmp = strArr[i].trim().split("  ");
            // TODO could be unstable
            if (tmp.length > 3){
                map.put(tmp[0], tmp[4]);
            } else {
                map.put(tmp[0], "");
            }
        }
        return map;
    }

    public static boolean checkCleanupAfterUninstall(){
        boolean globalResult = false;
        boolean currUsrGS = false;
        boolean localMachGS = false;
        boolean autoGS = false;
        boolean autoRunner = false;
        boolean uninst = false;
        String currUsrGoodSync = "\\Software\\Siber Systems\\GoodSync";
        String localMachineGoodSync = "\\Software\\Siber Systems\\GoodSync";
        String autoRunGoodSync = "\\Software\\Microsoft\\Windows\\CurrentVersion\\Run\\GoodSync";
        String autoRunGSrunner = "\\Software\\Microsoft\\Windows\\CurrentVersion\\Run\\gs-runner";
        String uninstallReg = "\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\{B26B00DA-2E5D-4CF2-83C5-911198C0F009}";
        Map <String, String> map = null;
        try {
            map = reg.readStringValues(HKey.HKCU, currUsrGoodSync);
                currUsrGS = false;
                map.clear();
        } catch (RegistryException e) {
            currUsrGS = true;
        }
        try {
            map = reg.readStringValues(HKey.HKLM, localMachineGoodSync);
                localMachGS = false;
                map.clear();
        } catch (RegistryException e) {
            localMachGS = true;
        }
        try {
            map = reg.readStringValues(HKey.HKCU, autoRunGoodSync);
                autoGS = false;
                map.clear();
        } catch (RegistryException e) {
            autoGS = true;
        }
        try {
            map = reg.readStringValues(HKey.HKCU, autoRunGSrunner);
                autoRunner = false;
                map.clear();
        } catch (RegistryException e) {
            autoRunner = true;
        }
        try {
            map = reg.readStringValues(HKey.HKLM, uninstallReg);
                uninst = false;
                map.clear();
        } catch (RegistryException e) {
            uninst = true;
        }
        if (autoGS && uninst && autoRunner && currUsrGS && localMachGS){
            globalResult = true;
        }
        return globalResult;
    }

    public static void delAllAboutGoodSyncFromRegistry(){
        String username = System.getProperty("user.name");
        try {
            new ProcessBuilder("reg.exe", "DELETE",
                    "HKCU\\Software\\Siber Systems","/f").start();
            new ProcessBuilder("reg.exe", "DELETE",
                    "HKLM\\SOFTWARE\\Policies\\Siber Systems","/f").start();
            new ProcessBuilder("reg.exe", "DELETE",
                    "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\" +
                            "{B26B00DA-2E5D-4CF2-83C5-911198C0F009}","/f").start();
            new ProcessBuilder("reg.exe", "DELETE",
                    "HKEY_LOCAL_MACHINE\\SOFTWARE\\Siber Systems","/f").start();
            new ProcessBuilder("reg.exe", "DELETE",
                    "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Policies\\Siber Systems","/f").start();
            new ProcessBuilder("reg.exe", "DELETE",
                    "HKEY_LOCAL_MACHINE\\SYSTEM\\ControlSet001\\Services\\GsRunner " + username,"/f").start();
            new ProcessBuilder("reg.exe", "DELETE",
                    "HKEY_LOCAL_MACHINE\\SYSTEM\\ControlSet001\\Services\\GsServer","/f").start();
            new ProcessBuilder("reg.exe", "DELETE",
                    "HKEY_LOCAL_MACHINE\\SYSTEM\\Setup\\FirstBoot\\Services\\GsServer","/f").start();
            new ProcessBuilder("reg.exe", "DELETE",
                    "HKEY_USERS\\.DEFAULT\\Software\\Siber Systems","/f").start();
            new ProcessBuilder("reg.exe", "DELETE",
                    "HKEY_USERS\\S-1-5-18\\Software\\Siber Systems","/f").start();
            new ProcessBuilder("reg.exe", "DELETE",
                    "HKEY_CURRENT_USER\\SOFTWARE\\Siber Systems","/f").start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean addRegistryKey(String root, String pathToNode, String newNodeName){
        boolean result = false;
        Process process;
        StringWriter sw = new StringWriter();
        String response;
        try {
            process = Runtime.getRuntime()
                    .exec("reg ADD \"" + root + pathToNode + "\\" + newNodeName + "\" ");
            InputStream reader = process.getInputStream();
            process.waitFor();
            int b;
            while((b = reader.read()) != -1){
                sw.write(b);
            }
            response = sw.toString();
            if(response.contains("success"))
                result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;

    }

    public static boolean addRegistryParamAndValue(String root, String pathToNode, String paramName,
                                                String paramValue, String paramType){
        boolean result = false;
        Process process;
        StringWriter sw = new StringWriter();
        String response;
        String cmd = "reg ADD \"" + root + pathToNode + "\" /v " + paramName + " /t " +
                paramType + " /d " + paramValue + " /f";
        try {
            process = Runtime.getRuntime()
                    .exec(cmd);
            InputStream reader = process.getInputStream();
            process.waitFor();
            int b;
            while((b = reader.read()) != -1){
                sw.write(b);
            }
            response = sw.toString();
            if(response.contains("success"))
                result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean deleteRegistryKey(String root, String pathToNode, boolean reqursiveDel){
        boolean result = false;
        Process process;
        StringWriter sw = new StringWriter();
        String response;
        try {
            if(reqursiveDel){
                process = Runtime.getRuntime()
                        .exec("reg DELETE \"" + root + pathToNode + "\" /va /f");
            } else {
                process = Runtime.getRuntime()
                        .exec("reg DELETE \"" + root + pathToNode + "\" /f");
            }
            InputStream reader = process.getInputStream();
            process.waitFor();
            int b;
            while((b = reader.read()) != -1){
                sw.write(b);
            }
            response = sw.toString();
            if(response.contains("success"))
                result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean deleteRegistryParam(String root, String pathToNode, String paramName){
        Process process;
        boolean result = false;
        StringWriter sw = new StringWriter();
        String response;
        try {
            process = Runtime.getRuntime()
                    .exec("reg DELETE \"" + root + pathToNode + "\" /v " + paramName + " /f");
            InputStream reader = process.getInputStream();
            process.waitFor();
            int b;
            while((b = reader.read()) != -1){
                sw.write(b);
            }
            response = sw.toString();
            if(response.contains("success"))
                result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
