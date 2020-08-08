/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generala;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author Marcelo
 */
public class Generala {

    protected int[] dice;

    public Generala(int d1, int d2, int d3, int d4, int _5) {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = _5;
    }

    //Metodo modificado con programación funcional
    public static int chance(int d1, int d2, int d3, int d4, int d5) {
        int total = Arrays.asList(d1, d2, d3, d4, d5).stream()
                .reduce(0, Integer::sum);
        return total;
    }

    // '(int... dice)' es similar a tener public static int generala(int d1, int d2, int d3 , etc) pero permite realizar operaciones como -> for (int die : dice)
    //es una forma de decir que el metodo puede aceptar 1 o más parametros de tipo int ... lista de parametros dinamicos.
    public static int generala(int... dice) {
        if (areAllTheSame(dice)) {
            return 50;
        }
        return 0;
    }

    // Metodo adicional en la refactorización de metodo generala a programación funcional.
    public static boolean areAllTheSame(int... dice) {
        if (Arrays.stream(dice).distinct().toArray().length == 1) {
            return true;
        }
        return false;
    }

    // Metodo Ones, modificado para funcionar con programación funcional
    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        return addACertainNumber(d1, d2, d3, d4, d5, 1);
    }

    // Metodo twoss, modificado para funcionar con programación funcional
    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        return addACertainNumber(d1, d2, d3, d4, d5, 2);
    }

    // Metodo threes, modificado para funcionar con programación funcional
    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        return addACertainNumber(d1, d2, d3, d4, d5, 3);

    }

    // Metodo fours, modificado para funcionar con programación funcional
    public int fours() {
        return addACertainNumber(4);
    }

    // Metodo fives, modificado para funcionar con programación funcional
    public int fives() {
        return addACertainNumber(5);
    }

    // Metodo sixes, modificado para funcionar con programación funcional
    public int sixes() {
        return addACertainNumber(6);
    }

    //Método polimórfico creado para funcionar con metodos ones, twos y threes
    private static int addACertainNumber(int d1, int d2, int d3, int d4, int d5, int target) {
        return Arrays.asList(d1, d2, d3, d4, d5).stream()
                .filter(e -> e == target)
                .reduce(0, Integer::sum);
    }

    //Método polimórfico creado para funcionar con metodos fours, fives y sixes
    private int addACertainNumber(int target) {
        return Arrays.stream(this.dice)
                .filter(e -> e == target)
                .reduce(0, Integer::sum);
    }

    public static int score_pair(int d1, int d2, int d3, int d4, int d5) {
        int[] counts = new int[6];
        counts[d1 - 1]++;
        counts[d2 - 1]++;
        counts[d3 - 1]++;
        counts[d4 - 1]++;
        counts[d5 - 1]++;
        int at;
        for (at = 0; at != 6; at++) {
            if (counts[6 - at - 1] >= 2) {
                return (6 - at) * 2;
            }
        }
        return 0;
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5) {
        int[] counts = new int[6];
        counts[d1 - 1]++;
        counts[d2 - 1]++;
        counts[d3 - 1]++;
        counts[d4 - 1]++;
        counts[d5 - 1]++;
        int n = 0;
        int score = 0;
        for (int i = 0; i < 6; i += 1) {
            if (counts[6 - i - 1] >= 2) {
                n++;
                score += (6 - i);
            }
        }
        if (n == 2) {
            return score * 2;
        } else {
            return 0;
        }
    }

    public static int four_of_a_kind(int _1, int _2, int d3, int d4, int d5) {
        int[] tallies;
        tallies = new int[6];
        tallies[_1 - 1]++;
        tallies[_2 - 1]++;
        tallies[d3 - 1]++;
        tallies[d4 - 1]++;
        tallies[d5 - 1]++;
        for (int i = 0; i < 6; i++) {
            if (tallies[i] >= 4) {
                return (i + 1) * 4;
            }
        }
        return 0;
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        int[] t;
        t = new int[6];
        t[d1 - 1]++;
        t[d2 - 1]++;
        t[d3 - 1]++;
        t[d4 - 1]++;
        t[d5 - 1]++;
        for (int i = 0; i < 6; i++) {
            if (t[i] >= 3) {
                return (i + 1) * 3;
            }
        }
        return 0;
    }
    
    //Metodo modificado con segmentación de código
    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        if (thereIsRepetitiveDice(d1, d2, d3, d4, d5) || thereIsADice(d1, d2, d3, d4, d5, 6)) {
            return 0;
        }
        return 15;
    }

    //Metodo creado con la adición de los siguientes otros 2 metodos
    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        if (thereIsRepetitiveDice(d1, d2, d3, d4, d5) || thereIsADice(d1, d2, d3, d4, d5, 1)) {
            return 0;
        }
        return 20;
    }

    //Metodo adicional creado: thereIsRepetitiveDice que ve si hay valores repetidos
    public static boolean thereIsRepetitiveDice(int d1, int d2, int d3, int d4, int d5) {
        if (Arrays.asList(d1, d2, d3, d4, d5).stream()
                .distinct()
                .collect(Collectors.toList())
                .size() == 5) {
            return false;
        }

        return true;
    }

    //Metodo adicional creado: thereIsADice que ve si entre los dados está el valor
    //target
    public static boolean thereIsADice(int d1, int d2, int d3, int d4, int d5, int target) {
        if (Arrays.asList(d1, d2, d3, d4, d5).contains(target)) {
            return true;
        }

        return false;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;

        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;

        for (i = 0; i != 6; i += 1) {
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i + 1;
            }
        }

        for (i = 0; i != 6; i += 1) {
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i + 1;
            }
        }

        if (_2 && _3) {
            return _2_at * 2 + _3_at * 3;
        } else {
            return 0;
        }
    }
}
