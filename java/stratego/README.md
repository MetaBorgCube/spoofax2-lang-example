# Using Stratego code from a Spoofax project in Java

Use `./copy-and-install-stratego.sh` in this directory to copy the jar file with compiled Stratego code from the front project, and install it in a local Maven repository. _This requires that you've built the front project first!_

Then build with Maven, and run `java -jar target/stratego-0.1.0.jar` to see the Java code output the input AST and output AST to a call to `desugar` to STDOUT.

## Limitations

Note that if you use Stratego code from `libspoofax` in your Spoofax project, the compiled Stratego code may attempt to call into the Spoofax ecosystem. However, since this example code is attempting to show minimal Java dependencies, it does not depend on and start up Spoofax Core in the background. Therefore, if you run Stratego code in the way presented here, it may fail at run time when attempting to call into Spoofax. This will likely result in a `StrategoException("Primitive not defined: " + name)`. 
