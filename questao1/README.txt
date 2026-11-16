A configuracao e lida uma unica vez de config.properties (classpath ou questao1/src).

region=us-east-1
budget=500

O orcamento aceita os tres ambientes de demonstracao e recusa env-expensive.

Compilar e executar a partir da raiz do repositorio:

javac -encoding UTF-8 -d questao1/bin questao1/src/*.java
java -cp questao1/bin Main
