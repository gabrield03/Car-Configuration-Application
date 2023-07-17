# Car-Configuration-Application
Configure a car of your choice from a file of car options

Description: 
This program uses a client-server model to act as a rudimentary kbb.com and has two main functions.
1. The client gives an Automobile template (all information about the Automobile) to the server. The server receives it, processes the data, and 
adds the Automobile to a list (linked hash map) of Automobiles that can be selected for later configuration by the client. After the server
has added the Automobile to the linked hash map, it notifies the client that it was successfully added.
2. The client can request an Automobile from the server, select an Automobile, and configure it with the desired options. The server responds to the
client's request by providing a list of Automobiles in the linked hash map. When one is selected, it sends an instance to the client for configuration.
After the client configures the Automobile, the total configuration data (including the total cost) is printed to the screen.


How to use:
We run the program in two separate command line terminals. We compile both projects and run them (starting with the server). For the first function
(to add automobiles to the linked hash map) the client must provide a file path to the Automobile. 
Note: The file can be either .prop or .txt and the server will process both fine.
For the second function (configuring an automobile), the client must first provide Automobile files for the server to add to the linked hash map.
Once this is done, the client requests to configure an Automobile, and the server responds by sending an instance of an Automobile to the client.
The client then selects the desired options. After completing this, the total configuration is printed to the screen.

Notes:
The goal of this program is to get familiar with building and working with a client-server model. 
Input validation is left for a future implementation
