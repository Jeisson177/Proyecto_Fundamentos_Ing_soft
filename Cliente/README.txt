Proyecto de Gestión de Reservas
Descripción del Proyecto
Este proyecto implementa una aplicación de gestión de reservas desde el punto de vista del cliente, siguiendo el patrón de diseño Vista-Controlador-Negocio.
El sistema permite a los clientes gestionar sus reservas a través de una interfaz de usuario, mientras que el modelo de negocio y la lógica de persistencia de datos están encapsulados en las capas correspondientes.

Características principales:
Registro de clientes
Creación, visualización y cancelación de reservas
Consulta de la disponibilidad de recursos
Historial de reservas del cliente
Gestión de autenticación de clientes
Estructura del Proyecto
El proyecto está organizado en cuatro capas principales:

Vista (View):

Es la interfaz que interactúa con el cliente. Permite a los clientes registrarse, iniciar sesión, ver la disponibilidad de recursos y gestionar sus reservas.
Ubicación: /src/main/resources/vista/
Controlador (Controller):

Maneja la lógica de negocio y las interacciones entre la Vista y la capa de Negocio. Valida las acciones del usuario y actualiza la Vista de acuerdo con las respuestas de la capa de Negocio.
Ubicación: /src/main/java/controller/
Services (Business Logic):

Contiene las reglas de negocio y la lógica para procesar las solicitudes de reserva, consulta de disponibilidad y gestión de la información del cliente.
Ubicación: /src/main/java/services/
Repositorio (Repository):

Se encarga de la persistencia de los datos. Utiliza un patrón Repository para interactuar con la base de datos, facilitando la creación, lectura, actualización y eliminación (CRUD) de reservas y datos de clientes.
Ubicación: /src/repository/
Requisitos
Lenguaje: El proyecto está desarrollado en Java.
Base de datos: MySQL.
Frameworks y bibliotecas:

Instalación
Clonar el repositorio:

bash
Copiar código
git clone https://github.com/Jeisson177/Proyecto_Fundamentos_Ing_soft.git
Configurar la base de datos:

Crear una base de datos con el siguiente script SQL: base de datos.txt .
Configurar las credenciales de acceso a la base de datos: usuario:root contraseña: cl .
Ejecutar la aplicación:

Uso
Registro y autenticación:

Los usuarios registrados pueden iniciar sesión con su correo electrónico y contraseña.
Gestión de reservas:

Desde el panel de usuario, los clientes pueden ver la disponibilidad de recursos y realizar nuevas reservas.
Arquitectura
Vista-Controlador-Negocio: Este patrón separa claramente la interfaz de usuario (Vista), la lógica de control (Controlador) y las reglas de negocio (Negocio), proporcionando una arquitectura modular y fácil de mantener.
Repository Pattern: El patrón Repository encapsula la lógica de acceso a datos, permitiendo que la capa de negocio sea independiente de la forma en que se almacenan los datos.
Contribuciones
Las contribuciones son bienvenidas. Para contribuir, por favor sigue estos pasos:

Haz un fork del repositorio.
Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
Haz tus cambios y realiza un commit (git commit -m 'Agregar nueva funcionalidad').
Sube tus cambios a tu rama (git push origin feature/nueva-funcionalidad).
Abre un pull request.