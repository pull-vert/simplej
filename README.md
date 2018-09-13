# simplej

SimpleJ is a statically typed programming language.
 
Each SimpleJ file (*.sj) is parsed, and then generates Java file(s).
Java generation is done by a gradle or maven plugin.

A few SimpleJ concepts :
* NullPointer safe
* More concise than java
* No primitive type, everything is an Object
* No need to handle Checked Exception
* public is the default visibility
* classes & functions are final by default
* Collections are not mutable by default

SimpleJ generates java file for following targets :
* Java 8
* Java 10
* Java 11
