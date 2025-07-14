# POO_Proyecto

**Integrantes:**
- Eduardo Mora
- Ignacio Soto
- Vicente Miranda

A continuación presentamos nuestro proyecto final de Desarrollo Orientado a Objeto (DOO) que consiste en
simular un gestor de Torneos donde podemos adoptar el rol del creador como del participante del torneo.

**Funcionalidades y características:**
- Crear Torneos características como el nombre, fechas, formato, premios, étc.
- Registrar distintos tipo de participantes (equipos o jugadores individuales).
- Gestionar las fechas del torneo.
- Notificar eventos importantes.
- Uso de patrones de diseño para un código modular y extensible.

El proyecto esta dividido en dos partes, la parte lógica donde se implementan los elementos
lógicos del programa, y la parte "visual" donde se establece la interfaz gráfica de usuario para el gestor.

# Lógica.

* Formatos, se enumeran formatos de participantes y de tipo de torneo. Ejemplos de uso de esto es un torneo de equipos
en formato de liga simple.
* Creación y generación de calendario, se establece una fecha inicial para el torneo y se genera un cronográma automático
y tentativo para todos los enfrentamientos correspondientes entre los jugadores/equipos.
* Interfaces de notificación para avisar eventos importantes a los usuarios.
* Sistema de Login para creadores y participantes de torneos.

# Diagrama de Casos de Uso:
![image](https://github.com/Liivne/POO_Proyecto/blob/main/Caso%20de%20uso%20(1).jpg)

# Diagrama UML:

# Interfaz de Usuario.
En esta parte está toda la lógica de la interfaz gráfica del usuario. En esta etapa se encuentran elementos fundamentales 
que facilitan el buen uso de este proyecto. 

**Ventanas Principales**
* Ventana principal: Punto de partida donde puede loguearte como administrador o participante de un torneo.
* Ventana crear torneo: Se encarga de la creación de torneos con todas sus características.
* Ventana Administradora: Nos permite visualizar torneos creados con sus resultados.

**Interactividad**
* Botones estilizados con efectos Hover y colores llamativos para acciones como crear, limpiar o reiniciar.
* Listeners que reaccionan a eventos de usuario para una experiencia fluida.

Tecnologías usadas: Java Swing.

# Patrones de Diseño

  
|      Patron      | Justificación                                                                                                                                                                          |
|:----------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|      Builder     | La clase Torneo puede tener un Constructor con muchos parámetros (debe tener Formato de liga y Formato de equipos, Fecha, Lugar, equipos o jugadores, etc) por eso se optó por Utilizar builder para dar mayor orden al momento de construír nuevos Torneos.                                             
|     Observer     | Observer se utilizó para actualizar las listas de torneos en tiempo real, de modo que solo sean accesibles para los usuarios registrados y el propio administrador, sin tener que interferir con las listas de torneos de otros administradores.|

# Imagenes Interfaz Gráfica.
Para ingresar como administrador se pueden usar los siguientes logins: (cuneta:"Link", contraseña:"Hyrule") o (cuenta: "Ganon", contraseña:"Gerudo");

![image](https://github.com/Liivne/POO_Proyecto/blob/main/InterfazGr1.jpeg)

![image](https://github.com/Liivne/POO_Proyecto/blob/main/InterfazGr2.jpeg)

![image](https://github.com/Liivne/POO_Proyecto/blob/main/InterfazGr2.jpeg](https://github.com/Liivne/POO_Proyecto/blob/main/WhatsApp%20Image%202025-07-13%20at%2011.56.08%20PM%20(1).jpeg))






