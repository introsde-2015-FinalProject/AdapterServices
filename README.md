# AdapterServices

The Adapter Service is the service that communicate with third party services. This layer gets data like weather, weather forecast and motivation phrases from external data sources. In order to interact with external services, this layer uses external API:

* weather [http://openweathermap.org/current](http://openweathermap.org/current);
* weather forecast [http://openweathermap.org/forecast5](http://openweathermap.org/forecast5);
* motivation phrases [http://www.icndb.com/api/](http://www.icndb.com/api/).

[API Documentation](https://github.com/introsde-2015-FinalProject/AdapterServices/wiki/API-Documentation)  
[URL of the server (heroku)](https://as-enigmatic-journey-9195.herokuapp.com/sdelab/)

### Install
In order to execute this server locally you need the following technologies (in the brackets you see the version used to develop):

* Java (jdk1.8.0)
* ANT (version 1.9.4)

Then, clone the repository. Run in your terminal:

```
git clone https://github.com/introsde-2015-FinalProject/AdapterServices.git && cd AdapterServices
```

and run the following command:
```
ant install
```

### Getting Started
To run the server locally then run:
```
ant start
```