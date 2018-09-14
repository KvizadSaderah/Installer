package tests.Listeners;

import Helper.SysHelper;
import org.apache.commons.io.FileUtils;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MyListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result){
        String rootDir = System.getenv("SystemDrive") + "\\TestResults";
        String appData = System.getenv("APPDATA") + "\\GoodSync";
        String progData = System.getenv("PROGRAMDATA") + "\\GoodSync";
        String testClass = result.getTestClass().getName().replace('.', ' ').split(" ")[1];
        String tempFolder = rootDir + "\\temp-" + System.currentTimeMillis();
        if(!SysHelper.isFolderOrFileExist(rootDir)){
            SysHelper.createFolder(rootDir + "\\" + testClass);
        }
        if(!SysHelper.isFolderOrFileExist(rootDir + "\\" + testClass)){
            SysHelper.createFolder(rootDir + "\\" + testClass);
        }
        SysHelper.createFolder(tempFolder);
        makeScreenShot(tempFolder);
        try {
            FileUtils.copyDirectory(new File(appData), new File(tempFolder));
            FileUtils.copyDirectory(new File(progData), new File(tempFolder));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            packDirToZip(tempFolder, rootDir + "\\" + testClass + "\\" + result.getName() + ".zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SysHelper.deleteDirOrFile(tempFolder);
    }

    private void packDirToZip(String sourceDirPath, String zipFilePath) throws IOException {
        if(SysHelper.isFolderOrFileExist(zipFilePath)){
            SysHelper.deleteDirOrFile(zipFilePath);
        }
        Path p = Files.createFile(Paths.get(zipFilePath));
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
            Path pp = Paths.get(sourceDirPath);
            Files.walk(pp)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                            System.err.println(e);
                        }
                    });
        }
    }

    private void makeScreenShot(String folder){
        Screen screen = new Screen();
        BufferedImage image = screen.capture().getImage();
        try {
            ImageIO.write(image, "png", new File(folder + "\\screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
