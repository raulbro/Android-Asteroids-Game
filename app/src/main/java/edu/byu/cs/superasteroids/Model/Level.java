package edu.byu.cs.superasteroids.Model;

/**
 * Created by raulbr on 2/12/16.
 * Version 1.0
 * Will represent a level object
 */
public class Level {

    private long _id;
    private int _number;

    /** Represents the title of the current game/level */
    private String _title;

    /** Represents the hint of the current game/level */
    private String _hint;

    /** Represents the width of the current game/level */
    private int _width;

    /** Represents the height the current game/level */
    private int _height;

    /** Represents a list of asteroids on the current game/level */
    private String _music;

    /** Contructor to initialize data members */
    public Level() {

    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int get_number() {
        return _number;
    }

    public void set_number(int _number) {
        this._number = _number;
    }

    public void set_title(String _levelTitle) {
        this._title = _levelTitle;
    }

    public void set_hint(String _hint) {
        this._hint = _hint;
    }

    public int get_width() {
        return _width;
    }

    public void set_width(int _width) {
        this._width = _width;
    }

    public int get_height() {
        return _height;
    }

    public void set_height(int _height) {
        this._height = _height;
    }

    public void set_music(String _music) {
        this._music = _music;
    }
}
