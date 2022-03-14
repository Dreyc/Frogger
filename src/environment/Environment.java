package environment;

import java.awt.*;
import java.util.ArrayList;

import gameCommons.IEnvironment;
import util.Handler;
import state.PauseState;

public class Environment implements IEnvironment{
    private ArrayList<Lane> lanes;
    private Handler handler;
    private int score = 0;
    private boolean isInfinite, safety = false, isRoad = false;
    private PauseState pause;
    private long stopwatch;

    public Environment(Handler handler, boolean inf) {
        this.handler = handler;
        this.isInfinite = inf;
        this.lanes = addLanes();
        pause = new PauseState(this.handler);
        stopwatch = System.currentTimeMillis();
    }

    /* Add new lanes to fill the environment at his creation */

    private ArrayList<Lane> addLanes(){
        ArrayList<Lane> res = new ArrayList<>();
        double den;
        for(int i = handler.getRealHeight() - 2*handler.getPixelByCase(); i > 0; i-=handler.getPixelByCase()){
            if(isInfinite){
                if(i == (handler.getHeight()/2 -1)*handler.getPixelByCase() || i >= handler.getRealHeight() - 3*handler.getPixelByCase()){
                    den = 0.0D;
                    safety = true;
                    isRoad = false;
                }
                else if(i < (handler.getHeight()/2 -1)*handler.getPixelByCase()){
                    den = handler.getDefaultDensity();
                    isRoad = false;
                    safety = false;
                }
                else {
                    den = handler.getDefaultDensity();
                    isRoad = true;
                    safety = false;
                }
            }
            else {
                if(i == handler.getPixelByCase() || i == (handler.getHeight()/2)*handler.getPixelByCase() || i == handler.getRealHeight() - 2*handler.getPixelByCase()){
                    den = 0.0D;
                    safety = true;
                    isRoad = false;
                }
                else if(i < (handler.getHeight()/2)*handler.getPixelByCase()){
                    den = handler.getDefaultDensity();
                    isRoad = false;
                    safety = false;
                }
                else {
                    den = handler.getDefaultDensity();
                    isRoad = true;
                    safety = false;
                }
            }
            res.add(new Lane(this.handler, i, den, safety, isRoad));
        }
        return res;
    }

    /* Go up or down for infinite mode */

    public void goUp(){
        for (Lane l : this.lanes) {
            l.goUp();
        }
        int numberOfLane = lanes.size();
        if(lanes.get(numberOfLane - 1).ord > 0){
            double den;
            this.score++;
            if(numberOfLane%6 == 1){
                safety = true;
                den = 0.0D;
            }
            else if(numberOfLane%12 > 0 && numberOfLane%12 <= 6){
                den = handler.getDefaultDensity();
                safety = false;
                isRoad = true;
            }
            else {
                den = handler.getDefaultDensity();
                safety = false;
                isRoad = false;
            }
            this.lanes.add(new Lane(handler, handler.getPixelByCase(), den, safety, isRoad));
        }
    }

    public void goDown() {
        if (this.lanes.get(0).ord != 0) {
            for (Lane l : this.lanes) {
                l.goDown();
            }
        }
    }

    /* Update and render methods */

    public void update() {
        if(!pause.isActive()) {
            handler.getLauncher().setStopwatch((System.currentTimeMillis() - stopwatch) / 1000);
            if (isInfinite) {
                handler.getLauncher().setScore(score);
            }
            for (Lane l : lanes) {
                l.update();
            }
        }
        pause.update();
    }

    public void render(Graphics g){
        for(Lane l : lanes){
            l.render(g);
        }
    }

    public void postRender(Graphics g){
        pause.render(g);
    }




    /* GETTERS AND SETTERS */

    public int getScore() {
        return this.score;
    }

    public Lane getLane(int idx){
        return this.lanes.get(idx);
    }

    public PauseState getPause() {
        return pause;
    }
}