# Distribuidos-Profeco
Proyecto final

Primero se corre el microservicio de configuraciones
Después el servicio de eureka (Service-registry)
Luego el servicio de API-Gateway
Y los microservicios en el orden que desees.

Es probable que al correr un microservicio, este
tarde en ser reconocido por la API Gateway y dar error
500, es normal, solo es cuestión de esperar 1-2 minutos

Para cuando necesites hacer peticiones POST, leer el archivo txt "Formatos para POST en JSON archivo informativo". 