package frog;

import environment.Car;
import gameCommons.IEnvironment;
import gameCommons.IFrog;
import gfx.Assets;
import util.Case;
import util.Handler;
import util.Utils;

import java.awt.*;
import java.util.ArrayList;

public class FrogMulti implements IFrog {
    private ArrayList<Case> position;
    private int frame, frame2;
    private Handler handler;
    private IEnvironment env;
    private int fps = 7;
    private double timePerUpdate = 1000000000f/fps;
    private double delta = 0;
    private long lastTime = System.nanoTime();
    private long timer = 0;
    private boolean survive = true;
    private boolean[] alive;
    public int[] life;
    private float counter = 0, counter1 = 0;

    public FrogMulti(Handler handler) {
        this.handler = handler;
        position = new ArrayList<>(2);
        position.add(new Case((handler.getWidth()*2/3) * handler.getPixelByCase(), handler.getRealHeight() - 2*handler.getPixelByCase()));
        position.add(new Case((handler.getWidth()/3) * handler.getPixelByCase(), handler.getRealHeight() - 2*handler.getPixelByCase()));
        life = new int[2];
        life[0] = 5;
        life[1] = 5;
        alive = new boolean[2];
        alive[0] = true;
        alive[1] = true;
    }

    /* Update and render methods */

    public void update(){
        int idx = handler.getHeight() - (position.get(0).ord / handler.getPixelByCase()) - 2;
        int idx1 = handler.getHeight() - (position.get(1).ord / handler.getPixelByCase()) - 2;

        long now = System.nanoTime();
        delta += (now - lastTime) / timePerUpdate;
        timer += now - lastTime;
        lastTime = now;

        if (delta >= 1) {
            if(!env.getPause().isActive()) {
                move();
                move1();
            }
            delta--;
        }
        if (timer >= 1000000000) {
            timer = 0;
        }

        if (!env.getPause().isActive()) {
            if (!env.getLane(idx).isRoad() && !env.getLane(idx).isSafety()) {
                if (counter > env.getLane(idx).getSpeed()) {
                    counter = 0;
                    if (this.position.get(0).absc + handler.getPixelByCase() + 1 >= handler.getRealWidth() || position.get(0).absc <= 0) {
                        alive(0);
                    }
                    else {
                        if (env.getLane(idx).isLeftToRight()) {
                            this.position.set(0, new Case(position.get(0).absc + 1, position.get(0).ord));
                        } else {
                            this.position.set(0, new Case(position.get(0).absc - 1, position.get(0).ord));
                        }
                    }
                }
                counter+=0.1;
            }
            else if(!env.getLane(idx1).isRoad() && !env.getLane(idx1).isSafety()){
                if(counter1 > env.getLane(idx1).getSpeed()) {
                    counter1 = 0;
                    if (this.position.get(1).absc + handler.getPixelByCase() + 1 >= handler.getRealWidth() || position.get(1).absc <= 0) {
                        alive(1);
                    }
                    else {
                        if (env.getLane(idx1).isLeftToRight()) {
                            this.position.set(1, new Case(position.get(1).absc + 1, position.get(1).ord));
                        } else {
                            this.position.set(1, new Case(position.get(1).absc - 1, position.get(1).ord));
                        }
                    }
                }
                counter1+=0.1;
            }
        }
        survive();
    }

    public void render(Graphics g){
        if(alive[0]) g.drawImage(Assets.player[frame], position.get(0).absc, position.get(0).ord, handler.getPixelByCase(), handler.getPixelByCase(), null);
        if(alive[1]) g.drawImage(Assets.player2[frame2], position.get(1).absc, position.get(1).ord, handler.getPixelByCase(), handler.getPixelByCase(), null);
        for(int i = 0; i < this.life[1]; i++) {
            g.drawImage(Assets.player2[0], 1+handler.getPixelByCase()*i, handler.getRealHeight() - handler.getPixelByCase(), handler.getPixelByCase(), handler.getPixelByCase(), null);
        }
        for(int i = 0; i < this.life[0]; i++) {
            g.drawImage(Assets.player[0], handler.getRealWidth() - (1+handler.getPixelByCase()*(i+1)), handler.getRealHeight() - handler.getPixelByCase(), handler.getPixelByCase(), handler.getPixelByCase(), null);
        }
        renderTime(g);
    }

