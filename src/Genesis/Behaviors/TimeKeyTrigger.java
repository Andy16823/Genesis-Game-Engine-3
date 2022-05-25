package Genesis.Behaviors;

import Genesis.Game;
import Genesis.GameBehavior;
import Genesis.GameElement;
import Genesis.Toolkit;
import com.sun.glass.events.KeyEvent;

import java.awt.*;
import java.util.Vector;

public class TimeKeyTrigger extends GameBehavior {
    private Vector<GameElement> triggerElements;
    private int keyCode = KeyEvent.VK_E;
    private int triggerTime = 5000;
    private float triggerRadius = 100f;
    private boolean isTrigger;
    private boolean drawTriggerArea = false;
    private long startTrigger = 0;
    private boolean isTriggerProgression;

    public TimeKeyTrigger() {
        this.triggerElements = new Vector<>();
    }

    public TimeKeyTrigger(GameElement element) {
        this.triggerElements = new Vector<>();
        this.triggerElements.add(element);
    }

    public Vector<GameElement> getTriggerElements() {
        return triggerElements;
    }

    public void setTriggerElements(Vector<GameElement> triggerElements) {
        this.triggerElements = triggerElements;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(int triggerTime) {
        this.triggerTime = triggerTime;
    }

    public float getTriggerRadius() {
        return triggerRadius;
    }

    public void setTriggerRadius(float triggerRadius) {
        this.triggerRadius = triggerRadius;
    }

    public boolean isTrigger() {
        if(this.isTrigger) {
            this.resetTriggerState();
            return true;
        }
        return false;
    }

    public void setTrigger(boolean trigger) {
        isTrigger = trigger;
    }

    public boolean isDrawTriggerArea() {
        return drawTriggerArea;
    }

    public void setDrawTriggerArea(boolean drawTriggerArea) {
        this.drawTriggerArea = drawTriggerArea;
    }

    private float getDistance(GameElement element) {
        float xD = element.getCenterLocation().getX() - this.getParent().getCenterLocation().getX();
        float yD = element.getCenterLocation().getY() - this.getParent().getCenterLocation().getY();

        return (float) Math.sqrt((xD * xD) + (yD * yD));
    }

    @Override
    public void bevoreRender(Graphics g) {
        super.afterRender(g);
        if(this.drawTriggerArea) {
            int x = this.getParent().getCenterLocation().getX() - (int) triggerRadius;
            int y = this.getParent().getCenterLocation().getY() - (int) triggerRadius;
            int size = (int) (triggerRadius *2);
            Color oldColor = g.getColor();
            g.setColor(Color.green);
            g.fillOval(x,y,size, size);
            g.setColor(oldColor);
        }
    }

    @Override
    public void afterRender(Graphics g) {
        super.afterRender(g);
        if(this.isTriggerProgression) {
            Graphics2D g2d = (Graphics2D) g;
            float dash1[] = {10.0f};
            BasicStroke stroke = new BasicStroke(14);
            long estTriggerTime = this.startTrigger + this.triggerTime;
            long now = System.currentTimeMillis();
            long diff = estTriggerTime - now;
            long diffP = diff * 100 / triggerTime;
            int arcStart = 360;
            long arcEnd = 360;
            long arcp = arcEnd / 100 * diffP;
            arcp = arcEnd - arcp;


            int width = 40;
            int height = 40;
            int x = (Toolkit.getScreenResulution().getX() / 2) - (width / 2);
            int y = (Toolkit.getScreenResulution().getY() / 2) - (height / 2);
            Color arcBG = Color.DARK_GRAY;
            Color oldColor = g2d.getColor();
            Stroke oldStroke = g2d.getStroke();


            //g2d.setColor(Color.LIGHT_GRAY);
            //g2d.setStroke(stroke);
            //g2d.drawArc(x,y,width,height,0,360);
            g2d.setColor(arcBG);
            g2d.setStroke(stroke);
            g2d.drawArc(x,y,width,height,arcStart +90, (int) -arcp);
            g2d.setColor(oldColor);
            g2d.setStroke(oldStroke);
        }
    }

    @Override
    public void onUpdate(Game game) {
        super.onUpdate(game);
        for(GameElement e : this.triggerElements) {
            //System.out.println("Distance to: " + e.getName() + ": " + this.getDistance(e));
            if(getDistance(e) < this.triggerRadius) {
                if(game.getInput().isKeyDown(this.keyCode)) {
                    //System.out.println("Trigger with " + e.getName());
                    if(this.startTrigger == 0) {
                        this.startTrigger = System.currentTimeMillis();
                    }
                    if(System.currentTimeMillis() > (this.startTrigger + this.triggerTime)) {
                        this.isTrigger = true;
                    }
                    this.isTriggerProgression = true;
                    return;
                }
            }
        }
        this.isTriggerProgression = false;
        this.isTrigger = false;
        this.startTrigger = 0;
    }

    public void resetTriggerState() {
        this.isTrigger = false;
        this.isTriggerProgression = false;
        this.startTrigger = 0;
    }
}
