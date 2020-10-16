IO Simulator

This URCap is made for simulating input registor [64...67]. Only works for e-series. Version >= 1.8.
To run this program, clone the project or download it as a zip. Open the project in an IDE or just open a terminal and direct to the resource folder and then the library folder and install the jars by running the following maven commands:

mvn install:install-file -Dfile=skynet-ur-rtde-1.0-SNAPSHOT -DgroupId=com.github.o5h.skynet -DartifactId=skynet-ur-rtde -Dversion=1.0-SNAPSHOT -Dpackaging=jar

mvn install:install-file -Dfile=skynet-sl4j-1.0-SNAPSHOT -DgroupId=com.github.o5h.skynet -DartifactId=skynet-sl4j -Dversion=1.0-SNAPSHOT -Dpackaging=jar

Now you can deploy the URCap to the URSim or UR robot like you used to.

To test the URCap you can create a robot program:
- waits for and input register using the following script "read_input_boolean_register(X) X:[64...67]
- Popup saying e.g true.

Now run the program. 
While the program is running, use the toolbar created by the URCap, and click on one of the input that you are checking e.g. input register 64. It should enable the popup in the robot program.
