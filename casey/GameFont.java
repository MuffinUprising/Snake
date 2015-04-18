package casey;

import java.awt.*;
import java.io.File;
import java.io.IOException;
/**
 * Created by casey on 4/17/15.
 */
public class GameFont {

    private static String fontPath;

    public GameFont(String filePath) {
        GameFont.fontPath = filePath;
        registerFont();
    }
    //registers the font to be used in the game
    private void registerFont() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
        }
        catch (IOException ioe) {
            String err = ioe.getMessage();
            System.out.println(err);
        }
        catch (FontFormatException ffe) {
            String err = ffe.getMessage();
            System.out.println(err);
        }
    }
}
