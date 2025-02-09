# MultiThreaded-HTTP-Server

### Desarrollado por: **Manuel Cardona**  
**Código de estudiante:** A00399980

---

## 🚀 Descripción

Este proyecto es un **servidor HTTP multihilo** desarrollado en Java, capaz de manejar múltiples solicitudes de clientes de forma concurrente utilizando hilos. El servidor escucha en el puerto 8080 y responde a solicitudes HTTP simples, permitiendo la entrega de archivos estáticos como HTML, CSS, JavaScript e imágenes.


---

## 📦 Manual de Usuario

### ✅ **Requisitos previos:**
- Java JDK 8 o superior
- IDE o editor de texto (Eclipse, IntelliJ, VS Code, etc.)

### ⚙️ **Instrucciones de Ejecución:**
1. **Clona o descarga el repositorio:**
   ```bash
   git clone https://github.com/JManuel2004/MultiThreaded-HTTP-Server
   ```

2. **Compila el código Java:**
   ```bash
   javac Main.java HttpRequest.java
   ```

3. **Ejecuta el servidor:**
   ```bash
   java Main
   ```

4. **Accede desde el navegador:**
   - Abre un navegador web.
   - Ingresa la URL: `http://localhost:8080`

   Puedes solicitar archivos específicos, por ejemplo:
   - `http://localhost:8080/index.html`
   - `http://localhost:8080/style.css`

### 📂 **Estructura de Archivos:**
```
MultiThreaded-HTTP-Server/
├── src/
│   ├── index.html
│   ├── meme.jpg
│   ├── 404.html
├── Main.java
├── HttpRequest.java
└── README.md
```

### ⚡ **Funcionalidades:**
- Manejo de múltiples conexiones de clientes simultáneamente.
- Soporte para archivos estáticos (HTML, CSS, JS, imágenes).
- Gestión de errores con una página personalizada para el error 404.

### 🚨 **Notas:**
- El servidor escucha por defecto en el puerto `8080`. Puedes cambiarlo modificando la variable `port` en `Main.java`.
- Los archivos deben estar ubicados en la carpeta `src` para ser accesibles.

---



