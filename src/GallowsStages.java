import javax.swing.*;

public enum GallowsStages {
    FIRST_STAGE("""  
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========
            """),

    SECOND_STAGE("""
               +---+
               |   |
               O   |
                   |
                   |
                   |
            =========
            """),

    THIRD_STAGE("""
               +---+
               |   |
               O   |
               |   |
                   |
                   |
            =========
            """),

    FOURTH_STAGE("""
               +---+
               |   |
               O   |
              /|   |
                   |
                   |
            =========
            """),

    FIFTH_STAGE("""
               +---+
               |   |
               O   |
              /|\\ |
                   |
                   |
            =========
            """),

    SIXTH_STAGE("""
               +---+
               |   |
               O   |
              /|\\ |
              /    |
                   |
            =========
            """),


    SEVENTH_STAGE("""
                 +---+
                 |   |
                 O   |
                /|\\ |
                / \\ |
                     |
               =========
            """);


    private static final GallowsStages[] VALUES = values();
    private final String image;

    public static GallowsStages fromOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal >= VALUES.length) {
            throw new IllegalArgumentException("Invalid ordinal: " + ordinal);
        }
        return VALUES[ordinal];
    }

    GallowsStages(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}