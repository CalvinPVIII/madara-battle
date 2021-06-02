package com.uptech.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy {

    private SpriteBatch batch = new SpriteBatch();
    private String characterState = "idle";
    private float stateTime = 0f;
    private float currentX = 300;
    private float currentY = 100;
    private float width = 80;
    private float height = 120;

    //    Texture Atlas Creation
    private TextureAtlas idleAtlas = new TextureAtlas("enemies/enemy1/idle/idleSpriteSheet.atlas");
    private TextureAtlas punchAtlas = new TextureAtlas("enemies/enemy1/punch/punchSpriteSheet.atlas");
    private TextureAtlas walkAtlas = new TextureAtlas("enemies/enemy1/walk/walkSpriteSheet.atlas");;


    //    Animation Creation
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> idleAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/3f, idleAtlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> punchAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/10f, punchAtlas.getRegions());
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> walkAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/10f, walkAtlas.getRegions());;

    public void spawn(){
        batch.begin();
        stateTime+= Gdx.graphics.getDeltaTime();
        batch.draw(idleAnimation.getKeyFrame(stateTime, true), currentX, currentY, width, height);
        batch.end();
    }



}
