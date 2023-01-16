package com.sharebook.pocfirestore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class MyConsole implements CommandLineRunner {

    private Firestore firestore;
    Scanner scanner;
    @Override
    public void run(String... args) throws Exception {

        try {
            appInitialize();
            mainMenu();
        }
        catch (Exception ex){
            System.out.println("\n\nOCORREU UM ERRO :/ \n\n");
            System.out.println(ex.getMessage());
        }


    }

    private void mainMenu() throws Exception {
        System.out.println("\n\nPOC FIRESTORE CRUD\n\n");

        var sair = false;

        while(!sair) {
            var opcaoSelecionada = escolheOpcaoMenu();

            // se fosse java 12 em diante daria pra usar "Switch Expressions"
            switch (opcaoSelecionada){
                case 1: {
                    write("CREATE");
                    break;
                }
                case 2: {
                    read();
                    break;
                }
                case 3: {
                    write("UPDATE");
                    break;
                }
                case 4: {
                    delete();
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

    private void delete() {
        try {
            System.out.println("DELETE.");

            System.out.println("Id do aluno?");
            var id = scanner.nextLine();

            DocumentReference docRef = firestore.collection("alunos").document(id);
            docRef.delete();
        }
        catch(Exception ex){
            System.out.println("Ocorreu um erro: " + ex.getMessage());
        }




    }

    private void read() throws ExecutionException, InterruptedException {

        try {
            System.out.println("READ.");

            // asynchronously retrieve all users
            ApiFuture<QuerySnapshot> query = firestore.collection("alunos").get();

            // ...
            // query.get() blocks on response
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                System.out.println("Id: " + document.getId());
                System.out.println("Nome: " + document.getString("nome"));
                System.out.println("Idade: " + document.getLong("idade"));
                System.out.println("Turma: " + document.getString("turma"));
                System.out.println("---------------------------------------------------");
            }

            if(documents.stream().count() ==0 ) System.out.println("Nenhum aluno encontrado.");
        }
        catch(Exception ex){
            System.out.println("Ocorreu um erro: " + ex.getMessage());
        }
    }

    private void write(String operation) {
        try {
            var id = "";
            System.out.println(operation);

            if(operation == "UPDATE"){
                System.out.println("Id do aluno?");
                id = scanner.nextLine();
            }

            System.out.println("Nome do aluno?");
            var nome = scanner.nextLine();

            System.out.println("Idade do aluno?");
            var idade = Integer.parseInt(scanner.nextLine());

            System.out.println("Turma do aluno?");
            var turma = scanner.nextLine();

            id = id == ""
                    ? nome + "-" + String.valueOf(idade)
                    : id;

            DocumentReference docRef = firestore.collection("alunos").document(id);

            // Add document data  with id "alovelace" using a hashmap
            Map<String, Object> data = new HashMap<>();
            data.put("nome", nome);
            data.put("idade", idade);
            data.put("turma", turma);

            //asynchronously write data
            // ApiFuture<Object> result =
            docRef.set(data);

            // ...
            // result.get() blocks on response
            // System.out.println("Update time : " + result.get().getUpdateTime());
        }
        catch(Exception ex){
            System.out.println("Ocorreu um erro: " + ex.getMessage());
        }



    }

    private void appInitialize() throws Exception {

        scanner = new Scanner(System.in);

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current absolute path is: " + s);

        // Use a service account
        InputStream serviceAccount = new FileInputStream("./src/main/resources/firestore-credential.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);

        firestore = FirestoreClient.getFirestore();

    }

    private int escolheOpcaoMenu() {
        var escolheu = false;
        var opcaoSelecionada = 0;

        while(!escolheu) {
            System.out.println("\n\nO que deseja fazer?");
            System.out.println("1 - create");
            System.out.println("2 - read");
            System.out.println("3 - update");
            System.out.println("4 - delete");
            System.out.println("5 - quit");

            try{
                opcaoSelecionada = Integer.parseInt(scanner.nextLine());
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
