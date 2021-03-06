package shaders;

import java.io.*;
import java.nio.FloatBuffer;

import engineTester.MainGameLoop;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public abstract class ShaderProgram {

    //ID of the shader program
    private int programID;
    //ID of the vertexShader
    private int vertexShaderID;
    //ID of the fragmentShader
    private int fragmentShaderID;

    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    /**
     * Basic rules for a new shader program.
     * @param vertexFile The vertex shader file path (from src, src/shaders...)
     * @param fragmentFile The fragment shader file path
     */
    public ShaderProgram(String vertexFile,String fragmentFile){
        //Load both shaders
        vertexShaderID = loadShader(vertexFile,GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile,GL20.GL_FRAGMENT_SHADER);
        //Create a shader program
        programID = GL20.glCreateProgram();
        //Attach the shaders to the program
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        //Bind the attributes to the shaders (VBOS from the VAO)
        bindAttributes();
        //Initialize the program.
        GL20.glLinkProgram(programID);
        GL20.glUseProgram(programID);
        GL20.glValidateProgram(programID);
        getAllUniformLocations();
    }

    protected int getUniformVariable(String uniformName) {
        return GL20.glGetUniformLocation(programID, uniformName);
    }

    protected void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    protected void loadVector3f(int location, Vector3f vector3f) {
        GL20.glUniform3f(location, vector3f.x, vector3f.y, vector3f.z);
    }

    protected void loadVector2f(int location, Vector2f vector2f) {
        GL20.glUniform2f(location, vector2f.x, vector2f.y);
    }

    protected void loadBoolean(int location, boolean value) {
        float i = value ? 1 : 0;
        GL20.glUniform1f(location, i);
    }

    protected void loadMatrix(int location, Matrix4f matrix) {
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(location, false, matrixBuffer);
    }

    protected abstract void getAllUniformLocations();

    public void start(){
        //Start
        GL20.glUseProgram(programID);
    }

    public void stop(){
        //Stop
        GL20.glUseProgram(0);
    }

    public void cleanUp(){
        //Calls on window close.
        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }

    /**
     * This method will contain all the attributes from the VAO the shader
     * program will take in.
     */
    protected abstract void bindAttributes();

    /**
     * This method is used to bind a VAO to a specific input name in the shader
     * @param attribute The ID of the attribute to add (This is a VBO ID)
     * @param variableName The name of the variable to bind this to.
     */
    protected void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    /**
     * Loads a shader file into the display.
     * @param file The path to the shader file (From src, e.g., src/shaders/...);
     * @param type The type of the shader, either GL20.GL_VERTEX_SHADER or GL20.GL_FRAGMENT_SHADER
     * @return The ID of the shader created.
     */
    private static int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        InputStream is = MainGameLoop.class.getResourceAsStream(file);
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine())!=null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
        return shaderID;
    }

}
