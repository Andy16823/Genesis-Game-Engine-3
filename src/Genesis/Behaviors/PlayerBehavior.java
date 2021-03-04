package Genesis.Behaviors;

import Genesis.Game;
import Genesis.GameBehavior;
import Genesis.Graphics.Camera;
import Genesis.Input;

import java.awt.event.KeyEvent;

public class PlayerBehavior extends GameBehavior {
    private Input input;
    private boolean AutoClear = false;
    private int MoveSpeed = 5;
    private int MoveUpKey = KeyEvent.VK_W;
    private int MoveDownKey = KeyEvent.VK_S;
    private int MoveLeftKey = KeyEvent.VK_A;
    private int MoveRightKey = KeyEvent.VK_D;
    private Camera camera;
    private Direction Direction;

    public PlayerBehavior(Input input) {
        this.input = input;
    }

    public PlayerBehavior(Input input, Camera camera) {
        this.input = input;
        this.camera = camera;
    }

    public PlayerBehavior(Input input, int moveUpKey, int moveDownKey, int moveLeftKey, int moveRightKey) {
        this.input = input;
        this.MoveUpKey = moveUpKey;
        this.MoveDownKey = moveDownKey;
        this.MoveLeftKey = moveLeftKey;
        this.MoveRightKey = moveRightKey;
    }

    public PlayerBehavior(Input input, Camera camera, int moveUpKey, int moveDownKey, int moveLeftKey, int moveRightKey) {
        this.input = input;
        this.MoveUpKey = moveUpKey;
        this.MoveDownKey = moveDownKey;
        this.MoveLeftKey = moveLeftKey;
        this.MoveRightKey = moveRightKey;
        this.camera = camera;
    }

    @Override
    public void onKeyDown(KeyEvent e) {
        super.onKeyDown(e);
    }

    @Override
    public void onUpdate(Game game) {
        super.onUpdate(game);

        if(this.input.isIsInput()) {
            if(this.input.getInputKey() == MoveUpKey) {
                this.getParent().getLocation().addY(-MoveSpeed);
                this.Direction = Genesis.Behaviors.Direction.north;
                if(this.camera != null) {
                    this.camera.setY(this.camera.getY() - MoveSpeed);
                }
            }
            else if(this.input.getInputKey() == MoveDownKey) {
                this.getParent().getLocation().addY(MoveSpeed);
                this.Direction = Genesis.Behaviors.Direction.south;
                if(this.camera != null) {
                    this.camera.setY(this.camera.getY() + MoveSpeed);
                }
            }
            else if(this.input.getInputKey() == MoveLeftKey) {
                this.getParent().getLocation().addX(-MoveSpeed);
                this.Direction = Genesis.Behaviors.Direction.west;
                if(this.camera != null) {
                    this.camera.setX(this.camera.getX() - MoveSpeed);
                }
            }
            else if(this.input.getInputKey() == MoveRightKey) {
                this.getParent().getLocation().addX(MoveSpeed);
                this.Direction = Genesis.Behaviors.Direction.east;
                if(this.camera != null) {
                    this.camera.setX(this.camera.getX() + MoveSpeed);
                }
            }
            if(this.AutoClear) {
                this.input.clearInput();
            }
        }
    }

    public Genesis.Behaviors.Direction getDirection() {
        return Direction;
    }

    public void setDirection(Genesis.Behaviors.Direction direction) {
        Direction = direction;
    }
}
