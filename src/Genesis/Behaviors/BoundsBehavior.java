package Genesis.Behaviors;

import Genesis.GameBehavior;

import java.awt.*;

public class BoundsBehavior extends GameBehavior {

    @Override
    public void afterRender(Graphics g) {
        super.afterRender(g);

        Color old = g.getColor();
        g.setColor(Color.GREEN);
        g.drawRect(this.getParent().getLocation().getX(), this.getParent().getLocation().getY(), this.getParent().getSize().getX(), this.getParent().getSize().getY());
        g.setColor(old);
    }
}
