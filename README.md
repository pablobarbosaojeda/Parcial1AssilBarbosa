# PARCIAL 1 FABRICA DE CAMPANAS DE GAUSS ASSIL ASRI, PABLO BARBOSA y DANIEL SOUSA

# Link al Repositorio: https://github.com/pablobarbosaojeda/Parcial1AssilBarbosaDani.git

# Puerto de Arranque del Proyecto: Port:4432 / http://localhost:4432/
Este proyecto simula una línea de producción en una fábrica de campanas de Gauss y visualiza la distribución de componentes a través de una interfaz web. Se utiliza `Spring Boot` para el backend y `Chart.js` en el frontend. Los componentes producidos se distribuyen en distintas posiciones usando un modelo de probabilidad.

## Estructura del Proyecto

La estructura principal del proyecto es la siguiente:

- **controller**: Controladores para gestionar las rutas de acceso.
- **model**: Clases de modelo que representan los elementos de la fábrica (`Ball`, `Component`, `Workstation`).
- **service**: Servicios de lógica de negocio para gestionar la producción y la distribución de componentes.
- **util**: Utilidades para programar y visualizar la producción.
- **resources/templates**: Contiene la interfaz `galton.html` para visualizar la distribución.

## Clases y Funcionamiento

### `FactoryController`
Controlador principal del backend que expone dos endpoints:
- **/start**: Inicia la producción en la fábrica al generar múltiples instancias de la clase `Ball`, cada una simulando el movimiento a través del tablero.
- **/distribution**: Retorna la distribución actual de los componentes, la cual se actualiza en tiempo real.

### `WebController`
Redirige la solicitud raíz (`/`) a la página `galton.html`, donde se visualiza la distribución de los componentes en un gráfico.

### `Ball`
Simula el comportamiento de una bola que cae a través de un tablero en 50 niveles, donde cada nivel puede cambiar la posición en una dirección aleatoria. Al final, se actualiza la posición de la bola en la distribución.

### `Component`
Clase que representa un componente producido en la fábrica, caracterizado por un tipo (`type`) que indica el tipo de componente (por ejemplo, "Clavo", "Marco", "Bola").

### `Workstation`
Define una estación de trabajo en la fábrica, que procesa un tipo específico de componente y tiene un identificador (`id`) para ser rastreada en el sistema.

### `AssemblyLineService`
Servicio que se ejecuta continuamente, consumiendo componentes del buffer de producción. Cada componente se extrae y se "ensambla", representando una etapa del proceso de fabricación.

### `FactoryService`
Servicio central que gestiona la producción y distribución de componentes:
- **startProduction**: Inicia la producción de bolas en la fábrica, enviando múltiples hilos para simular cada bola que atraviesa el tablero.
- **updateDistribution**: Actualiza la distribución para reflejar el conteo de cada posición final alcanzada por las bolas.
- **getDistribution**: Retorna un mapa de la distribución actual en un formato que se muestra en la interfaz.

### `WorkstationService`
Servicio asociado a una estación de trabajo específica. Produce componentes del tipo definido por la estación y los almacena en el buffer. Cada instancia de `WorkstationService` se ejecuta en un hilo separado.

### `Scheduler`
Clase de utilidad que inicia las instancias de `WorkstationService` y controla la frecuencia de producción de los componentes en la línea de ensamblaje. También se encarga de iniciar el `Visualizer`.

### `Visualizer`
Recupera continuamente componentes del buffer y llama a `FactoryService` para actualizar la distribución en la interfaz web.

## Interfaz de Usuario

El archivo `galton.html` en el directorio `resources/templates` utiliza `Chart.js` para visualizar la distribución de los componentes en tiempo real. Al acceder a `http://localhost:8080/`, se inicializa el gráfico y se actualiza automáticamente cada segundo para mostrar la distribución actual.

- **startFactory**: Al cargar la página, se inicia automáticamente la producción.
- **fetchDistribution**: Solicita datos de distribución del backend y actualiza el gráfico en el frontend, mostrando cómo se distribuyen los componentes a lo largo del tiempo.

## Flujo de Ejecución

1. **Inicio de la Producción**: Cuando el usuario accede a la interfaz en `http://localhost:8080/`, el método `startFactory` es ejecutado automáticamente, lo que envía una solicitud al backend para iniciar la producción mediante el endpoint `/start`.

2. **Generación de Componentes**: `FactoryService` inicia múltiples hilos que simulan bolas cayendo a través de un tablero (simulado por la clase `Ball`). Cada bola elige aleatoriamente su posición final, y esta se registra en la distribución.

3. **Visualización de la Distribución**: Mientras la producción avanza, `Visualizer` toma componentes del buffer y actualiza la distribución en `FactoryService`. Estos datos de distribución son accesibles desde el frontend a través del endpoint `/distribution`, que los presenta en tiempo real usando un gráfico de líneas en `galton.html`.

4. **Actualización en Tiempo Real**: La interfaz solicita la distribución al backend cada segundo, y `Chart.js` refresca el gráfico para mostrar la última distribución, reflejando cómo se acumulan los componentes a lo largo de distintas posiciones.



