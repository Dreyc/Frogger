package environment;

import java.awt.*;
import java.util.ArrayList;

import gfx.Assets;
import state.GameState;
import util.Case;
import util.Handler;

public class Lane {
    int ord;
    private float speed;
    private ArrayList<Car> cars;
    private boolean leftToRight;
    private double density;
    private float timer;
    private Handler handler;
    private boolean safety, isRoad;

    Lane(Handler handler, int ord, double density, boolean safety, boolean isRoad) {
        this.handler = handler;
        this.ord = ord;
        this.speed = GameState.randomGen.nextFloat()*0.5f -0.2f;
        this.leftToRight = GameState.randomGen.nextBoolean();
        this.cars = new ArrayList<>();
        timer = 0;
        this.safety = safety;
        this.isRoad = isRoad;
        this.density = density;
        for(int i = 0; i < 4*handler.getRealWidth(); i++){
            this.update();
            this.mayAddCar();
        }
        if(!isRoad) this.density+=density;
    }

    /* Go up or down in infinite mode */

    void goUp(){
        this.ord = this.ord + handler.getPixelByCase();
        for(Car c : this.cars){
            c.goUp();
        }
    }

    void goDown(){
        this.ord = this.ord - handler.getPixelByCase();
        for(Car c : this.cars){
            c.goDown();
        }
    }

    /* Car handling */

    private boolean isSafe(int length){
        if(cars.size() != 0) {
            Car c = cars.get(cars.size() - 1);
            if (leftToRight)
                return c.leftPosition.absc >= (length+1) * handler.getPixelByCase();
            else
                return c.leftPosition.absc < handler.getRealWidth() - (length+2)*handler.getPixelByCase();
        }
        return true;
    }

    private void removeCars(){
        ArrayList<Car> temp = new ArrayList<>();
        for(Car c : cars){
            if(!c.onWindow()){
                temp.add(c);
            }
        }
        for(Car c : temp){
            this.cars.remove(c);
        }
    }

    private void mayAddCar() {
        int length = GameState.randomGen.nextInt(3)+1;
        if(isSafe(length)) {
            if (GameState.randomGen.nextDouble() < density) {
                if(leftToRight)
                    cars.add(new Car(handler, new Case(0 - handler.getPixelByCase()*length, this.ord), true, length, isRoad));
                else
                    cars.add(new Car(handler, new Case(handler.getRealWidth(), this.ord), leftToRight, length, isRoad));
            }
        }
    }

    /* Update and render methods */

    public void update() {
        if(timer > this.speed){
            timer = 0;
            for(Car c: cars){
                c.update();
            }
            this.mayAddCar();
        }
        this.removeCars();
        timer+=0.1;
    }

    public void render(Graphics g){
        if(this.ord > 0 && this.ord < handler.getRealHeight() - handler.getPixelByCase()){
            for(int i = 0; i < handler.getRealWidth(); i+=handler.getPixelByCase()){
                if(isRoad) g.setColor(Color.DARK_GRAY);
                else g.setColor(Color.BLUE);
                g.fillRect(i, this.ord, handler.getPixelByCase()*2, handler.getPixelByCase());
            }
            if(safety){
                g.drawImage(Assets.grass, 0, this.ord, handler.getRealWidth(), handler.getPixelByCase(), null);
            }
            for(Car c : this.cars){
                c.render(g);
            }
        }
    }




    /* GETTERS AND SETTERS */

    public ArrayList<Car> getCars() {
        return cars;
    }

    public float getSpeed() {
        return speed;
    }

    public boolean isRoad() {
        return isRoad;
    }

    public boolean isSafety() {
        return safety;
    }

    public boolean isLeftToRight() {
        return leftToRight;
    }
}