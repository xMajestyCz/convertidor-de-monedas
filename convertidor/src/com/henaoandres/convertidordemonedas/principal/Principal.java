package com.henaoandres.convertidordemonedas.principal;

import java.util.*;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ConvertidorDeMonedas convertidorDeMonedas = new ConvertidorDeMonedas();
        List<String> listaDeConversiones = new ArrayList<>();
        String dolar = "USD";
        String pesoArgentino = "ARS";
        String realBrasileno = "BRL";
        String pesoColombiano = "COP";
        int opcion = 0;

        System.out.println("\nBienvenido/a al conversor de monedas!");
        while(opcion != 7){
            try {
                System.out.println("\nPor favor, elija una opción:\n");
                System.out.println( """
                    1. Dólar ==> Peso argentino.
                    2. Peso argentido ==> Dólar.
                    3. Dólar =>> Real brasileño.
                    4. Real brasileño ==> Dólar.
                    5. Dólar ==> Peso colombiano.
                    6. Peso colombiano ==> Dólar.
                    7. Salir.
                """);
                opcion = scanner.nextInt();

                if(opcion == 7){
                    System.out.println("Gracias por utilizar el programa!");
                    break;
                } else if (opcion < 1 || opcion > 7) {
                    System.out.println("Favor ingresar solo los numeros indicados.");
                    continue;
                }

                System.out.print("Ingrese el valor que desee convertir: ");
                double valor = scanner.nextDouble();

                switch (opcion){
                    case 1 -> convertidorDeMonedas.convertir(dolar, pesoArgentino,valor, listaDeConversiones);
                    case 2 -> convertidorDeMonedas.convertir(pesoArgentino, dolar, valor, listaDeConversiones);
                    case 3 -> convertidorDeMonedas.convertir(dolar, realBrasileno, valor, listaDeConversiones);
                    case 4 -> convertidorDeMonedas.convertir(realBrasileno, dolar, valor, listaDeConversiones);
                    case 5 -> convertidorDeMonedas.convertir(dolar, pesoColombiano, valor, listaDeConversiones);
                    case 6 -> convertidorDeMonedas.convertir(pesoColombiano, dolar, valor, listaDeConversiones);
                }

            }catch (InputMismatchException e) {
                System.out.println("Favor ingresar solo los numeros indicados.");
                scanner.nextLine();
            }
        }
        System.out.println("Historial de conversiones:");
        listaDeConversiones.forEach(System.out::println);
    }
}
