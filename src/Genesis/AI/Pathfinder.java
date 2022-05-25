package Genesis.AI;

import Genesis.GameElement;
import Genesis.Math.Grid;
import Genesis.Math.Rectangle;
import Genesis.Math.Vector2;
import Genesis.Scene;

/**
 * Class for pathfinding.
 */
public class Pathfinder {
    private Grid grid;

    public Pathfinder(Grid grid) {
        this.grid = grid;
    }

    /**
     *  gets the next waypoint close to the destination
     * @param start the start waypoint
     * @param destinatin the destination waypoint
     * @param offset the offset. E.g. scene offset
     * @return a Rectangle with the new coords for the location
     */
    public Rectangle getNextWaypoint(Vector2 start, Vector2 destinatin, Vector2 offset) {
        if(this.grid.contains(start, offset)) {
            if(this.grid.contains(destinatin, offset)) {
                // Get Start Rectangle with scene offset
                Rectangle current = this.grid.getRectAtPosition(start,offset);
                Rectangle destination = grid.getRectAtPosition(destinatin, offset);
                Rectangle nextRect = null;
                int nextDist = 0;
                for(Rectangle refRect : this.grid.getRectangles()) {
                    if(!refRect.isSame(current)) {
                        int startX = current.getX();
                        int startY = current.getY();
                        int refX = refRect.getX() + offset.getX();
                        int refY = refRect.getY() + offset.getY();
                        int xA = (refX - startX) / 48;
                        int yA = (refY - startY) / 48;

                        if(xA <= 1 && xA >= -1 && yA <= 1 && yA >= -1) {
                            int x = Math.abs((destination.getX() - refX) / 48);
                            int y = Math.abs((destination.getY() - refY) / 48);
                            int dist = x + y;

                            if(nextRect == null) {
                                nextDist = dist;
                                nextRect = new Rectangle(refX, refY, refRect.getWidth(), refRect.getHeight());
                            }
                            else {
                                if(dist < nextDist) {
                                    nextDist = nextDist;
                                    nextRect = new Rectangle(refX, refY, refRect.getWidth(), refRect.getHeight());
                                }
                            }
                        }
                    }
                }
                if(nextRect!= null) {
                    return nextRect;
                }
            }
        }
        return null;
    }

    /**
     * Gets the distance between start and destination in cells
     * @param start the start vector
     * @param destinatin the destination vector
     * @param offset the offset
     * @return Vector with the distance in cells
     */
    public Vector2 getDistance(Vector2 start, Vector2 destinatin, Vector2 offset) {
        if(this.grid.contains(start, offset)) {
            if(this.grid.contains(destinatin, offset)) {
                // Get Start Rectangle with scene offset
                Rectangle current = this.grid.getRectAtPosition(start, offset);
                Rectangle destination = grid.getRectAtPosition(destinatin, offset);
                int xA = (destination.getX() - current.getX()) / 48;
                int yA = (destination.getY() - current.getY()) / 48;
                return new Vector2(xA, yA);
            }
        }
        return null;
    }

}
