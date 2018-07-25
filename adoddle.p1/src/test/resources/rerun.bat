@echo off
setlocal enabledelayedexpansion
call mvn clean test -P P1Test
Timeout 10
del /q /s .\target\generated-test-sources\*.*
FOR %%I in (target\cucumber-parallel\*.rerun) do (if %%~zI equ 0 del "%%I") 
FOR %%f IN (target\cucumber-parallel\*.rerun) DO type ""%%f"" >> "target\rerun.txt" && mvn test -Dtest=org.asite.automation.adoddle.p1.testrunners.RerunTest -DfailIfNoTests=false && del "target\rerun.txt"