    private void renderTime(Graphics g){
        int[] time = Utils.convert(handler.getLauncher().getStopwatch());
        int total = handler.getRealWidth()/2 - (Assets.widths.get(14)/2 + (time.length+3)*Assets.widths.get(11)/2 - Assets.widths.get(11)/2);
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
        if (handler.getKeyManager().up && position.get(0).ord - 1 >= handler.getPixelByCase() &&
                (position.get(0).ord != position.get(1).ord + handler.getPixelByCase() ||
                position.get(0).absc != position.get(1).absc)) {
            frame = 0;
            if(canMove(1, 0)){
                position.set(0, new Case(position.get(0).absc, position.get(0).ord - handler.getPixelByCase()));
            }
            else {
                alive(0);
            }
        }
        if (handler.getKeyManager().down && position.get(0).ord + handler.getPixelByCase() < handler.getRealHeight() - handler.getPixelByCase() &&
                (position.get(0).ord + handler.getPixelByCase() != position.get(1).ord ||
                position.get(0).absc != position.get(1).absc)) {
            frame = 1;
            if(canMove(2, 0)){
                position.set(0, new Case(position.get(0).absc, position.get(0).ord + handler.getPixelByCase()));
            }
            else {
                alive(0);
            }
        }
        if (handler.getKeyManager().left && position.get(0).absc >= 0 &&
                (position.get(0).absc != position.get(1).absc + handler.getPixelByCase() ||
                position.get(0).ord != position.get(1).ord)) {
            frame = 2;
            int depla = handler.getPixelByCase();
            if(position.get(0).absc - handler.getPixelByCase() < 0){
                depla = position.get(0).absc;
            }
            if(canMove(3, 0)) {
                position.set(0, new Case(position.get(0).absc - depla, position.get(0).ord));
            }
            else {
                alive(0);
            }
        }
        if (handler.getKeyManager().right && position.get(0).absc + 2*handler.getPixelByCase() <= handler.getRealWidth() &&(
                position.get(0).absc + handler.getPixelByCase() != position.get(1).absc ||
                position.get(0).ord != position.get(1).ord)) {
            frame = 3;
            int depla = handler.getPixelByCase();
            if(position.get(0).absc + 2*handler.getPixelByCase() >= handler.getRealWidth()){
                depla = handler.getRealWidth() - (position.get(0).absc + handler.getPixelByCase());
            }
            if(canMove(4, 0)) {
                position.set(0, new Case(position.get(0).absc + depla, position.get(0).ord));
            }
            else {
                alive(0);
            }
        }
    }

    private void move1() {
        if (handler.getKeyManager().z && position.get(1).ord - 1 >= handler.getPixelByCase() &&
                (position.get(1).ord != position.get(0).ord + handler.getPixelByCase() ||
                position.get(1).absc != position.get(0).absc)) {
            frame2 = 0;
            if(canMove(1, 1)){
                position.set(1, new Case(position.get(1).absc, position.get(1).ord - handler.getPixelByCase()));
            }
            else {
                alive(1);
            }
        }
        if (handler.getKeyManager().s && position.get(1).ord + handler.getPixelByCase() < handler.getRealHeight() - handler.getPixelByCase() &&
                (position.get(1).ord + handler.getPixelByCase() != position.get(0).ord ||
                position.get(1).absc != position.get(0).absc)) {
            frame2 = 1;
            if(canMove(2, 1)){
                position.set(1, new Case(position.get(1).absc, position.get(1).ord + handler.getPixelByCase()));
            }
            else {
                alive(1);
            }
        }
        if (handler.getKeyManager().q && position.get(1).absc >= 0 &&
                (position.get(1).absc != position.get(0).absc + handler.getPixelByCase() ||
                position.get(1).ord != position.get(0).ord)) {
            frame2 = 2;
            int depla = handler.getPixelByCase();
            if(position.get(1).absc - handler.getPixelByCase() < 0){
                depla = position.get(1).absc;
            }
            if(canMove(3, 1)) {
                position.set(1, new Case(position.get(1).absc - depla, position.get(1).ord));
            }
            else {
                alive(1);
            }
        }
        if (handler.getKeyManager().d && position.get(1).absc + 2*handler.getPixelByCase() <= handler.getRealWidth() &&
                (position.get(1).absc + handler.getPixelByCase() != position.get(0).absc ||
                position.get(1).ord != position.get(0).ord)) {
            frame2 = 3;
            int depla = handler.getPixelByCase();
            if(position.get(1).absc + 2*handler.getPixelByCase() >= handler.getRealWidth()){
                depla = handler.getRealWidth() - (position.get(1).absc + handler.getPixelByCase());
            }
            if(canMove(4, 1)) {
                position.set(1, new Case(position.get(1).absc + depla, position.get(1).ord));
            }
            else {
                alive(1);
            }
        }
    }

