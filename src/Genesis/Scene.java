/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import Genesis.Graphics.Camera;
import Genesis.Graphics.Lightmap;
import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *
 * @author Andy
 */
public class Scene {
    private String name;
    private String tag;
    private boolean enabled;
    private Vector2 location;
    private Vector2 size;
    private Vector<GameElement> elements;
    private Vector<Lightmap> Lightmaps;
    private BufferedImage scene_buffer;
    private Vector<SceneActionListener> SceneActionListeners;
    private Vector<Layer> layer;
    private boolean callBeforeRenderElements = false;
    private boolean callAfterRenderElements = false;
    private Camera camera;
    private Game game;

    /**
     * 
     * @param Name Your name for the scene
     * @param Size the size from the scene
     */
    public Scene(String Name, Vector2 Size)
    {
        this.name = Name;
        this.location = new Vector2(0,0);
        this.size = Size;
        this.elements = new Vector<GameElement>();
        this.Lightmaps = new Vector<Lightmap>();
        this.SceneActionListeners = new Vector<SceneActionListener>();
        this.layer = new Vector<Layer>();
    }

    public void initScene() {

    }

    public void onLoopStart() {

    }

    public void onLoadScene() {

    }
    
    /**
     * Render the STATIC elements
     */
    public void renderStaticElements()
    {
        this.scene_buffer = new BufferedImage(size.getX(), size.getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scene_buffer.createGraphics();
        
        for(SceneActionListener listener : this.SceneActionListeners)
        {
            listener.BeforeRenderStaticElements(g2d);
        }
        
        for(GameElement e : elements)
        {
            if(e.getRender_mode() == RenderMode.STATIC && e.isEnabled())
            {
                e.bevoreRender(g2d);
                g2d.drawImage(e.getSprite(), e.getLocation().getX(), e.getLocation().getY(), e.getSize().getX(), e.getSize().getY(), null);
                e.afterRender(g2d);
            }
        }

        for(Layer layer : this.layer) {
            if(layer.isActive())
            {
                layer.RenderStaticElements(g2d);
            }
        }
        
        for(SceneActionListener listener : this.SceneActionListeners)
        {
            listener.AfterRenderStaticElements(g2d);
        }
    }
    
    /**
     * Renders the scene
     * @param g 
     */
    public void renderScene(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for(SceneActionListener listener : this.SceneActionListeners)
        {
            listener.BeforeRender(g2d);
        }
        
        if(this.scene_buffer != null)
        {
            g2d.drawImage(scene_buffer, this.location.getX(), this.location.getY(), this.size.getX(), this.size.getY(), null);

            // Load all gameElements if its needed
            Vector<GameElement> allElements = null;
            if(this.callAfterRenderElements || this.callBeforeRenderElements) {
                allElements = this.getAllGeameElements();
            }

            // Bevore Render GameElements
            if(this.callBeforeRenderElements && allElements != null) {
                for(GameElement e : allElements) {
                    for(GameBehavior behavior : e.getBehaviors()) {
                        behavior.bevoreRenderElements(g2d);
                    }
                }
            }

            // Rende Scene GameElement
            for(GameElement e : this.elements)
            {
                if(e.getRender_mode() == RenderMode.DYNAMIC && e.isEnabled())
                {
                    e.afterRender(g2d);
                    AffineTransform oldTransform = g2d.getTransform();
                    AffineTransform newtransform = new AffineTransform();
                    newtransform.rotate(Math.toRadians(e.getRotation()), (e.getLocation().getX() + (e.getSize().getX() / 2)), (e.getLocation().getY() + (e.getSize().getY() / 2)));
                    // g2d.translate((e.getLocation().getX() - (e.getSize().getX() / 2)), (e.getLocation().getY() - (e.getSize().getY() / 2)));
                    g2d.setTransform(newtransform);
                    // g2d.rotate(Math.toRadians(e.getRotation()), (e.getLocation().getX() + (e.getSize().getX() / 2)), (e.getLocation().getY() + (e.getSize().getY() / 2)));
                    g2d.drawImage(e.getSprite(), e.getLocation().getX(), e.getLocation().getY(), e.getSize().getX(), e.getSize().getY(),null);
                    g2d.setTransform(oldTransform);
                    e.bevoreRender(g2d);
                }
            }

            // Render Layer
            for(Layer layer : this.layer) {
                if(layer.isActive()) {
                    layer.RenderLayer(g2d);
                }
            }

            // After Render GameElmenets
            if(this.callAfterRenderElements && allElements != null) {
                for(GameElement e : allElements) {
                    for(GameBehavior behavior : e.getBehaviors()) {
                        behavior.afterRenderElements(g2d);
                    }
                }
            }

            //Lightmap
            for(Lightmap lm : this.Lightmaps)
            {
                if(lm.isEnabled())
                {
                    if(lm.getRenderMode() == RenderMode.DYNAMIC)
                    {
                        lm.RenderLightmap();
                    }
                    g2d.drawImage(lm.getLightmap(), lm.getLocation().getX(), lm.getLocation().getY(), lm.getSize().getX(), lm.getSize().getY(), null);
                }
            }
            
        }
        
        for(SceneActionListener listener : this.SceneActionListeners)
        {
            listener.AfterRender(g);
        }
        
    }
    
    public void transformScene(int x, int y)
    {
        this.location.addX(x);
        this.location.addY(y);
        
        for(GameElement e : this.elements)
        {
            if(!e.isIgnoreSceneTranform())
            {
                e.getLocation().addX(x);
                e.getLocation().addY(y);
            }
        }

        for(Layer layer : this.layer) {
            layer.TransformLayer(x, y);
        }
        
        for(Lightmap lm : this.Lightmaps)
        {
            lm.transformLightMap(x,y);
        }
    }

    public void transformScene(int x, int y, GameElement excludeElement)
    {
        this.location.addX(x);
        this.location.addY(y);

        for(GameElement e : this.elements)
        {
            if(e != excludeElement) {
                e.getLocation().addX(x);
                e.getLocation().addY(y);
            }
        }

        for(Layer layer : this.layer) {
            layer.TransformLayer(x, y);
        }

        for(Lightmap lm : this.Lightmaps)
        {
            lm.getLocation().addX(x);
            lm.getLocation().addY(y);
        }
    }

    public void resetTransform() {
        this.transformScene(-this.location.getX(), -this.location.getY());
    }
    
    public void renderLightmaps()
    {
        for(Lightmap lm : this.Lightmaps)
        {
            lm.RenderLightmap();
        }
    }
    
    /**
     * Update the Scene
     */
    public void onUpdate(Game game)
    {
        for(GameElement e : this.elements)
        {
            e.beforeUpdate();

            if(e.isEnabled())
            {
                e.onUpdate(game);
            }

            e.afterUpdate();
        }

        for(Layer layer : this.layer)
        {
            if(layer.isActive()) {
                layer.onUpdate(game);
            }
        }
    }
    
    public void OnKeyDown(KeyEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onKeyDown(e);
            }
        }

