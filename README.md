# 🚀 Guia de Inicialização do Ambiente

Este documento descreve os passos necessários para subir o ambiente com **MySQL**, **Keycloak** e **Spring Boot** utilizando Docker e Maven.

---

## 🐳 1. Subir o container do MySQL

```bash
docker compose up -d mysql-db
```

---

## 🗄️ 2. Criar banco e usuário para o Keycloak

Acesse o console do MySQL dentro do container:

```bash
docker exec -it mysql-db mysql -uroot -proot
```

No console MySQL, execute:

```sql
CREATE DATABASE IF NOT EXISTS keycloak_db;

CREATE USER IF NOT EXISTS 'keycloak_user'@'%' IDENTIFIED BY 'keycloak_pass';

GRANT ALL PRIVILEGES ON keycloak_db.* TO 'keycloak_user'@'%';

FLUSH PRIVILEGES;
```

### ✔️ Comandos úteis de verificação

Listar bancos:

```sql
SHOW DATABASES;
```

Listar usuários:

```sql
SELECT User, Host FROM mysql.user;
```

---

## 🐳 3. Subir o Keycloak

```bash
docker compose up keycloak
```

---

## 🌱 4. Executar o backend Spring Boot

```bash
./mvnw spring-boot:run
```

---

<br/>

#### 
# Autora
Juliana Lima

[![Linkedin](https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/feed/?trk=guest_homepage-basic_nav-header-signin)
[![WhatsApp](https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo=whatsapp&logoColor=white)](https://contate.me/Juliana-Lima)
[![Portfólio](https://img.shields.io/badge/Portf%C3%B3lio-%E2%9C%88%EF%B8%8F-lightgrey?style=for-the-badge)](https://codedeving.netlify.app/)
