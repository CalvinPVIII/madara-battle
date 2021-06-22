package com.uptech.game;

import java.util.ArrayList;

public class EnemyWave {
    private ArrayList<Enemy> rightEnemies;
    private ArrayList<Enemy> leftEnemies;
    private ArrayList<Enemy> deadEnemies;
    private Madara madara;

    public EnemyWave(Madara madara){
        rightEnemies = new ArrayList<Enemy>();
        leftEnemies = new ArrayList<Enemy>();
        deadEnemies = new ArrayList<Enemy>();
        this.madara = madara;
    }

    public void addRightEnemies(int numberOfEnemies){
        float startingX = 800;
        float maxX = madara.getX() + 100;
        for (int i = 0; i < numberOfEnemies; i++){
            Enemy enemy = new Enemy(false, i, this, startingX, maxX);
            rightEnemies.add(enemy);
            startingX += 100;
            maxX += 100;
        }
    }

    public void addLeftEnemies(int numberOfEnemies){
        float startingX = 0;
        float maxX = madara.getX() - 100;
        for (int i = 0; i < numberOfEnemies; i++){
            Enemy enemy = new Enemy(true, i, this, startingX, maxX);
            leftEnemies.add(enemy);
            startingX -= 100;
            maxX -= 100;
        }
    }


    public ArrayList<Enemy> getRightEnemies(){
        return rightEnemies;
    }

    public ArrayList<Enemy> getLeftEnemies(){
        return leftEnemies;
    }


    public void renderRightEnemies(Madara madara, float elapsedTime){
            for(Enemy enemy : rightEnemies){
                enemy.Ai(madara.getX());
                enemy.animations(elapsedTime);
        }
    }
    public void renderLeftEnemies(Madara madara, float elapsedTime){
        for(Enemy enemy : leftEnemies){
            enemy.Ai(madara.getX());
            enemy.animations(elapsedTime);
        }
    }

        public void adjustMaxX(String leftOrRight, float amount){
            if (leftOrRight.equals("left")){
                for (Enemy enemy : leftEnemies){
                    enemy.setMaxX(enemy.getMaxX() + amount);
                }
            }
            if (leftOrRight.equals("right")){
                for (Enemy enemy : rightEnemies){
                    enemy.setMaxX(enemy.getMaxX() + amount);
                }
            }
        }





}
