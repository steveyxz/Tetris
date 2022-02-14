package util;

import com.sun.tools.javac.Main;
import engineTester.MainGameLoop;
import enums.GameState;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

public class MouseListener {

    private double cooldown = 0;

    public MouseListener() {
        try {
            Mouse.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        if (Mouse.isButtonDown(0)) {
            if (cooldown > 0) {
                if (MainGameLoop.gameState == GameState.START) {
                    if (checkBounds(Mouse.getX(), Mouse.getY(), 50, 70, 504, 280)) {
                        MainGameLoop.gameState = GameState.PLAY;
                    }
                }
                cooldown = 0;
                return;
            }
            cooldown++;
        }
    }

    public boolean checkBounds(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < height + y;
    }

    public void checkClick() {

    }
}
