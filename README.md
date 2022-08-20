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

Método: GET

```sh
http://localhost:8080/movie?id=15
```
Este servicio REST responderá con un código de estado 200 (Ok) en caso de realizar un proceso correcto y además obtener un registro que coincide con el id dado.

![image](https://user-images.githubusercontent.com/67648220/185752050-5cb785a3-1157-4897-8d68-96f9cc78f4b5.png)

En caso de no encontrar el id, el servicio responderá con un código de estado 404 Not Found, y un cuerpo de la solicitud de una película sin ninguna información.

![image](https://user-images.githubusercontent.com/67648220/185752069-9ed82bf8-1ad6-4ba5-a052-92c35e9e7c47.png)


## El segundo enpoint permite obtener una cantidad de registros dada ordenados de forma descendente o ascendente según sea el valor del parámetro en la URL. Acceda a esta URL para interactuar con el servicio.

Método: GET 

```sh
http://localhost:8080/movies?total=5&order=asc
```
En este caso los resultados se limitan a 5 registros ordenador de forma ascendente por el nombre de cada una de las películas.
Este servicio responderá con un código de estado 200(Ok) en caso de encontrar registros y procesar correctamente la petición. El atributo que específica el tipo
de ordenamiento (asc o desc) se puede envíar también usando mayúsculas. El sistema tolera este comportamiento.

La respuesta correcta sería la siguiente

![image](https://user-images.githubusercontent.com/67648220/185762578-7f79aa77-1fa3-43e5-9a83-333c4a9ec7d0.png)

En caso no encontrar coincidencias con algún valor de entrada, entonces la respuesta tendrá un código de estado 400 Bad Request y además responderá con un cuerpo que refleja un mensaje.

Un ejemplo de una petición sin coincidencias podría ser el siguiente:

```sh
http://localhost:8080/movies?total=5&order=cvv
```
La respuesta obtenida sería la siguiente

![image](https://user-images.githubusercontent.com/67648220/185751929-f53d0f6f-095e-4daf-92a8-dc2790aac5a2.png)

Como se logra identificar, el cliente está haciendo un mal uso del atributo "order" por ende es una petición que no se encuentra bien formada.

## El tercer y último endpoint permite guardar un registro dentro del repositorio. Se espera recibir el cuerpo del modelo Movie y recibir un mensaje de confirmación del registro. Acceda a esta URL para acceder el servicio.

Método : POST

```sh
http://localhost:8080/movie
```
Envíe dentro del cuerpo la petición la información a guardar, por ejemplo:

```sh
{
"id": 79,
"film":"Parasite",
"genre": "Drama",
"studio": "Barunson E&A",
"score": 97,
"year": 2019
}
```

Recibirá un código de estado 201 Created en caso de guardar correctamente y un mensaje como el siguiente

![image](https://user-images.githubusercontent.com/67648220/185752520-f5ccd614-909e-4ab1-8f70-09f4eb6a0e8c.png)

En caso de que no se pueda realizar el registro, quizá por información inválida, por ejemplo

```sh
{
"id": 79,
"film":"Parasite",
"genre": "Drama",
"studio": "Barunson E&A",
"score": 97,
"year": 0
}
```
En donde el año es 0, el servicio responderá de la siguiente manera.

![image](https://user-images.githubusercontent.com/67648220/185752576-0317ef23-7ac1-435e-bfcd-7ac3081a805c.png)

En donde se específica el mensaje y además el código 500 de Error Interno del Servidor.




