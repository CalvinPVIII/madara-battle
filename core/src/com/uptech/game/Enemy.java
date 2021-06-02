package com.uptech.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy {

    private SpriteBatch batch = new SpriteBatch();
    private String characterState = "idle";
    private float stateTime = 0f;
    private float currentX = 500;
    private float currentY = 110;
    private float width = 80;
    private float height = 120;
    private float difficultyModifier = 4 ;
    private boolean facingRight = true;
    private int health = 1;

    //    Texture Atlas Creation
    private TextureAtlas idleAtlas = new TextureAtlas("enemies/enemy1/idle/idleSpriteSheet.atlas");
    private TextureAtlas punchAtlas = new TextureAtlas("enemies/enemy1/punch/punchSpriteSheet.atlas");
    private TextureAtlas walkAtlas = new TextureAtlas("enemies/enemy1/walk/walkSpriteSheet.atlas");
    private TextureAtlas deathAtlas = new TextureAtlas("enemies/enemy1/death/deathSpriteSheet.atlas");


    //    Animation Creation
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> idleAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/3f, idleAtlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> punchAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/10f, punchAtlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> walkAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/10f, walkAtlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> deathAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/3f, deathAtlas.getRegions());

    public void spawn(){
        batch.begin();
        stateTime+= Gdx.graphics.getDeltaTime();
        batch.draw(idleAnimation.getKeyFrame(stateTime, true), currentX, currentY, width, height);
        batch.end();
    }

    public void Ai(float madaraX){
        batch.begin();

        if(health == 0){
            characterState = "dead";
            currentX = madaraX + 150;

        }
        if (currentX >= madaraX + 100&& health > 0) {
            characterState = "walking";

        }
        if (currentX <= madaraX + 100 && health > 0){
            characterState = "idle";

        }
        batch.end();

    }


    public void animations(){
        batch.begin();

        if (characterState.equals("idle")){
            stateTime+= Gdx.graphics.getDeltaTime();
            batch.draw(idleAnimation.getKeyFrame(stateTime, true), currentX, currentY, width, height);
        }
        if (characterState.equals("walking")){
            currentX -= 2* difficultyModifier;
            stateTime+= Gdx.graphics.getDeltaTime();
            batch.draw(walkAnimation.getKeyFrame(stateTime, true), currentX, currentY, -width, height);
        }if (characterState.equals("dead")){
            stateTime+= Gdx.graphics.getDeltaTime();
            batch.draw(deathAnimation.getKeyFrame(stateTime, false), currentX, currentY, -width, height);
        }
        if (characterState.equals("punching")){

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
