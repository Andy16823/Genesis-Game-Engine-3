package Genesis.Behaviors;

import Genesis.*;
import Genesis.GameElements.ColliderElement;
import Genesis.Graphics.SpriteSheet;
import Genesis.Math.Vector2;

import java.awt.event.KeyEvent;
import java.util.Vector;

public class SpriteMovementBehavior extends GameBehavior {
    private Game game;
    private int moveDownKey;
    private int moveUpKey;
    private int moveLeftKey;
    private int moveRightKey;
    private SpriteSheet spriteSheet;
    private int[] moveDownSprite;
    private int[] moveUpSprite;
    private int[] moveLeftSprite;
    private int[] moveRightSprite;
    private int moveSpeed;
    private int animationNorthStep = 0;
    private int animationSouthStep = 0;
    private int animationWestStep = 0;
    private int animationEastStep = 0;
    private MovementDirection movementDirection;


    public SpriteMovementBehavior(SpriteSheet spriteSheet, Game game, int[] moveDownSprite, int[] moveUpSprite, int[] moveLeftSprite, int[] moveRightSprite) {
        this.game = game;
        this.spriteSheet = spriteSheet;
        this.moveDownSprite = moveDownSprite;
        this.moveUpSprite = moveUpSprite;
        this.moveLeftSprite = moveLeftSprite;
        this.moveRightSprite = moveRightSprite;
        this.moveDownKey = KeyEvent.VK_S;
        this.moveUpKey = KeyEvent.VK_W;
        this.moveLeftKey = KeyEvent.VK_A;
        this.moveRightKey = KeyEvent.VK_D;
        this.moveSpeed = 10;
    }

    @Override
    public void onUpdate(Game game) {
        super.onUpdate(game);

    }

    public void move() {
        Sprite sprite = (Sprite) this.getParent();
        if(game.getInput().isKeyDown(this.moveDownKey)) {
            this.getParent().getLocation().addY(moveSpeed);
            sprite.defineSprite(this.moveDownSprite);
        }
        if(game.getInput().isKeyDown(this.moveUpKey)) {
            this.getParent().getLocation().addY(-moveSpeed);
            sprite.defineSprite(this.moveUpSprite);
        }
        if(game.getInput().isKeyDown(this.moveLeftKey)) {
            this.getParent().getLocation().addX(-moveSpeed);
            sprite.defineSprite(this.moveLeftSprite);
        }
        if(game.getInput().isKeyDown(this.moveRightKey)) {
            this.getParent().getLocation().addX(moveSpeed);
            sprite.defineSprite(this.moveRightSprite);
        }
    }

