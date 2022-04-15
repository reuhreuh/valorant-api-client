# valorant-api-client
Simple Java Valorant client consuming on official Riot API

## Features
valorant-api-client provides 1 to 1 SDK to read official Valorant Riot API. There is no data transformation, no statistics calculation. 

So far following end-points are implemented:

VAL-MATCH-V1

- /val/match/v1/matches/{matchId}
- /val/match/v1/matchlists/by-puuid/{puuid} (very soon)

The SDK also provides a set of Java Enums for Valorant model (with UUIDs used in API) for :
- Acts
- Agents
- Maps
- Regions
- Weapons


## Getting started

### Prerequisites
What you need is :
- A Valorant Riot API key
- Java 11 & Maven

### Installation
Import dependency in your pom.xml :

```xml
<properties>
  ...
  <!-- Use the latest version whenever possible. -->
  <valorant.client.version>1.0.0-SNAPSHOT</valorant.client.version>
  ...
</properties>

<dependencies>
  ...
  <dependency>
	<groupId>net.rrworld</groupId>
	<artifactId>valorant-api-client</artifactId>
    <version>${valorant.client.version}</version>
  </dependency>
  ...
</dependencies>
```

### Usage
Just instanciate the client with API key and Region :

```java
	ValorantClient client = new ValorantClient("RGAPI-XXX", Region.EU);
	Match m = client.getMatch("de3dc2b3-7db8-4b96-8a8a-17e5cee8e634");
```

If you are in a Spring context, you may wish to initialize a bean using your RestTemplate instance :

```java
	@Bean
	public ValorantClient getValorantClient(@Autowired RestTemplate restClient) {
		return  client = new ValorantClient("RGAPI-XXX", Region.EU, restClient);
	}
```


## Documentation
