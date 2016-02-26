package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -80;

    private Array<Tube> tubes;
    private Bird bird;
    private Texture bgTexture;
    private Texture groundTexture;
    private Vector2 positionGround1, positionGround2;
    private BitmapFont font;
    private int count;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bgTexture = new Texture("bg.png");
        groundTexture = new Texture("ground.png");
        positionGround1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        positionGround2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + groundTexture.getWidth(), GROUND_Y_OFFSET);
        bird = new Bird(20, FlappyDemo.HEIGHT / 2);
        font = new BitmapFont();

        tubes = new Array<Tube>();

        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube((i + 1) * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handelInput() {

        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handelInput();
        bird.update(dt);

        camera.position.x = bird.getPosition().x + 80;
        camera.update();
        updateGround();
        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > (tube.getPositionTopTube().x + tube.getTopTubeTexture().getWidth())) {
                tube.reposition(tube.getPositionTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
                count++;
                bird.upSpeed();
            }
            if (tube.collides(bird.getBoundsBird())) {
                gsm.set(new GameOverState(gsm, count));
            }
        }

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(bgTexture, camera.position.x - camera.viewportWidth / 2, 0);
        batch.draw(bird.getBirdTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            batch.draw(tube.getTopTubeTexture(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            batch.draw(tube.getBottomTubeTexture(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        }

        batch.draw(groundTexture, positionGround1.x, positionGround1.y);
        batch.draw(groundTexture, positionGround2.x, positionGround2.y);

        font.draw(batch, "Count: "+ count, camera.position.x - camera.viewportWidth / 2, camera.viewportHeight);

        batch.end();
    }

    private void updateGround() {
        if (camera.position.x - camera.viewportWidth / 2 > positionGround1.x + groundTexture.getWidth()) {
            positionGround1.add(groundTexture.getWidth() * 2, 0);
        }
        if (camera.position.x - camera.viewportWidth / 2 > positionGround2.x + groundTexture.getWidth()) {
            positionGround2.add(groundTexture.getWidth() * 2, 0);
        }

    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        groundTexture.dispose();
        bird.dispose();
        font.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }

    }
}
