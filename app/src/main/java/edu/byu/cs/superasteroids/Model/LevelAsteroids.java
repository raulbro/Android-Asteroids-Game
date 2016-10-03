package edu.byu.cs.superasteroids.Model;

/**
 * Created by raulbr on 2/18/16.
 */
public class LevelAsteroids {
    private long _id;
    private int _levelId;
    private int _number;
    private int _asteroidId;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int get_levelId() {
        return _levelId;
    }

    public void set_levelId(int _levelId) {
        this._levelId = _levelId;
    }

    public int get_number() {
        return _number;
    }

    public void set_number(int _number) {
        this._number = _number;
    }

    public int get_asteroidId() {
        return _asteroidId;
    }

    public void set_asteroidId(int _asteroidId) {
        this._asteroidId = _asteroidId;
    }
}
