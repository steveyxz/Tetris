package gui;

import models.RawModel;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import org.w3c.dom.UserDataHandler;
import renderEngine.Loader;
import tools.maths.TransformationMaths;

import java.util.List;

public class GuiRenderer {

    private final RawModel quad;
    private GuiShader shader;

    public GuiRenderer(Loader loader) {
        float[] position = new float[] {-1, 2, -1, -2, 1, 2, 1, -2};
        quad = loader.loadToVAO(position);
        this.shader = new GuiShader();
    }

    public void render(GUIHandler handler) {
        shader.start();
        List<GuiTexture> guis = handler.getGuis();
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        for (GuiTexture t : guis) {
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, t.getTexture());
            Matrix4f matrix = TransformationMaths.createTransformationMatrix(t.getPos(), t.getScale());
            shader.loadTransformation(matrix);
            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
        }
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        GL11.glDisable(GL11.GL_BLEND);
        shader.stop();
    }

    public void cleanUp(){
        shader.stop();
    }

}
