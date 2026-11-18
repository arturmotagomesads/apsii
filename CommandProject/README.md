# CommandProject

O projeto foi baseado em um controle remoto de atalhos para Windows, feito em Java com Swing e baseado no padrão de projeto **Command**. As teclas **F1 a F12** abrem programas, controlam o volume do sistema, desfazem ações e encerram a aplicação, enquanto a janela exibe a lista de atalhos e a última tecla pressionada.

## Grupo

Artur Mota Gomes
Francisco Juan Feliciano da Silva
Esdras de Almeida Oliveira

## Sobre o projeto

O programa abre uma janela chamada **Atalhos** com os 12 atalhos disponíveis e um rótulo **"ÚLTIMA TECLA DIGITADA"**. Ao pressionar uma tecla de função, o comando correspondente é executado no Windows.

### Atalhos

| Tecla | Ação |
|---|---|
| F1 | Abrir o Windows Explorer |
| F2 | Abrir o Microsoft Edge |
| F3 | Abrir o Paint |
| F4 | Abrir a Calculadora |
| F5 | Abrir o Bloco de Notas |
| F6 | Abrir as Configurações do Windows |
| F7 | Aumentar o volume |
| F8 | Diminuir o volume |
| F9 | Ativar/desativar o mudo |
| F10 | Ir para a Área de Trabalho (minimizar tudo) |
| F11 | Desfazer a última ação |
| F12 | Sair do programa |

As teclas são capturadas por um `KeyEventDispatcher` global do Swing. Por isso os atalhos funcionam enquanto a janela da aplicação estiver em foco. Combinações com **Alt** são ignoradas, para não interferir em atalhos do sistema como Alt+F4.

### Tecnologias

- Java (JDK 25) e Maven
- Swing / AWT para a interface e a captura de teclas
- `ProcessBuilder` para executar programas e comandos do Windows (`cmd.exe`, `taskkill`, PowerShell)
- Desenvolvido no Eclipse

### Requisitos

- Windows (os receivers dependem de executáveis e comandos do sistema)
- JDK instalado, compatível com a versão configurada no `pom.xml`

### Como executar

1. Importe o projeto no Eclipse como projeto Maven existente (ou compile com `mvn compile`).
2. Execute a classe `remote.Cliente`, que contém o método `main`.
3. Com a janela em foco, pressione as teclas **F1 a F12**.

## Arquitetura

O projeto usa os cinco papéis clássicos do padrão Command:

| Papel | Pacote | Classes |
|---|---|---|
| **Command** | `command` | `Command`, `NoCommand` |
| **ConcreteCommand** | `concrete` | `OpenEdge`, `OpenPaint`, `OpenCalc`, `OpenNotepad`, `OpenSettings`, `OpenExplorer`, `VolumeUp`, `VolumeDown`, `ActivateMute`, `ShowDesktop` |
| **Receiver** | `receiver` | `Edge`, `Paint`, `Calc`, `Notepad`, `Settings`, `Explorer`, `Volume`, `Mute`, `Desktop` |
| **Invoker** | `remote` | `Remote` |
| **Client** | `remote` | `Cliente` |

A interface gráfica fica isolada no pacote `ui` (`Janela`).

### Fluxo de uma ação

```
Tecla F4 ──► Cliente (captura) ──► Remote.ButtonPushed(F4)
                                        │
                                        ▼
                              OpenCalc.execute()
                                        │
                                        ▼
                                 Calc.abrir()  ──► ProcessBuilder("calc.exe")
```

1. `Cliente` cria os receivers e comandos e registra cada comando em uma tecla com `Remote.setCommand(...)`.
2. Ao detectar uma tecla, `Cliente` chama `Remote.ButtonPushed(...)`.
3. `Remote` executa o comando registrado e o guarda no histórico.
4. O comando delega o trabalho ao receiver, que sabe como falar com o Windows.
5. Com **F11**, `Remote` desfaz o comando mais recente chamando `undo()`.

### Desfazer (undo)

Cada comando implementa `execute()` e `undo()`, sendo o `undo` a operação inversa:

| Comando | `execute()` | `undo()` |
|---|---|---|
| `OpenCalc`, `OpenEdge`, `OpenPaint`, `OpenNotepad`, `OpenSettings`, `OpenExplorer` | abre o programa | fecha o programa |
| `VolumeUp` / `VolumeDown` | aumenta / diminui o volume | diminui / aumenta o volume |
| `ActivateMute` | ativa o mudo | desativa o mudo |
| `ShowDesktop` | minimiza todas as janelas | restaura as janelas |

O histórico é mantido em uma pilha (`Deque<Command>`), então várias ações podem ser desfeitas na ordem inversa.

O `NoCommand` segue o padrão **Null Object**: teclas sem comando associado não executam nada, e o `Remote` não precisa checar `null`.

## Por que o padrão Command?

### O problema

