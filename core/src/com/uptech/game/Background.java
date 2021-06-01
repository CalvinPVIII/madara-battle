package com.uptech.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
   SpriteBatch batch = new SpriteBatch();
   Sprite background = new Sprite(new Texture("background-desert.png"));


    public void renderBackground(OrthographicCamera camera){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        background.draw(batch);
        batch.end();
    }

    public void dispose(){
        background.getTexture().dispose();
    }

    public void setSize(float width, float height){
        background.setSize(width, height);
    }

    public void setPosition(float width, float height){
        background.setSize(width, height);
    }
}
