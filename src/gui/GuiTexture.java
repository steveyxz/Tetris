package gui;

import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.GLUtils;

public class GuiTexture {

    private int texture;
    private Vector2f pos;
    private Vector2f scale;

    public GuiTexture(int texture, Vector2f pos, Vector2f scale) {
        this.texture = texture;
        this.pos = pos;
        this.scale = scale;
    }

    public int getTexture() {
        return texture;
    }

    public Vector2f getPos() {
        return pos;
    }

    public Vector2f getScale() {
        return scale;
    }
}
