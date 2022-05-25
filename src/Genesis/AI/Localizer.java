package Genesis.AI;

import Genesis.Game;
import Genesis.GameElement;
import Genesis.Toolkit;

public class Localizer {

    public Localizer() {

    }

    public boolean isElementNearBy(GameElement  source, GameElement target, float distance) {
        float refDist = (float) Toolkit.getDistance(source.getCenterLocation(), target.getCenterLocation());
        if(refDist < distance) {
            return true;
        }
        return false;
    }

}
