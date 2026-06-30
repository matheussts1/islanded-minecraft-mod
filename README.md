# 🌊 Islanded

**Islanded** é um mod de sobrevivência e exploração aquática para Minecraft desenvolvido em **Java** utilizando a **Fabric API**. Inspirado em mecânicas de jogos de sobrevivência como *Subnautica*, o mod transforma a experiência dos oceanos introduzindo novos sistemas de sobrevivência, fauna marinha dinâmica e equipamentos tecnológicos de mergulho.

---

## 🚀 Funcionalidades Principais

*   **💧 Sistema de Sede:** Uma nova barra de sobrevivência que obriga o jogador a gerenciar sua hidratação, purificar água e coletar recursos para não desidratar.
*   **🪖 Traje de Mergulho de Cobre:** Conjunto completo de armadura funcional. Cada peça concede vantagens únicas e o uso do *Full Set* libera efeitos avançados de respiração (`WATER_BREATHING`) e agilidade hidrodinâmica (`DOLPHINS_GRACE`).
*   **🐟 Nova Fauna Marinha:** Introdução de novas espécies de peixes (como o Peixe-Palhaço e o Peixe-Monge) com mecânicas de captura, culinária e utilidades únicas.
*   **📉 Mecânica de Profundidade:** Manipulação da profundidade do mar, onde descer demais sem o equipamento correto traz desafios e perigos realistas para a exploração.
*   **🎨 Manipulação Gráfica & Atmosfera:** Alterações visuais e efeitos personalizados para criar uma atmosfera subaquática imersiva e claustrofóbica.

---

## 🛠️ Arquitetura Técnica (Clean Code)

Este projeto foi desenvolvido aplicando boas práticas de engenharia de software e padrões de desenvolvimento do ecossistema Fabric:

*   **Inversão de Controle & Passagem de Parâmetros:** Arquitetura limpa estruturada sem o uso de variáveis estáticas globais mutáveis para evitar vazamento de memória e conflitos de concorrência.
*   **Programação Funcional & Eventos:** Uso extensivo de expressões Lambda do Java para acoplamento de lógica de ticks nos barramentos de eventos do servidor (`ServerTickEvents`).
*   **Data Driven Content:** Sistema de craftings moldados (`Shaped Recipes`) totalmente isolado do código fonte, implementado via JSONs modernos no padrão de carregamento do Minecraft 1.21+.

---

## 💻 Tecnologias Utilizadas

*   **Linguagem:** Java 21
*   **Framework:** Fabric API / Fabric Loader
*   **Gerenciador de Dependências:** Gradle
*   **Configuração de Dados:** JSON (Minecraft Data Generation)
---

## 🔨 Como Compilar o Projeto (Desenvolvedores)

Se quiser clonar o repositório e rodar o ambiente de desenvolvimento:

1. Clone o repositório:
   ```bash
   git clone [https://github.com/](https://github.com/)[seu_usuario_do_github]/[nome_do_repositorio].git
