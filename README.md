# valorant-api-client
Simple Java Valorant client consuming official [Riot API](https://developer.riotgames.com/apis).

## Status

| Type | Status |
| ---- | ------ |
| Build | [![Maven build CI](https://github.com/reuhreuh/valorant-api-client/actions/workflows/maven.yml/badge.svg)](https://github.com/reuhreuh/valorant-api-client/actions/workflows/maven.yml)|
| Javadoc | [![javadoc](https://javadoc.io/badge2/net.rr-world/valorant-api-client/javadoc.svg)](https://javadoc.io/doc/net.rr-world/valorant-api-client)|
| Tests | ![GitHub Workflow Status](https://raw.githubusercontent.com/reuhreuh/valorant-api-client/master/.github/badges/jacoco.svg)|
| CodeQ (LGTM.com) | [![Total alerts](https://img.shields.io/lgtm/alerts/g/reuhreuh/valorant-api-client.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/reuhreuh/valorant-api-client/alerts/) [![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/reuhreuh/valorant-api-client.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/reuhreuh/valorant-api-client/context:java)|
|Licence | [![GitHub license](https://img.shields.io/github/license/reuhreuh/valorant-api-client)](https://github.com/reuhreuh/valorant-api-client/blob/master/LICENSE)|


## Features
`valorant-api-client` provides 1 to 1 SDK to read official Valorant Riot API. There is no data transformation, no statistics calculation at least for now...;)

So far following end-points are implemented:

`VAL-MATCH-V1`

- /val/match/v1/matches/{matchId}
- /val/match/v1/matchlists/by-puuid/{puuid}

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
Import dependency in your `pom.xml` :

```xml
<properties>
  ...
  <!-- Use the latest version whenever possible. -->
  <valorant.client.version>1.0.3</valorant.client.version>
  ...
</properties>

<dependencies>
  ...
  <dependency>
    <groupId>net.rr-world</groupId>
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

If you are in a Spring context, you may wish to initialize a bean using your `RestTemplate` instance :

```java
@Bean
public ValorantClient getClient(@Autowired RestTemplate restClient, @Value("${riot.api.key}") String apiKey) {
	return new ValorantClient(apiKey, Region.EU, restClient);
}
```

## Documentation
Javadoc is available [here](https://javadoc.io/doc/net.rr-world/valorant-api-client/latest/index.html)

## Changelog
### v1.0.3 (2022-06-22)
- Add new Episode 5
- Add new map Pearl
### v1.0.2 (2022-05-07)
- Add Agents role
- Add Chamber & Neon abilities/Ultimate as Weapon (as seen by Riot API) 
### v1.0.1 (2022-04-27)
- Package refactoring
- Add new Enum value for Fade Agent
### v1.0.0 (2022-04-20)
- Init
