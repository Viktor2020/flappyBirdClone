package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyDemo;

public class MenuState extends State {

    private Texture bgTexture;
    private Texture btnPlayTexture;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bgTexture = new Texture("bg.png");
        btnPlayTexture = new Texture("playbtn.png");
    }

    @Override
    protected void handelInput() {

        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handelInput();

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(bgTexture, 0, 0);
        batch.draw(btnPlayTexture, camera.position.x - btnPlayTexture.getWidth() / 2, camera.position.y - btnPlayTexture.getHeight() / 2);

        batch.end();

    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        btnPlayTexture.dispose();

    }
}
