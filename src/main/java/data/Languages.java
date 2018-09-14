package data;

public enum Languages {
    ENGLISH (1),
    ARABIC (2),
    ARMENIAN (3),
    BELARUSSIAN (4),
    BULGARIAN (5),
    CATALAN (6),
    CROATIAN (7),
    CZECH (8),
    DANISH (9),
    DUTCH (10),
    FRENCH (11),
    GALEGO (12),
    GERMAN (13),
    GREEK (14),
    HEBREW (15),
    HUNGARIAN (16),
    ICELANDIC (17),
    INDONESIAN (18),
    ITALIAN (19),
    JAPANESE (20),
    KOREAN (21),
    NORWEGIAN (22),
    NORWEGIANNYNORSK (23),
    PERSIAN (24),
    POLISH (25),
    PORTUGUESE (26),
    PORTUGUESEBR (27),
    ROMANIAN (28),
    RUSSIAN (29),
    SERBIANCYRIL (30),
    SERBIANLATIN (31),
    SIMPCHINESE (32),
    SLOVAK (33),
    SPANISH (34),
    SWEDISH (35),
    TRADCHINESE (36),
    TURKISH (37),
    UKRAINIAN (38),
    VALENCIAN (39),
    VIETNAMESE (40);

    public int getId() {
        return id;
    }

    private final int id;

    Languages(int num){
        this.id = num;
    }

}
