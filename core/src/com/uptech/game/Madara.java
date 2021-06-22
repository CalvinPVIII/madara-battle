package com.uptech.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

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
    private TextureAtlas punch1Atlas = new TextureAtlas("madara/punch1sheet/punch1SpriteSheet.atlas");
    private TextureAtlas punch2Atlas = new TextureAtlas("madara/punch2sheet/punch2SpriteSheet.atlas");
    private TextureAtlas punch3Atlas = new TextureAtlas("madara/punch3sheet/punch3SpriteSheet.atlas");
    private TextureAtlas punch4Atlas = new TextureAtlas("madara/punch4sheet/punch4SpriteSheet.atlas");
    private TextureAtlas punch5Atlas = new TextureAtlas("madara/punch5sheet/punch5SpriteSheet.atlas");


//    Animation Creation
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> idleAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/3f, idleAtlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> punch1Animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/20f, punch1Atlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> punch2Animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/20f, punch2Atlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> punch3Animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/20f, punch3Atlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> punch4Animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/20f, punch4Atlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> punch5Animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/20f, punch5Atlas.getRegions());

    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> punchAnimations[] = new Animation[]{punch1Animation, punch2Animation, punch3Animation, punch4Animation, punch5Animation};

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
            //            need to adjust this amount
            enemies.adjustMaxX("left", 50);
            attack(true, enemies);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.O)){
            characterState = "punch";
            if (width > 0){
                width *= -1;
            }
            currentX -= 10;
//            need to adjust this amount
            enemies.adjustMaxX("right", -50);
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
//                move this eventually
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
//                move this eventually
                closestEnemyLeftId ++;
            }
            if (closestEnemy == null){
                missSound.play();
            }
        }



    }

    public void animation(float time){
        Random random = new Random();
        int randomIndex = random.nextInt(punchAnimations.length);
        batch.begin();
        if(characterState.equals("idle")) {
            batch.draw(idleAnimation.getKeyFrame(time, true), currentX, currentY, width, height);
            stateTime = 0f;
        }
        if(characterState.equals("punch")){
            stateTime += Gdx.graphics.getDeltaTime();

            com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> punchAnimation = punchAnimations[4];
            batch.draw(punchAnimation.getKeyFrame(stateTime, false), currentX,currentY, width, height);
            if (punchAnimation.isAnimationFinished(stateTime)){
                characterState = "idle";
            }

        }

        batch.end();
    }


    public void dispose(){
        punch1Atlas.dispose();
        idleAtlas.dispose();
    }

    public float getX(){
        return currentX;
    }

}