    /**
     * Perform movement with a collision check
     * @param checkElements
     */
    public void moveSave(Vector<GameElement> checkElements) {
        Sprite sprite = (Sprite) this.getParent();
        Vector2 movePoint = null;
        Vector2 newCord = null;

        if(game.getInput().isKeyDown(this.moveDownKey)) {
            this.movementDirection = MovementDirection.SOUTH;
            movePoint = new Vector2(this.getParent().getCenterLocation().getX(), (this.getParent().getLocation().getY() + this.getParent().getSize().getY()) + moveSpeed);
            newCord = new Vector2(this.getParent().getLocation().getX(), this.getParent().getLocation().getY() + moveSpeed);

            int mAnimationStepps = this.moveDownSprite.length / 2;
            sprite.defineSprite(this.moveDownSprite, animationSouthStep);
            animationSouthStep++;
            if(animationSouthStep >= mAnimationStepps)
            {
                animationSouthStep = 0;
            }
            animationEastStep = 0;
            animationWestStep = 0;
            animationNorthStep = 0;
        }
        if(game.getInput().isKeyDown(this.moveUpKey)) {
            this.movementDirection = MovementDirection.NORTH;
            movePoint = new Vector2(this.getParent().getCenterLocation().getX(), this.getParent().getLocation().getY() - moveSpeed);
            newCord = new Vector2(this.getParent().getLocation().getX(), this.getParent().getLocation().getY() - moveSpeed);

            int mAnimationStepps = this.moveUpSprite.length / 2;
            sprite.defineSprite(this.moveUpSprite, animationNorthStep);
            animationNorthStep++;
            if(animationNorthStep >= mAnimationStepps)
            {
                animationNorthStep = 0;
            }
            animationEastStep = 0;
            animationWestStep = 0;
            animationSouthStep = 0;
        }
        if(game.getInput().isKeyDown(this.moveLeftKey)) {
            this.movementDirection = MovementDirection.WEST;
            movePoint = new Vector2(this.getParent().getLocation().getX() - moveSpeed, this.getParent().getCenterLocation().getY());
            newCord = new Vector2(this.getParent().getLocation().getX() - moveSpeed, this.getParent().getLocation().getY());

            int mAnimationStepps = this.moveLeftSprite.length / 2;
            sprite.defineSprite(this.moveLeftSprite, animationWestStep);
            animationWestStep++;
            if(animationWestStep >= mAnimationStepps) {
                animationWestStep = 0;
            }
            animationEastStep = 0;
            animationNorthStep = 0;
            animationSouthStep = 0;
        }
        if(game.getInput().isKeyDown(this.moveRightKey)) {
            this.movementDirection = MovementDirection.EAST;
            movePoint = new Vector2((this.getParent().getLocation().getX() + this.getParent().getSize().getX()) + moveSpeed, this.getParent().getCenterLocation().getY());
            newCord = new Vector2(this.getParent().getLocation().getX() + moveSpeed, this.getParent().getLocation().getY());

            int mAnimationStepps = this.moveRightSprite.length / 2;
            sprite.defineSprite(this.moveRightSprite, animationEastStep);
            animationEastStep++;
            if(animationEastStep >= mAnimationStepps)
            {
                animationEastStep = 0;
            }
            animationWestStep = 0;
            animationNorthStep = 0;
            animationSouthStep = 0;
        }

        if(movePoint != null && newCord != null) {
            boolean canMove = true;
            for(GameElement element : checkElements) {
                ColliderElement colliderElement = (ColliderElement) element;
                if(!colliderElement.collideWithPoint(movePoint)) {

                }
                else {
                    System.out.println("Collide!");
                    canMove = false;
                    break;
                }
            }
            if(canMove) {
                this.getParent().setLocation(newCord);
            }
        }

    }

    public int getMoveDownKey() {
        return moveDownKey;
    }

    public void setMoveDownKey(int moveDownKey) {
        this.moveDownKey = moveDownKey;
    }

    public int getMoveUpKey() {
        return moveUpKey;
    }

    public void setMoveUpKey(int moveUpKey) {
        this.moveUpKey = moveUpKey;
    }

    public int getMoveLeftKey() {
        return moveLeftKey;
    }

    public void setMoveLeftKey(int moveLeftKey) {
        this.moveLeftKey = moveLeftKey;
    }

    public int getMoveRightKey() {
        return moveRightKey;
    }

    public void setMoveRightKey(int moveRightKey) {
        this.moveRightKey = moveRightKey;
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public int[] getMoveDownSprite() {
        return moveDownSprite;
    }

    public void setMoveDownSprite(int[] moveDownSprite) {
        this.moveDownSprite = moveDownSprite;
    }

    public int[] getMoveUpSprite() {
        return moveUpSprite;
    }

    public void setMoveUpSprite(int[] moveUpSprite) {
        this.moveUpSprite = moveUpSprite;
    }

    public int[] getMoveLeftSprite() {
        return moveLeftSprite;
    }

    public void setMoveLeftSprite(int[] moveLeftSprite) {
        this.moveLeftSprite = moveLeftSprite;
    }

    public int[] getMoveRightSprite() {
        return moveRightSprite;
    }

    public void setMoveRightSprite(int[] moveRightSprite) {
        this.moveRightSprite = moveRightSprite;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public MovementDirection getMovementDirection() {
        return movementDirection;
    }

    public void setMovementDirection(MovementDirection movementDirection) {
        this.movementDirection = movementDirection;
    }
}
