package pages.NewInstaller.LanguageSelectionModule;

import data.Languages;
import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import org.sikuli.script.Button;
import org.sikuli.script.Screen;

public class LanguageSelectionModule {
    private Screen sikuli;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/scrolldownSmallBtn.png", similarity = 70.0f)
    private SikuliElement scrollDownSmallBtn;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/scrollUpSmallBtn.png", similarity = 70.0f)
    private SikuliElement scrollUpSmallBtn;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/englishLang.png", similarity = 70.0f)
    private SikuliElement englishLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/arabicLang.png", similarity = 70.0f)
    private SikuliElement arabicLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/armenianLang.png", similarity = 70.0f)
    private SikuliElement armenianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/belarushianLang.png", similarity = 70.0f)
    private SikuliElement belarussianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/bulgarianLang.png", similarity = 70.0f)
    private SikuliElement bulgarianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/catalanLang.png", similarity = 70.0f)
    private SikuliElement catalanLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/croatianLang.png", similarity = 70.0f)
    private SikuliElement croatianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/czechLang.png", similarity = 70.0f)
    private SikuliElement czechLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/danishLang.png", similarity = 70.0f)
    private SikuliElement danishLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/dutchLang.png", similarity = 70.0f)
    private SikuliElement dutchLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/frenchLang.png", similarity = 70.0f)
    private SikuliElement frenchLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/galegoLang.png", similarity = 70.0f)
    private SikuliElement galegoLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/germanLang.png", similarity = 70.0f)
    private SikuliElement germanLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/greekLang.png", similarity = 70.0f)
    private SikuliElement greekLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/hebrewLang.png", similarity = 70.0f)
    private SikuliElement hebrewLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/hungarianLang.png", similarity = 70.0f)
    private SikuliElement hungarianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/icelandicLang.png", similarity = 70.0f)
    private SikuliElement icelandicLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/indonesianLang.png", similarity = 70.0f)
    private SikuliElement indonesianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/italianLang.png", similarity = 70.0f)
    private SikuliElement italianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/japaneseLang.png", similarity = 70.0f)
    private SikuliElement japaneseLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/koreanLang.png", similarity = 70.0f)
    private SikuliElement koreanLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/norwegianLang.png", similarity = 70.0f)
    private SikuliElement norwegianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/norwegiannynorskLang.png", similarity = 70.0f)
    private SikuliElement norwegiannynorskLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/persianLang.png", similarity = 70.0f)
    private SikuliElement persianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/polishLang.png", similarity = 70.0f)
    private SikuliElement polishLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/portugueseLang.png", similarity = 70.0f)
    private SikuliElement portugueseLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/portuguesebrLang.png", similarity = 70.0f)
    private SikuliElement portuguesebrLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/romanianLang.png", similarity = 70.0f)
    private SikuliElement romanianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/russianLang.png", similarity = 70.0f)
    private SikuliElement russianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/serbianCyrilLang.png", similarity = 70.0f)
    private SikuliElement serbiancyrilLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/serbianLatinLang.png", similarity = 70.0f)
    private SikuliElement serbianlatinLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/simpchineseLang.png", similarity = 70.0f)
    private SikuliElement simpchineseLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/slovakLang.png", similarity = 70.0f)
    private SikuliElement slovakLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/spanishLang.png", similarity = 70.0f)
    private SikuliElement spanishLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/swedishLang.png", similarity = 70.0f)
    private SikuliElement swedishLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/tradchineseLang.png", similarity = 70.0f)
    private SikuliElement tradchineseLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/turkishLang.png", similarity = 70.0f)
    private SikuliElement turkishLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/ukrainianLang.png", similarity = 70.0f)
    private SikuliElement ukranianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/valencianLang.png", similarity = 70.0f)
    private SikuliElement valencianLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/vietnameseLang.png", similarity = 70.0f)
    private SikuliElement vietnameseLang;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/languageSelectionBtn.png", similarity = 70.0f)
    private SikuliElement languageSelectionBtn;

    @FindBy(image = "src/test/java/pages/NewInstaller/LanguageSelectionModule/img/scrollBar.png", similarity = 50.0f)
    private SikuliElement scrollBar;

    public LanguageSelectionModule(Screen s){
        this.sikuli = s;
        SikuliFactory.initElements(sikuli, this);
    }

    public void selectLanguage(int langID){
        if(!getElementNameByID(langID).exists()){
            boolean found = false;
            for(int i = 0; i < 10; i++){
                scrollBar.wheel(Button.WHEEL_DOWN, 1);
                if(getElementNameByID(langID).exists()){
                    found = true;
                    getElementNameByID(langID).click();
                    break;
                }
            }
            // if not found during 10 scroll to down - try to find it in the start of the list
            if(!found){
                for(int i = 0; i < 10; i++){
                    scrollBar.wheel(Button.WHEEL_UP, 1);
                    if(getElementNameByID(langID).exists()){
                        getElementNameByID(langID).click();
                        break;
                    }
                }
            }
        } else {
            getElementNameByID(langID).click();
        }
    }

    private SikuliElement getElementNameByID(int id){
        switch(id){
            case 1:
                return englishLang;
            case 2:
                return arabicLang;
            case 3:
                return armenianLang;
            case 4:
                return belarussianLang;
            case 5:
                return bulgarianLang;
            case 6:
                return catalanLang;
            case 7:
                return croatianLang;
            case 8:
                return czechLang;
            case 9:
                return danishLang;
            case 10:
                return dutchLang;
            case 11:
                return frenchLang;
            case 12:
                return galegoLang;
            case 13:
                return germanLang;
            case 14:
                return greekLang;
            case 15:
                return hebrewLang;
            case 17:
                return hungarianLang;
            case 18:
                return icelandicLang;
            case 19:
                return indonesianLang;
            case 20:
                return italianLang;
            case 21:
                return japaneseLang;
            case 22:
                return koreanLang;
            case 23:
                return norwegianLang;
            case 24:
                return norwegiannynorskLang;
            case 25:
                return persianLang;
            case 26:
                return polishLang;
            case 27:
                return portugueseLang;
            case 28:
                return portuguesebrLang;
            case 29:
                return romanianLang;
            case 30:
                return russianLang;
            case 31:
                return serbiancyrilLang;
            case 32:
                return serbianlatinLang;
            case 33:
                return simpchineseLang;
            case 34:
                return slovakLang;
            case 35:
                return spanishLang;
            case 36:
                return swedishLang;
            case 37:
                return tradchineseLang;
            case 38:
                return turkishLang;
            case 39:
                return ukranianLang;
            case 40:
                return valencianLang;
            case 41:
                return vietnameseLang;
        }
        return null;
    }

    public boolean isLanguageSelected(int id){
        return getElementNameByID(id).exists();
    }

    public void clickOnLangSelectionBtn(){
        languageSelectionBtn.click();
    }
}
