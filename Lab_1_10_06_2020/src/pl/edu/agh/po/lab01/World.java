package pl.edu.agh.po.lab01;

import agh.cs.lab1.Direction;

import java.util.ArrayList;


public class World {
    public static void main(String[] args) {
        System.out.println("START");
        run(convertFromString(args));
        System.out.println("STOP");
    }

    private static ArrayList<Direction> convertFromString(String[] args) {
        ArrayList<Direction> enum_args = new ArrayList<>();

        for (String dir_str : args) {
            try {
                enum_args.add(Direction.valueOf(dir_str));
            } catch (Exception e) {
//                In case of no enum
            }

        }
        return enum_args;
    }

    private static void run(ArrayList<Direction> args) {


        for (Direction out : args) {
            switch (out) {
                case f -> System.out.println("ZWIERZAK IDZIE DO PRZODU");
                case b -> System.out.println("ZWIERZAK IDZIE DO TYLU");
                case r -> System.out.println("ZWIERZAK IDZIE W PRAWO");
                case l -> System.out.println("ZWIERZAK IDZIE W LEWO");

            }
        }
    }
}
