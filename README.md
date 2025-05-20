# Jogo de Xadrez em Java

Este é um projeto de **Jogo de Xadrez** desenvolvido em Java, utilizando **Programação Orientada a Objetos (POO)**. O jogo não possui interface gráfica no momento, funcionando apenas no console, com o objetivo de aplicar e demonstrar conceitos fundamentais de POO.

---

## 📜 Sobre o Projeto

Este projeto foi criado como parte de estudos sobre **POO**. O foco principal foi implementar as regras do xadrez de maneira modular e reutilizável, explorando os seguintes conceitos:

- **Encapsulamento**
- **Herança**
- **Polimorfismo**
- **Abstração**

---

## 🛠️ Estrutura do Código

### Principais Classes
- **Tabuleiro**: Representa o tabuleiro de xadrez e suas operações básicas.
- **Peça** (classe abstrata): Classe base para todas as peças de xadrez (como Peão, Torre, Cavalo, etc.).
- **Partida**: Gerencia o fluxo do jogo, incluindo turnos e validação de jogadas.
- **Jogador**: Representa os jogadores do jogo.

### Hierarquia das Peças
```plaintext
Peça
├── Peão
├── Torre
├── Cavalo
├── Bispo
├── Rainha
└── Rei

🚀 Funcionalidades
Validação de movimentos com base nas regras do xadrez.

Verificação de xeque e xeque-mate.

Gerenciamento de turnos entre dois jogadores.

Exibição do tabuleiro no console.


🧰 Tecnologias Utilizadas
Java: Linguagem de programação principal.

POO: Paradigma de programação aplicado.

📚 Aprendizados
Este projeto ajudou a reforçar conceitos de POO, como:

Planejamento e design de classes.

Implementação de hierarquias e reutilização de código.

Validação de lógica complexa (regras de xadrez).