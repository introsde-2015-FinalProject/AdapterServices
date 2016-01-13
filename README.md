# AdapterServices

# **@GET /motivation**

Get motivation joke phrase of Chuck Norris used to motivate the user.
| GET https://as-enigmatic-journey-9195.herokuapp.com/sdelab/person/motivation |
|------------------------------------------------------------------------------|

| Parameter | Description | Required? |
|-----------|-------------|-----------|
| N/A | N/A  | N/A |

# **@GET /weather**

Get current weather data for one location.
| GET https://as-enigmatic-journey-9195.herokuapp.com/sdelab/person/weather?city=Trento,it&units=metric&mode=json |
|-----------------------------------------------------------------------------------------------------------------|

| Parameter | Description | Required? |
|-----------|-------------|-----------|
| city | <Trento>,<it> location and nation code for which get current weather data  | YES |
| units | <metric> type of units to use for measure  | YES |
| mode | <json> type of return data  | YES |

# **@GET /forecast**

Get weather forecast for 5 days with data every 3 hours by city name.
| GET https://as-enigmatic-journey-9195.herokuapp.com/sdelab/person/forecast?city=Trento,it&units=metric&mode=json |
|------------------------------------------------------------------------------------------------------------------|

| Parameter | Description | Required? |
|-----------|-------------|-----------|
| city | <Trento>,<it> location and nation code for which get forecast weather data  | YES |
| units | <metric> type of units to use for measure  | YES |
| mode | <json> type of return data  | YES |
