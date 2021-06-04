package com.uptech.game;

import java.util.ArrayList;

public class EnemyWave {
    private ArrayList<Enemy> leftEnemies;
    private ArrayList<Enemy> rightEnemies;

    public EnemyWave(){
        leftEnemies = new ArrayList<Enemy>();
        rightEnemies = new ArrayList<Enemy>();
    }

    public void addLeftEnemies(int numberOfEnemies){
        for (int i = 0; i < numberOfEnemies; i++){
            Enemy enemy = new Enemy(false);
            leftEnemies.add(enemy);
        }
    }

    public void addRightEnemies(int numberOfEnemies){
        for (int i = 0; i < numberOfEnemies; i++){
            Enemy enemy = new Enemy(true);
            rightEnemies.add(enemy);
        }
    }


    public ArrayList<Enemy> getLeftEnemies(){
        return leftEnemies;
    }

    public ArrayList<Enemy> getRightEnemies(){
        return rightEnemies;
    }


    public void renderLeftEnemies(Madara madara, float elapsedTime){
        for(Enemy enemy : leftEnemies){
            enemy.Ai(madara.getX());
            enemy.animations(elapsedTime);
        }
    }
    public void renderRightEnemies(Madara madara, float elapsedTime){
        for(Enemy enemy : rightEnemies){
            enemy.Ai(madara.getX());
            enemy.animations(elapsedTime);
        }
    }
}
