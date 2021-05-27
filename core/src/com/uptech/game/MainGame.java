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
	private Background background;
	private Madara madara;
	private float elapsedTime = 0f;

	
	@Override
	public void create () {
		madara = new Madara();
		background = new Background();


	}

	@Override
	public void render () {
		elapsedTime += Gdx.graphics.getDeltaTime();
		background.renderBackground();
		madara.animationControler();
		madara.animation(elapsedTime);
	}
	
	@Override
	public void dispose () {
		background.dispose();
		madara.dispose();
	}
}
