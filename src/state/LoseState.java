package state;

import gfx.Assets;
import ui.UiImageButton;
import ui.UiManager;
import util.Handler;
import util.Utils;

import java.awt.*;
import java.awt.event.WindowEvent;

public class LoseState extends State {
    private UiManager uiManager;

    /* Lose interface */

    LoseState(Handler handler){
        super(handler);
        uiManager = new UiManager(handler);
        handler.getMouseManager().setUiManager(this.uiManager);
        uiManager.addObject(new UiImageButton((float) (handler.getRealWidth()/2 - Assets.widths.get(0)/2),  (float) (handler.getRealHeight()*5/8), Assets.widths.get(0), Assets.buttonSize,
                Assets.tryAgain, () -> {
                    handler.getMouseManager().setUiManager(null);
                    handler.getLauncher().gameState = new GameState(handler);
                    State.setState(handler.getLauncher().gameState);
                }));
        uiManager.addObject(new UiImageButton((float) (handler.getRealWidth()/2 - Assets.widths.get(4)/2),  (float) (handler.getRealHeight()*6/8), Assets.widths.get(4), Assets.buttonSize,
                Assets.mainMenu, () -> {
                    handler.getMouseManager().setUiManager(null);
                    handler.getLauncher().menuState = new MenuState(handler);
                    State.setState(handler.getLauncher().menuState);
                }));
        uiManager.addObject(new UiImageButton((float) (handler.getRealWidth()/2 - Assets.widths.get(5)/2),  (float) (handler.getRealHeight()*7/8), Assets.widths.get(5), Assets.buttonSize,
                Assets.quit, () -> {
                    handler.getMouseManager().setUiManager(null);
                    handler.getDisplay().getFrame().dispatchEvent(new WindowEvent(handler.getDisplay().getFrame(), WindowEvent.WINDOW_CLOSING));
                }));
    }

    /* Update and render methods */

    @Override
    public void update() {
        uiManager.update();
    }

    public void render(Graphics g) {
        g.drawImage(Assets.loseBg, 0, 0, handler.getRealWidth(), handler.getRealHeight(), null);
        g.drawImage(Assets.gameOver, handler.getRealWidth()/2 - Assets.widths.get(6), Assets.buttonSize/2, Assets.widths.get(6)*2, Assets.buttonSize*2, null);
        if(handler.getLauncher().gameMode == 2){
            renderScore(g);
        }
        renderTime(g);
        uiManager.render(g);
    }

    private void renderScore(Graphics g){
        int y = 0;
        int[] score = Utils.convert(handler.getLauncher().getScore());
        int total = handler.getRealWidth()/2 - (Assets.widths.get(9) + (score.length+1)*Assets.widths.get(11))/2;
        g.drawImage(Assets.yourScore, total, handler.getRealHeight()*2/8, Assets.widths.get(9), Assets.buttonSize, null);
        for(int i : score){
            g.drawImage(Assets.numbers[i], total + Assets.widths.get(9) + (y+1)*Assets.widths.get(11), handler.getRealHeight()*2/8, Assets.buttonSize/2 +3, Assets.buttonSize, null);
            y++;
        }
    }

    private void renderTime(Graphics g){
        int[] time = Utils.convert(handler.getLauncher().getStopwatch());
        int total = handler.getRealWidth()/2 - (Assets.widths.get(10) + (time.length+1)*Assets.widths.get(11))/2;
        int y = 0;
        g.drawImage(Assets.yourTime, total, handler.getRealHeight()*3/8, Assets.widths.get(10), Assets.buttonSize, null);
        for(int i : time){
            g.drawImage(Assets.numbers[i], total + Assets.widths.get(10) + (y+1)*Assets.widths.get(11), handler.getRealHeight()*3/8, Assets.buttonSize/2 +3, Assets.buttonSize, null);
            y++;
        }
        g.drawImage(Assets.time[0], total + Assets.widths.get(10) + (time.length+2)*Assets.widths.get(11) - Assets.widths.get(11)/2, handler.getRealHeight()*3/8 + Assets.buttonSize/4, Assets.widths.get(11)*3/4, Assets.buttonSize*3/4, null);
    }
}
