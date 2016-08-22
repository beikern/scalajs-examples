# Example Scala.js application 

This is an example of an application written in
[Scala.js](http://www.scala-js.org/).

## Get started

To get started, run `sbt ~fastOptJS` in this example project. This should
download dependencies and prepare the relevant javascript files. If you open
`localhost:12345/target/scala-2.11/classes/index-dev.html` in your browser, it will show you a clock 

## The optimized version

Run `sbt fullOptJS` and open up `index-opt.html` for an optimized (~200kb) version
of the final application, useful for final publication.

