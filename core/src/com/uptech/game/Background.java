package com.uptech.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
   SpriteBatch batch = new SpriteBatch();
    Texture background = new Texture("background-desert.png");


    public void renderBackground(){
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth()+100, Gdx.graphics.getHeight()+100);
        batch.end();
    }

    public void dispose(){
        background.dispose();
    }
}
