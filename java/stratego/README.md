# Using Stratego code from a Spoofax project in Java

Use `./copy-and-install-stratego.sh` in this directory to copy the jar file with compiled Stratego code from the front project, and install it in a local Maven repository. _This requires that you've built the front project first!_

Then build with Maven, and run `java -jar target/stratego-0.1.0.jar` to see the Java code output the input AST and output AST to a call to `desugar` to STDOUT.
