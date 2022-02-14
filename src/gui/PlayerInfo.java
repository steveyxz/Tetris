package gui;

import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import org.lwjgl.util.vector.Vector2f;

import static engineTester.MainGameLoop.*;

public class PlayerInfo {

    public static int SCORE = 0;
    public static int HIGH_SCORE = 0;

    public static void addScore(int add) {
        SCORE += add;
        TextMaster.removeText(score);
        score = new GUIText(Integer.toString(PlayerInfo.SCORE), 1f, font, new Vector2f(0.87f, 0f), 1f, false);
        score.setColour(1, 0, 0);
        if (SCORE > HIGH_SCORE) {
            TextMaster.removeText(highScore);
            HIGH_SCORE = SCORE;
            highScore = new GUIText( Integer.toString(PlayerInfo.HIGH_SCORE), 1f, font, new Vector2f(0.95f, 0.04f), 1f, false);
            highScore.setColour(0.5f, 0.5f, 1);
        }
    }

}
