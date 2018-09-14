package LogUtils;

import Helper.SysHelper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GsServerLogReader {

    public static boolean findStringInLog(String srchStrInLog, String folderPath, String fileName, boolean lastLogOnly){
        boolean result = false;
        List<String> tmp;
        if(lastLogOnly){
            URI uri = getLogURL(folderPath, fileName);
            /*try (Stream<String> stream = Files.lines(Paths.get(getLogURL(folderPath, fileName)))){
                result = stream.filter(lines -> lines.contains(srchStrInLog))
                        .findAny().isPresent();*/
                tmp = srchStrInLogs(srchStrInLog, uri);
                if(!tmp.isEmpty())
                    result = true;
            /*} catch (IOException e) {
                e.printStackTrace();
            }*/
        } else {
            URI[] _uri = getLogsURIs(folderPath, fileName);
            for(int i = 0; i < _uri.length; i++){
                tmp = srchStrInLogs(srchStrInLog, _uri[i]);
                if(!tmp.isEmpty())
                    result = true;
                break;
                /*try (Stream<String> stream = Files.lines(Paths.get(_uri[i]))){
                    result = stream.filter(lines -> lines.contains(srchStrInLog))
                            .findAny().isPresent();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        }

        return result;
    }

    public static String[] getLogErrors(String folderPath, String fileName, boolean lastLogOnly){
        List<String> finalResult = new ArrayList<>();
        List<String> rslt;
        if(lastLogOnly){
            URI uri = getLogURL(folderPath, fileName);
            rslt = srchStrInLogs("err=", uri);
            if(rslt != null && !rslt.isEmpty()){
                finalResult.addAll(rslt);
            }
            rslt = srchStrInLogs("ERROR", uri);
            if(rslt != null && !rslt.isEmpty()){
                finalResult.addAll(rslt);
            }
            rslt = srchStrInLogs("Bad CL arg", uri);
            if(rslt != null && !rslt.isEmpty()){
                finalResult.addAll(rslt);
            }
            rslt = srchStrInLogs("Error", uri);
            if(rslt != null && !rslt.isEmpty()){
                finalResult.addAll(rslt);
            }
            rslt = srchStrInLogs("Cannot", uri);
            if(rslt != null && !rslt.isEmpty()){
                finalResult.addAll(rslt);
            }
        } else {
            URI[] _uri = getLogsURIs(folderPath, fileName);
            for(int i = 0; i < _uri.length; i++) {
                rslt = srchStrInLogs("err=", _uri[i]);
                if(rslt != null && !rslt.isEmpty()){
                    finalResult.addAll(rslt);
                }
                rslt = srchStrInLogs("Bad CL arg", _uri[i]);
                if(rslt != null && !rslt.isEmpty()){
                    finalResult.addAll(rslt);
                }
                rslt = srchStrInLogs("ERROR", _uri[i]);
                if(rslt != null && !rslt.isEmpty()){
                    finalResult.addAll(rslt);
                }
                rslt = srchStrInLogs("Error", _uri[i]);
                if(rslt != null && !rslt.isEmpty()){
                    finalResult.addAll(rslt);
                }
                rslt = srchStrInLogs("Cannot", _uri[i]);
                if(rslt != null && !rslt.isEmpty()){
                    finalResult.addAll(rslt);
                }
            }
        }
        if(finalResult != null){
                return finalResult.stream().toArray(String[]::new);
        }
        return new String[0];

    }

    private static URI[] getLogsURIs(String pathToFolder, String fileName){
        Object[] obj = Arrays.stream(SysHelper.listDir(pathToFolder))
                .filter(files -> files.contains(fileName)).toArray();
        // cast obj[] to string[]
        String[] file = Arrays.copyOf(obj, obj.length, String[].class);
        URI[] _uris = new URI[file.length];
        for(int i = 0; i < file.length; i++){
            _uris[i] = new File(pathToFolder + "\\" + file[i]).toURI();
        }
        return _uris;
    }

    private static URI getLogURL(String pathToFolder, String fileName){
        Object[] obj = Arrays.stream(SysHelper.listDir(pathToFolder))
                .filter(files -> files.contains(fileName)).toArray();
        // cast obj[] to string[]
        String[] file = Arrays.copyOf(obj, obj.length, String[].class);
        // some shit below happens
        int[] numbers = new int[file.length];
        // parse integers of log timestamp
        for(int i = 0; i < file.length; i++){
            String timestamp = file[i].replaceAll(".log", "").split("-")[2];
            numbers[i] = Integer.parseInt(timestamp);
        }
        // finding max value index
        int index = 0;
        for(int i = 1; i < numbers.length; i++){
            if(numbers[index] > numbers[i]){
                continue;
            } else {
                index = i;
            }
        }
        return new File(pathToFolder + "\\" + file[index]).toURI();
    }

    private static List<String> srchStrInLogs(String srchStr, URI uri) {
        List<String> tmp = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(uri))) {
            List<String> tmp1 = null;
            // in case of strict match requires change to lines.matches(regexp)
            tmp = stream.parallel().filter(lines -> lines.contains(srchStr))
                    .collect(Collectors.toList());
            if (tmp1 != null) {
                tmp.addAll(tmp1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
    }
}
