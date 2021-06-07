package com.uptech.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
    private boolean facingRight;
    private int health = 1;

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

    public  Enemy(boolean facingRight){
        this.facingRight = facingRight;
        if (facingRight == false){
            currentX = 500;
            width = 80;
        }
        if(facingRight == true){
            currentX = 0;
            width = -80;
        }

    }

    public void Ai(float madaraX){


        if(health == 0){
            characterState = "dead";
            if (facingRight){
                currentX --;
            }else if (facingRight == false){
                currentX ++;
            }


        }
        if (!attackInRange(madaraX) && health > 0 && facingRight == false) {
            characterState = "walking";
            currentX -= 2* difficultyModifier;

        }
        if (!attackInRange(madaraX) && health > 0 && facingRight){
            characterState = "walking";
            currentX += 2* difficultyModifier;
        }


        if (attackInRange(madaraX) && health > 0){



        }



    }

    public void attack(){

    }

    public boolean attackInRange(float madaraX){
        if (facingRight && currentX >= madaraX - 50){
            return true;
        }
        if (!facingRight && currentX <= madaraX + 100){
            return true;
        }
        return false;
    }


    public void animations(float delta){

        batch.begin();

        if (characterState.equals("idle")){
            batch.draw(idleAnimation.getKeyFrame(delta, true), currentX, currentY, width, height);
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

        }
        batch.end();
    }

    public void die(){

    }

    public void damageTaken(){
        health --;

    }

    public int getHealth(){
        return health;
    }

    public float getX(){
        return currentX;
    }



}
