/*
 * Ananya Iyer
 * Space Game
 * 05/15/2024
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Per8_Iyer_0515 extends Drawing {

    public static void main(String[] args) {
        Runner.project = new Per8_Iyer_0515();
        Runner.project.drawGrid = drawGrid;
        Runner.project.directions = directions;
        Runner.project.windowTitle = windowTitle;
        Runner.main();
    }
    //Change this to false to turn off the grid
    static boolean drawGrid = false;
    //Change the strings in the the array to display your directions for your project
    static String[] directions = {"a","b","c","d","e"};
    static String windowTitle = "Ananya Iyer - Space Game";
    //Global Variables go here
    
    //COLORS ----------------------------------------------------------------
    
    //SPACE
    Color space = new Color(5,6,51);
    
    //STARS
    Color white = new Color(255,255,255);
    
    //SPACESHIP
    Color lightGray = new Color(182,189,202);
    Color gray = new Color(123,126,134);
    Color darkishGray = new Color(121,127,136);
    Color darkGray = new Color(78,81,89);
    
    Color red = new Color(235,90,46);
    Color yellow = new Color(246,199,68);
    
    Color lightBlue = new Color(101,197,249);
    Color blue = new Color(64,144,238);
    Color darkBlue = new Color(44,103,186);
    
    //PLANETS
    
    //BLUE PLANET
    Color ocean = new Color(52,98,222);
    Color darkOcean = new Color(42,79,181);
    Color lightOcean = new Color(80,123,242);
    
    //MOON
    //USE SAME GRAYS AS SPACESHIP
    
    //MARS
    Color brightRed = new Color(206,74,74);
    Color darkRed = new Color(146,50,57);
    
    //MONSTERS
    
    //RED MONSTER
    Color monsterRed = new Color(212,46,34);
    Color yellowEye = new Color(232,230,77);
    Color blueLeg1 = new Color(0,23,227);
    
    //TEAL MONSTER
    Color monsterTeal = new Color(72,150,152);
    Color redEye = new Color(184,38,26);
    Color blueLeg2 = new Color(0,18,196); 
    
    //PURPLE MONSTER
    Color monsterPurple = new Color(147,1,209);
    
    //GAME ------------------------------------------------------------------
    String gameState = "Intro";
    
    int[] playerCoor = {395,400};
    int[] playerVel = {0,0};
    int playerSpeed = 5;
    int[] playerHitbox = {playerCoor[0]-30, playerCoor[1], 70, 85};
    
    int[] laserCoorL = {playerCoor[0]-30,playerCoor[1]+30,5,5};
    int[] laserCoorR = {playerCoor[0]+35,playerCoor[1]+30,5,5};
    int[] laserVel = {0,0};
    int laserSpeed = 9;
    
    //LEVEL 1
    int[] monsterStart1 = {200,100,5,5};
    int[] monsterEnd1 = {570,100,5,5};
    int[] monsterStart2 = {160,180,5,5};
    int[] monsterEnd2 = {610,180,5,5};
    int[] monsterStart3 = {200,260,5,5};
    int[] monsterEnd3 = {570,260,5,5};
    
    //Row 1 Walls
    int[] wall1 = {115,100,5,5};
    int[] wall2 = {655,100,5,5};
    
    //Row 2 Walls
    int[] wall3 = {75,180,5,5};
    int[] wall4 = {695,180,5,5};
    
    //Row 3 Walls
    int[] wall5 = {115,260,5,5};
    int[] wall6 = {655,260,5,5};
    
    int[][] monsterRow1 = { {220,100},
                            {300,100},
                            {380,100},
                            {460,100},
                            {540,100} };
                            
    int[][] monsterRow1Hitboxes = { {monsterRow1[0][0]-5,100,25,40},
                                    {monsterRow1[1][0]-5,100,25,40},
                                    {monsterRow1[2][0]-5,100,25,40},
                                    {monsterRow1[3][0]-5,100,25,40},
                                    {monsterRow1[4][0]-5,100,25,40} };
                            
    int[][] monsterRow2 = { {180,180},
                            {260,180},
                            {340,180},
                            {420,180},
                            {500,180},
                            {580,180} };
    
    int[][] monsterRow2Hitboxes = { {monsterRow2[0][0]-5,180,25,40},
                                    {monsterRow2[1][0]-5,180,25,40},
                                    {monsterRow2[2][0]-5,180,25,40},
                                    {monsterRow2[3][0]-5,180,25,40},
                                    {monsterRow2[4][0]-5,180,25,40},
                                    {monsterRow2[5][0]-5,180,25,40}};
                            
    int[][] monsterRow3 = { {220,260},
                            {300,260},
                            {380,260},
                            {460,260},
                            {540,260} };
                            
    int[][] monsterRow3Hitboxes = { {monsterRow3[0][0]-5,260,25,40},
                                    {monsterRow3[1][0]-5,260,25,40},
                                    {monsterRow3[2][0]-5,260,25,40},
                                    {monsterRow3[3][0]-5,260,25,40},
                                    {monsterRow3[4][0]-5,260,25,40} };
                                    
    int[] monsterStatusRow1 = {1,1,1,1,1};
    int[] monsterStatusRow2 = {1,1,1,1,1,1};
    int[] monsterStatusRow3 = {1,1,1,1,1};
                            
    int[] monsterVel = {2,0};
    int monsterSpeed = 2;
    
    int score = 0;
    
    long startTime = 0;
    int sec = 30;
    
    int winCount = 0;
    
    
    
    @Override
    public void drawPerFrame(Graphics2D g2d)
    { 
        //Drawing code goes here
        if (gameState.equals("Intro"))
        {
            drawIntroScreen(g2d);
            if (Keys.a)
            {
                gameState = "Instructions";
            }
        }
        else if (gameState.equals("Instructions"))
        {
            drawInstructions(g2d);
            //drawPlayer(g2d, playerCoor[0], playerCoor[1]);
            //updatePlayer(g2d);
            //updateLaser(g2d);
            
            if (Keys.s)
            {
                gameState = "Level1";
                startTime = System.currentTimeMillis();
            }
        }
        else if (gameState.equals("Level1"))
        {
            drawStars(g2d,180,360);
            drawStars(g2d,100,480);
            drawStars(g2d,600,420);
            drawStars(g2d,720,520);
            drawPlayer(g2d, playerCoor[0], playerCoor[1]);
            updatePlayer(g2d);
            updateLaser(g2d);
            createMonsters(g2d);
            laserHit(g2d);
            moveMonsters();
            drawScore(g2d);
            drawTime(g2d);
        }
        else if (gameState.equals("Win"))
        {
            drawWin(g2d);
            
            if (Keys.r)
            {
                resetGame();
                gameState = "Level1";
            }
        }
        else if (gameState.equals("Lose"))
        {
            drawLose(g2d);
            
            if (Keys.r)
            {
                resetGame();
                gameState = "Level1";
            }
        }
        
    }
    //Methods go HERE
    
    public void drawIntroScreen(Graphics2D g2d)
    {
        Font title = new Font("Monospaced", Font.PLAIN,50);
        Font subtitle = new Font("Monospaced", Font.PLAIN,20);
            
        g2d.setColor(Color.white);
        g2d.setFont(title);
        g2d.drawString("space game",265,300);
        
        g2d.setFont(subtitle);
        g2d.drawString("press <a> to play",300,340);
        
        drawStars(g2d,200,160);
        drawStars(g2d,680,340);
        drawStars(g2d,160,420);
        drawStars(g2d,520,100);
        drawStars(g2d,480,460);
    }
    
    public void drawInstructions(Graphics2D g2d)
    {
        Font title = new Font("Monospaced", Font.PLAIN,40);
        Font subtitle = new Font("Monospaced", Font.PLAIN,20);
            
        g2d.setColor(Color.white);
        g2d.setFont(title);
        g2d.drawString("how to play",100,70);
        
        g2d.setFont(subtitle);
        g2d.drawString("use <a>,<d>,<w>,and <s> or <left>,<right>,<up>,<down>",100,130);
        g2d.drawString("to move the player",100,160);
        
        g2d.drawString("HOLD DOWN <space> to shoot lasers",100,220);
        
        g2d.drawString("shoot lasers at the monsters to increase your score",100,280);
        
        g2d.drawString("kill all the monsters before the time runs out",100,340);
        
        g2d.drawString("The timer will reduce by 5 seconds every time you win",100,400);
        g2d.drawString("If you lose, the timer resets to 30 seconds",100,430);
        
        g2d.drawString("PRESS <S> TO START",100,490);
        
        drawMonsters(g2d,monsterRed,yellowEye,blueLeg1,120,510);
        drawMonsters(g2d,monsterTeal,redEye,blueLeg2,200,510);
        drawMonsters(g2d,monsterPurple,redEye,blueLeg2,280,510);
    }
    
    public void drawWin(Graphics2D g2d)
    {
        Font title = new Font("Monospaced", Font.PLAIN,55);
        Font subtitle = new Font("Monospaced", Font.PLAIN,25);
        
        g2d.setFont(title);
        g2d.setColor(Color.white);
        g2d.drawString("you won!",290,300);
        g2d.setFont(subtitle);
        g2d.drawString("press <r> to play again",240,360);
        drawStars(g2d,200,120);
        drawStars(g2d,620,180);
        drawStars(g2d,140,440);
        drawStars(g2d,680,460);
    }
    
    public void drawLose(Graphics2D g2d)
    {
        Font title = new Font("Monospaced", Font.PLAIN,55);
        Font subtitle = new Font("Monospaced", Font.PLAIN,25);
        
        g2d.setFont(title);
        g2d.setColor(Color.white);
        g2d.drawString("you lose",290,300);
        g2d.setFont(subtitle);
        g2d.drawString("press <r> to play again",240,360);
        sec = 30;
        
        drawMonsters(g2d,monsterRed,yellowEye,blueLeg1,160,120);
        drawMonsters(g2d,monsterTeal,redEye,blueLeg2,240,100);
        drawMonsters(g2d,monsterPurple,redEye,blueLeg2,220,180);
    
        drawMonsters(g2d,monsterPurple,redEye,blueLeg2,560,120);
        drawMonsters(g2d,monsterRed,yellowEye,blueLeg1,620,180);
        drawMonsters(g2d,monsterTeal,redEye,blueLeg2,640,100);
        
        drawMonsters(g2d,monsterPurple,redEye,blueLeg2,220,420);
        drawMonsters(g2d,monsterRed,yellowEye,blueLeg1,200,480);
        drawMonsters(g2d,monsterTeal,redEye,blueLeg2,140,440);
        
        drawMonsters(g2d,monsterPurple,redEye,blueLeg2,640,500);
        drawMonsters(g2d,monsterRed,yellowEye,blueLeg1,620,420);
        drawMonsters(g2d,monsterTeal,redEye,blueLeg2,560,480);
    }
    
    public void resetGame()
    {              
        /*
        int[] playerCoor = {395,400};
        int[] playerVel = {0,0};
        int playerSpeed = 5;
        int[] playerHitbox = {playerCoor[0]-30, playerCoor[1], 70, 85};
    
        int[] laserCoorL = {playerCoor[0]-30,playerCoor[1]+30,5,5};
        int[] laserCoorR = {playerCoor[0]+35,playerCoor[1]+30,5,5};
        int[] laserVel = {0,0};
        int laserSpeed = 9;
        
        monsterRow1 = { {220,100},
                        {300,100},
                        {380,100},
                        {460,100},
                        {540,100}};
                            
        monsterRow1Hitboxes = { {monsterRow1[0][0]-5,100,25,40},
                                {monsterRow1[1][0]-5,100,25,40},
                                {monsterRow1[2][0]-5,100,25,40},
                                {monsterRow1[3][0]-5,100,25,40},
                                {monsterRow1[4][0]-5,100,25,40} };
                                
        monsterRow2 = { {180,180},
                                {260,180},
                                {340,180},
                                {420,180},
                                {500,180},
                                {580,180} };
        
        monsterRow2Hitboxes = { {monsterRow2[0][0]-5,180,25,40},
                                        {monsterRow2[1][0]-5,180,25,40},
                                        {monsterRow2[2][0]-5,180,25,40},
                                        {monsterRow2[3][0]-5,180,25,40},
                                        {monsterRow2[4][0]-5,180,25,40},
                                        {monsterRow2[5][0]-5,180,25,40}};
                                
        monsterRow3 = { {220,260},
                                {300,260},
                                {380,260},
                                {460,260},
                                {540,260} };
                                
        monsterRow3Hitboxes = { {monsterRow3[0][0]-5,260,25,40},
                                        {monsterRow3[1][0]-5,260,25,40},
                                        {monsterRow3[2][0]-5,260,25,40},
                                        {monsterRow3[3][0]-5,260,25,40},
                                        {monsterRow3[4][0]-5,260,25,40} };
                                        
        monsterStatusRow1 = {1,1,1,1,1};
        monsterStatusRow2 = {1,1,1,1,1,1};
        monsterStatusRow3 = {1,1,1,1,1};
        */
       
        playerCoor[0] = 395;
        playerCoor[1] = 400;
        
        laserCoorL[0] = playerCoor[0]-30;
        laserCoorL[1] = playerCoor[1]+30;
        
        laserCoorR[0] = playerCoor[0]+35;
        laserCoorR[1] = playerCoor[1]+30;
        
       
        monsterRow1[0][0] = 220;
        monsterRow1[1][0] = 300;
        monsterRow1[2][0] = 380;
        monsterRow1[3][0] = 460;
        monsterRow1[4][0] = 540;
        
        monsterRow2[0][0] = 180;
        monsterRow2[1][0] = 260;
        monsterRow2[2][0] = 340;
        monsterRow2[3][0] = 420;
        monsterRow2[4][0] = 500;
        monsterRow2[5][0] = 580;
        
        monsterRow3[0][0] = 220;
        monsterRow3[1][0] = 300;
        monsterRow3[2][0] = 380;
        monsterRow3[3][0] = 460;
        monsterRow3[4][0] = 540;
        
        monsterRow1Hitboxes[0][0] = monsterRow1[0][0]-5;
        monsterRow1Hitboxes[1][0] = monsterRow1[1][0]-5;
        monsterRow1Hitboxes[2][0] = monsterRow1[2][0]-5;
        monsterRow1Hitboxes[3][0] = monsterRow1[3][0]-5;
        monsterRow1Hitboxes[4][0] = monsterRow1[4][0]-5;
        
        monsterRow2Hitboxes[0][0] = monsterRow2[0][0]-5;
        monsterRow2Hitboxes[1][0] = monsterRow2[1][0]-5;
        monsterRow2Hitboxes[2][0] = monsterRow2[2][0]-5;
        monsterRow2Hitboxes[3][0] = monsterRow2[3][0]-5;
        monsterRow2Hitboxes[4][0] = monsterRow2[4][0]-5;
        monsterRow2Hitboxes[5][0] = monsterRow2[5][0]-5;
        
        monsterRow3Hitboxes[0][0] = monsterRow3[0][0]-5;
        monsterRow3Hitboxes[1][0] = monsterRow3[1][0]-5;
        monsterRow3Hitboxes[2][0] = monsterRow3[2][0]-5;
        monsterRow3Hitboxes[3][0] = monsterRow3[3][0]-5;
        monsterRow3Hitboxes[4][0] = monsterRow3[4][0]-5;
        
        monsterStatusRow1[0] = 1;
        monsterStatusRow1[1] = 1;
        monsterStatusRow1[2] = 1;
        monsterStatusRow1[3] = 1;
        monsterStatusRow1[4] = 1;
        
        monsterStatusRow2[0] = 1;
        monsterStatusRow2[1] = 1;
        monsterStatusRow2[2] = 1;
        monsterStatusRow2[3] = 1;
        monsterStatusRow2[4] = 1;
        monsterStatusRow2[5] = 1;
        
        monsterStatusRow3[0] = 1;
        monsterStatusRow3[1] = 1;
        monsterStatusRow3[2] = 1;
        monsterStatusRow3[3] = 1;
        monsterStatusRow3[4] = 1;
        
        startTime = System.currentTimeMillis();
        
    }
    
    public void drawStars(Graphics2D g2d, int x, int y)
    {
        g2d.setColor(Color.white);
        
        Polygon star = new Polygon();
        
        star.addPoint(x,y);
        star.addPoint(x-5,y+15);
        
        star.addPoint(x-20,y+20);
        star.addPoint(x-5,y+25);
        
        star.addPoint(x,y+40);
        star.addPoint(x+5,y+25);
        
        star.addPoint(x+20,y+20);
        star.addPoint(x+5,y+15);
        
        g2d.fillPolygon(star);
    }
    
    public void drawScore(Graphics2D g2d)
    {
        Font subtitle = new Font("Monospaced", Font.PLAIN,20);
        
        score = countScore();
        
        g2d.setColor(Color.white);
        g2d.setFont(subtitle);
        g2d.drawString("score: " + score, 40,60);
        
    }
    
    public int countScore()
    {
        int count = 0;
        
        for (int i=0;i<monsterStatusRow1.length;i++)
        {
            if (monsterStatusRow1[i] == 0)
            {
                count += 1;
            }
        }
        
        for (int i=0;i<monsterStatusRow2.length;i++)
        {
            if (monsterStatusRow2[i] == 0)
            {
                count += 1;
            }
        }
        
        for (int i=0;i<monsterStatusRow3.length;i++)
        {
            if (monsterStatusRow3[i] == 0)
            {
                count += 1;
            }
        }
        
        return count;
    }
    
    public void drawTime(Graphics2D g2d)
    {
        long endTime = System.currentTimeMillis();
        long timeElapsed = (endTime - startTime)/1000;
        long time = sec-timeElapsed;
        Font subtitle = new Font("Monospaced", Font.PLAIN,20);
        
        g2d.setFont(subtitle);
        g2d.setColor(Color.white);
        
        if (time >=10)
        {
            g2d.drawString("0:" + time,700,60);
        }
        else 
        //if (time < 10 && time > 0)
        {
            g2d.drawString("0:0" + time,700,60);
        }
        
        /*
        if (time>=0 && score == 16)
        {
            gameState = "Win";
            winCount += 1;
        }
        else if (time == 0 && score<16){
            gameState = "Lose";
        }
        */
       
        
        if (time>=0 && score == 16)
        {
            gameState = "Win";
            sec -= 5;
            
            if (sec < 0)
            {
                sec = 30;
            }
        }
        else if (time<0)
        {
            gameState = "Lose";
            sec = 30;
        }
        
    }
    
    public void updatePlayer(Graphics2D g2d)
    {
        updatePlayerX(g2d);
        updatePlayerY(g2d);
    }
    
    //-----------------------------------------------------updatePlayerX()
    public void updatePlayerX(Graphics2D g2d)
    {
        if(Keys.left || Keys.a)
        {
            playerVel[0] = -playerSpeed;
            
            g2d.setColor(yellow);
            g2d.fillRect(playerCoor[0]-25,playerCoor[1]+90,5,5);
            g2d.fillRect(playerCoor[0]-25,playerCoor[1]+95,5,5);
            g2d.fillRect(playerCoor[0]+30,playerCoor[1]+90,5,5);
            g2d.fillRect(playerCoor[0]+30,playerCoor[1]+95,5,5);
        }
        else if(Keys.right || Keys.d)
        {
            playerVel[0] = playerSpeed;
            
            g2d.setColor(yellow);
            g2d.fillRect(playerCoor[0]-25,playerCoor[1]+90,5,5);
            g2d.fillRect(playerCoor[0]-25,playerCoor[1]+95,5,5);
            g2d.fillRect(playerCoor[0]+30,playerCoor[1]+90,5,5);
            g2d.fillRect(playerCoor[0]+30,playerCoor[1]+95,5,5);
        }
        else
        {
            playerVel[0] = 0;
        }
        
        playerCoor[0] += playerVel[0];
        
    }
    
    public void updatePlayerY(Graphics2D g2d)
    {
        if(Keys.up || Keys.w)
        {
            playerVel[1] = -playerSpeed;
            
            g2d.setColor(yellow);
            g2d.fillRect(playerCoor[0]-25,playerCoor[1]+90,5,5);
            g2d.fillRect(playerCoor[0]-25,playerCoor[1]+95,5,5);
            g2d.fillRect(playerCoor[0]+30,playerCoor[1]+90,5,5);
            g2d.fillRect(playerCoor[0]+30,playerCoor[1]+95,5,5);
        }
        else if(Keys.down || Keys.s)
        {
            playerVel[1] = playerSpeed;
            
            g2d.setColor(yellow);
            g2d.fillRect(playerCoor[0]-25,playerCoor[1]+90,5,5);
            g2d.fillRect(playerCoor[0]-25,playerCoor[1]+95,5,5);
            g2d.fillRect(playerCoor[0]+30,playerCoor[1]+90,5,5);
            g2d.fillRect(playerCoor[0]+30,playerCoor[1]+95,5,5);
        }
        else
        {
            playerVel[1] = 0;
        }
        
        playerCoor[1] += playerVel[1];
    }
    
        
    public void drawPlayer(Graphics2D g2d, int x, int y)
    {
        //1
        g2d.setColor(gray);
        g2d.fillRect(x,y,5,5);
        g2d.fillRect(x+5,y,5,5);
        
        //2
        g2d.setColor(lightGray);
        g2d.fillRect(x,y+5,5,5);
        g2d.fillRect(x+5,y+5,5,5);
        
        //3
        g2d.fillRect(x-5,y+10,5,5);
        g2d.fillRect(x+10,y+10,5,5);
        
        g2d.setColor(lightBlue);
        g2d.fillRect(x,y+10,5,5);
        g2d.fillRect(x+5,y+10,5,5);
        
        //4
        g2d.setColor(darkishGray);
        g2d.fillRect(x-5,y+15,5,5);
        g2d.fillRect(x+10,y+15,5,5);
        
        g2d.setColor(blue);
        g2d.fillRect(x,y+15,5,5);
        g2d.fillRect(x+5,y+15,5,5);
        
        //5
        g2d.setColor(darkishGray);
        g2d.fillRect(x-5,y+20,5,5);
        g2d.fillRect(x+10,y+20,5,5);
        
        g2d.setColor(blue);
        g2d.fillRect(x,y+20,5,5);
        g2d.fillRect(x+5,y+20,5,5);
        
        //6
        g2d.setColor(darkGray);
        g2d.fillRect(x-10,y+25,5,5);
        g2d.fillRect(x+15,y+25,5,5);
        
        g2d.setColor(darkishGray);
        g2d.fillRect(x-5,y+25,5,5);
        g2d.fillRect(x+10,y+25,5,5);
        
        g2d.setColor(darkBlue);
        g2d.fillRect(x,y+25,5,5);
        g2d.fillRect(x+5,y+25,5,5);
        
        //7
        g2d.setColor(red);
        g2d.fillRect(x-30,y+30,5,5);
        g2d.fillRect(x+35,y+30,5,5);
        
        g2d.setColor(darkishGray);
        g2d.fillRect(x-15,y+30,5,5);
        g2d.fillRect(x-5,y+30,5,5);
        g2d.fillRect(x+10,y+30,5,5);
        g2d.fillRect(x+20,y+30,5,5);
        
        g2d.setColor(darkGray);
        g2d.fillRect(x-10,y+30,5,5);
        g2d.fillRect(x,y+30,5,5);
        g2d.fillRect(x+5,y+30,5,5);
        g2d.fillRect(x+15,y+30,5,5);
        
        //8
        g2d.setColor(darkGray);
        g2d.fillRect(x-30,y+35,5,5);
        g2d.fillRect(x-10,y+35,5,5);
        g2d.fillRect(x+15,y+35,5,5);
        g2d.fillRect(x+35,y+35,5,5);
        
        g2d.setColor(darkishGray);
        g2d.fillRect(x-15,y+35,5,5);
        g2d.fillRect(x-5,y+35,5,5);
        g2d.fillRect(x+10,y+35,5,5);
        g2d.fillRect(x+20,y+35,5,5);
        
        g2d.setColor(lightGray);
        g2d.fillRect(x,y+35,5,5);
        g2d.fillRect(x+5,y+35,5,5);
        
        //9
        g2d.fillRect(x,y+40,5,5);
        g2d.fillRect(x+5,y+40,5,5);
        g2d.fillRect(x-25,y+40,5,5);
        g2d.fillRect(x+30,y+40,5,5);
        
        g2d.setColor(darkishGray);
        g2d.fillRect(x-30,y+40,5,5);
        g2d.fillRect(x-20,y+40,5,5);
        g2d.fillRect(x-15,y+40,5,5);
        g2d.fillRect(x-5,y+40,5,5);
        g2d.fillRect(x+10,y+40,5,5);
        g2d.fillRect(x+20,y+40,5,5);
        g2d.fillRect(x+25,y+40,5,5);
        g2d.fillRect(x+35,y+40,5,5);
        
        g2d.setColor(darkGray);
        g2d.fillRect(x-10,y+40,5,5);
        g2d.fillRect(x+15,y+40,5,5);
        
        //10
        g2d.fillRect(x-15,y+45,5,5);
        g2d.fillRect(x-10,y+45,5,5);
        g2d.fillRect(x+15,y+45,5,5);
        g2d.fillRect(x+20,y+45,5,5);
        
        g2d.setColor(darkishGray);
        g2d.fillRect(x-20,y+45,5,5);
        g2d.fillRect(x-5,y+45,5,5);
        g2d.fillRect(x,y+45,5,5);
        g2d.fillRect(x+5,y+45,5,5);
        g2d.fillRect(x+10,y+45,5,5);
        g2d.fillRect(x+25,y+45,5,5);
        
        g2d.setColor(lightGray);
        g2d.fillRect(x-30,y+45,5,5);
        g2d.fillRect(x-25,y+45,5,5);
        g2d.fillRect(x+30,y+45,5,5);
        g2d.fillRect(x+35,y+45,5,5);
        
        //11
        g2d.fillRect(x-30,y+50,5,5);
        g2d.fillRect(x-25,y+50,5,5);
        g2d.fillRect(x+30,y+50,5,5);
        g2d.fillRect(x+35,y+50,5,5);
        
        g2d.setColor(darkishGray);
        g2d.fillRect(x-35,y+50,5,5);
        g2d.fillRect(x-20,y+50,5,5);
        g2d.fillRect(x-5,y+50,5,5);
        g2d.fillRect(x+10,y+50,5,5);
        g2d.fillRect(x+25,y+50,5,5);
        g2d.fillRect(x+40,y+50,5,5);
        
        g2d.setColor(darkGray);
        g2d.fillRect(x-15,y+50,5,5);
        g2d.fillRect(x-10,y+50,5,5);
        g2d.fillRect(x,y+50,5,5);
        g2d.fillRect(x+5,y+50,5,5);
        g2d.fillRect(x+15,y+50,5,5);
        g2d.fillRect(x+20,y+50,5,5);
        
        //12
        g2d.fillRect(x-15,y+55,5,5);
        g2d.fillRect(x-10,y+55,5,5);
        g2d.fillRect(x+15,y+55,5,5);
        g2d.fillRect(x+20,y+55,5,5);
        
        g2d.setColor(darkishGray);
        g2d.fillRect(x-35,y+55,5,5);
        g2d.fillRect(x-25,y+55,5,5);
        g2d.fillRect(x-20,y+55,5,5);
        g2d.fillRect(x-5,y+55,5,5);
        g2d.fillRect(x+10,y+55,5,5);
        g2d.fillRect(x+25,y+55,5,5);
        g2d.fillRect(x+30,y+55,5,5);
        g2d.fillRect(x+40,y+55,5,5);
        
        g2d.setColor(lightGray);
        g2d.fillRect(x-30,y+55,5,5);
        g2d.fillRect(x+35,y+55,5,5);
        
        //13
        g2d.fillRect(x-25,y+60,5,5);
        g2d.fillRect(x+30,y+60,5,5);
        
        g2d.setColor(darkGray);
        g2d.fillRect(x-30,y+60,5,5);
        g2d.fillRect(x-10,y+60,5,5);
        g2d.fillRect(x+15,y+60,5,5);
        g2d.fillRect(x+15,y+60,5,5);
        g2d.fillRect(x+35,y+60,5,5);
        
        g2d.setColor(darkishGray);
        g2d.fillRect(x-20,y+60,5,5);
        g2d.fillRect(x-15,y+60,5,5);
        g2d.fillRect(x+20,y+60,5,5);
        g2d.fillRect(x+25,y+60,5,5);
        
        //14
        g2d.fillRect(x-35,y+65,5,5);
        g2d.fillRect(x-25,y+65,5,5);
        g2d.fillRect(x-20,y+65,5,5);
        g2d.fillRect(x-15,y+65,5,5);
        g2d.fillRect(x+20,y+65,5,5);
        g2d.fillRect(x+25,y+65,5,5);
        g2d.fillRect(x+30,y+65,5,5);
        g2d.fillRect(x+40,y+65,5,5);
        
        g2d.setColor(lightGray);
        g2d.fillRect(x-30,y+65,5,5);
        g2d.fillRect(x+35,y+65,5,5);
        
        //15
        g2d.fillRect(x-30,y+70,5,5);
        g2d.fillRect(x-25,y+70,5,5);
        g2d.fillRect(x+30,y+70,5,5);
        g2d.fillRect(x+35,y+70,5,5);
        
        g2d.setColor(darkishGray);
        g2d.fillRect(x-35,y+70,5,5);
        g2d.fillRect(x-20,y+70,5,5);
        g2d.fillRect(x+25,y+70,5,5);
        g2d.fillRect(x+40,y+70,5,5);
        
        //16
        g2d.fillRect(x-35,y+75,5,5);
        g2d.fillRect(x+40,y+75,5,5);
        
        g2d.setColor(darkGray);
        g2d.fillRect(x-25,y+75,5,5);
        g2d.fillRect(x-20,y+75,5,5);
        g2d.fillRect(x+25,y+75,5,5);
        g2d.fillRect(x+30,y+75,5,5);
        
        //17
        g2d.setColor(red);
        g2d.fillRect(x-25,y+80,5,5);
        g2d.fillRect(x-20,y+80,5,5);
        g2d.fillRect(x+25,y+80,5,5);
        g2d.fillRect(x+30,y+80,5,5);
        
        
        
    }
    
    public void drawMonsters(Graphics2D g2d, Color body, Color eye, Color legs, int x, int y)
    {
        //1
        g2d.setColor(body);
        g2d.fillRect(x,y,5,5);
        g2d.fillRect(x+10,y,5,5);
        
        //2
        g2d.fillRect(x,y+5,5,5);
        g2d.fillRect(x+10,y+5,5,5);
        
        //3
        g2d.fillRect(x-20,y+10,5,5);
        g2d.fillRect(x-5,y+10,5,5);
        g2d.fillRect(x,y+10,5,5);
        g2d.fillRect(x+5,y+10,5,5);
        g2d.fillRect(x+10,y+10,5,5);
        g2d.fillRect(x+15,y+10,5,5);
        g2d.fillRect(x+30,y+10,5,5);
        
        //4
        g2d.fillRect(x-20,y+15,5,5);
        g2d.fillRect(x-15,y+15,5,5);
        g2d.fillRect(x-10,y+15,5,5);
        g2d.fillRect(x-5,y+15,5,5);
        g2d.fillRect(x+5,y+15,5,5);
        g2d.fillRect(x+15,y+15,5,5);
        g2d.fillRect(x+20,y+15,5,5);
        g2d.fillRect(x+25,y+15,5,5);
        g2d.fillRect(x+30,y+15,5,5);
        
        g2d.setColor(eye);
        g2d.fillRect(x,y+15,5,5);
        g2d.fillRect(x+10,y+15,5,5);
        
        //5
        g2d.setColor(body);
        g2d.fillRect(x-5,y+20,5,5);
        g2d.fillRect(x,y+20,5,5);
        g2d.fillRect(x+5,y+20,5,5);
        g2d.fillRect(x+10,y+20,5,5);
        g2d.fillRect(x+15,y+20,5,5);
        
        //6
        g2d.fillRect(x,y+25,5,5);
        g2d.fillRect(x+5,y+25,5,5);
        g2d.fillRect(x+10,y+25,5,5);
        
        g2d.setColor(legs);
        g2d.fillRect(x-15,y+25,5,5);
        g2d.fillRect(x-10,y+25,5,5);
        g2d.fillRect(x-5,y+25,5,5);
        g2d.fillRect(x+15,y+25,5,5);
        g2d.fillRect(x+20,y+25,5,5);
        g2d.fillRect(x+25,y+25,5,5);
        
        //7
        g2d.fillRect(x-20,y+30,5,5);
        g2d.fillRect(x-15,y+30,5,5);
        g2d.fillRect(x-10,y+30,5,5);
        g2d.fillRect(x+20,y+30,5,5);
        g2d.fillRect(x+25,y+30,5,5);
        g2d.fillRect(x+30,y+30,5,5);
        
        g2d.setColor(body);
        g2d.fillRect(x,y+30,5,5);
        g2d.fillRect(x+5,y+30,5,5);
        g2d.fillRect(x+10,y+30,5,5);
        
        //8
        g2d.fillRect(x+5,y+35,5,5);
        
        g2d.setColor(legs);
        g2d.fillRect(x-20,y+35,5,5);
        g2d.fillRect(x-15,y+35,5,5);
        g2d.fillRect(x+25,y+35,5,5);
        g2d.fillRect(x+30,y+35,5,5);
        
    }
    
    public void updateLaserL(Graphics2D g2d) 
    {
        laserVel[1] = laserSpeed;
        laserCoorL[1] -= laserVel[1];
        laserCoorL[0] = playerCoor[0]-30;
        
        if (laserCoorL[1]+5 <= 0)
        {
            laserCoorL[1] = playerCoor[1]+30;
        }
        
        g2d.setColor(yellow);
        g2d.fillRect(laserCoorL[0], laserCoorL[1], 5,5);
        g2d.fillRect(laserCoorL[0], laserCoorL[1]+5, 5,5);
    }
    
    public void updateLaserR(Graphics2D g2d)
    {
        laserVel[1] = laserSpeed;
        laserCoorR[1] -= laserVel[1];
        laserCoorR[0] = playerCoor[0] + 35;
        
        if (laserCoorR[1]+5 <= 0)
        {
            laserCoorR[1] = playerCoor[1]+30;
        }
        
        g2d.setColor(yellow);
        g2d.fillRect(laserCoorR[0], laserCoorR[1], 5,5);
        g2d.fillRect(laserCoorR[0], laserCoorR[1]+5, 5,5);
    }
    
    public void updateLaser(Graphics2D g2d)
    {
        if (Keys.space)
        {
            updateLaserL(g2d);
            updateLaserR(g2d);
        }
    }
    
    public void createMonsters(Graphics2D g2d)
    {
        for (int i=0; i<monsterRow1.length; i++)
        {
            if (monsterStatusRow1[i] == 1)
            {
                drawMonsters(g2d,monsterRed,yellowEye,blueLeg1,monsterRow1[i][0],monsterRow1[0][1]);
            }
            else 
            {
                drawMonsters(g2d,monsterRed,yellowEye,blueLeg1,1000,monsterRow1[0][1]);
            }
        }
        
        for (int i=0; i<monsterRow2.length; i++)
        {
            if (monsterStatusRow2[i] == 1)
            {
                drawMonsters(g2d,monsterTeal,redEye,blueLeg2,monsterRow2[i][0],monsterRow2[0][1]);
            }
            else
            {
                drawMonsters(g2d,monsterTeal,redEye,blueLeg2,1000,monsterRow2[0][1]);
            }
        }
        
        for (int i=0; i<monsterRow3.length; i++)
        {
            if (monsterStatusRow3[i] == 1)
            {
                drawMonsters(g2d,monsterPurple,redEye,blueLeg2,monsterRow3[i][0],monsterRow3[0][1]);
            }
            else
            {
                drawMonsters(g2d,monsterPurple,redEye,blueLeg2,1000,monsterRow3[0][1]);
            }
        }
    }
    
    public boolean collide(int[] a, int[] b)
    {
        if (a[0] + a[2] < b[0]        ||
            a[0]        > b[0] + b[2] ||
            a[1] + a[3] < b[1]        ||
            a[1]        > b[1] + b[3]   )
        {
            return false;  
        }
        else 
        {
            return true;
        }
    }
    
    public void laserHit(Graphics2D g2d)
    {
        for (int i=0; i<monsterRow1Hitboxes.length; i++)
        {
            if (collide(monsterRow1Hitboxes[i], laserCoorL) ||
                collide(monsterRow1Hitboxes[i], laserCoorR) )
            {
                monsterStatusRow1[i] = 0;
            }
        }
        
        for (int i=0; i<monsterRow2Hitboxes.length; i++)
        {
            if (collide(monsterRow2Hitboxes[i], laserCoorL) ||
                collide(monsterRow2Hitboxes[i], laserCoorR ))
            {
                monsterStatusRow2[i] = 0;
            }
        }
        
        for (int i=0; i<monsterRow3Hitboxes.length; i++)
        {
            if (collide(monsterRow3Hitboxes[i], laserCoorL) ||
                collide(monsterRow3Hitboxes[i], laserCoorR ))
            {
                monsterStatusRow3[i] = 0;
            }
        }
        
    }
    
    public void moveMonsters1()
    {
        for (int i=0;i<monsterRow1.length;i++)
        {
            monsterRow1[i][0] += monsterVel[0];
            monsterRow1Hitboxes[i][0] = monsterRow1[i][0] - 5;
        }
        
        monsterStart1[0] += monsterVel[0];
        monsterEnd1[0] += monsterVel[0];
        
        if (collide(wall1,monsterStart1))
        {
            monsterVel[0] = monsterSpeed;
        }
        else if (collide(wall2,monsterEnd1))
        {
            monsterVel[0] = -monsterSpeed;
        }
    }
    
    public void moveMonsters2()
    {
        for (int i=0;i<monsterRow2.length;i++)
        {
            monsterRow2[i][0] += -monsterVel[0];
            monsterRow2Hitboxes[i][0] = monsterRow2[i][0] - 5;
        }
        
        monsterStart2[0] -= monsterVel[0];
        monsterEnd2[0] -= monsterVel[0];
        
        if (collide(wall3,monsterStart2))
        {
            monsterVel[0] = monsterSpeed;
        }
        else if (collide(wall4,monsterEnd2))
        {
            monsterVel[0] = -monsterSpeed;
        }
    }
    
    public void moveMonsters3()
    {
        for (int i=0;i<monsterRow3.length;i++)
        {
            monsterRow3[i][0] += monsterVel[0];
            monsterRow3Hitboxes[i][0] = monsterRow3[i][0] - 5;
        }
        
        //monsterStart3[0] += monsterVel[0];
        //monsterEnd3[0] += monsterVel[0];
        
        if (collide(wall5,monsterStart3))
        {
            monsterVel[0] = monsterSpeed;
        }
        else if (collide(wall6,monsterEnd3))
        {
            monsterVel[0] = -monsterSpeed;
        }
        
    }
    
    public void moveMonsters()
    {
        moveMonsters1();
        moveMonsters2();
        moveMonsters3();
    }
    
    public void drawWalls(Graphics2D g2d, int[] a)
    {
        g2d.setColor(Color.blue);
        g2d.fillRect(a[0],a[1],a[2],a[3]);
    }
    
}