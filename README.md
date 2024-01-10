# valorant-api-client
Simple Java Valorant client consuming official [Riot API](https://developer.riotgames.com/apis).

## Status

| Type | Status |
| ---- | ------ |
| Build | [![Maven build CI](https://github.com/reuhreuh/valorant-api-client/actions/workflows/maven-master.yml/badge.svg)](https://github.com/reuhreuh/valorant-api-client/actions/workflows/maven-master.yml)|
| Quality | [![CodeQL](https://github.com/reuhreuh/valorant-api-client/actions/workflows/codeql.yml/badge.svg)](https://github.com/reuhreuh/valorant-api-client/actions/workflows/codeql.yml)|
| Javadoc | [![javadoc](https://javadoc.io/badge2/net.rr-world/valorant-api-client/javadoc.svg)](https://javadoc.io/doc/net.rr-world/valorant-api-client)|
| Tests | ![GitHub Workflow Status](https://raw.githubusercontent.com/reuhreuh/valorant-api-client/master/.github/badges/jacoco.svg)|
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
  <valorant.client.version>1.0.13</valorant.client.version>
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
### v1.0.14 (TBD)
- ...
### v1.0.13 (2024-01-10)
- Fix `Outlaw` identifier
### v1.0.12 (2024-01-10)
- Add new Episode 8
- Add new weapon `Outlaw`
- Add `PremierMatchInfo` attribute on `Match`
### v1.0.11 (2023-10-31)
- Add new Agent `Iso`
### v1.0.10 (2023-09-07)
- Add new Map `Sunset`
- Add `accountLevel` and `isObserver` mapping on `Player`
### v1.0.9 (2023-07-01)
- Add new Episode 7
- Add new Agent `Deadlock`
### v1.0.8 (2023-03-08)
- Add new Agent `Gekko`
### v1.0.7 (2023-02-19)
- Upgrade Spring version
- Use concrete classes for `RoundResult` few attributes (`bombDefuser`, `defusePlayerLocations`)
- Use one single object `Location`
### v1.0.6 (2023-01-11)
- Add Episode 6, with 3 new Acts
- Add new Map `Lotus`
### v1.0.5 (2022-11-07)
- Fix Harbor identifier
- Upgrade spring starter version
### v1.0.4 (2022-10-17)
- Add new Agent `Harbor`
### v1.0.3 (2022-06-22)
- Add new Episode 5
- Add new map `Pearl`
### v1.0.2 (2022-05-07)
- Add Agents role
- Add Chamber & Neon abilities/Ultimate as Weapon (as seen by Riot API) 
### v1.0.1 (2022-04-27)
- Package refactoring
- Add new Enum value for `Fade` Agent
### v1.0.0 (2022-04-20)
- Init
