# Buildah-java

[![Circle CI](https://circleci.com/gh/jshiftio/buildah-java/tree/master.svg?style=shield)](https://circleci.com/gh/jshiftio/buildah-java/tree/master)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=jshiftio_buildah-java&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=jshiftio_buildah-java)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=jshiftio_buildah-java&metric=sqale_index)](https://sonarcloud.io/dashboard?id=jshiftio_buildah-java)

This library provides a wrapper for [Buildah](https://github.com/containers/buildah) which is a CLI tool for building OCI images.

* **Buildah Java** <br/>
[![Maven Central](https://img.shields.io/maven-central/v/io.jshift/buildah-java.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.jshift%22%20AND%20a:%22buildah-java%22)
* **Buildah Core** <br/>
[![Maven Central](https://img.shields.io/maven-central/v/io.jshift/buildah-core.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.jshift%22%20AND%20a:%22buildah-core%22)

Buildah-java is composed of a `core` module which is the front interface to execute Buildah commands.
All integrators should use this interface as its base to call Buildah from Java.

## Installation

## Core

Core module acts as an interface between Java and Buildah.

To install it you need to add next dependency:

 ```
    <dependency>
      <groupId>io.jshift</groupId>
      <artifactId>buildah-core</artifactId>
      <version>${project.version}</version>
    </dependency>
 ```

Buildah-java does not assume where Buildah is installed, so you need to provide the location of Buildah home.
But you can skip this step if you add next dependency:

 ```
    <dependency>
      <groupId>io.jshift</groupId>
      <artifactId>buildah-binary</artifactId>
      <version>${project.version}</version>
    </dependency>`
 ```

If you do this, Buildah will be automatically installed and you'll be ready to use Buildah-java without having to install Buildah manually nor setting the Buildah home directory.

## Usage

Assuming that you have `buildah-binary` in classpath.

* Create Buildah instance:

`final Buildah buildah = new Buildah();`

This will create `BuildahConfiguration` object and the Buildah will be installed in `/tmp/.../` directory.
Buildah can also be installed in the specified directory or projectDirectory:

* Create BuildahConfiguration instance:

`final BuildahConfiguration buildahConfiguration = new BuildahConfiguration();`

* Setting the installation directory

`buildahConfiguration.setInstallationDir(p);`

* Creating Buildah Instance with BuildahConfiguration instance as parameter

`final Buildah buildah = new Buildah(b);`

* Creating Buildah Container

`buildah.createContainer("java").build().execute();`

If base image is not present locally, it will pull it and build a container.

* Listing Buildah Containers

`buildah.listContainers().build().execute();`

* Listing Buildah Images

`buildah.listImages().build().execute();`

* Buildah Push

`buildah.push("imageToBePushed").creds("username:password").registry(Registry).build().execute();`

* Buildah Login

`buildah.login("registryName").username("username").password("password").build().execute();`

* Buildah Logout

`buildah.logout().registryName("registryName").build().execute();`

* Buildah Commit

 ```
buildah.commit("containerId or containerName").contRemAfterCommit(true).withImageName("targetImageName").build().execute();
 ```
* Buildah Config

 ```
 buildah.config("containerName").authorInfo("authorName").volume("volume to be set").workingDir("workingDir to be set").annotation(List<String>).port(List<String>).label(List<String>).build().execute();
 ```

* Buildah Add

`buildah.add("containerName","srcPath").destination("destPath").Build().execute();`

* Buildah Copy

`buildah.add("containerName","srcPath").destination("destPath").Build().execute();`

* Buildah Remove

`buildah.rm().containerId("containerName").build().execute();`

* Buildah Remove All

`buildah.rm().all(true).build().execute();`

* Buildah Remove Image

`buildah.rmi().image("imageId").build().execute();`

* Buildah Remove All Images

`buildah.rmi().all(true).build().execute();`

* Buildah Inspect Image

`buildah.inspect().type("image").image("imageId").build().execute();`

* Buildah Inspect Container

`buildah.inspect().type("container").container("containerId").build().execute();`

* Buildah build-using-dockerfile

 ```
 buildah.bud("context where dockerfile is located").dockerfileList(dockerfileList).targetImage("targetImageName").build().execute();
 ```

* Buildah Run

`buildah.run("containerName", "commandName").commandOptions(commandOptionsList).build().execute();`

* Buildah Pull

`buildah.pull("ImageName").build().execute();`
