package aoop.asteroids.util;

import aoop.asteroids.model.game.*;

import java.nio.ByteBuffer;

public class ByteUtil {

    private static final int SIZE_BOOLEAN = 1;

    private static final int SIZE_BYTE = 1;

    private static final int SIZE_INT = 4;

    private static final int SIZE_DOUBLE = 8;

    private static final int SIZE_GAME_OBJECT = 5 * SIZE_DOUBLE + SIZE_INT;

    private static final int SIZE_SPACESHIP = SIZE_GAME_OBJECT + SIZE_DOUBLE + SIZE_INT;

    private static final int SIZE_BULLET = SIZE_GAME_OBJECT + SIZE_INT;

    private static final int SIZE_ASTEROID = SIZE_GAME_OBJECT;

    private static final int DEFAULT_ARRAY_SIZE = 0;

    private byte[] bytes;
    private int read;
    private int write;

    public ByteUtil() {
        bytes = new byte[DEFAULT_ARRAY_SIZE];
        read = 0;
        write = 0;
    }

    public ByteUtil(byte[] bytes) {
        this.bytes = bytes;
        read = 0;
        write = bytes.length;
    }

    public void add(boolean value) {
        if (!possibleToWrite(SIZE_BOOLEAN)) increaseArraySize(SIZE_BOOLEAN);
        bytes[write] = value ? (byte) 1 : (byte) 0;
        incrementWrite(SIZE_BOOLEAN);
    }

    public void add(byte value) {
        if (!possibleToWrite(SIZE_BYTE)) increaseArraySize(SIZE_BYTE);
        bytes[write] = value;
        incrementWrite(SIZE_BYTE);
    }

    public void add(int value) {
        if (!possibleToWrite(SIZE_INT)) increaseArraySize(SIZE_INT);
        ByteBuffer.wrap(bytes).putInt(write, value);
        incrementWrite(SIZE_INT);
    }

    public void add(double value) {
        if (!possibleToWrite(SIZE_DOUBLE)) increaseArraySize(SIZE_DOUBLE);
        ByteBuffer.wrap(bytes).putDouble(write, value);
        incrementWrite(SIZE_DOUBLE);
    }

    public void add(Spaceship spaceship) {
        if (!possibleToWrite(SIZE_SPACESHIP)) increaseArraySize(SIZE_SPACESHIP);
        addGameObject(spaceship);
        add(spaceship.getID());
        add(spaceship.getDirection());
    }

    public void add(Bullet bullet) {
        if (!possibleToWrite(SIZE_BULLET)) increaseArraySize(SIZE_BULLET);
        addGameObject(bullet);
        add(bullet.getStepsLeft());
    }

    public void add(Asteroid asteroid) {
        if (!possibleToWrite(SIZE_ASTEROID)) increaseArraySize(SIZE_ASTEROID);
        addGameObject(asteroid);
    }

    public void add(Game game) {
        int as = game.getAsteroids().size();
        int bs = game.getBullets().size();
        int ss = game.getSpaceships().size();
        int totalSize = 3 * SIZE_BYTE + as * SIZE_ASTEROID + bs * SIZE_BULLET + ss * SIZE_SPACESHIP;
        if (!possibleToWrite(totalSize)) increaseArraySize(totalSize);
        add((byte) game.getSpaceships().size());
        for (Spaceship ship: game.getSpaceships()) {
            add(ship);
        }
        add((byte) game.getAsteroids().size());
        for (Asteroid asteroid: game.getAsteroids()) {
            add(asteroid);
        }
        add((byte) game.getBullets().size());
        for (Bullet bullet: game.getBullets()) {
            add(bullet);
        }
    }

    public void addGameObject(GameObject object) {
        if (!possibleToWrite(SIZE_GAME_OBJECT)) increaseArraySize(SIZE_GAME_OBJECT);
        add(object.getLocation().x);
        add(object.getLocation().y);
        add(object.getVelocity().x);
        add(object.getVelocity().y);
        add(object.getRadius());
        add(object.getStepsUntilCollisionPossible());
    }

    private boolean possibleToWrite(int size) {
        return bytes.length >= write + size;
    }

    private boolean possibleToRead(int size) {
        return write >= read + size;
    }

    public boolean getBoolean() {
        boolean b= bytes[read] == 1;
        incrementRead(SIZE_BOOLEAN);
        return b;
    }

    public byte getByte() {
        byte b = bytes[read];
        incrementRead(SIZE_BYTE);
        return b;
    }

    public int getInt() {
        int x = ByteBuffer.wrap(bytes).getInt(read);
        incrementRead(SIZE_INT);
        return x;
    }

    public double getDouble() {
        double x = ByteBuffer.wrap(bytes).getDouble(read);
        incrementRead(SIZE_DOUBLE);
        return x;
    }

    public Spaceship getSpaceship() {
        return new Spaceship(getDouble(), getDouble(), getDouble(), getDouble(), getDouble(), getInt(),
                getInt(), getDouble());
    }

    public Asteroid getAsteroid() {
        return new Asteroid(getDouble(), getDouble(), getDouble(), getDouble(), getDouble(), getInt());
    }

    public Bullet getBullet() {
        return new Bullet(getDouble(), getDouble(), getDouble(), getDouble(), getDouble(), getInt(), getInt());
    }

    private void increaseArraySize(int length) {
        byte[] newBytes = new byte[bytes.length + length];
        System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
        bytes = newBytes;
    }

    private void incrementRead(int value) {
        read += value;
    }

    private void incrementWrite(int value) {
        write += value;
    }

    public byte[] getByteArray() {
        return bytes;
    }
}
