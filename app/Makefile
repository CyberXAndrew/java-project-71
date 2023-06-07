.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

c:
	./gradlew clean

build:
	./gradlew clean build

b:
	./gradlew build

id:
	./gradlew installDist

install:
	./gradlew clean install

run-dist:
	./build/install/java-package/bin/java-package

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions


build-run: build run

.PHONY: build