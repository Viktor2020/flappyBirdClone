package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyDemo;

public class GameOverState extends State {

    private Texture bgTexture;
    private Texture gameOver;
    private BitmapFont font;
    private int count;

    public GameOverState(GameStateManager gsm, int count) {
        super(gsm);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bgTexture = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
        this.count = count;
        font = new BitmapFont();
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
        batch.draw(gameOver, camera.position.x - gameOver.getWidth() / 2, camera.position.y - gameOver.getHeight() / 2);

        font.draw(batch, "Count: "+ count, camera.position.x - 20, camera.position.y + 50);
        batch.end();

    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        gameOver.dispose();
        font.dispose();
    }
}
