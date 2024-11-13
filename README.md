<a id="readme-top"></a>

<div align="center">
    <img src="images/logo-noback.png" alt="logo" width="400" />
    <p align="center">
        Android Kotlin project of a video game viewer
    </p>
</div>

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>


## About The Project

<div align="center">
    <img src="images/banner.png" alt="logo" width="1200" />
</div>


Android project for a game and stream search app. This research project was built using Kotlin, Retrofit, and Jetpack Compose.

The goal of the project is to build a data visualizer for games. The app allows you to query data about games, search for games, and view the top Twitch streams for a given game.

The data is obtained through the Twitch and IGDB APIs.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

<table border=0>
    <tr>
        <td><img src="https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white"></td>
        <td><img src="https://img.shields.io/badge/Kotlin-B125EA?style=for-the-badge&logo=kotlin&logoColor=white"></td>
        <td><img src="https://img.shields.io/badge/Twitch-9146FF?style=for-the-badge&logo=twitch&logoColor=white"></td>
        <td><img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"></td>
        <td><img src="https://img.shields.io/badge/material%20design-757575?style=for-the-badge&logo=material%20design&logoColor=white"></td>
    </tr>
</table>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started

The project is using Android Studio Koala

### Instalation

1. Clone the repo

```sh
git clone git@github.com:Danilo-Oliveira-Silva/weather-track.git
```

2. Altere o arquivo `/app/src/main/java/com/betrybe/arcadeatlas/core/di/ApiModule.kt` para definir as chaves de API da Twitch e IGDB.

```kotlin
fun provideApi() : ApiInterface {
        val baseUrl = "https://api.igdb.com/v4/"
        val bearerToken = "token-igdb"
        val clientId = "client-id-twitch"
}
```


```kotlin
 fun provideApiTwitch() : ApiInterfaceTwitch {
        val baseUrl = "https://api.twitch.tv/"
        val bearerToken = "token-twitch"
        val clientId = "client-id-twitch"
 }
 ```

## Usage

<div align="center">
    <table border=0>
        <tr>
            <td><img src="images/screenshot-1.png" width="200"></td>
            <td><img src="images/screenshot-2.png" width="200"></td>
            <td><img src="images/screenshot-3.png" width="200"></td>
            <td><img src="images/screenshot-4.png" width="200"></td>
            <td><img src="images/screenshot-5.png" width="200"></td>
        </tr>
    </table>
</div>

This project requires authentication on Twitch API.


<p align="right">(<a href="#readme-top">back to top</a>)</p>


## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contact

Danilo Silva

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/danilodevs/)
[![website](https://img.shields.io/badge/website-580ea1?style=for-the-badge&logo=twitter&logoColor=white)](https://www.iamdanilo.com/)
[![bluesky](https://img.shields.io/badge/bluesky-1DA1F2?style=for-the-badge&logo=bluesky&logoColor=white)](https://bsky.app/profile/danilodev.bsky.social)
[![email](https://img.shields.io/static/v1?label=&message=E-mail&color=007722&style=for-the-badge&logo=mail.ru)](mailto:danilo.o.s@hotmail.com)
[![devto](https://img.shields.io/badge/dev.to-0A0A0A?style=for-the-badge&logo=devdotto&logoColor=white)](https://dev.to/danilosilva)

<p align="right">(<a href="#readme-top">back to top</a>)</p>