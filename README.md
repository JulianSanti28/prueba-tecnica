# Descripción General
Este desarrollo se encuentra elaborado en dentro de la tecnología de Spring Boot, se hace uso de lenguaje de programación Java en su versión 11. Además la gestión de dependencias se hace atraves del gestor Gradle.

# Clone este repositorio siguiendo las siguientes instrucciones
Ubíquese en la carpeta en la que desea descargar el proyecto, a continuación ejecute el siguiente comando git
```sh
git clone https://github.com/JulianSanti28/prueba-tecnica.git
```
A continuacón se descargará el repositorio y dentro de la carpeta generada estará el proyecto. Este recibe el nombre de: "prueba-tecnica-software-colombia"

# Instrucciones de compilación
El proyecto ya cuenta en su base path con el archivo usado como repositorio (movies.csv). No es necesario hacer nada adicional con este archivo.

- [x] Usando el IDE, simplemente limpie y construya el proyecto. Luego vaya al método principal y ejecute el programa
- [x] El proyecto iniciará en el puerto 8080.
- [x] Ahora está listo para recibir peticiones en cada uno de sus 3 endpoints.  

# Enpoints

## El primer enpoint permite obtener un registro dentro del repositorio por el id de la película. Acceda a esta URL desde un cliente REST como Postman o el mismo navegador web para acceder a la respuesta del servicio.

```sh
http://localhost:8080/movie?id=15
```
Este servicio REST responderá con un código de estado 200 (Ok) en caso de realizar un proceso correcto y además obtener un registro que coincide con el id dado.
En caso de no encontrar el id, ek servicio responderá con un código de estado 404 Not Found, y un cuerpo de la solicitud de una película sin ninguna información.

![image](https://user-images.githubusercontent.com/67648220/185752050-5cb785a3-1157-4897-8d68-96f9cc78f4b5.png)

![image](https://user-images.githubusercontent.com/67648220/185752069-9ed82bf8-1ad6-4ba5-a052-92c35e9e7c47.png)


## El segundo enpoint permite obtener una cantidad de registros dada ordenados de forma descendente o ascendente según sea el valor del parámetro en la URL. Acceda a esta URL para interactuar con el servicio.

```sh
http://localhost:8080/movies?total=5&order=asc
```
En este caso los resultados se limitan a 5 registros ordenador de forma ascendente por el id de cada una de las películas.
Este servicio responderá con un código de estado 200(Ok) en caso de encontrar registros y procesar correctamente la petición.
En caso no encontrar coincidencias con algún valor de entrada, entonces la respuesta tendrá un código de estado 400 Bad Request y además responderá con un cuerpo que refleja el siguiente mensaje:

![image](https://user-images.githubusercontent.com/67648220/185751929-f53d0f6f-095e-4daf-92a8-dc2790aac5a2.png)

Un ejemplo de una petición sin coincidencias podría ser el siguiente:

```sh
http://localhost:8080/movies?total=5&order=cvv
```

Como se logra identificar, el cliente está haciendo un mal uso del atributo "order" por ende es una petición que no se encuentra bien formada.



