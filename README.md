# Darker-Side-Randomizer
The Darker Side Randomizer is a WIP project with the end-goal being a website which does the following:
- Generates a list of 500 moons, excluding the moon "Long Journey's End", which can be collected to access Darker Side.
- Has sufficient logic to never generate a moon with prerequisites if the prerequisites are not also met.
- Allows for options to turn Toadette achievements on or off, as well as specific achievements (5k coins, purple coin spending).
- Generates postrequisite moons frequently enough. This is subjective and will take some fine-tuning.

The source code for this project is available at: https://github.com/JKTKops/Darker-Side-Randomizer
Suggestions and ideas are welcome in the #randomizer channel of the Super Mario Odyssey Speedrunning Discord server.

The main method in the Randomizer class will now successfully generate a randomized run.
Changing the "seed" parameter of the randomize() method is necessary to obtain different runs.
 
Future goals include:
- Some general code cleanup
- Options to not force World Peace (long-term, difficult)
- Dynamic interaction with the Capturing Master moon (difficulty is currently hard to assess)
