/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import Genesis.Math.Vector2;
import Genesis.UI.Canvas;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JPanel;

/**
 *
 * @author Andy
 */
public class Game extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private Vector<Scene> scenes;
    private Vector<Canvas> uiCanvases;
    private Scene active_scene;
    private GameCallbacks callbacks;
    private boolean bQuit = false;
    private Timer loop;
    private Vector2 MousePosition;
    private Input Input;
    private float Zoom = 1.0f;
    private BufferedImage backgroundPlane;
    
    public Game(GameCallbacks cbs) {
        this.scenes = new Vector<Scene>();
        this.uiCanvases = new Vector<Canvas>();
        this.setBackground(new java.awt.Color(46, 154, 254));
        this.callbacks = cbs;
        this.loop = new Timer(30, this);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.setFocusable(true);
        this.MousePosition = new Vector2(0,0);
        this.Input = new Input();
    }

    public Game(GameCallbacks cbs, int maxFps) {
        this.scenes = new Vector<Scene>();
        this.uiCanvases = new Vector<Canvas>();
        this.setBackground(new java.awt.Color(46, 154, 254));
        this.callbacks = cbs;
        this.loop = new Timer(maxFps, this);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.setFocusable(true);
        this.MousePosition = new Vector2(0,0);
        this.Input = new Input();
    }
    
    /**
     * Adds a scene to the game
     * @param scene the item to add 
     */
    public void addScene(Scene scene) {
        this.scenes.add(scene);
    }

    /**
     * Adds a canvas to the game
     * @param canvas the canvas to add
     */
    public void AddCanvas(Canvas canvas)
    {
        this.uiCanvases.add(canvas);
    }
    
    /**
     * Returns the canvas with the name n
     * @param Name the name from the canvas
     * @return Canvas
     */
    public Canvas GetCanvas(String Name)
    {
        for(Canvas c : this.uiCanvases)
        {
            if(c.getName().equals(Name))
            {
                return c;
            }
        }
        return null;
    }
    
    /**
     * Loads a scene
     * @param i the scene to load
     */
    public void loadScene(int i) {
        this.active_scene = this.scenes.elementAt(i);
        this.active_scene.onLoadScene();
        this.active_scene.renderStaticElements();
    }
    
    /**
     * Loads the scene
     * @param name the name of the scene
     * @return true if its loadet, false if scene dosent exist
     */
    public boolean loadScene(String name)
    {
        for(Scene scene : this.scenes)
        {
            if(scene.getName() == name)
            {
                this.active_scene = scene;
                this.active_scene.onLoadScene();
                this.active_scene.renderStaticElements();
                return true;
            }
        }
        return false;
    }
    
    public Scene getSelectedScene(){
        return this.active_scene;
    }
    
    /**
     * Updates the game
     */
    public void UpdateGame() {
        this.callbacks.onUpdate();
        this.active_scene.onUpdate(this);
        for(Canvas c : this.uiCanvases) {
            if(c.isEnable()) {
                c.onUpdate(this);
            }
        }
        this.callbacks.afterUpdate();
    }
    
    /**
     * Starts the Loop
     */
    public void theLoop() {
        this.getSelectedScene().onLoopStart();
        this.loop.run();
    }
    
    /**
     * Stops the Loop
     */
    public void Stop() {
        this.bQuit = true;
    }
        
    /**
     * Set the callback functions
     * @param callbacks the callbacks
     */
    public void setCallbacks(GameCallbacks callbacks) {
        this.callbacks = callbacks;
    }
    
    /**
     * Get the callbacks
     * @return 
     */
    public GameCallbacks getCallbacks() {
        return callbacks;
    }
    
    /**
     * Resturn the loop state
     * @return 
     */
    public boolean isbQuit() {
        return bQuit;
    }
    
    /**
     * Render the game and the ui
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if(this.backgroundPlane != null) {
            g2d.drawImage(this.backgroundPlane,0, 0, this.getWidth(), this.getHeight(), null);
        }

        g2d.scale(this.Zoom, this.Zoom);
        if(this.active_scene != null)
        {
            active_scene.renderScene(g2d);
        }

        g2d.scale(1.0f, 1.0f);
        for(Canvas c : this.uiCanvases)
        {
            if(c.isEnable())
            {
                c.RenderCanvas(g2d);
            }
        }
    }
    
    /**
     * Returns the Mouse Position
     * @return Vector2
     */
    public Vector2 MousePosition() {
        return this.MousePosition;
    }

    /**
     * Event for KeyTyped
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    /**
     * Event for KeyPressed
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        this.callbacks.onKeyDown(e);
        this.Input.setIsInput(true);
        this.Input.setInputKey(e.getKeyCode());
        this.getSelectedScene().OnKeyDown(e);
        for(Canvas c : this.uiCanvases) {
            c.keyPressed(this.Input);
        }
    }

    /**
     * Event for KeyRelease
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        this.callbacks.onKeyRelease(e);
        this.Input.setIsInput(false);
        this.Input.onKeyRelease(e.getKeyCode());
        this.getSelectedScene().OnKeyUp(e);
        for(Canvas c : this.uiCanvases) {
            c.keyReleased(this.Input);
        }
    }
    
    /**
     * Event for Mouse Click
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        this.callbacks.onMouseClick(e);
        //this.Input.setMouseInput(true);
        //this.Input.setMouseInputKey(e.getButton());
        for(Canvas c : this.uiCanvases)
        {
            if(c.Contains(e.getX(), e.getY()))
            {
                c.OnClick(e);
            }
        }
    }

    /**
     * Event for Mouse Pressed
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e) {
        this.callbacks.onMouseDown(e);
        this.Input.setMouseX(e.getX());
        this.Input.setMouseY(e.getY());
        this.Input.setMouseInput(true);
        this.Input.setMouseInputKey(e.getButton());
        this.getSelectedScene().OnMouseDown(e);
    }

    /**
     * Event for Mouse Release
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.callbacks.onMouseRelease(e);
        this.Input.setMouseInput(false);
        this.Input.setMouseInputKey(0);
    }

    /**
     * Event for Mouse Enter
     * @param e 
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.callbacks.onMouseEnter(e);
    }

    /**
     * Event for Mouse Exit
     * @param e 
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.callbacks.onMouseLeave(e);
    }

    /**
     * Event for Mouse Dragged
     * @param e 
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        this.Input.setMouseX(e.getX());
        this.Input.setMouseY(e.getY());
    }

    /**
     * Event for MouseMoved
     * @param e 
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        this.MousePosition.set(e.getX(), e.getY());
        this.Input.setMouseX(e.getX());
        this.Input.setMouseY(e.getY());
        this.callbacks.onMouseMove(e);
        for(Canvas c : this.uiCanvases)
        {
            if(c.Contains(e.getX(), e.getY()))
            {
                c.Hover(e);
            }
            else {
                c.OnMouseLeave(e);
            }
        }
        if(this.getSelectedScene() != null)
        {
            this.getSelectedScene().OnMouseMove(e);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.callbacks.onMouseWheeleMove(e);
    }

    /**
     * Returns the input informations
     * @return
     */
    public Input getInput() {
        return Input;
    }

    /**
     * Returns all scenes
     * @return
     */
    public Vector<Scene> getScenes() {
        return scenes;
    }

    /**
     * Returns the fps value
     * @return
     */
    public int getFps() {
        return this.loop.getFps();
    }

    public long getDeltaTime() {
        return  this.loop.getDeltaTime();
    }

    public float getZoom() {
        return Zoom;
    }

    public void setZoom(float zoom) {
        Zoom = zoom;
    }

    public void addZoom(float zoom) {
        Zoom = Zoom + zoom;
    }

    public BufferedImage getBackgroundPlane() {
        return backgroundPlane;
    }

    public void setBackgroundPlane(BufferedImage backgroundPlane) {
        this.backgroundPlane = backgroundPlane;
    }
}
