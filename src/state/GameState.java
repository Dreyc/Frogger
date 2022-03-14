package state;

import environment.*;
import frog.*;
import gameCommons.*;
import util.Handler;

import java.awt.*;
import java.util.Random;

public class GameState extends State {
    private IFrog frog;
    private IEnvironment env;

    public static final Random randomGen = new Random();

    /* Our game */

    GameState(Handler handler){
        super(handler);

        if(handler.getLauncher().gameMode == 1){
            env = new Environment(handler, false);
            frog = new Frog(handler);
        }

        else if(handler.getLauncher().gameMode == 2){
            env = new Environment(handler, true);
            this.frog = new FrogInf(handler);
        }

        else if(handler.getLauncher().gameMode == 3){
            env = new Environment(handler, false);
            this.frog = new FrogMulti(handler);
        }
        frog.setEnv(env);
    }

    /* Update and render methods */

    @Override
    public void update() {
        env.update();
        frog.setEnv(env);
        frog.update();
        if(!frog.getSurvive()){
            handler.getLauncher().loseState = new LoseState(handler);
            State.setState(handler.getLauncher().loseState);
        }
        if(frog.win()){
            if(frog instanceof FrogMulti){
                if(((FrogMulti) frog).life[0] == -1) {
                    handler.setWinner(2);
                }
                else {
                    handler.setWinner(1);
                }
            }
            handler.getLauncher().winState = new WinState(handler);
            State.setState(handler.getLauncher().winState);
        }
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, handler.getRealWidth(), handler.getRealHeight());
        env.render(g);
        frog.render(g);
        if(env.getPause().isActive()){
            env.postRender(g);
        }
    }
}
