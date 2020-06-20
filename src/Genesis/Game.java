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
import java.util.Vector;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

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
    public void AddScene(Scene scene) {
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
    public void LoadScene(int i) {
        this.active_scene = this.scenes.elementAt(i);
        this.active_scene.OnLoadScene();
        this.active_scene.RenderStaticElements();
    }
    
    /**
     * Loads the scene
     * @param name the name of the scene
     * @return true if its loadet, false if scene dosent exist
     */
    public boolean LoadScene(String name)
    {
        for(Scene scene : this.scenes)
        {
            if(scene.getName() == name)
            {
                this.active_scene = scene;
                this.active_scene.OnLoadScene();
                this.active_scene.RenderStaticElements();
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
        this.callbacks.CB_ON_UPDATE();
        this.active_scene.OnUpdate();
        this.callbacks.CB_AFTER_UPDATE();
    }
    
    /**
     * Starts the Loop
     */
    public void TheLoop() {
        this.getSelectedScene().OnLoopStart();
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

        g2d.scale(this.Zoom, this.Zoom);
        if(this.active_scene != null)
        {
            active_scene.RenderScene(g2d);
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
        this.callbacks.CB_ON_KEY_DOWN(e);
        this.Input.setIsInput(true);
        this.Input.setInputKey(e.getKeyCode());
        this.getSelectedScene().OnKeyDown(e);
    }

    /**
     * Event for KeyRelease
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        this.callbacks.CB_ON_KEY_RELEASE(e);
        this.Input.setIsInput(false);
        this.getSelectedScene().OnKeyUp(e);
    }
    
    /**
     * Event for Mouse Click
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        this.callbacks.CB_ON_MOUSE_CLICK(e);
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
        this.callbacks.CB_ON_MOUSE_DOWN(e);
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
        this.callbacks.CB_ON_MOUSE_RELEASE(e);
        this.Input.setMouseInput(false);
        this.Input.setMouseInputKey(0);
    }

    /**
     * Event for Mouse Enter
     * @param e 
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.callbacks.CB_ON_MOUSE_ENTER(e);
    }

    /**
     * Event for Mouse Exit
     * @param e 
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.callbacks.CB_ON_MOUSE_LEAVE(e);
    }

    /**
     * Event for Mouse Dragged
     * @param e 
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        
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
        this.callbacks.CB_ON_MOUSE_MOVE(e);
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
        this.callbacks.CB_ON_MOUSE_WHEELE_MOVE(e);
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

    public float getZoom() {
        return Zoom;
    }

    public void setZoom(float zoom) {
        Zoom = zoom;
    }

    public void addZoom(float zoom) {
        Zoom = Zoom + zoom;
    }
}
