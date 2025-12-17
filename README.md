# 🗨️ Servidor de Chat Concurrente en Java (Sockets + Threads)

Práctica de **Programación de Servicios y Procesos (PSP)** cuyo objetivo es desarrollar un **servidor de chat multihilo** capaz de atender a múltiples clientes simultáneamente mediante **Java Sockets** y **Threads**.

---

## 📌 Objetivo de la práctica

Partiendo de un ejemplo básico de comunicación cliente-servidor, se ha desarrollado un sistema de chat que soluciona dos problemas principales:

- ❌ Comunicación de un solo mensaje
- ❌ Servidor bloqueador que solo atiende a un cliente

La solución implementa:
- Comunicación continua cliente-servidor
- Concurrencia mediante hilos independientes por cliente

---

## 🛠️ Tecnologías utilizadas

- Java
- Sockets (`ServerSocket`, `Socket`)
- Hilos (`Thread`, `Runnable`)
- Streams de entrada y salida (`DataInputStream`, `DataOutputStream`)

---

## 🚀 Funcionamiento

### Servidor
- Escucha conexiones en el puerto **5000**
- Acepta clientes de forma indefinida
- Por cada cliente:
  - Muestra su IP
  - Crea un hilo independiente para gestionarlo

### Cliente
- Se conecta al servidor
- Introduce un nombre de usuario
- Envía mensajes de forma continua
- La comunicación termina al escribir **FIN**

---

## 🔄 Protocolo de comunicación

- Cliente → envía mensajes mediante `writeUTF()`
- Servidor → responde inmediatamente
- La conversación finaliza cuando el cliente escribe `FIN`

---

## 🧵 Concurrencia

Cada cliente es gestionado por una instancia de `GestorCliente`, que:
- Implementa `Runnable`
- Recibe su propio `Socket`
- Ejecuta la conversación en un hilo independiente

Esto permite que varios clientes hablen con el servidor **al mismo tiempo**, sin bloqueos.

---
