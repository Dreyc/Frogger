package util;

import gameCommons.Launcher;
import gfx.Display;

public class Handler {
    private Launcher launcher;

    public Handler(Launcher game){ this.launcher = game; }

    public int getWidth(){ return launcher.getWidth(); }

    public int getHeight(){ return launcher.getHeight(); }

    public KeyManager getKeyManager(){ return launcher.getKeyManager(); }

    public MouseManager getMouseManager(){ return launcher.getMouseManager(); }

    public double getDefaultDensity(){return launcher.getDefaultDensity();}

    public Launcher getLauncher() {
        return launcher;
    }

    public int getPixelByCase(){return launcher.getPixelByCase();}

    public int getRealHeight(){ return this.getHeight()*this.getPixelByCase(); }

    public int getRealWidth(){ return this.getWidth()*this.getPixelByCase(); }

    public Display getDisplay(){return this.launcher.getGraphic();}

    public void setWinner(int winner){this.launcher.setWinner(winner);}

    public int getWinner(){return this.launcher.getWinner();}
}