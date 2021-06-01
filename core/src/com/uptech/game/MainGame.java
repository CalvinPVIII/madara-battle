package com.uptech.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainGame extends ApplicationAdapter {
	private Background background;
	private Madara madara;
	private float elapsedTime = 0f;
	private OrthographicCamera camera;
	final float MAX_WIDTH = 160;
	final float MAX_HEIGHT = 90;


	
	@Override
	public void create () {
		madara = new Madara();
		background = new Background();
		background.setPosition(0,0);
		background.setSize(MAX_WIDTH, MAX_HEIGHT);
		float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
		camera = new OrthographicCamera(MAX_WIDTH*aspectRatio, MAX_HEIGHT);
		camera.position.set(MAX_WIDTH/2, MAX_HEIGHT/2,0);



	}

	@Override
	public void render () {
		elapsedTime += Gdx.graphics.getDeltaTime();
		camera.update();
		background.renderBackground(camera);
		madara.animationControler();
		madara.animation(elapsedTime);


	}
	
	@Override
	public void dispose () {
		background.dispose();
		madara.dispose();
	}
}
