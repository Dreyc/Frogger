package state;

import gfx.Assets;
import ui.UiImageButton;
import ui.UiManager;
import util.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class PauseState {
    private boolean active = false;
    private Handler handler;
    private UiManager uiManager;


    /*Pause interface */

    public PauseState(Handler handler){
        this.handler = handler;
        this.uiManager = new UiManager(handler);
        handler.getMouseManager().setUiManager(this.uiManager);
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

    public void update() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE))
            active = !active;
        if(!active) {
            return;
        }
        uiManager.update();
    }

    public void render(Graphics g){
        if(!active)
            return;
        g.drawImage(Assets.pauseBg, 0, 0, handler.getRealWidth(), handler.getRealHeight(),null);
        g.drawImage(Assets.pause, handler.getRealWidth()/2 - Assets.widths.get(7), handler.getRealHeight()/2 - Assets.buttonSize, Assets.widths.get(7)*2, Assets.buttonSize*2, null);
        uiManager.render(g);
    }



    /* GETTERS AND SETTERS */

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }
}