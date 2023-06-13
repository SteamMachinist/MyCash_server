plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"

}

group = "tp35"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}


repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")

	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")

	implementation ("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor ("org.mapstruct:mapstruct-processor:1.5.5.Final")

	implementation ("com.auth0:java-jwt:4.4.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

	implementation("org.apache.commons:commons-math3:3.6.1")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.3")

	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

	implementation("com.opencsv:opencsv:3.7")

	testImplementation("junit:junit:4.13.1")
	testImplementation("org.testcontainers:testcontainers:1.18.3")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")
	testImplementation("org.mockito:mockito-core:2.1.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
