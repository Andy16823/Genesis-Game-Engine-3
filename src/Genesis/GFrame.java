package Genesis;

import Genesis.Graphics.Camera;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Vector;

public class GFrame extends JFrame implements GameCallbacks {
    public Game gamePanel;
    public Camera camera;
    public Pipeline pipeline;
    public Vector<GFrameCallbacks> callbacks;

    public GFrame(int width, int height) {
        this.setSize(width, height);
        this.gamePanel = new Game(this, 100);
        this.callbacks = new Vector<>();
        this.pipeline = new Pipeline();
        this.camera = new Camera(0,0, width, height);
        this.add(gamePanel);
    }

    public GFrame(int width, int height, long maxFps) {
        this.setSize(width, height);
        this.gamePanel = new Game(this, (int) maxFps);
        this.callbacks = new Vector<>();
        this.pipeline = new Pipeline();
        this.camera = new Camera(0,0, width, height);
        this.add(gamePanel);
    }

    public void init() {
        for(GFrameCallbacks callback : this.callbacks) {
            callback.onInit(this.gamePanel);
        }
    }

    public void startGame() {
        for(GFrameCallbacks callback : this.callbacks) {
            callback.onStart(this.gamePanel);
        }
        this.gamePanel.theLoop();
    }

    @Override
    public void onUpdate() {
        for(GFrameCallbacks callback : this.callbacks) {
            callback.onUpdate(this.gamePanel);
        }
    }

    @Override
    public void afterUpdate() {
        for(GFrameCallbacks callback : this.callbacks) {
            callback.afterUpdate(this.gamePanel);
        }
    }

    @Override
    public void onKeyDown(KeyEvent e) {

    }

    @Override
    public void onKeyRelease(KeyEvent e) {

    }

    @Override
    public void onMouseDown(MouseEvent e) {

    }

    @Override
    public void onMouseRelease(MouseEvent e) {

    }

    @Override
    public void onMouseClick(MouseEvent e) {

    }

    @Override
    public void onMouseEnter(MouseEvent e) {

    }

    @Override
    public void onMouseLeave(MouseEvent e) {

    }

    @Override
    public void onMouseMove(MouseEvent e) {

    }

    @Override
    public void onMouseWheeleMove(MouseWheelEvent e) {

    }

    public Game getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(Game gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public Vector<GFrameCallbacks> getCallbacks() {
        return callbacks;
    }

    public void setCallbacks(Vector<GFrameCallbacks> callbacks) {
        this.callbacks = callbacks;
    }

    public void addCallback(GFrameCallbacks callback) {
        this.callbacks.add(callback);
    }
}
