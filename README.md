# threadPool
The ThreadPool project represents a use case of a custom thread pool that executes tasks with a certain delay. 
The use case is the simulator of manipulating by a lunar station from Earth. Having the known average distance between Earth and Moon,
one can estimate that the operator on Earth gets response from the lunar station no sooner than after 2 seconds.
The simulated transmitter (a thread pool) contains three channels (threads) that accept commands (runnables), which are executed after the 2 sec delay.
For demo purposes, the station recognizes only two commands: gt (alias to gettime) and tp (alias to takephoto).
The operator on Earth can send (e.g. type) commands as quickly as he can. The commands are stored in a queue at the transmitter side.
Once any channel releases from the previous command, it peeks the next command from the queue and sends to the Moon.
The channel waits for response, thus it cannot send another command while waiting.

To run the simulator, run $ java Simulator
On Linux or MacOS, it would be better to open the second terminal for output.
On the second terminal, run $ ttyd
It would return that name of the device associated with the terminal, for example, tty002
On the first terminal, run the simulator redirecting its output to the second terminal: $ java Simulator > /dev/tty002
replacing the device name to the one returned by ttyd.

Type commands, pressing <Enter>: "gt" or "tp"
See the delayed lunar station response in the second terminal.
To stop the session, type "exit".
