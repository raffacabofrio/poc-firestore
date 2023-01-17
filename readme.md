# poc-firestore

App console simples para explorar o firestore da google com as operações CRUD. Inspirado no tutorial oficial:
https://firebase.google.com/docs/firestore/quickstart

# GCP - como criar o arquivo de credenciais?

Numa primeira tentativa fui pelo GCP e deu muita dor de cabeça. Desisti. 😰

Depois fui pelo firebase console, e foi bem suave. Inclusive fez automaticamente todas as configurações necessárias no GCP.

1 - criar um projeto no firebase.
    https://console.firebase.google.com/

2 - colocar essa permissão
allow read, write: if request.auth != null;

3 - obter as credenciais de acesso
 - https://console.cloud.google.com/
 - GCP >> IAM >> contas de serviço
 - entrar na conta, e faça o download da chave dela.
 - salve em "poc-firestore\src\main\resources\firestore-credential.json"


---------------------------------------------------------
# rodar pelo console
~~~
./gradlew bootRun 
~~~

---------------------------------------------------------
# firestore vs firebase

- firestore é o banco nosql.

- firebase é o framework que devs mobile amam. usa o firestore como banco.

