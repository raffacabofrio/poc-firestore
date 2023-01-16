# poc-firestore

App console simples para explorar o firestore da google com as operações CRUD. Inspirado no tutorial oficial:
https://firebase.google.com/docs/firestore/quickstart

# GCP - como criar o arquivo de credenciais?

GCP >> IAM >> contas de serviço 

1 - criar uma conta de serviço.
2 - entrar na conta, e criar uma chave pra ela.

GCP >> firestore >> regras de segurança

1 - ativar o firebase
2 - ir para o console do firebase >> https://console.firebase.google.com/u/0/project/firestore-poc-374622/firestore/rules

3 - colocar essa permissão
allow read, write: if request.auth != null;



---------------------------------------------------------
# rodar pelo console
./gradlew bootRun 


---------------------------------------------------------


