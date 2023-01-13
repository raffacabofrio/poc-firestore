package com.sharebook.pocfirestore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MyConsole implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n\nPOC FIRESTORE CRUD\n\n");
        var sair = false;

        while(!sair) {
            var opcaoSelecionada = mostraMenu();

            // se fosse java 12 em diante daria pra usar "Switch Expressions"
            switch (opcaoSelecionada){
                case 1: {
                    System.out.println("CREATE.");
                    break;
                }
                case 2: {
                    System.out.println("READ.");
                    break;
                }
                case 3: {
                    System.out.println("UPDATE.");
                    break;
                }
                case 4: {
                    System.out.println("DELETE.");
                    break;
                }
                case 5: {
                    sair = true;
                    System.out.println("SAIR.");
                    break;
                }
            }
        }




    }

    private int mostraMenu() {
        var escolheu = false;
        var opcaoSelecionada = 0;
        Scanner s = new Scanner(System.in);

        while(!escolheu) {
            System.out.println("\n\nO que deseja fazer?");
            System.out.println("1 - create");
            System.out.println("2 - read");
            System.out.println("3 - update");
            System.out.println("4 - delete");
            System.out.println("5 - quit");

            try{
                opcaoSelecionada = Integer.parseInt(s.nextLine());
                if(opcaoSelecionada < 0 || opcaoSelecionada > 5) throw new Exception("opção inválida");
            }
            catch (Exception ex){
                System.out.println("Opção inválida. Tente de novo.");
                continue;
            }

            escolheu = true;
        }

        return opcaoSelecionada;
    }
}
