package state;

import gfx.Assets;
import ui.*;
import util.Handler;

import java.awt.*;
import java.awt.event.WindowEvent;

public class MenuState extends State {
    private UiManager uiManager;

    /* Menu interface */

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UiManager(handler);
        handler.getMouseManager().setUiManager(this.uiManager);
        uiManager.addObject(new UiImageButton((float) (handler.getRealWidth()/2 - Assets.widths.get(1)/2),  (float) (handler.getRealHeight()*4/8 - Assets.buttonSize/2), Assets.widths.get(1), Assets.buttonSize,
                Assets.singlePlayer, () -> {
                    handler.getMouseManager().setUiManager(null);
                    handler.getLauncher().setGameMode(1);
                    handler.getLauncher().gameState = new GameState(handler);
                    State.setState(handler.getLauncher().gameState);
                }));
        uiManager.addObject(new UiImageButton((float) (handler.getRealWidth()/2 - Assets.widths.get(2)/2),  (float) (handler.getRealHeight()*5/8 - Assets.buttonSize/2), Assets.widths.get(2), Assets.buttonSize,
                Assets.multiplayer, () -> {
                    handler.getMouseManager().setUiManager(null);
                    handler.getLauncher().setGameMode(3);
                    handler.getLauncher().gameState = new GameState(handler);
                    State.setState(handler.getLauncher().gameState);
                }));
        uiManager.addObject(new UiImageButton((float) (handler.getRealWidth()/2 - Assets.widths.get(3)/2),  (float) (handler.getRealHeight()*6/8 - Assets.buttonSize/2), Assets.widths.get(3), Assets.buttonSize,
                Assets.endless, () -> {
                    handler.getMouseManager().setUiManager(null);
                    handler.getLauncher().setGameMode(2);
                    handler.getLauncher().gameState = new GameState(handler);
                    State.setState(handler.getLauncher().gameState);
                }));
        uiManager.addObject(new UiImageButton((float) (handler.getRealWidth()/2 - Assets.widths.get(5)/2),  (float) (handler.getRealHeight()*7/8 - Assets.buttonSize/2), Assets.widths.get(5), Assets.buttonSize,
                Assets.quit, () -> {
                    handler.getMouseManager().setUiManager(null);
                    handler.getDisplay().getFrame().dispatchEvent(new WindowEvent(handler.getDisplay().getFrame(), WindowEvent.WINDOW_CLOSING));
                }));
    }

    /* Update and render methods */

    @Override
    public void update() {
        uiManager.update();

        //temporaire : passe directement a la gamestate selectionnée
//        handler.getMouseManager().setUiManager(null);
//        handler.getLauncher().setGameMode(2);
//        handler.getLauncher().gameState = new GameState(handler);
//        State.setState(handler.getLauncher().gameState);
    }

    public void render(Graphics g){
        g.drawImage(Assets.menuBg, 0, 0, handler.getRealWidth(), handler.getRealHeight(), null);
        g.drawImage(Assets.title, handler.getRealWidth()/2 - 530/2, handler.getRealHeight()*2/8 - 108, 530, 108, null);
        uiManager.render(g);
    }
}