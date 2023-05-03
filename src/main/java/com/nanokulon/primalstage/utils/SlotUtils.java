package com.nanokulon.primalstage.utils;

public abstract class SlotUtils {

    public static int getSlot(double x, double y) {
        if( x < 0 && y < 0){
            return ( x > -0.5 && y < -0.5 ) ? 3 : ( x < -0.5 && y < -0.5) ? 2 : ( x < -0.5 && y > -0.5) ? 1 : 0;
        }
        if( x > 0 && y > 0){
            return ( x > 0.5 && y < 0.5 ) ? 3 : ( x < 0.5 && y < 0.5) ? 2 : ( x < 0.5 && y > 0.5) ? 1 : 0;
        }
        if( x > 0 && y < 0){
            return ( x > 0.5 && y < -0.5 ) ? 3 : ( x < 0.5 && y < -0.5) ? 2 : ( x < 0.5 && y > -0.5) ? 1 : 0;
        }
        return ( x > -0.5 && y < 0.5 ) ? 3 : ( x < -0.5 && y < 0.5) ? 2 : ( x < -0.5 && y > 0.5) ? 1 : 0;
    }

    public static int getNorthSlot(double x, double y) {
        if( x < 0 && y < 0){
            return ( x > -0.5 && y < -0.5 ) ? 3 : ( x < -0.5 && y < -0.5) ? 0 : ( x < -0.5 && y > -0.5) ? 1 : 2;
        }
        if( x > 0 && y > 0){
            return ( x > 0.5 && y < 0.5 ) ? 3 : ( x < 0.5 && y < 0.5) ? 0 : ( x < 0.5 && y > 0.5) ? 1 : 2;
        }
        if( x > 0 && y < 0){
            return ( x > 0.5 && y < -0.5 ) ? 3 : ( x < 0.5 && y < -0.5) ? 0 : ( x < 0.5 && y > -0.5) ? 1 : 2;
        }
        return ( x > -0.5 && y < 0.5 ) ? 3 : ( x < -0.5 && y < 0.5) ? 0 : ( x < -0.5 && y > 0.5) ? 1 : 2;
    }

    public static int getSouthSlot(double x, double y) {
        if( x < 0 && y < 0){
            return ( x > -0.5 && y < -0.5 ) ? 1 : ( x < -0.5 && y < -0.5) ? 2 : ( x < -0.5 && y > -0.5) ? 3 : 0;
        }
        if( x > 0 && y > 0){
            return ( x > 0.5 && y < 0.5 ) ? 1 : ( x < 0.5 && y < 0.5) ? 2 : ( x < 0.5 && y > 0.5) ? 3 : 0;
        }
        if( x > 0 && y < 0){
            return ( x > 0.5 && y < -0.5 ) ? 1 : ( x < 0.5 && y < -0.5) ? 2 : ( x < 0.5 && y > -0.5) ? 3 : 0;
        }
        return ( x > -0.5 && y < 0.5 ) ? 1 : ( x < -0.5 && y < 0.5) ? 2 : ( x < -0.5 && y > 0.5) ? 3 : 0;
    }

    public static int getWestSlot(double z, double y) {
        if( z < 0 && y < 0){
            return ( z > -0.5 && y < -0.5 ) ? 2 : ( z < -0.5 && y < -0.5) ? 1 : ( z < -0.5 && y > -0.5) ? 0 : 3;
        }
        if( z > 0 && y > 0){
            return ( z > 0.5 && y < 0.5 ) ? 2 : ( z < 0.5 && y < 0.5) ? 1 : ( z < 0.5 && y > 0.5) ? 0 : 3;
        }
        if( z > 0 && y < 0){
            return ( z > 0.5 && y < -0.5 ) ? 2 : ( z < 0.5 && y < -0.5) ? 1 : ( z < 0.5 && y > -0.5) ? 0 : 3;
        }
        return ( z > -0.5 && y < 0.5 ) ? 2 : ( z < -0.5 && y < 0.5) ? 1 : ( z < -0.5 && y > 0.5) ? 0 : 3;
    }

    public static int getEastSlot(double z, double y) {
        if( z < 0 && y < 0){
            return ( z > -0.5 && y < -0.5 ) ? 0 : ( z < -0.5 && y < -0.5) ? 3 : ( z < -0.5 && y > -0.5) ? 2 : 1;
        }
        if( z > 0 && y > 0){
            return ( z > 0.5 && y < 0.5 ) ? 0 : ( z < 0.5 && y < 0.5) ? 3 : ( z < 0.5 && y > 0.5) ? 2 : 1;
        }
        if( z > 0 && y < 0){
            return ( z > 0.5 && y < -0.5 ) ? 0 : ( z < 0.5 && y < -0.5) ? 3 : ( z < 0.5 && y > -0.5) ? 2 : 1;
        }
        return ( z > -0.5 && y < 0.5 ) ? 0 : ( z < -0.5 && y < 0.5) ? 3 : ( z < -0.5 && y > 0.5) ? 2 : 1;
    }
}