        for(Layer layer : this.layer) {
            if(layer.isActive()) {
                layer.OnKeyDown(e);
            }
        }
    }
    
    public void OnKeyUp(KeyEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onKeyUp(e);
            }
        }

        for(Layer layer : this.layer) {
            if(layer.isActive()) {
                layer.OnKeyUp(e);
            }
        }
    }
    
    public void OnMouseClick(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onMouseClick(e);
            }
        }

        for(Layer layer : this.layer) {
            if(layer.isActive()) {
                layer.OnMouseClick(e);
            }
        }
    }
    
    public void OnMouseDown(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onMouseDown(e);
            }
        }

        for(Layer layer : this.layer) {
            if(layer.isActive()) {
                layer.OnMouseDown(e);
            }
        }
    }
    
    public void OnMouseUp(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onMouseUp(e);
            }
        }

        for(Layer layer : this.layer) {
            if(layer.isActive()) {
                layer.OnMouseUp(e);
            }
        }
    }
    
    public void OnMouseEnter(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onMouseEnter(e);
            }
        }

        for(Layer layer : this.layer) {
            if(layer.isActive()) {
                layer.OnMouseEnter(e);
            }
        }
    }
    
    public void OnMouseLeave(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onMouseLeave(e);
            }
        }

        for(Layer layer : this.layer) {
            if(layer.isActive()) {
                layer.OnMouseLeave(e);
            }
        }
    }

    public void OnMouseMove(MouseEvent e) {
        for(GameElement element : this.elements) {
            if(element.isEnabled()) {
                if(element.contains(e.getX(), e.getY())) {
                    element.setMouseFocused(true);
                }
                else  {
                    element.setMouseFocused(false);
                }
            }
        }
        for(Layer layer : this.layer) {
            if(layer.isActive()) {
                for(GameElement element : layer.getElements()) {
                    if(element.isEnabled()) {
                        if(element.contains(e.getX(), e.getY())) {
                            element.setMouseFocused(true);
                        }
                        else {
                            element.setMouseFocused(false);
                        }
                    }
                }
            }
        }
    }

    /**
     * Adds a new GameElement
     * @param e the element
     */
    public void AddGameElement(GameElement e)
    {
        for(SceneActionListener listener : this.SceneActionListeners)
        {
            listener.BeforeAddGameElement(e);
        }
        
        this.elements.add(e);
        
        for(SceneActionListener listener : this.SceneActionListeners)
        {
            listener.AfterAddGameElement(e);
        }
        
    }

    public void addGameElement(int Layer, GameElement GameElement) {
        if(this.layer.get(Layer) != null) {
            this.layer.get(Layer).addGameElement(GameElement);
        }
    }

    /**
     * Adds a new layer to the scene
     * @param layer
     */
    public void addLayer(Layer layer) {
        layer.setParentScene(this);
        this.layer.add(layer);
    }
    
    /**
     * Removes the GameElement
     * @param Name 
     */
    public void RemoveGameElement(String Name)
    {
        for(GameElement e : this.elements)
        {
            if(e.getName().equals(Name))
            {
                this.elements.remove(e);
            }
        }
    }
    
    /**
     * Removes the GameElement
     * @param e
     * */
    public void RemoveGameElement(GameElement e) 
    {
        this.elements.remove(e);
    }

    public void RemoveLayer(Layer layer) {
        this.layer.remove(layer);
    }
    
    public void AddLightmap(Lightmap lm)
    {
        this.Lightmaps.add(lm);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public Vector<GameElement> getElements() {
        return elements;
    }

    public void setElements(Vector<GameElement> elements) {
        this.elements = elements;
    }

    public boolean isCallBeforeRenderElements() {
        return callBeforeRenderElements;
    }

    public void setCallBeforeRenderElements(boolean callBeforeRenderElements) {
        this.callBeforeRenderElements = callBeforeRenderElements;
    }

    public boolean isCallAfterRenderElements() {
        return callAfterRenderElements;
    }

    public void setCallAfterRenderElements(boolean callAfterRenderElements) {
        this.callAfterRenderElements = callAfterRenderElements;
    }

    public Vector<Lightmap> getLightmaps() {
        return Lightmaps;
    }

    public void setLightmaps(Vector<Lightmap> Lightmaps) {
        this.Lightmaps = Lightmaps;
    }

    public BufferedImage getScene_buffer() {
        return scene_buffer;
    }

    public void setScene_buffer(BufferedImage scene_buffer) {
        this.scene_buffer = scene_buffer;
    }
   
    public void addSceneActionListener(SceneActionListener e)
    {
        this.SceneActionListeners.add(e);
    }

    public GameElement getElement(String name) {
        for(GameElement e : this.elements)
        {
            if(e.getName().equals(name))
            {
                return e;
            }
        }
        return null;
    }

    public GameElement getElement(String LayerName, String ElementName) {
        return this.getLayer(LayerName).getGameElement(ElementName);
    }

    public Layer getLayer(String name) {
        for(Layer layer : this.layer) {
            if(layer.getName().equals(name)) {
                return  layer;
            }
        }
        return  null;
    }

    public Vector<GameElement> getAllGeameElements() {
        Vector<GameElement> ret = new Vector<>();
        for (GameElement e : this.elements) {
            ret.add(e);
        }

        for(Layer layer : this.layer) {
            for (GameElement e : layer.getElements()) {
                ret.add(e);
            }
        }
        return  ret;
    }

    public Vector<GameElement> getGameElementsByTag(String tag) {
        Vector<GameElement> ret = new Vector<>();
        for(GameElement e : this.elements) {
            if(e.getTag().equals(tag)) {
                ret.add(e);
            }
        }

        for(Layer layer : this.layer) {
            for(GameElement e : layer.getElements()) {
                if(e.getTag().equals(tag)) {
                    ret.add(e);
                }
            }
        }

        return ret;
    }

    public Vector<GameElement> getGameElementByClass(String className) {
        Vector<GameElement> ret = new Vector<>();
        for(GameElement e : this.elements) {
            if(e.getClass().getName().equals(className)) {
                ret.add(e);
            }
        }
        for(Layer layer : this.layer) {
            for(GameElement e : layer.getElements()) {
                if(e.getClass().getName().equals(className)) {
                    ret.add(e);
                }
            }
        }
        return ret;
    }

    public void addCamera(Camera camera){
        this.camera = camera;
        this.camera.setScene(this);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
