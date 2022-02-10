# Módulo de dependências externas
*by Nattana Gasparim*

## Objetivo
Crie um projeto individual/grupo Utilizando os principais topicos apresentados ao longo dos módulos.

Desejável:
- Aplicar principios da OO
- Utilizar um gerenciador de pacotes ( Maven / Gradle)
- Utilizar Lombok
- Utilizar pelo menos 2 dependências
- Utilizar pelo menos 2 estrutura de dados distintas
- Gerar os arquivos de saída para cada

Precisa ser inserida 2 funcionalidades.
- Imprimir cada turma e seus alunos
  - Nome da turma
  - total alunos
  - nome dos alunos
- imprimir todos os alunos da escola ( sem duplicados, ja que existem alunos nas duas turmas) em ordem alfabetica
- As impressões deverão ser geradas em um arquivo.txt

## Detalhes da implementação
1. Há um arquivo de configurações com os paths dos arquivos de entrada e saída em **src/main/resources/config.properties**.
2. Os nomes que constam nos arquivos são lidos e armazenados em ordem, em um **ArrayList**. São igualmente impressos em ordem nos arquivos de saída individuais.
3. O arquivo de saída geral, contendo todos os nomes, é impresso em ordem alfabética. É o resultado da ordenação padrão realizada por um **TreeSet**, ignorando-se os pronomes de tratamento.