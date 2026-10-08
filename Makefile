
.PHONY: compile test package


compile:
	mvn clean compile

test:
	mvn test

package:
	mvn package -DskipTests