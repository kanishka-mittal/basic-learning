# Basic Learner Setup

Project imported from [GitLab](https://gitlab.science.ru.nl/ramonjanssen/basic-learning/), modified such that build/dependency management is handled by Maven. 
All credit goes to the original author (Ramon Janssen).
The repo contains a basic learner setup. It makes use of learlib and automatalib ( http://learnlib.de/ ). 

BasicLearner is the most important class for external use. ExampleExperiment.java contains an example main-method of how to call the basic learner, and learns a hard-coded example SUL. BasicLearner contains some utility methods to quickly start learning. It also contains some learning and testing (equivalence checking) methods, including one which lets the user search for counterexamples. These can be used in the utility methods as parameters. It also contains some settings as simple static attributes; use them by simply changing their settings before starting a learning experiment. 

The project contains two SUL-interfaces: an example in Java-code, and a socket-wrapper which you can connect to your own SUL. Furthermore, it contains a simple observation tree used to check consistency of observations within an experiment (and give an error upon non-determinism).
