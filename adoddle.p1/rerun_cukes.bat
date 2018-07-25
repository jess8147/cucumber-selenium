@echo off
mvn clean test && Timeout 10 && mvn test -Dtest=org.asite.automation.adoddle.p1.testrunners.RerunTest -DfailIfNoTests=false
