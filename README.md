# Desafio BCI
***
# para ver la documentacion de swagger ingresar a:

# http://localhost:7979/swagger-ui/index.html

# Dependencias necesarias:

## Java 11, gradle 6.8

### Para iniciar el proyecto se debe importar a tu ide, en mi caso
### utilizo intelliJ, se deben descargar las dependencias, esto se puede realizar de la siguiente manera:

### entrar en una consola en la carpeta del proyecto y ejecutar 'gradle build', caso contrario utilizar el ide.

### luego de eso ya se puede arrancar la aplicacion dandole al boton play.
***
# Docker
## para iniciar el proyecto con docker realizar los siguientes comandos.

### gradlew clean build -x test

### docker image build -t app .

### docker images (para ver id de la imagen)

### docker run -d -p 7979:7979 image-id(id de comando anterior)

### el puerto de la aplicacion es 7979