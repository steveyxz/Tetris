package engineTester;

import com.sun.tools.javac.Main;
import entities.Camera;
import enums.GameState;
import enums.PlayState;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import generation.TileGenerator;
import gui.GUIHandler;
import gui.GuiRenderer;
import gui.GuiTexture;
import gui.PlayerInfo;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.texture.TextureShader;
import util.EntityHandler;
import util.MouseListener;

import java.io.File;

public class MainGameLoop {

    public static PlayState playState = PlayState.TILE_GENERATE;
    public static GameState gameState = GameState.START;

    public static float xOffset = -0.55f;
    public static float yOffset = -0.4f;
    public static float tileWidth = 0.08f;

    public static GUIText highScore;
    public static GUIText score;
    public static FontType font;

    public static void main(String[] args) {
        //Create the display
        DisplayManager.createDisplay();

        //Initialise the basic stuff
        Loader loader = new Loader();

        TextMaster.init(loader);

        FontType font = new FontType(loader.loadTexture("verdana"), MainGameLoop.class.getResourceAsStream("/verdana.fnt"));
        GUIText text = new GUIText("Score: ", 1f, font, new Vector2f(0.77f, 0f), 1f, false);
        GUIText text2 = new GUIText("High Score: ", 1f, font, new Vector2f(0.77f, 0.04f), 1f, false);
        text.setColour(1, 0, 0);
        text2.setColour(0.5f, 0.5f, 1);
        score = new GUIText(Integer.toString(PlayerInfo.SCORE), 1f, font, new Vector2f(0.87f, 0f), 1f, false);
        highScore = new GUIText( Integer.toString(PlayerInfo.HIGH_SCORE), 1f, font, new Vector2f(0.95f, 0.04f), 1f, false);
        score.setColour(1, 0, 0);
        highScore.setColour(0.5f, 0.5f, 1);
        MainGameLoop.font = font;

        TextureShader shader = new TextureShader();
        Renderer renderer = new Renderer(shader, loader);

        Camera camera = new Camera();
        EntityHandler entityHandler = new EntityHandler(camera, shader, renderer);

        TileGenerator generator = new TileGenerator(entityHandler, loader);

        GUIHandler guiHandler = new GUIHandler();
        GuiRenderer startScreenRenderer = new GuiRenderer(loader);

        GuiTexture texture = new GuiTexture(loader.loadTexture("start"), new Vector2f(0.8f, -1f), new Vector2f(2, 2));
        guiHandler.addGui(texture);

        MouseListener listener = new MouseListener();

        shader.start();

        long lastTime  = System.nanoTime();
        double amountOfTicks = 30.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        //Game loop
        while (!Display.isCloseRequested()) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                if (gameState == GameState.PLAY) {
                    entityHandler.tick();
                    generator.tick();
                }
                listener.tick();
                delta--;
            }

            listener.checkClick();

            //Camera logic
            camera.move();

            //Prepare the render shader.
            renderer.prepare();

            //Start shader
            shader.start();
            //Change the view to match the camera view
            shader.changeView(camera);
            //Render the entities
            if (gameState == GameState.PLAY) {
                entityHandler.render();
            }

            shader.stop();
            if (gameState == GameState.PLAY) {
                TextMaster.render();
            } else if (gameState == GameState.START) {
                startScreenRenderer.render(guiHandler);
            }

            //Stop the shader

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }

            DisplayManager.updateDisplay();

        }

        //Clean up and close.
        TextMaster.cleanUp();
        loader.cleanUp();
        shader.cleanUp();
        startScreenRenderer.cleanUp();
        DisplayManager.closeDisplay();
    }

}
