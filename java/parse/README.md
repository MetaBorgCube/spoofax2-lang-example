# Using the parser from a Spoofax project in Java

Use `./copy-parsetable.sh` in this directory to copy the parse table file from the front project. _This requires that you've built the front project first!_

Then build with Maven, and run `java -jar target/parse-0.1.0.jar ../../front/front.example/test.fro` to see the parser parse and output an abstract syntax tree to STDOUT.
