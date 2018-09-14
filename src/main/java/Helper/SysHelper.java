package Helper;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SysHelper {
    private static FileLock lock = null;

    public static String[] listDir(String path){
        String[] files;

        File f =new File(path);
        files = f.list();
        if (files == null){
            return new String[]{};
        }
        return files;
    }

    public static void clnTempFolder(){
        // Get listing of Temp folder -> filter names contains gs-inst -> delete them in cycle
        String sysDisk = System.getenv("SystemDrive");
        List<String> lst = Arrays.asList(listDir(sysDisk + "\\Temp"));
        String[] result = lst.stream().filter(line -> line.contains("gs-inst"))
                .collect(Collectors.toList()).stream().toArray(String[]::new);
        for(int i = 0; i < result.length; i++){
            deleteDirOrFile(sysDisk + "\\Temp\\" + result[i]);
        }
    }

    public static void deleteDirOrFile(String path){
        File file = new File(path);
        delete(file);
    }

    private static void delete(File file){
        if(!file.exists()){
            return;
        }
        if(file.isDirectory()){
            if(file.listFiles().length == 0){
                file.delete();
            } else {
                for(File f:file.listFiles()){
                    delete(f);
                    file.delete();
                }
            }
        } else {
            file.delete();
        }
    }

    public static void lockFileOrFolder(String path){
        File file = new File(path);
        FileChannel channel = null;
        try {
            channel = new RandomAccessFile(file, "rw").getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Use the file channel to create a lock on the file.
        // This method blocks until it can retrieve the lock.
        try {
            lock = channel.lock();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unlockFileOrFolder(){
        try {
            lock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkShortcutsExistOnDesktop(){
        String[] res = listDir("C:\\Users\\Public\\Desktop");
        boolean result = false;
        boolean goodsyncLink = false;
        boolean gsExplorerLink = false;
        for(int i = 0; i < res.length; i++){
            if(res[i].equals("GoodSync Explorer.lnk")){
                goodsyncLink = true;
            }
            if(res[i].equals("GoodSync.lnk")){
                gsExplorerLink = true;
            }
            if(goodsyncLink && gsExplorerLink){
                result = true;
                break;
            }
        }
        return result;
    }

    private static boolean checkProcess(String processName){
        String line;
        StringBuilder pidInfo = new StringBuilder();
        boolean result = false;

        Process p = null;
        try {
            p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));

        try {
            while ((line = input.readLine()) != null) {
                pidInfo.append(line);
            }

            input.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }


        if(pidInfo.toString().contains(processName))
        {
            result = true;
        }
        return result;
    }

    public static boolean isGoodSyncDesktopAppInProcessList(){
        return checkProcess("GoodSync-v10.exe");
    }

    public static boolean isGoodSyncDesktopInstallerInProcessList(){
        return checkProcess("GoodSync-v10-Setup-pvt");
    }

    public static void killProcess(String procName){
        Process process;
        StringWriter sw = new StringWriter();
        String response;
        try {
            process = Runtime.getRuntime().exec("taskkill /F /IM " + procName);
            InputStream input = process.getInputStream();
            process.waitFor();
            int b;
            while((b = input.read()) != -1){
                sw.write(b);
            }
            response = sw.toString();
        } catch (IOException e) {
            // do nothing while not needed
        } catch (InterruptedException e) {
            // do nothing while not needed
        }
    }

    public static void createFolder(String path){
        Path pathToFolder = Paths.get(path);
        try {
            Files.createDirectories(pathToFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Deprecated
    public static String checkServiceStatus(String service){
        String result = null;
        try {
            Process process = new ProcessBuilder("C:\\Windows\\System32\\sc.exe", "query" , service ).start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;
            String scOutput = "";

            // Append the buffer lines into one string
            while ((line = br.readLine()) != null) {
                scOutput +=  line + "\n" ;
            }

            if (scOutput.contains("STATE")) {
                // TODO return service state here
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isServiceStatusRunning(String service){
        String serviceName = service;
        boolean result = false;

        try {
            Process process = new ProcessBuilder("C:\\Windows\\System32\\sc.exe", "query" , serviceName ).start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;
            String scOutput = "";

            // Append the buffer lines into one string
            while ((line = br.readLine()) != null) {
                scOutput +=  line + "\n" ;
            }
            System.err.println(scOutput);

            if (scOutput.contains("STATE")) {
                if (scOutput.contains("RUNNING")) {
                    result = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isAppStatusRunning(String service){
        String serviceName = service;
        boolean result = false;
        try {
            Process process = Runtime.getRuntime().exec("tasklist.exe /v /fi \"STATUS eq running\"");
                    /*new ProcessBuilder(System.getenv("windir") + "\\system32\\tasklist.exe", " /v /fi ",
                            "\"STATUS eq running\"").start();*/
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;
            String scOutput = "";

            // Append the buffer lines into one string
            while ((line = br.readLine()) != null) {
                scOutput +=  line + "\n" ;
            }

            if (scOutput.contains(serviceName)) {
                result = true;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    public static void deleteGsServerService(){
        try {
            Runtime.getRuntime().exec("taskkill /F /IM gs-server.exe");
            //Runtime.getRuntime().exec("cmd /c sc stop GsServer");
            Runtime.getRuntime().exec("cmd /c sc delete GsServer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGsRunnerService(){
        String username = System.getProperty("user.name");
        try {
            Runtime.getRuntime().exec("cmd /c sc stop GsRunner " + username);
            Runtime.getRuntime().exec("cmd /c sc delete GsRunner " + username);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean isFolderOrFileExist(String path){
        File f = new File(path);
        return f.exists();
    }

}
