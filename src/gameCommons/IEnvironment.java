package gameCommons;

import environment.Lane;
import state.PauseState;

import java.awt.*;

public interface IEnvironment {
     void update();
     void render(Graphics g);
     void goUp();
     void goDown();
     Lane getLane(int idx);
     PauseState getPause();
     void postRender(Graphics g);
}