package data;

import Helper.SysHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileDownloader {
    public static void downloadFile(String urlLink,String path){
        File pathToGS = new File(path);
        if (!pathToGS.exists()){
            SysHelper.createFolder("C:\\GoodSync");
        }
        URL url = null;
        try {
            url = new URL(urlLink);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ReadableByteChannel rbc = null;
        try {
            rbc = Channels.newChannel(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
