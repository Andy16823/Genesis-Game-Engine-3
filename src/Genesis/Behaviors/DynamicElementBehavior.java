package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.GameElement;
import Genesis.Math.Rectangle;

public class DynamicElementBehavior extends GameBehavior {
    private Rectangle FieldOfView;

    public DynamicElementBehavior(Rectangle fieldOfView) {
        FieldOfView = fieldOfView;
    }

    @Override
    public void beforeUpdate(GameElement e) {
        super.beforeUpdate(e);

        // if the field of fiew contains the game element then activate it
        if(FieldOfView.contains(this.getParent().getCenterLocation())) {
            this.getParent().setEnabled(true);
        }
        else
        {
            this.getParent().setEnabled(false);
        }
    }
}
