package frog;

import environment.Car;
import gameCommons.IEnvironment;
import gameCommons.IFrog;
import gfx.Assets;
import util.Case;
import util.Handler;
import util.Utils;

import java.awt.*;

public class Frog implements IFrog {
    private Case position;
    private int frame;
    private Handler handler;
    private IEnvironment env;
    private int fps = 7;
    private double timePerUpdate = 1000000000f / fps;
    private double delta = 0;
    private long lastTime = System.nanoTime();
    private long timer = 0;
    private boolean survive = true;
    private int life;
    private float counter = 0;

    public Frog(Handler handler) {
        this.handler = handler;
        position = new Case((handler.getWidth()/2) * handler.getPixelByCase(), handler.getRealHeight() - 2*handler.getPixelByCase());
        life = 5;
    }

    /* Update and render methods */

    public void update() {
        int idx = handler.getHeight() - (position.ord / handler.getPixelByCase()) - 2;

        long now = System.nanoTime();
        delta += (now - lastTime) / timePerUpdate;
        timer += now - lastTime;
        lastTime = now;

        if(delta >= 1) {
            if (!env.getPause().isActive()) {
                move();
            }
            delta--;
        }
        if(timer >= 1000000000) {
            timer = 0;
        }
        if (!env.getPause().isActive()) {
            if (!env.getLane(idx).isRoad() && !env.getLane(idx).isSafety()) {
                if (counter > env.getLane(idx).getSpeed()) {
                    counter = 0;
                    if (this.position.absc + handler.getPixelByCase() + 1 >= handler.getRealWidth() || position.absc <= 0) {
                        alive();
                    }
                    else {
                        if (env.getLane(idx).isLeftToRight()) {
                            this.position = new Case(position.absc + 1, position.ord);
                        } else {
                            this.position = new Case(position.absc - 1, position.ord);
                        }
                    }
                }
                counter+=0.1;
            }
        }
        survive();
    }

    public void render(Graphics g) {
        g.drawImage(Assets.player[frame], position.absc, position.ord, handler.getPixelByCase(), handler.getPixelByCase(), null);
        for(int i = 0; i < this.life; i++) {
            g.drawImage(Assets.player[0], 1+handler.getPixelByCase()*i, handler.getRealHeight() - handler.getPixelByCase(), handler.getPixelByCase(), handler.getPixelByCase(), null);
        }
        renderTime(g);
    }

    private void renderTime(Graphics g){
        int[] time = Utils.convert(handler.getLauncher().getStopwatch());
        int total = handler.getRealWidth() - (Assets.widths.get(14) + (time.length+3)*Assets.widths.get(11) - Assets.widths.get(11)/2);
        int y = 0;
        g.drawImage(Assets.inGameTime[0], total, handler.getRealHeight() - Assets.buttonSize, Assets.widths.get(14), Assets.buttonSize, null);
        for(int i : time){
            g.drawImage(Assets.numbers[i], total + Assets.widths.get(14) + (y+1)*Assets.widths.get(11), handler.getRealHeight() - Assets.buttonSize, Assets.buttonSize/2 +3, Assets.buttonSize, null);
            y++;
        }
        g.drawImage(Assets.time[0], total + Assets.widths.get(14) + (time.length+2)*Assets.widths.get(11) - Assets.widths.get(11)/2, handler.getRealHeight() - Assets.buttonSize + Assets.buttonSize/4, Assets.widths.get(11)*3/4, Assets.buttonSize*3/4, null);
    }

    /* Movement methods */

    private void move() {
        if (handler.getKeyManager().up && position.ord - 1 >= handler.getPixelByCase()) {
            frame = 0;
            if (canMove(1)) {
                position = new Case(position.absc, position.ord - handler.getPixelByCase());
            } else {
                alive();
            }
        }
        if (handler.getKeyManager().down && position.ord + handler.getPixelByCase() < handler.getRealHeight() - handler.getPixelByCase()) {
            frame = 1;
            if (canMove(2)) {
                position = new Case(position.absc, position.ord + handler.getPixelByCase());
            } else {
                alive();
            }
        }
        if (handler.getKeyManager().left && position.absc >= 0) {
            frame = 2;
            int depla = handler.getPixelByCase();
            if(position.absc - handler.getPixelByCase() < 0){
                depla = position.absc;
            }
            if (canMove(3)) {
                position = new Case(position.absc - depla, position.ord);
            } else {
                alive();
            }
        }
        if (handler.getKeyManager().right && position.absc + 2*handler.getPixelByCase() <= handler.getRealWidth()) {
            frame = 3;
            int depla = handler.getPixelByCase();
            if(position.absc + 2*handler.getPixelByCase() >= handler.getRealWidth()){
                depla = handler.getRealWidth() - (position.absc + handler.getPixelByCase());
            }
            if (canMove(4)) {
                position = new Case(position.absc + depla, position.ord);
            } else {
                alive();
            }
        }
    }

    private boolean canMove(int moveType) {
        int idx = 0, y  = 0, x = 0;
        if (moveType == 1){                                                                                //up
            idx = handler.getHeight() - (position.ord / handler.getPixelByCase()) - 1;
            x = position.absc;
            y = position.ord - handler.getPixelByCase();
        }
        else if (moveType == 2) {                                                                          //down
            idx = handler.getHeight() - (position.ord / handler.getPixelByCase()) - 3;
            x = position.absc;
            y = position.ord + handler.getPixelByCase();
        }
        else if(moveType == 3){                                                                            //left
            idx = handler.getHeight() - (position.ord / handler.getPixelByCase()) - 2;
            x = position.absc - handler.getPixelByCase();
            y = position.ord;
        }
        else if(moveType == 4){                                                                            //right
            idx = handler.getHeight() - (position.ord / handler.getPixelByCase()) - 2;
            x = position.absc + handler.getPixelByCase();
            y = position.ord;
        }
        for (Car c : env.getLane(idx).getCars()) {
            Rectangle r = new Rectangle(c.leftPosition.absc, c.leftPosition.ord, c.length * handler.getPixelByCase(), handler.getPixelByCase());
            if (r.intersects(new Rectangle(x, y, handler.getPixelByCase(), handler.getPixelByCase()))) {
                return !env.getLane(idx).isRoad() && !env.getLane(idx).isSafety();
            }
        }
        return env.getLane(idx).isRoad() || env.getLane(idx).isSafety();
    }

    /* Survivability test */

    public void survive() {
        int idx = handler.getHeight() - this.position.ord / handler.getPixelByCase() - 2;
        for (Car c : env.getLane(idx).getCars()) {
            Rectangle r = new Rectangle(c.leftPosition.absc, c.leftPosition.ord, c.length * handler.getPixelByCase(), handler.getPixelByCase());
            if (r.intersects(new Rectangle(this.position.absc, this.position.ord, handler.getPixelByCase(), handler.getPixelByCase()))) {
                if(env.getLane(idx).isRoad() || env.getLane(idx).isSafety()){
                    alive();
                }
            }
        }
    }

    /* Utils */

    public boolean win() {
        return this.position.ord == handler.getPixelByCase();
    }

    private void resetOrd(){this.position = new Case((handler.getWidth() / 2) * handler.getPixelByCase(), handler.getRealHeight() - 2*handler.getPixelByCase());}

    private void alive(){
        if(this.life == 0) {
            this.survive = false;
        } else {
            this.life--;
            resetOrd();
        }
    }




    /* GETTERS AND SETTERS */

    public void setEnv(IEnvironment env) {
        this.env = env;
    }

    public boolean getSurvive() {
        return survive;
    }
}