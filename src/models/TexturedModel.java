package models;

import org.w3c.dom.Text;
import textures.ModelTexture;

public class TexturedModel {

    private RawModel rawModel;
    private ModelTexture texture;

    /**
     * Represents a textured model
     * @param model The raw model which this textured model is based on.
     * @param texture The texture which will then be applied to the raw model.
     */
    public TexturedModel(RawModel model, ModelTexture texture) {
        this.rawModel = model;
        this.texture = texture;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public ModelTexture getTexture() {
        return texture;
    }
}
