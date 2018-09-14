package data;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class GoodSyncFiles {
    private static String[] goodSyncV10 = {
            "ar-arabic.rfs",
            "be-belarusian.rfs",
            "br-portuguesebr.rfs",
            "bg-bulgarian.rfs",
            "sibres",
            "html-templates",
            "ca-catalan.rfs",
            "clout.exe",
            "cn-simpchinese.rfs",
            "cz-czech.rfs",
            "da-danish.rfs",
            "dbghelp.dll",
            "de-german.rfs",
            "diff.exe",
            "el-greek.rfs",
            "en-english.rfs",
            "es-spanish.rfs",
            "fa-persian.rfs",
            "fr-french.rfs",
            "ga-galego.rfs",
            "goodsync.adm",
            "GoodSync-v10.exe",
            "gscp.exe",
            "GsExplorer.exe",
            "gs-runner.exe",
            "gs-server.crt",
            "gs-server.exe",
            "gs-server.key",
            "gsync.exe",
            "he-hebrew.rfs",
            "hr-croatian.rfs",
            "hu-hungarian.rfs",
            "hy-armenian.rfs",
            "id-indonesian.rfs",
            "is-icelandic.rfs",
            "it-italian.rfs",
            "jp-japanese.rfs",
            "ko-korean.rfs",
            "LogView.exe",
            "nl-dutch.rfs",
            "nn-norwegiannynorsk.rfs",
            "no-norwegian.rfs",
            "pl-polish.rfs",
            "pt-portuguese.rfs",
            "ro-romanian.rfs",
            "ru-russian.rfs",
            "sb-serbianlatin.rfs",
            "sk-slovak.rfs",
            "sr-serbiancyril.rfs",
            "sv-swedish.rfs",
            "tr-turkish.rfs",
            "uk-ukrainian.rfs",
            "va-valencian.rfs",
            "vi-vietnamese.rfs",
            "zh-tradchinese.rfs",
            "GoodSync2Go.bat"
    };

    public static String[] getGoodSyncv10Files(){
        return goodSyncV10;
    }

    public static String compareArrays(String[] arr){
        Collection<String> real = Arrays.asList(arr);
        Collection<String> expected = Arrays.asList(goodSyncV10);
        Collection<String> similar = new HashSet<String>(real);
        Collection<String> different = new HashSet<String>();
        different.addAll(real);
        different.addAll(expected);

        similar.retainAll(expected);
        different.removeAll( similar );
        String[] array = different.toArray(new String[different.size()]);
        String returnedRes = "";
        if (array.length == 0){
            return "";
        } else {
            for(int i = 0; i < array.length; i++){
                returnedRes += array[i] + " ";
            }
            return returnedRes;
        }

    }

    public static String join(String[] arr){
        return Arrays.asList(arr).stream().collect(Collectors.joining());
    }


}
