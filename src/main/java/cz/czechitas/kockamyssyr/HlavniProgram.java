package cz.czechitas.kockamyssyr;

import cz.czechitas.kockamyssyr.api.*;

import java.awt.*;
import java.util.Random;

/**
 * Hlaví třída pro hru Kočka–myš–sýr.
 */
public class HlavniProgram {
    private final Random random = new Random();

    private final int VELIKOST_PRVKU = 50;
    private final int SIRKA_OKNA = 1000 - VELIKOST_PRVKU;
    private final int VYSKA_OKNA = 600 - VELIKOST_PRVKU;

    private Cat tom;
    private Mouse jerry;

    /**
     * Spouštěcí metoda celé aplikace.
     *
     * @param args
     */
    public static void main(String[] args) {
        new HlavniProgram().run();
    }

    /**
     * Hlavní metoda obsahující výkonný kód.
     */
    public void run() {
        tom = vytvorKocku();
        tom.setBrain(new KeyboardBrain(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D));

        jerry = vytvorMys();
        jerry.setBrain(new KeyboardBrain());

        vytvorVeci(4);
        chytMys();
    }

    public void chytMys() {
        // TODO: Sem vepište svůj program

        int JX = jerry.getX();
        int JY = jerry.getY();
        int TX = tom.getX();
        int TY = tom.getY();

        int x = JX - TX;
        int y = JY - TY;

//        MYS JE NALEVO

        if (x < 0 && tom.getOrientation() == PlayerOrientation.UP) {
            //kočka se dívá nahoru
            tom.turnLeft();
            while ( x != 0) {
                tom.moveForward();
            }
        }

        if (x < 0 && tom.getOrientation() == PlayerOrientation.RIGHT) {
            //kočka se dívá doprava
            tom.turnLeft();
            tom.turnLeft();
            while ( x != 0) {
                tom.moveForward();
            }
        }

        if (x < 0 && tom.getOrientation() == PlayerOrientation.DOWN) {
            //kočka se dívá dolu
            tom.turnRight();
            while ( x != 0) {
                tom.moveForward();
            }
        }

        if (x < 0 && tom.getOrientation() == PlayerOrientation.LEFT) {
            //kočka se dívá doleva
            while ( x != 0) {
                tom.moveForward();
            }
        }

//        MYS JE NAPRAVO

        if (x > 0 && tom.getOrientation() == PlayerOrientation.UP) {
            //kočka se dívá nahoru
            tom.turnRight();
            for (int i=0; i<=x; i++) {
                tom.moveForward();
            }
        }

        if (x > 0 && tom.getOrientation() == PlayerOrientation.RIGHT) {
            //kočka se dívá doprava
            for (int i=0; i<=x; i++) {
                tom.moveForward();
            }
        }

        if (x > 0 && tom.getOrientation() == PlayerOrientation.DOWN) {
            //kočka se dívá dolu
            tom.turnLeft();
            for (int i=0; i<=x; i++) {
                tom.moveForward();
            }
        }

        if (x > 0 && tom.getOrientation() == PlayerOrientation.LEFT) {
            //kočka se dívá doleva
            tom.turnRight();
            tom.turnRight();
            for (int i=0; i<=x; i++) {
                tom.moveForward();
            }
        }

    }

    public void vytvorVeci(int pocetStromu) {
        for (int i = 0; i < pocetStromu; i++) {
            vytvorStrom();
        }
        vytvorSyr();
        vytvorJitrnici();
    }
    public Tree vytvorStrom() {
        return new Tree(vytvorNahodnyBod());
    }

    public Cat vytvorKocku() {
        return new Cat(vytvorNahodnyBod());
    }

    public Mouse vytvorMys() {
        return new Mouse(vytvorNahodnyBod());
    }

    public Cheese vytvorSyr() {
        return new Cheese(vytvorNahodnyBod());
    }

    public Meat vytvorJitrnici() {
        return new Meat(vytvorNahodnyBod());
    }

    private Point vytvorNahodnyBod() {
        return new Point(random.nextInt(SIRKA_OKNA), random.nextInt(VYSKA_OKNA));
    }

}
