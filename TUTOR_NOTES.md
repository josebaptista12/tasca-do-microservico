# TUTOR_NOTES.md — Notas para o tutor avaliar o projecto

## O que foi implementado

### book-service (Backend Java)
- **REST API completa:** Endpoints para listagem, pesquisa e detalhe de livros.
- **Persistência Poliglota:**
    - **PostgreSQL:** Armazenamento do catálogo de livros via Spring Data JPA.
    - **MongoDB:** Armazenamento das avaliações (reviews) dos leitores via Spring Data MongoDB.
- **Data Seeding:** Implementação de um `DataSeeder` que popula automaticamente as duas bases de dados ao iniciar a aplicação.

### web-ui (Frontend Python)
- **Tecnologia:** Interface desenvolvida em **Python Flask**.
- **Funcionalidades:** Consumo da API REST do `book-service` para exibir o catálogo, permitir pesquisas e submeter novas avaliações via browser.

### Docker & Docker Compose
- **Containerização:** Dockerfiles otimizados para o serviço Java e para o serviço Python.
- **Orquestração:** `docker-compose.yml` configurado com 4 serviços: `postgresql`, `mongodb`, `book-service` e `web-ui`.
- **Rede e Dependências:** Configuração de nomes DNS internos para comunicação entre containers e gestão de ordem de arranque.
- **Persistência:** Utilização de volumes Docker para garantir que os dados das bases de dados não se perdem entre reinícios.

## Desafios implementados (Opcional)
- [x] **Persistência Poliglota:** Integração simultânea de SQL (Postgres) e NoSQL (MongoDB) no mesmo microserviço Java.
- [x] **Comunicação Cross-Language:** Frontend Python a comunicar com Backend Java em containers distintos.
- [x] **Data Seeding Automático:** Lógica para garantir que o tutor veja dados assim que o projeto sobe.

## Dificuldades encontradas
- **Bloqueio de Ficheiros no Windows:** O Maven falhou várias vezes ao tentar limpar a pasta `target` (comando clean) porque o processo Java ou o IDE estavam a segurar os ficheiros. Resolvido através do encerramento forçado de processos Java pendentes e fecho do IDE durante o build.
- **Sincronização de Rede no Docker:** Inicialmente o `book-service` tentava ligar-se às bases de dados antes de estas estarem prontas. Resolvido com políticas de reinício automático e configuração de dependências no Compose.
- **Configuração Git:** Conflitos no primeiro push para o GitHub devido a ficheiros gerados automaticamente no repositório remoto, resolvidos com `git push --force`.

## Tempo de desenvolvimento
- **Labs 1 & 2 (Backend & BDs):** aproximadamente 4 horas.
- **Labs 3 & 4 (Frontend & Docker):** aproximadamente 3 horas.