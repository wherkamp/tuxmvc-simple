# tuxmvc-simple
## Building a Website
### Maven
```xml

   <repository>
      <id>kingtux-repo</id>
      <url>http://repo.kingtux.me/repository/maven-public/</url>
    </repository>
    <dependency>
      <groupId>me.kingtux</groupId>
      <artifactId>tuxmvc-simple</artifactId>
      <!---Make sure you use Latest Version!-->
      <version>1.0</version>
      <scope>compile</scope>
    </dependency>
```
### Gradle
```
repositories {
  maven { url 'http://repo.kingtux.me/repository/maven-public/' }
}

dependencies {
   compile "me.kingtux:tuxmvc-simple:1.0"
}
```
### The Builder
```java
import me.kingtux.tmvc.core.Website;
import me.kingtux.tmvc.core.view.templategrabbers.IETemplateGrabber;
import me.kingtux.tuxmvc.simple.SimpleWebsiteBuilder;

import java.io.File;

class Main{
     public static void main(String[] args) {
         Website website;
         SimpleWebsiteBuilder websiteBuilder = SimpleWebsiteBuilder.create();
         websiteBuilder.host("www.my.site.domain");
         //Setting the Template Grabber. Their are 3 of these. IETemplateGrabber(WIll grab from internal or external location)
         // ExternalTemplateGrabber(All Templates will be grabbed from the external) InternalTemplateGrabber(It only grabs from inside the jar;
         websiteBuilder.templateGrabber(new IETemplateGrabber(new File("tmpls"), "templates"));
         //The port
         websiteBuilder.port(9080);
         //websiteBuilder.ssl() the ssl. https://docs.kingtux.me/tuxmvc-simple/index.html?me/kingtux/tuxmvc/simple/SimpleWebsiteBuilder.html
         //Build the website
         website= websiteBuilder.build();
     }
 }
```