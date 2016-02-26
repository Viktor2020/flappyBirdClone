package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube  {

    public static final int TUBE_WIDTH = 52;
    public static final int FLUCTUATION = 130;
    public static final int TUBE_GAP = 100;
    public static final int LOWEST_OPENING = 100;


    private Texture topTubeTexture, bottomTubeTexture;
    private Vector2 positionTopTube, positionBottomTube;
    private Rectangle boundTopTube, boundsBottomTube;
    private Random random;

    public Tube(int x) {
        topTubeTexture = new Texture("toptube.png");
        bottomTubeTexture = new Texture("bottomtube.png");

        random = new Random();

        positionTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube = new Vector2(x, positionTopTube.y - TUBE_GAP - bottomTubeTexture.getHeight());

        boundTopTube = new Rectangle(positionTopTube.x, positionTopTube.y, topTubeTexture.getWidth(), topTubeTexture.getHeight());
        boundsBottomTube = new Rectangle(positionBottomTube.x, positionBottomTube.y, bottomTubeTexture.getWidth(), bottomTubeTexture.getHeight());
    }

    public void reposition(float x){
        positionTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube.set(x, positionTopTube.y - TUBE_GAP - bottomTubeTexture.getHeight());

        boundTopTube.setPosition(positionTopTube.x, positionTopTube.y);
        boundsBottomTube.setPosition(positionBottomTube.x, positionBottomTube.y);

    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsBottomTube) || player.overlaps(boundTopTube);
    }

    public Texture getTopTubeTexture() {
        return topTubeTexture;
    }

    public void setTopTubeTexture(Texture topTubeTexture) {
        this.topTubeTexture = topTubeTexture;
    }

    public Texture getBottomTubeTexture() {
        return bottomTubeTexture;
    }

    public void setBottomTubeTexture(Texture bottomTubeTexture) {
        this.bottomTubeTexture = bottomTubeTexture;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public void setPositionTopTube(Vector2 positionTopTube) {
        this.positionTopTube = positionTopTube;
    }

    public Vector2 getPositionBottomTube() {
        return positionBottomTube;
    }

    public void setPositionBottomTube(Vector2 positionBottomTube) {
        this.positionBottomTube = positionBottomTube;
    }

    public void dispose() {
        topTubeTexture.dispose();
        bottomTubeTexture.dispose();
    }
}
