# Jogo de Xadrez em Java  

Este é um projeto de **Jogo de Xadrez** desenvolvido em **Java**, utilizando os conceitos de **Programação Orientada a Objetos (POO)**. O jogo funciona no console e implementa todas as regras oficiais de xadrez, incluindo jogadas especiais como **Castling (Roque)**, **En Passant (Captura ao Passar)** e **Promotion (Promoção de Peão)**.  

---

## 📜 Sobre o Projeto  

O objetivo deste projeto é aplicar conceitos fundamentais de **POO** e criar uma estrutura modular e reutilizável. A implementação cobre:  
- **Regras do xadrez**, incluindo situações como xeque, xeque-mate. 
- **Jogadas especiais**, que adicionam complexidade e autenticidade ao jogo.  

---

## 🚀 Funcionalidades  

### 📌 Regras Gerais  
1. **Movimentação de Peças**:  
   - Cada peça segue suas regras específicas de movimentação.  
   - Validações impedem jogadas inválidas.  

2. **Verificação de Xeque e Xeque-Mate**:  
   - Identificação automática de situações em que um Rei está em xeque.  
   - Determinação de xeque-mate quando nenhuma jogada válida é possível.  

3. **Exibição do Tabuleiro no Console**:  
   - Representação visual do estado atual do tabuleiro após cada jogada.  

### 📌 Jogadas Especiais  
1. **Castling (Roque)**:  
   - Movimentação conjunta entre o Rei e uma Torre para proteção.  
   - Regras aplicadas:  
     - Nem o Rei nem a Torre podem ter se movido antes.  
     - Não pode haver peças entre eles.  
     - O Rei não pode estar em xeque, nem atravessar ou terminar em uma casa sob ataque.  

2. **En Passant (Captura ao Passar)**:  
   - Jogada especial onde um Peão captura outro que avançou duas casas no último turno.  
   - Regras aplicadas:  
     - O Peão capturado deve estar ao lado do Peão capturador.  
     - Captura válida somente na jogada imediatamente após o movimento do Peão adversário.  
     - Casas válidas para captura:  
       - 5ª fileira para as brancas.  
       - 4ª fileira para as pretas.  

3. **Promotion (Promoção de Peão)**:  
   - Quando um Peão alcança a última fileira, ele é promovido a outra peça, como Rainha, Torre, Bispo ou Cavalo.  
   - A escolha da peça é feita pelo jogador, conforme sua estratégia.  

---

## 🛠️ Estrutura do Código  

### Principais Classes  
- **Tabuleiro**: Representa o tabuleiro e suas operações básicas.  
- **Peça** (classe abstrata): Base para as peças de xadrez, como Rei, Rainha, Peão, etc.  
- **Partida**: Controla o fluxo do jogo, turnos, e validações.  
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