O projeto precisa ligar **uma tecla** a **uma ação**, com três exigências:

1. **Muitas ações diferentes** (abrir programas, mexer no volume, minimizar janelas) que tratam o sistema de formas bem distintas.
2. **Associação flexível**: trocar o que uma tecla faz, ou acrescentar uma nova ação, sem mexer em quem captura as teclas.
3. **Desfazer**: cada ação precisa saber como ser revertida.

### Por que Command é a melhor escolha

O Command transforma uma requisição em um **objeto**. Isso resolve cada uma das exigências:

- **Desacoplamento.** O `Remote` só conhece a interface `Command`. Ele não sabe o que é o Paint, o Edge ou o volume. Quem pede (a tecla) fica separado de quem faz (o receiver).
- **Extensibilidade (Aberto/Fechado).** Para adicionar, por exemplo, o Word, basta criar um receiver `Word` e um comando `OpenWord`, e registrá-lo no `Cliente`. `Remote` e `Command` não mudam.
- **Undo natural.** Como a ação é um objeto com `execute()` e `undo()`, o histórico vira só uma pilha de comandos. Sem o padrão, o `Remote` precisaria de um `switch` enorme para saber como reverter cada tecla.
- **Configuração em tempo de execução.** Como o mapa tecla → comando é dado, qualquer tecla pode receber outro comando com `setCommand`, sem recompilar a lógica do invoker.
- **Testabilidade.** Cada comando pode ser testado isoladamente com um receiver falso, sem abrir programas reais.
- **Encaixe com o domínio.** O exemplo clássico do padrão é justamente o controle remoto, em que cada botão dispara uma ação encapsulada. Um painel de atalhos de teclado é a mesma estrutura.

### Alternativas consideradas

| Alternativa | Como seria usada aqui | Por que não é a melhor |
|---|---|---|
| **`switch`/`if-else` direto** | Um grande bloco que, para cada tecla, chama o código correspondente | Simples para poucos casos, mas mistura captura de teclas com lógica de negócio, viola o Aberto/Fechado (toda ação nova altera o bloco) e dificulta o undo |
| **Strategy** | Cada tecla recebe uma "estratégia" que implementa a ação | Estruturalmente parecido, mas o Strategy foca em **trocar algoritmos** para uma mesma tarefa. Não tem a noção de requisição reversível e histórico, que são centrais aqui |
| **Memento** | Guardar o estado anterior do sistema para restaurá-lo no undo | Pouco viável: o estado do Windows (programas abertos, volume) não pode ser capturado e restaurado por completo. É mais simples ensinar cada comando a reverter a si mesmo |
| **Observer** | Cada receiver "assina" eventos de tecla e decide se reage | Dá desacoplamento, mas deixa a associação tecla → ação espalhada pelos receivers e não oferece undo. Seria útil para notificar a `Janela` sobre a última tecla pressionada |
| **Chain of Responsibility** | Passar a tecla por uma cadeia de handlers até um deles tratar | Faz sentido quando vários objetos podem tratar o mesmo evento. Aqui cada tecla tem um único destino, então a cadeia só adiciona complexidade |
| **Lambdas/referências a método** | Mapear teclas a `Runnable` ou a pares de lambdas (`execute`/`undo`) | Em Java moderno é uma alternativa legítima e mais enxuta, mas perde a estrutura explícita de classes e a separação entre comando e receiver que este projeto quer demonstrar |

**Em resumo:** Strategy, Observer e lambdas poderiam complementar o projeto, mas nenhum cobre sozinho o conjunto "requisição encapsulada + desacoplamento + undo". O Command é o único que atende aos três de forma direta.

## Princípios SOLID

| Princípio | Como aparece no projeto |
|---|---|
| **S** | Cada comando traduz `execute`/`undo` em chamadas ao receiver; cada receiver isola os detalhes do Windows; `Remote` só mapeia tecla → comando; `Janela` cuida só da interface |
| **O** | Novos comandos são adicionados sem alterar `Remote` nem `Command` |
| **L** | Qualquer `Command`, inclusive `NoCommand`, pode ser usado no `Remote` sem quebrá-lo |
| **I** | `Command` é uma interface pequena, com apenas `execute()` e `undo()` |
| **D** | `Remote` depende da abstração `Command`; os receivers são injetados nos comandos pelo construtor |

## Possíveis melhorias

- Criar uma interface/classe base para os receivers que abrem e fecham processos (`Edge`, `Paint`, `Calc`, `Notepad`, `Settings`), eliminando código duplicado e padronizando os nomes dos métodos.
- Centralizar tecla, comando e descrição em um único lugar, para que `Janela` não duplique o mapeamento feito em `Cliente`.
- Substituir os números `112` e `122` no `Remote` por constantes de `KeyEvent`.
- Implementar `redo`, usando uma segunda pilha.
- Criar testes unitários para os comandos, com receivers falsos.
