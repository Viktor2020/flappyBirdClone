package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.FlappyDemo;

public class Bird {

    private int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Animation birdAnimation;
    private Rectangle boundsBird;
    private Texture texture;
    private Sound sound;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        int countFrame = 3;
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), countFrame, 0.5f);
        boundsBird = new Rectangle(position.x, position.y, texture.getWidth() / countFrame, texture.getHeight());

        sound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

    }

    public void upSpeed() {
        MOVEMENT++;
    }

    public void update(float dt) {
        birdAnimation.update(dt);
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);

        position.add(MOVEMENT * dt, velocity.y, 0);

        velocity.scl(1 / dt);

        if (position.y < 0) {
            position.y = 0;
        }
        if (position.y > FlappyDemo.HEIGHT / 2) {
            position.y = FlappyDemo.HEIGHT / 2;
        }

        boundsBird.setPosition(position.x, position.y);
    }

    public Rectangle getBoundsBird() {
        return boundsBird;
    }

    public void jump() {
        velocity.y = 200;
        sound.play();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public TextureRegion getBirdTexture() {
        return birdAnimation.getFrame();
    }

    public void dispose() {
        texture.dispose();
        sound.dispose();
    }
}
