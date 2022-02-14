package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import java.security.Key;

public class Camera {

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch; //Up down
    private float yaw; //Left right
    private float roll; //Rotation (in degrees)

    private float speed = 0.03f;

    public void move() {

    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
