package environment;

import java.awt.*;
import java.awt.image.BufferedImage;

import gfx.Assets;
import state.GameState;
import util.Case;
import util.Handler;

public class Car {
    public Case leftPosition;
    private boolean leftToRight;
    public int length;
    private Handler handler;
    private BufferedImage[] sprite;
    private int color = GameState.randomGen.nextInt(2);

    Car(Handler handler, Case leftPosition, boolean leftToRight, int length, boolean isCar) {
        this.handler = handler;
        this.leftPosition = leftPosition;
        this.leftToRight = leftToRight;
        this.length = length;
        if(isCar) {
            if (length == 1) {
                sprite = Assets.oneTileCar;
            } else if (length == 2) {
                sprite = Assets.twoTileCar;
            } else if (length == 3) {
                sprite = Assets.threeTileCar;
            }
        }
        else {
            if (length == 1) {
                sprite = Assets.tortoise;
            } else if (length == 2) {
                sprite = Assets.twoTilesWood;
            } else if (length == 3) {
                sprite = Assets.threeTilesWood;
            }
        }
    }

    /* Utils */

    boolean onWindow(){
        return leftPosition.absc <= handler.getRealWidth() && leftPosition.absc + length*handler.getPixelByCase() >= 0;
    }


    /* Go up or down in infinite mode */

    void goUp(){
        this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord + handler.getPixelByCase());
    }

    void goDown(){
        this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord - handler.getPixelByCase());
    }

    /* Update and render methods */

    public void update(){
        if(leftToRight){
            this.leftPosition = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
        }
        else{
            this.leftPosition = new Case(this.leftPosition.absc - 1, this.leftPosition.ord);
        }
    }

    public void render(Graphics g){
        if(leftToRight){
            g.drawImage(sprite[color+3], this.leftPosition.absc, this.leftPosition.ord, handler.getPixelByCase()*this.length, handler.getPixelByCase(), null);
        }
        else{
            g.drawImage(sprite[color], this.leftPosition.absc, this.leftPosition.ord, handler.getPixelByCase()*this.length, handler.getPixelByCase(), null);
        }
    }
}