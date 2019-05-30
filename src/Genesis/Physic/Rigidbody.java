/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.Physic;

import Genesis.GameBehavior;

/**
 *
 * @author Andy
 */
public class Rigidbody extends GameBehavior {
    private int GravitationX;
    private int GravitationY;
    
    public Rigidbody(int GravitationX, int GravitationY)
    {
        this.GravitationX = GravitationX;
        this.GravitationY = GravitationY;
    }

    @Override
    public void ON_UPDATE() {
        super.ON_UPDATE(); 
        this.getParent().getLocation().addX(GravitationX);
        this.getParent().getLocation().addY(GravitationY);
    }
   
    
}