    private boolean canMove(int moveType, int frog) {
        int idx = 0, y  = 0, x = 0;
        if (moveType == 1){                                                                                //up
            idx = handler.getHeight() - (position.get(frog).ord / handler.getPixelByCase()) - 1;
            x = position.get(frog).absc;
            y = position.get(frog).ord - handler.getPixelByCase();
        }
        else if (moveType == 2) {                                                                          //down
            idx = handler.getHeight() - (position.get(frog).ord / handler.getPixelByCase()) - 3;
            x = position.get(frog).absc;
            y = position.get(frog).ord + handler.getPixelByCase();
        }
        else if(moveType == 3){                                                                            //left
            idx = handler.getHeight() - (position.get(frog).ord / handler.getPixelByCase()) - 2;
            x = position.get(frog).absc - handler.getPixelByCase();
            y = position.get(frog).ord;
        }
        else if(moveType == 4){                                                                            //right
            idx = handler.getHeight() - (position.get(frog).ord / handler.getPixelByCase()) - 2;
            x = position.get(frog).absc + handler.getPixelByCase();
            y = position.get(frog).ord;
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

    public void survive(){
        for(int i = 0; i < 2; i++) {
            int idx = handler.getHeight() - this.position.get(i).ord / handler.getPixelByCase() - 2;
            for (Car c : env.getLane(idx).getCars()) {
                Rectangle r = new Rectangle(c.leftPosition.absc, c.leftPosition.ord, c.length * handler.getPixelByCase(), handler.getPixelByCase());
                if (r.intersects(new Rectangle(this.position.get(i).absc, this.position.get(i).ord, handler.getPixelByCase(), handler.getPixelByCase()))) {
                    if(env.getLane(idx).isRoad() || env.getLane(idx).isSafety()){
                        alive(i);
                    }
                }
            }
        }
    }

    /* Utils */

    public boolean win(){
        if(this.position.get(0).ord == handler.getPixelByCase()){
            life[1] = -1;
            return true;
        }
        else if(this.position.get(1).ord == handler.getPixelByCase()){
            life[0] = -1;
            return true;
        }
        else {
            return false;
        }
    }

    public void setEnv(IEnvironment env) {
        this.env = env;
    }

    private void alive(int x){
        if(this.life[x] == 0) {
            this.alive[x] = false;
        } else {
            this.life[x]--;
            if(x == 0) resetOrd1();
            else resetOrd2();
        }
        if(!alive[0] && !alive[1]){
            survive = false;
        }
    }


    /* GETTERS AND SETTERS */

    public boolean getSurvive() {
        return survive;
    }

    private void resetOrd1(){this.position.set(0, new Case((handler.getRealWidth()*2/3) - handler.getPixelByCase(), handler.getRealHeight() - 2*handler.getPixelByCase()));}

    private void resetOrd2(){this.position.set(1, new Case((handler.getRealWidth()/3) - handler.getPixelByCase(), handler.getRealHeight() - 2*handler.getPixelByCase()));}

}
