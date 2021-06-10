package com.uptech.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.Animation;

public class Madara {
    private SpriteBatch batch = new SpriteBatch();
    private String characterState = "idle";
    private float stateTime = 0f;
    private float currentX = 300;
    private float currentY = 100;
    private float width = 100;
    private float height = 150;
    private int closestEnemyLeftId = 0;
    private int closestEnemyRightId = 0;

//    Texture Atlas Creation
    private TextureAtlas idleAtlas = new TextureAtlas("madara/idle1sheet/idle1spritesheet.atlas");
    private TextureAtlas punchAtlas = new TextureAtlas("madara/punch1sheet/punch11spritesheet.atlas");


//    Animation Creation
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> idleAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/3f, idleAtlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> punchAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/20f, punchAtlas.getRegions());

// Sounds
    private Sound punchSound = Gdx.audio.newSound(Gdx.files.internal("sounds/punch1.wav"));
    private Sound missSound = Gdx.audio.newSound(Gdx.files.internal("sounds/miss1.wav"));


    public Madara(){
    }


    public void animationControler(EnemyWave enemies){
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)){
            characterState = "punch";
            if (width < 0 ){
                width *= -1;
            }
            currentX += 10;
            attack(true, enemies);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.O)){
            characterState = "punch";
            if (width > 0){
                width *= -1;
            }
            currentX -= 10;
            attack(false, enemies);

        }

    }

    public void attack(boolean facingRight, EnemyWave enemies){
        Enemy closestEnemy;
        if(facingRight){
            if (closestEnemyRightId <= enemies.getRightEnemies().size() -1){
                 closestEnemy = enemies.getRightEnemies().get(closestEnemyRightId);
            }else{
                 closestEnemy = null;
            }
            if(closestEnemy != null && closestEnemy.getX() - currentX <= 100){
                closestEnemy.damageTaken();
                punchSound.play();
                closestEnemyRightId ++;
            }
            if(closestEnemy == null){
                missSound.play();
            }
        }

        if(!facingRight){
            if(closestEnemyLeftId <= enemies.getLeftEnemies().size()-1){
                closestEnemy = enemies.getLeftEnemies().get(closestEnemyLeftId);
            }else{
                closestEnemy = null;
            }
            if (closestEnemy != null && currentX - closestEnemy.getX() <= 100){
                closestEnemy.damageTaken();
                punchSound.play();
                closestEnemyLeftId ++;
            }
            if (closestEnemy == null){
                missSound.play();
            }
        }



    }

    public void animation(float time){

        batch.begin();
        if(characterState.equals("idle")) {
            batch.draw(idleAnimation.getKeyFrame(time, true), currentX, currentY, width, height);
            stateTime = 0f;
        }
        if(characterState.equals("punch")){
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(punchAnimation.getKeyFrame(stateTime, false), currentX,currentY, width, height);
            if (punchAnimation.isAnimationFinished(stateTime)){
                characterState = "idle";
            }

        }

        batch.end();
    }


    public void dispose(){
        punchAtlas.dispose();
        idleAtlas.dispose();
    }

    public float getX(){
        return currentX;
    }

}
