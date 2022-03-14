package gameCommons;

import java.awt.*;

public interface IFrog {
    void update();
    void render(Graphics g);
    void setEnv(IEnvironment env);
    void survive();
    boolean win();
    boolean getSurvive();
}