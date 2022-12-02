The application is written in kotlin and is expected to be executed with kscript.

Commands to install required depedencies are.

```
brew install kotlin

sdk install kscript
```

first system argument can be supplied that is a filepath string leading to a file containing the data required to solve the problem
to run the application run

```
kscript day1.kts <optional filepath parameter | default is a file called test in the same directory as this file>
```

or

```
./day1.kts <optional filepath parameter | default is a file called test in the same directory as this file>
```