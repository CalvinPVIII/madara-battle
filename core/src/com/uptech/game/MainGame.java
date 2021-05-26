package com.uptech.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	TextureAtlas idleAtlas;
	TextureAtlas punchAtlas;
	com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> idleAnimation;
//	^ setting Animation to type Texture Region
	Animation punchAnimation;
	float elapsedTime = 0f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background-desert.png");
		idleAtlas = new TextureAtlas("madara/idle1sheet/idle1spritesheet.atlas");
		idleAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/3f, idleAtlas.getRegions());
	}

	@Override
	public void render () {
		elapsedTime += Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(background, 0, 0,Gdx.graphics.getWidth()+100, Gdx.graphics.getHeight()+100);
		batch.draw(idleAnimation.getKeyFrame(elapsedTime,true), 100,100, 100, 150);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
