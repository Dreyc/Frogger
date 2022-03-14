package gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {
    public static BufferedImage[] player;
    public static BufferedImage[] player2;
    public static BufferedImage[] tryAgain;
    public static BufferedImage[] oneTileCar;
    public static BufferedImage[] twoTileCar;
    public static BufferedImage[] threeTileCar;
    public static BufferedImage[] singlePlayer;
    public static BufferedImage[] multiplayer;
    public static BufferedImage[] endless;
    public static BufferedImage[] mainMenu;
    public static BufferedImage[] quit;
    public static BufferedImage[] numbers;
    public static BufferedImage[] twoTilesWood;
    public static BufferedImage[] threeTilesWood;
    public static BufferedImage[] tortoise;
    public static BufferedImage[] time;
    public static BufferedImage[] inGameTime;
    public static BufferedImage[] inGameScore;

    public static BufferedImage grass;
    public static BufferedImage gameOver;
    public static BufferedImage pause;
    public static BufferedImage youWin;
    public static BufferedImage pauseBg;
    public static BufferedImage title;
    public static BufferedImage yourTime;
    public static BufferedImage yourScore;
    public static BufferedImage playerOneWin;
    public static BufferedImage playerTwoWin;
    public static BufferedImage menuBg;
    public static BufferedImage loseBg;
    public static BufferedImage winBg;

    public static int buttonSize = 28;

    public static ArrayList<Integer> widths = new ArrayList<>();

    public static void init(){
        final int size = 32;

        /* Player & Environment */

        SpriteSheet playerOneSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Player_1.png"));
        SpriteSheet playerTwoSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Player_2.png"));
        SpriteSheet carOneSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Voitures_1.png"));
        SpriteSheet carTwoSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Voitures_2.png"));
        SpriteSheet carThreeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Voitures_3.png"));
        SpriteSheet woodTwoSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Rondin_2.png"));
        SpriteSheet woodThreeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Rondin_3.png"));
        SpriteSheet tortoiseSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Tortues.png"));

        player = new BufferedImage[4];
        player2 = new BufferedImage[4];
        oneTileCar = new BufferedImage[6];
        twoTileCar = new BufferedImage[6];
        threeTileCar = new BufferedImage[6];
        twoTilesWood = new BufferedImage[6];
        threeTilesWood = new BufferedImage[6];
        tortoise = new BufferedImage[6];

        grass = ImageLoader.loadImage("/textures/Grass.png");

        twoTilesWood[0] = woodTwoSheet.crop(0, 0, 2*size, size);
        twoTilesWood[1] = woodTwoSheet.crop(0, size, 2*size, size);
        twoTilesWood[2] = woodTwoSheet.crop(0, 2*size, 2*size, size);
        twoTilesWood[3] = woodTwoSheet.crop(2*size, 0, 2*size, size);
        twoTilesWood[4] = woodTwoSheet.crop(2*size, size, 2*size, size);
        twoTilesWood[5] = woodTwoSheet.crop(2*size, 2*size, 2*size, size);

        threeTilesWood[0] = woodThreeSheet.crop(0, 0, 3*size, size);
        threeTilesWood[1] = woodThreeSheet.crop(0, size, 3*size, size);
        threeTilesWood[2] = woodThreeSheet.crop(0, 2*size, 3*size, size);
        threeTilesWood[3] = woodThreeSheet.crop(3*size, 0, 3*size, size);
        threeTilesWood[4] = woodThreeSheet.crop(3*size, size, 3*size, size);
        threeTilesWood[5] = woodThreeSheet.crop(3*size, 2*size, 3*size, size);

        tortoise[0] = tortoiseSheet.crop(0, 0, size, size);
        tortoise[1] = tortoiseSheet.crop(0, size, size, size);
        tortoise[2] = tortoiseSheet.crop(0, 2*size, size, size);
        tortoise[3] = tortoiseSheet.crop(size, 0, size, size);
        tortoise[4] = tortoiseSheet.crop(size, size, size, size);
        tortoise[5] = tortoiseSheet.crop(size, 2*size, size, size);

        oneTileCar[0] = carOneSheet.crop(0, 0, size, size);
        oneTileCar[1] = carOneSheet.crop(0, size, size, size);
        oneTileCar[2] = carOneSheet.crop(0, size*2, size, size);
        oneTileCar[3] = carOneSheet.crop(size, 0, size, size);
        oneTileCar[4] = carOneSheet.crop(size, size, size, size);
        oneTileCar[5] = carOneSheet.crop(size, size*2, size, size);

        twoTileCar[0] = carTwoSheet.crop(0, 0, size*2, size);
        twoTileCar[1] = carTwoSheet.crop(0, size, size*2, size);
        twoTileCar[2] = carTwoSheet.crop(0, size*2, size*2, size);
        twoTileCar[3] = carTwoSheet.crop(size*2, 0, size*2, size);
        twoTileCar[4] = carTwoSheet.crop(size*2, size, size*2, size);
        twoTileCar[5] = carTwoSheet.crop(size*2, size*2, size*2, size);

        threeTileCar[0] = carThreeSheet.crop(0, 0, size*3, size);
        threeTileCar[1] = carThreeSheet.crop(0, size, size*3, size);
        threeTileCar[2] = carThreeSheet.crop(0, size*2, size*3, size);
        threeTileCar[3] = carThreeSheet.crop(size*3, 0, size*3, size);
        threeTileCar[4] = carThreeSheet.crop(size*3, size, size*3, size);
        threeTileCar[5] = carThreeSheet.crop(size*3, size*2, size*3, size);

        player[0] = playerOneSheet.crop(0, 0, size, size);
        player[1] = playerOneSheet.crop(0, size, size, size);
        player[2] = playerOneSheet.crop(size, 0, size, size);
        player[3] = playerOneSheet.crop(size, size, size, size);

        player2[0] = playerTwoSheet.crop(0, 0, size, size);
        player2[1] = playerTwoSheet.crop(0, size, size, size);
        player2[2] = playerTwoSheet.crop(size, 0, size, size);
        player2[3] = playerTwoSheet.crop(size, size, size, size);


        /* other graphical elements */

        SpriteSheet tryAgainSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Try_Again.png"));
        SpriteSheet singlePlayerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Single_Player.png"));
        SpriteSheet multiplayerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Multiplayer.png"));
        SpriteSheet mainMenuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Main_Menu.png"));
        SpriteSheet endlessSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Endless.png"));
        SpriteSheet quitSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Quit.png"));
        SpriteSheet numbersSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Chiffres.png"));
        SpriteSheet timeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/S_M.png"));
        SpriteSheet inGameTimeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Your_Time.png"));
        SpriteSheet inGameScoreSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Your_Score.png"));

        tryAgain = new BufferedImage[3];
        singlePlayer = new BufferedImage[3];
        multiplayer = new BufferedImage[3];
        endless = new BufferedImage[3];
        mainMenu = new BufferedImage[3];
        quit = new BufferedImage[3];
        numbers = new BufferedImage[10];
        time = new BufferedImage[2];
        inGameTime = new BufferedImage[1];
        inGameScore = new BufferedImage[1];

        gameOver = ImageLoader.loadImage("/textures/Game_Over.png");
        pause = ImageLoader.loadImage("/textures/Pause.png");
        youWin = ImageLoader.loadImage("/textures/You_Win.png");
        pauseBg = ImageLoader.loadImage("/textures/Fond_Gris.png");
        title = ImageLoader.loadImage("/textures/Frogger.png");
        yourTime = ImageLoader.loadImage("/textures/Your_Time.png");
        yourScore = ImageLoader.loadImage("/textures/Your_Score.png");
        playerOneWin = ImageLoader.loadImage("/textures/Player_1_Win.png");
        playerTwoWin = ImageLoader.loadImage("/textures/Player_2_Win.png");
        menuBg = ImageLoader.loadImage("/textures/menuBg.jpg");
        loseBg = ImageLoader.loadImage("/textures/loseBg.jpg");
        winBg = ImageLoader.loadImage("/textures/winBg.jpg");

        widths.add(tryAgainSheet.getWidth());      //0
        widths.add(singlePlayerSheet.getWidth());  //1
        widths.add(multiplayerSheet.getWidth());   //2
        widths.add(endlessSheet.getWidth());       //3
        widths.add(mainMenuSheet.getWidth());      //4
        widths.add(quitSheet.getWidth());          //5
        widths.add(gameOver.getWidth());           //6
        widths.add(pause.getWidth());              //7
        widths.add(youWin.getWidth());             //8
        widths.add(yourScore.getWidth());          //9
        widths.add(yourTime.getWidth());           //10
        widths.add(numbersSheet.getWidth()/2);     //11
        widths.add(playerOneWin.getWidth());       //12
        widths.add(playerTwoWin.getWidth());       //13
        widths.add(inGameTimeSheet.getWidth()-80); //14

        inGameTime[0] =  inGameTimeSheet.crop(80, 0, widths.get(14), 28);
        inGameScore[0] = inGameScoreSheet.crop(80, 0, widths.get(14), 28);

        tryAgain[0] = tryAgainSheet.crop(0, 0, widths.get(0), buttonSize);
        tryAgain[1] = tryAgainSheet.crop(0, buttonSize, widths.get(0), buttonSize);
        tryAgain[2] = tryAgainSheet.crop(0,buttonSize*2, widths.get(0), buttonSize);

        singlePlayer[0] = singlePlayerSheet.crop(0, 0, widths.get(1), buttonSize);
        singlePlayer[1] = singlePlayerSheet.crop(0, buttonSize, widths.get(1), buttonSize);
        singlePlayer[2] = singlePlayerSheet.crop(0, buttonSize*2, widths.get(1), buttonSize);

        multiplayer[0] = multiplayerSheet.crop(0, 0, widths.get(2), buttonSize);
        multiplayer[1] = multiplayerSheet.crop(0, buttonSize, widths.get(2), buttonSize);
        multiplayer[2] = multiplayerSheet.crop(0, buttonSize*2, widths.get(2), buttonSize);

        endless[0] = endlessSheet.crop(0, 0, widths.get(3), buttonSize);
        endless[1] = endlessSheet.crop(0, buttonSize, widths.get(3), buttonSize);
        endless[2] = endlessSheet.crop(0, buttonSize*2, widths.get(3), buttonSize);

        mainMenu[0] = mainMenuSheet.crop(0, 0, widths.get(4), buttonSize);
        mainMenu[1] = mainMenuSheet.crop(0, buttonSize, widths.get(4), buttonSize);
        mainMenu[2] = mainMenuSheet.crop(0, buttonSize*2, widths.get(4), buttonSize);

        quit[0] = quitSheet.crop(0, 0, widths.get(5), buttonSize);
        quit[1] = quitSheet.crop(0, buttonSize, widths.get(5), buttonSize);
        quit[2] = quitSheet.crop(0, buttonSize*2, widths.get(5), buttonSize);

        numbers[0] = numbersSheet.crop(0, 0, widths.get(11), buttonSize);
        numbers[1] = numbersSheet.crop(0, buttonSize, widths.get(11), buttonSize);
        numbers[2] = numbersSheet.crop(0, buttonSize*2, widths.get(11), buttonSize);
        numbers[3] = numbersSheet.crop(0, buttonSize*3, widths.get(11), buttonSize);
        numbers[4] = numbersSheet.crop(0, buttonSize*4, widths.get(11), buttonSize);
        numbers[5] = numbersSheet.crop(widths.get(11), 0, widths.get(11), buttonSize);
        numbers[6] = numbersSheet.crop(widths.get(11), buttonSize, widths.get(11), buttonSize);
        numbers[7] = numbersSheet.crop(widths.get(11), buttonSize*2, widths.get(11), buttonSize);
        numbers[8] = numbersSheet.crop(widths.get(11), buttonSize*3, widths.get(11), buttonSize);
        numbers[9] = numbersSheet.crop(widths.get(11), buttonSize*4, widths.get(11), buttonSize);

        time[0] = timeSheet.crop(0, 0, widths.get(11), buttonSize);
        time[1] = timeSheet.crop(widths.get(11), 0, widths.get(11), buttonSize);
    }
}