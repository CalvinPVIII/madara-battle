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

//    Texture Atlas Creation
    private TextureAtlas idleAtlas = new TextureAtlas("madara/idle1sheet/idle1spritesheet.atlas");
    private TextureAtlas punchAtlas = new TextureAtlas("madara/punch1sheet/punch11spritesheet.atlas");;


//    Animation Creation
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> idleAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/3f, idleAtlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> punchAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/10f, punchAtlas.getRegions());;


    public Madara(){
    }
    public void animationControler(){
        if (Gdx.input.isKeyPressed(Input.Keys.P)){
            characterState = "punch";
        }

    }

    public void animation(float time){
        batch.begin();
        if(characterState.equals("idle")) {
            batch.draw(idleAnimation.getKeyFrame(time, true), 100, 100, 100, 150);
        }
        if(characterState.equals("punch")){
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(punchAnimation.getKeyFrame(stateTime, false), 100,100, 100, 150);
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

}
