package com.uptech.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Enemy {

    private SpriteBatch batch = new SpriteBatch();
    private String characterState;
    private float stateTime = 0f;
    private float currentX;
    private float currentY = 110;
    private float width;
    private float height = 120;
    private float difficultyModifier = 4 ;
    private boolean isOnLeftSide;
    private int health = 1;
    private int wavePosition;
    private EnemyWave wave;
    private float maxX;

    private Sound missSound = Gdx.audio.newSound(Gdx.files.internal("sounds/miss1.wav"));

    //    Texture Atlas Creation
    private TextureAtlas idleAtlas = new TextureAtlas("enemies/enemy1/idle/idleSpriteSheet.atlas");
    private TextureAtlas punchAtlas = new TextureAtlas("enemies/enemy1/punch/punchSpriteSheet.atlas");
    private TextureAtlas walkAtlas = new TextureAtlas("enemies/enemy1/walk/walkSpriteSheet.atlas");
    private TextureAtlas deathAtlas = new TextureAtlas("enemies/enemy1/death/deathSpriteSheet.atlas");


    //    Animation Creation
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> idleAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/3f, idleAtlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> punchAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/10f, punchAtlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> walkAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/10f, walkAtlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> deathAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/10f, deathAtlas.getRegions());

    public  Enemy(boolean isOnLeftSide, int wavePosition, EnemyWave wave, float currentX, float maxX){
        this.isOnLeftSide = isOnLeftSide;
        this.wavePosition = wavePosition;
        this.wave = wave;
        this.currentX = currentX;
        this.maxX = maxX;
        if (isOnLeftSide == false){
            width = 80;
        }
        if(isOnLeftSide == true){
            width = -80;
        }

    }

    public void Ai(float madaraX){
        if(health == 0){
            die();
        }

        if (!inPosition(madaraX) && health > 0 ) {
            move();
        }




        if (inPosition(madaraX) && health > 0){
            characterState = "idle";

        }



    }

    public void attack(){


    }

    public void move(){
        characterState = "walking";
        if (isOnLeftSide){
            currentX += 2* difficultyModifier;
        }
        if (!isOnLeftSide){
            currentX -= 2* difficultyModifier;
        }
    }

    public boolean inPosition(float madaraX){
        if (isOnLeftSide && currentX <= maxX) {
            return false;
        }else if(!isOnLeftSide && currentX >= maxX){
            return false;
        }else{
            return true;
        }

    }




    public void die(){
        characterState = "dead";
        Enemy nextEnemy;
        if (isOnLeftSide){
            if (wave.getLeftEnemies().indexOf(this) == wave.getLeftEnemies().size()-1){
                nextEnemy = null;
            }else{
                wave.getLeftEnemies().get(wavePosition + 1).setMaxX(maxX);
            }
//            currentX --;
        }else if (!isOnLeftSide){
            if(wave.getRightEnemies().indexOf(this) == wave.getRightEnemies().size()-1){
                nextEnemy = null;
            }else{
                wave.getRightEnemies().get(wavePosition +1).setMaxX(maxX);
            }

//            currentX ++;

        }
    }

    public void setMaxX(float maxX){
        this.maxX = maxX;
    }

    public float getMaxX(){return maxX;}

    public void damageTaken(){
        health --;

    }

    public int getHealth(){
        return health;
    }

    public float getX(){
        return currentX;
    }

    public String getCharacterState(){return characterState;}

    public int getWavePosition(){return wavePosition;}




    public void animations(float delta){

        batch.begin();

        if (characterState.equals("idle")){
            batch.draw(idleAnimation.getKeyFrame(delta, true), currentX, currentY, width, height);
            stateTime = 0f;
        }
        if (characterState.equals("walking")){
            batch.draw(walkAnimation.getKeyFrame(delta, true), currentX, currentY, width, height);
        }if (characterState.equals("dead")){
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(deathAnimation.getKeyFrame(stateTime, false), currentX, currentY, width, height);
        }
        if (characterState.equals("punching")){
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(punchAnimation.getKeyFrame(stateTime, false), currentX, currentY, width, height);
            if(punchAnimation.isAnimationFinished(stateTime)){
                characterState = "idle";
            }

        }
        batch.end();
    }

}
