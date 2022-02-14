package renderEngine;

import engineTester.MainGameLoop;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.PNGDecoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;


public class DisplayManager {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 720;
    public static final int FPS_CAP = 240;
    private static final String[] ICON_PATHS = new String[] {"/logoHD.png", "/logo16.png", "/logo32.png"};

    public static void createDisplay() {

        ContextAttribs attribs = new ContextAttribs(3, 2)
                .withForwardCompatible(true)
                .withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        Display.setTitle("Tetris");
        ByteBuffer[] icon_array = new ByteBuffer[ICON_PATHS.length];
        try {
            for (int i = 0; i < ICON_PATHS.length; i++){
                icon_array[i] = ByteBuffer.allocateDirect(1);
                String path = ICON_PATHS[i];
                icon_array[i] = loadIcon(MainGameLoop.class.getResourceAsStream(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Display.setIcon(icon_array);
        try {
            Display.create(new PixelFormat(), attribs);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(0, 0, WIDTH, HEIGHT);
    }

    private static ByteBuffer loadIcon(InputStream path) throws IOException {
        try {
            PNGDecoder decoder = new PNGDecoder(path);
            ByteBuffer bytebuf = ByteBuffer.allocateDirect(decoder.getWidth() * decoder.getHeight() * 4);
            decoder.decode(bytebuf, decoder.getWidth() * 4, PNGDecoder.RGBA);
            bytebuf.flip();
            return bytebuf;
        } finally {
            path.close();
        }
    }

    public static void updateDisplay() {

        Display.sync(FPS_CAP);
        Display.update();

    }

    public static void closeDisplay() {
        Display.destroy();
    }

}
