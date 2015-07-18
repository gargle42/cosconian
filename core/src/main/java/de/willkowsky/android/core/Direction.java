package de.willkowsky.android.core;

/**
 * Created by weg on 18.07.15.
 */
public enum Direction {
    N, E, S, W;

    public Direction invert() {
        switch (this) {
            case N:
                return S;
            case S:
                return N;
            case W:
                return E;
            case E:
                return W;
            default:
                return N;
        }
    }
}
