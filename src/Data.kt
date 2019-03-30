import Kingdom.*
import Tags.*
import Captures.*

enum class Kingdom {
    Cap, Cascade, Sand, Lake, Wooded, Cloud, Lost, Metro, Snow, Seaside,
    Luncheon, Ruined, Bowser, Moon, Mushroom, DarkSide, Achievements;

    override fun toString(): String {
        return when (this) {
            Bowser -> "Bowser's"
            DarkSide -> "Dark Side"
            else -> this.name
        }
    }
}

enum class Tags {
    Art, Bird, Bit, Cappy, Chest, Fishing, Goombette, Gp, Hat, Kfr, Mips, Multi, Music, 
    Notes, Outfit, Peach, Seed, Sheep, Slots, Sphynx, Story, Timer, Toad, Tourist, Trace;
}

enum class Captures {
    Bill, Binoc, Banzai, Cactus, Chomp, CoinCoffer, Dino, FireBro, Fist, Frog, Glydon, GoldChomp,
    Goomba, HammerBro, LakePuzzle, Lakitu, MetroBlock, MetroLetter, Moe, Paragoomba, PurpleFish,
    RedFish, Sherm, Tree, Uproot, Zipper;
}

class Lists {
    companion object {
        val elements: List<ListElement> = mutableListOf(
            Separator(Cap, false),
            Moon("Frog-Jumping Above the Fog", Cap, false, "PG", captures = listOf(Frog)),
            Moon("Frog-Jumping From the Top Deck", Cap, false, "PG", captures = listOf(Frog)),
            Moon("Cap Kingdom Timer Challenge 1", Cap, false, "PG", tags = listOf(Timer)),
            Moon("Good Evening, Captain Toad", Cap, false, "PG", tags = listOf(Toad)),
            Moon("Shopping in Bonneton", Cap, false, "PG"),
            Moon("Skimming the Poison Tide", Cap, false, "PG", captures = listOf(Paragoomba)),
            Moon("Slipping Through the Poison Tide", Cap, false, "PG", captures = listOf(Paragoomba)),
            Moon("Push-Block Peril", Cap, false, "PG"),
            Moon("Hidden Among the Push-Blocks", Cap, false, "PG"),
            Moon("Searching the Frog Pond", Cap, false, "PG"),
            Moon("Secrets of the Frog Pond", Cap, false, "PG"),
            Moon("The Forgotten Treasure", Cap, false, "PG", tags = listOf(Gp)),
            Moon("Taxi Flying Through Bonneton", Cap, false, "PG", captures = listOf(Binoc)),
            Moon("Bonneter Blockade", Cap, false, "PG", captures = listOf(Paragoomba)),
            Moon("Cap Kingdom Regular Cup", Cap, false, "PG", tags = listOf(Kfr)),
            Moon("Peach in the Cap Kingdom", Cap, false, "PG", tags = listOf(Peach)),
            // TODO: It might be necesessary to move all art moons manually so they appear in the correct place
            Moon("Found with Cap Kingdom Art", Moon, false, "PG", tags = listOf(Art, Gp)),
            NecessaryAction("Check Hint Art: Cap Kingdom", Cap, false, arrayOf("Found with Cap Kingdom Art")),

            Moon("Next to Glasses Bridge", Cap, false, "MR"),
            Moon("Danger Sign", Cap, false, "MR", captures = listOf(Paragoomba)),
            Moon("Under the Big One's Brim", Cap, false, "MR"),
            Moon("Fly to the Edge of the Fog", Cap, false, "MR"),
            Moon("Spin the Hat, Get a Prize", Cap, false, "MR"),
            Moon("Hidden in a Sunken Hat", Cap, false, "MR", tags = listOf(Cappy)),
            Moon("Fog Shrouded Platform", Cap, false, "MR", tags = listOf(Gp)),
            Moon("Bird Traveling in the Fog", Cap, false, "MR", tags = listOf(Bird)),
            Moon("Caught Hopping Near the Ship!", Cap, false, "MR", tags = listOf(Mips)),
            Moon(
                "Taking Notes: In the Fog", Cap, false,
                "MR", tags = listOf(Notes), captures = listOf(Paragoomba)
            ),
            Moon("Cap Kingdom Timer Challenge 2", Cap, false, "MR", tags = listOf(Timer)),
            Moon(
                "Cap Kingdom Master Cup", Cap, false, "MR",
                tags = listOf(Kfr), prereqs = listOf("Cap Kingdom Regular Cup")
            ),
            Moon("Roll On and On", Cap, false, "MR"),
            Moon("Precision Rolling", Cap, false, "MR"),


            Separator(Cascade, true),
            Moon(
                "Our First Power Moon", Cascade, true,
                tags = listOf(Story), captures = listOf(Chomp)
            ),
            Moon(
                "Multi Moon Atop the Falls", Cascade, true,
                "First Moon", tags = listOf(Story), captures = listOf(GoldChomp)
            ),
            Moon("Chomp Through the Rocks", Cascade, true, "First Moon", captures = listOf(Chomp)),
            Moon(
                "Behind the Waterfall", Cascade, true, "First Moon",
                tags = listOf(Bit), captures = listOf(Chomp)
            ),

            Moon("On Top of the Rubble", Cascade, true, "Madame"),
            Moon("Treasure of the Waterfall Basin", Cascade, true, "Madame", tags = listOf(Chest)),
            Moon("Above a High Cliff", Cascade, true, "Madame"),
            Moon("Across the Floating Isles", Cascade, true, "Madame"),
            Moon("Cascade Kingdom Timer Challenge 1", Cascade, true, "Madame", tags = listOf(Timer)),
            Moon("Cascade Kingdom Timer Challenge 2", Cascade, true, "Madame", tags = listOf(Timer)),
            Moon("Good Morning, Captain Toad!", Cascade, true, "Madame", tags = listOf(Toad)),
            Moon("Dinosaur Nest: Big Cleanup!", Cascade, true, "Madame", captures = listOf(Dino)),
            Moon("Dinosaur Nest: Running Wild!", Cascade, true, "Madame", captures = listOf(Dino)),
            Moon("Nice Shot with the Chain Chomp", Cascade, true, "Madame", captures = listOf(Chomp)),
            Moon("Very Nice Shot with the Chain Chomp!", Cascade, true, "Madame", captures = listOf(Chomp)),
            Moon("Past the Chasm Lifts", Cascade, true, "Madame"),
            Moon("Hidden Chasm Passage", Cascade, true, "Madame", tags = listOf(Bit)),

            Separator(Cascade, false),
            Moon("Secret Path to Fossil Falls!", Cascade, false, "PG"),
            Moon("A Tourist in the Cascade Kingdom", Cascade, false, "PG", tags = listOf(Tourist)),
            Moon("Rolling Rock by the Falls", Cascade, false, "PG"),
            Moon("Peach in the Cascade Kingdom", Cascade, false, "PG", tags = listOf(Peach)),
            Moon("Cascade Kingdom Regular Cup", Cascade, false, "PG", tags = listOf(Kfr)),
            Moon("Caveman Cave-Fan", Cascade, false, "PG", tags = listOf(Outfit)),
            Moon("Shopping in Fossil Falls", Cascade, false, "PG"),
            Moon("Sphynx Traveling to the Waterfall", Cascade, false, "PG", captures = listOf(Binoc)),

            Moon("Bottom of the Waterfall Basin", Cascade, false, "MR", tags = listOf(Gp)),
            Moon("Just a Hat, Skip, and a Jump", Cascade, false, "MR"),
            Moon("Treasure Under the Cliff", Cascade, false, "MR"),
            Moon("Next to Stone Arch", Cascade, false, "MR"),
            Moon("Guarded by a Colassal Fossil", Cascade, false, "MR", tags = listOf(Cappy)),
            Moon(
                "Under the Old Electrical Pole", Cascade, false, "MR",
                tags = listOf(Gp), captures = listOf(Dino)
            ),
            Moon(
                "Under the Ground", Cascade, false, "MR",
                tags = listOf(Gp), captures = listOf(Dino)
            ),
            Moon("Inside the Busted Fossil", Cascade, false, "MR", captures = listOf(Chomp)),
            Moon("Caught Hopping at the Waterfall!", Cascade, false, "MR", tags = listOf(Mips)),
            Moon("Taking Notes: Hurry Upward", Cascade, false, "MR", tags = listOf(Bit, Notes)),
            Moon(
                "Cascade Kingdom Master Cup", Cascade, false, "MR",
                tags = listOf(Kfr), prereqs = listOf("Cascade Kingdom Regular Cup")
            ),
            Moon("Across the Mysterious Clouds", Cascade, false, "MR"),
            Moon("Atop a Wall Among the Clouds", Cascade, false, "MR"),
            Moon("Across the Gusty Bridges", Cascade, false, "MR"),
            Moon("Flying Far Away from Gusty Bridges", Cascade, false, "MR"),


            Separator(Sand, true),
            NecessaryAction("Talk to Tourist", Sand, true, arrayOf("A Tourist in the Metro Kingdom")),
            Moon("Atop the Highest Tower", Sand, true),
            Moon("Moon Shards in the Sand", Sand, true, prereqs = listOf("Atop the Highest Tower")),
            Moon(
                "Showdown on the Inverted Pyramid", Sand, true,
                tags = listOf(Story, Multi), captures = listOf(Bill)
            ),
            Moon(
                "The Hole in the Desert", Sand, true, "Hariet",
                captures = listOf(Fist, Bill), prereqs = listOf("Showdown on the Inverted Pyramid")
            ),
            Moon("Overlooking the Desert Town", Sand, true),
            Moon("Alcove in the Ruins", Sand, true),
            Moon("On the Leaning Pillar", Sand, true),
            Moon("Hidden Room in the Flowing Sands", Sand, true),
            Moon("Secret of the Mural", Sand, true, tags = listOf(Bit)),
            Moon("Secret of the Inverted Mural", Sand, true, tags = listOf(Bit)),
            Moon("On Top of the Stone Archway", Sand, true),
            Moon("From a Crate in the Ruins", Sand, true),
            Moon("On the Statue's Tail", Sand, true, tags = listOf(Cappy)),
            Moon("Hang Your Hat on the Fountain", Sand, true, tags = listOf(Cappy)),
            Moon("Where the Birds Gather", Sand, true, tags = listOf(Gp)),
            Moon("Top of a Dune", Sand, true, tags = listOf(Gp)),
            Moon("Lost in the Luggage", Sand, true, tags = listOf(Gp)),
            Moon("Bullet Bill Breakthrough", Sand, true, "Hariet", captures = listOf(Bill)),
            Moon("Inside a Block Is a Hard Place", Sand, true, captures = listOf(Bill)),
            Moon("Bird Traveling the Desert", Sand, true, tags = listOf(Bird)),
            Moon("Bird Traveling the Wastes", Sand, true, "Knuckle", tags = listOf(Bird)),
            Moon("The Lurker Under the Stone", Sand, true, "Knuckle", tags = listOf(Gp)),
            Moon("The Treasure of Jaxi Ruins", Sand, true, tags = listOf(Chest)),
            // TODO: Move these 3 moons to the top of Sand 2
            Moon("Desert Gardening: Plaza Seed", Sand, false, tags = listOf(Seed)),
            Moon("Desert Gardening: Ruins Seed", Sand, false, tags = listOf(Seed)),
            Moon("Desert Gardening: Seed on the Cliff", Sand, false, tags = listOf(Seed)),
            NecessaryAction("Plant Plaza Seed", Sand, true, arrayOf("Desert Gardening: Plaza Seed")),
            NecessaryAction("Plant Ruins Seed", Sand, true, arrayOf("Desert Gardening: Ruins Seed")),
            NecessaryAction("Plant Cliff Seed", Sand, true, arrayOf("Desert Gardening: Seed on the Cliff")),
            Moon("Sand Kingdom Timer Challenge 1", Sand, true, "Knuckle", tags = listOf(Timer)),
            Moon("Sand Kingdom Timer Challenge 2", Sand, true, "Knuckle", tags = listOf(Timer)),
            Moon("Sand Kingdom Timer Challenge 3", Sand, true, "Knuckle", tags = listOf(Timer)),
            Moon("Found in the Sand! Good Dog!", Sand, true, "Knuckle", tags = listOf(Gp)),
            Moon("Taking Notes: Jump on the Palm", Sand, true, tags = listOf(Notes)),
            Moon("Herding Sheep in the Dunes", Sand, true, tags = listOf(Sheep)),
            Moon("Fishing in the Oasis", Sand, true, "Knuckle", tags = listOf(Fishing), captures = listOf(Lakitu)),
            Moon(
                "Love in the Heart of the Desert", Sand, true, "Knuckle",
                tags = listOf(Goombette), captures = listOf(Goomba)
            ),
            Moon("Among the Five Cactuses", Sand, true),
            Moon(
                "You're Quite a Catch, Captain Toad!", Sand, true, "Knuckle",
                tags = listOf(Fishing, Toad), captures = listOf(Lakitu)
            ),
            Moon("Jaxi Reunion!", Sand, true, "Knuckle"),
            Moon("Welcome Back, Jaxi!", Sand, true, "Knuckle"),
            Moon("Wandering Cactus", Sand, true, captures = listOf(Cactus)),
            Moon("Sand Quiz: Wonderful!", Sand, true, tags = listOf(Sphynx)),
            Moon("Shopping in Tostarena", Sand, true),
            Moon("Employees Only", Sand, true),
            Moon("Sand Kingdom Slots", Sand, true, tags = listOf(Slots)),
            Moon("Walking the Desert!", Sand, true, tags = listOf(Trace)),
            Moon("Hidden Room in the Inverted Pyramid", Sand, true, tags = listOf(Chest)),
            Moon("Underground Treasure Chest", Sand, true, "Hariet", tags = listOf(Chest)),
            Moon("Goomba Tower Assembly", Sand, true, "Hariet", captures = listOf(Goomba)),
            Moon("Under the Mummy's Curse", Sand, true, "Knuckle"),
            Moon("Ice Cave Treasure", Sand, true),
            Moon("Sphynx's Treasure Vault", Sand, true, tags = listOf(Chest)),
            Moon("A Rumble from the Sandy Floor", Sand, true, tags = listOf(Gp)),
            Moon("Dancing with New Friends", Sand, true, tags = listOf(Outfit)),
            Moon("The Invisible Maze", Sand, true),
            Moon("Skull Sign in the Transparent Maze", Sand, true, tags = listOf(Cappy)),
            Moon("The Bullet Bill Maze: Break Through!", Sand, true),
            Moon("The Bullet Bill Maze: Side Path", Sand, true),
            Moon("Jaxi Driver", Sand, true),
            Moon("Jaxi Stunt Driving", Sand, true, tags = listOf(Notes)),
            Moon("Strange Neighborhood", Sand, true, "Knuckle"),
            Moon("Above a Strange Neighborhood", Sand, true, "Knuckle"),

            Separator(Sand, false),
            Moon("Secret Path to Tostarena!", Sand, false, "PG"),
            // TODO: might have to move to Bowser 2 like all arts
            Moon("Found with Sand Kingdom Art", Bowser, false, "PG", tags = listOf(Art, Gp)),
            NecessaryAction("Check Hint Art: Sand Kingdom", Sand, false, arrayOf("Found with Sand Kingdom Art")),
            Moon("Jammin' in the Sand Kingdom", Sand, false, "PG", tags = listOf(Music)),
            Moon("Hat-and-Seek: In the Sand", Sand, false, "PG", tags = listOf(Hat)),
            Moon("Sand Kingdom Regular Cup", Sand, false, "PG", tags = listOf(Kfr)),
            Moon("Binding Band Returned", Sand, false, "PG", tags = listOf(Gp)),
            Moon(
                "'Round-the-World Tourist", Sand, false, "PG",
                prereqs = listOf("A Tourist in the Mushroom Kingdom"), tags = listOf(Tourist)
            ),
            Moon("Peach in the Sand Kingdom", Sand, false, "PG", tags = listOf(Peach)),

            Moon("Mighty Leap from the Palm Tree!", Sand, false, "MR"),
            Moon("On the North Pillar", Sand, false, "MR"),
            Moon("Into the Flowing Sands", Sand, false, "MR"),
            Moon("In the Skies Above the Canyon", Sand, false, "MR", captures = listOf(Glydon)),
            Moon("Island in the Poison Swamp", Sand, false, "MR"),
            Moon("An Invisible Gleam", Sand, false, "MR", tags = listOf(Gp)),
            Moon("On the Eastern Pillar", Sand, false, "MR", captures = listOf(Bill)),
            Moon("Caught Hopping in the Desert!", Sand, false, "MR", tags = listOf(Mips)),
            Moon("Poster Cleanup", Sand, false, "MR"),
            Moon("Taking Notes: Running Down", Sand, false, "MR", tags = listOf(Notes)),
            Moon("Taking Notes: In the Wall Painting", Sand, false, "MR", tags = listOf(Bit, Notes)),
            Moon(
                "Love at the Edge of the Desert", Sand, false, "MR",
                tags = listOf(Goombette), captures = listOf(Goomba)
            ),
            Moon(
                "More Walking in the Desert!", Sand, false, "MR",
                prereqs = listOf("Walking in the Desert!"), tags = listOf(Trace)
            ),
            Moon(
                "Sand Kingdom Master Cup", Sand, false, "MR",
                prereqs = listOf("Sand Kingdom Regular Cup"), tags = listOf(Kfr)
            ),
            Moon("Where the Transparent Platforms End", Sand, false, "MR"),
            Moon("Jump Onto the Transparent Lift", Sand, false, "MR", captures = listOf(Moe)),
            Moon("Colossal Ruins: Dash! Jump!", Sand, false, "MR"),
            Moon("Sinking Colossal Ruins: Hurry!", Sand, false, "MR"),
            Moon("Through the Freezing Waterway", Sand, false, "MR"),
            Moon("Freezing Waterway: Hidden Room", Sand, false, "MR"),


            Separator(Lake, true),
            Moon("Broodals Over the Lake", Lake, true, tags = listOf(Story, Multi)),
            Moon("Dorrie-Back Rider", Lake, true),
            Moon("Cheep Cheep Crossing", Lake, true),
            Moon("End of the Hidden Passage", Lake, true, captures = listOf(Zipper)),
            Moon("What's in the Box?", Lake, true),
            Moon("On the Lakeshore", Lake, true),
            Moon("From the Broken Pillar", Lake, true, tags = listOf(Gp)),
            Moon("Treasure in the Spiky Waterway", Lake, true, tags = listOf(Chest)),
            // TODO: move
            Moon("Lake Gardening: Spiky Passage Seed", Lake, false, tags = listOf(Seed)),
            NecessaryAction("Plant Seed", Lake, true, arrayOf("Lake Gardening: Spiky Passage Seed")),
            Moon("Lake Kingdom Timer Challenge 1", Lake, true, "Rango", tags = listOf(Timer)),
            Moon("Lake Kingdom Timer Challenge 2", Lake, true, "Rango", tags = listOf(Timer)),
            Moon("Moon Shards in the Lake", Lake, true),
            Moon("Taking Notes: Dive and Swim", Lake, true, tags = listOf(Notes)),
            Moon("Taking Notes: In the Cliffside", Lake, true, tags = listOf(Bit, Notes)),
            Moon("Lake Fishing", Lake, true, "Rango", tags = listOf(Fishing)),
            Moon("I Met a Lake Cheep Cheep!", Lake, true, "Rango", captures = listOf(RedFish)),
            Moon("Our Secret Little Room", Lake, true),
            Moon("Let's Go Swimming, Captain Toad!", Lake, true, tags = listOf(Toad), captures = listOf(RedFish)),
            Moon("Shopping in Lake Lamode", Lake, true),
            Moon("A Successful Repair Job", Lake, true, "Rango", captures = listOf(LakePuzzle)),
            Moon("I Feel Underdressed", Lake, true, tags = listOf(Cappy, Outfit)),
            Moon("Unzip the Chasm", Lake, true, captures = listOf(Zipper)),
            Moon("Super-Secret Zipper", Lake, true, captures = listOf(Zipper)),
            Moon("Jump, Grab, Cling, and Climb", Lake, true),
            Moon("Jump Grab, and Climb Some More", Lake, true, tags = listOf(Chest)),

            Moon("Secret Path to Lake Lamode!", Lake, false, "PG", tags = listOf(Chest)),
            // TODO: move
            Moon("Found with Lake Kingdom Art", Cascade, false, "PG", tags = listOf(Art, Gp)),
            NecessaryAction("Check Hint Art: Lake Kingdom", Lake, true, arrayOf("Found with Lake Kingdom Art")),
            Moon("Taxi Flying Through Lake Lamode", Lake, false, "PG", captures = listOf(Binoc)),
            Moon("That Trendy \"Pirate\" Look", Lake, false, "PG", tags = listOf(Outfit)),
            Moon("Space Is \"In\" Right Now", Lake, false, "PG", tags = listOf(Outfit)),
            Moon("That \"Old West\" Style", Lake, false, "PG", tags = listOf(Outfit)),
            Moon("Lake Kingdom Regular Cup", Lake, false, "PG", tags = listOf(Kfr)),
            Moon("Peach in the Lake Kingdom", Lake, false, "PG", tags = listOf(Peach)),
            Moon("Behind the Floodgate", Lake, false, "MR"),
            Moon("High-Flying Leap", Lake, false, "MR"),
            Moon("Deep, Deep Down", Lake, false, "MR", captures = listOf(RedFish)),
            Moon("Rooftop of the Water Plaza", Lake, false, "MR", tags = listOf(Gp)),
            Moon("Bird Traveling Over the Lake", Lake, false, "MR", tags = listOf(Bird)),
            Moon("Love by the Lake", Lake, false, "MR", tags = listOf(Goombette)),
            Moon(
                "Lake Kingdom Master Cup", Lake, false, "MR",
                prereqs = listOf("Lake Kingdom Regular Cup"), tags = listOf(Kfr)
            ),
            Moon("Waves of Poison: Hoppin' Over", Lake, false, "MR"),
            Moon("Waves of Poison: Hop to It!", Lake, false, "MR"),

            Separator(Wooded, true),
            Moon("Road to Sky Garden", Wooded, true, tags = listOf(Story)),
            Moon("Flower Thieves of Sky Garden", Wooded, true, "Piranha", tags = listOf(Story, Multi)),
            Moon(
                "Path to the Secret Flower Field", Wooded, true, "Spewart",
                tags = listOf(Story), captures = listOf(Sherm)
            ),
            Moon(
                "Defend the Secret Flower Field!", Wooded, true, "Windmill",
                tags = listOf(Story, Multi), captures = listOf(Uproot)
            ),
            Moon("Behind the Rock Wall", Wooded, true, "Spewart", captures = listOf(Sherm)),
            Moon("Back Way Up the Mountain", Wooded, true, "Spewart", tags = listOf(Bit)),
            Moon("Rolling Rock in the Woods", Wooded, true),
            Moon("Caught Hopping in the Forest!", Wooded, true, tags = listOf(Mips)),
            Moon("Thanks for the Charge!", Wooded, true, "Spewart", tags = listOf(Gp)),
            Moon("Atop the Tall Tree", Wooded, true, captures = listOf(Uproot)),
            Moon("Tucked Away Inside the Tunnel", Wooded, true),
            Moon("Over the Cliff's Edge", Wooded, true, "Spewart"),
            Moon("The Nut 'Round the Corner", Wooded, true),
            Moon("Climb the Cliff to Get the Nut", Wooded, true, captures = listOf(Uproot)),
            Moon("The Nut in the Red Maze", Wooded, true, captures = listOf(Uproot)),
            Moon("The Nut at the Dead End", Wooded, true),
            Moon("Cracked Nut on a Crumbling Tower", Wooded, true, "Piranha"),
            Moon("The Nut that Grew on the Tall Fence", Wooded, true, "Spewart"),
            Moon("Fire in the Cave", Wooded, true),
            Moon("Hey Out There, Captain Toad!", Wooded, true, "Torkdrift", tags = listOf(Toad)),
            Moon("Love in the Forest Ruins", Wooded, true, "Spewart", tags = listOf(Goombette)),
            Moon("Inside a Rock in the Forest", Wooded, true, "Torkdrift", captures = listOf(Sherm)),
            Moon("Shopping in Steam Gardens", Wooded, true),
            Moon("Nut Planted in the Tower", Wooded, true, "Piranha"),
            Moon("Stretching Your Legs", Wooded, true, "Piranha", captures = listOf(Uproot)),
            Moon("Spinning-Platforms Treasure", Wooded, true, "Spewart"),
            Moon("Make the Secret Flower Field Bloom", Wooded, true, "Torkdrift"),
            Moon("Rolling Rock in the Deep Woods", Wooded, true, captures = listOf(Dino)),
            Moon("Glowing in the Deep Woods", Wooded, true, tags = listOf(Timer)),
            Moon("Past the Peculiar Pipes", Wooded, true),
            Moon("By the Babbling Brook in the Deep Woods", Wooded, true, tags = listOf(Gp), captures = listOf(Dino)),
            Moon("The Hard Rock in Deep Woods", Wooded, true, tags = listOf(Gp), captures = listOf(Dino)),
            Moon("A Treasure Made from Coins", Wooded, true, captures = listOf(CoinCoffer)),
            Moon("Beneath the Roots of the Moving Tree", Wooded, true, tags = listOf(Gp), captures = listOf(Tree)),
            Moon("Deep Woods Treasure Trap", Wooded, true, tags = listOf(Chest)),
            Moon("Exploring for Treasure", Wooded, true, tags = listOf(Chest, Outfit)),
            Moon("Wooded Kingdom Timer Challenge 1", Wooded, true, "Torkdrift", tags = listOf(Timer)),
            Moon("Wooded Kingdom Timer Challenge 2", Wooded, true, "Torkdrift", tags = listOf(Timer)),
            Moon("Flooding Pipeway", Wooded, true),
            Moon("Flooding Pipeway Ceiling Secret", Wooded, true),
            Moon("Wandering in the Fog", Wooded, true, "Spewart", captures = listOf(Paragoomba)),
            Moon("Nut Hidden in the Fog", Wooded, true, "Spewart", captures = listOf(Paragoomba)),
            Moon("Flower Road Run", Wooded, true, "Spewart"),
            Moon("Flower Road Reach", Wooded, true, "Spewart", captures = listOf(Uproot)),
            Moon("Elevator Escalation", Wooded, true, "Spewart"),
            Moon("Elevator Blind Spot", Wooded, true, "Spewart"),
            Moon("Walking on Clouds", Wooded, true, "Torkdrift", captures = listOf(Uproot)),
            Moon("Above the Clouds", Wooded, true, "Torkdrift", captures = listOf(Uproot)),

            Separator(Wooded, false),
            Moon("Secret Path to the Steam Gardens!", Wooded, false, "PG"),
            // TODO: move
            Moon("Found with Wooded Kingdom Art", Sand, false, "PG", tags = listOf(Art, Gp)),
            NecessaryAction("Check Hint Art: Wooded Kingdom", Wooded, true, arrayOf("Found with Wooded Kingdom Art")),
            Moon("Swing Around Secret Flower Field", Wooded, false, "PG"),
            Moon("Jammin' in the Wooded Kingdom", Wooded, false, "PG", tags = listOf(Music)),
            Moon("Wooded Kingdom Regular Cup", Wooded, false, "PG", tags = listOf(Kfr)),
            Moon("Peach in the Wooded Kingdom", Wooded, false, "PG", tags = listOf(Peach)),
            Moon("High up in the Cave", Wooded, false, "MR"),
            Moon("Lost in the Tall Trees", Wooded, false, "MR", captures = listOf(Sherm)),
            Moon("Looking Down on the Goombas", Wooded, false, "MR"),
            Moon("High Up on a Rock Wall", Wooded, false, "MR", captures = listOf(Uproot)),
            Moon("The Nute in the Robot Storeroom", Wooded, false, "MR", captures = listOf(Uproot)),
            Moon("Above the Iron Mountain Path", Wooded, false, "MR"),
            Moon("The Nut Under the Observation Deck", Wooded, false, "MR"),
            Moon("Bird Traveling the Forest", Wooded, false, "MR", tags = listOf(Bird)),
            Moon("Invader in the Sky Garden", Wooded, false, "MR"),
            Moon("Hot, Hot, Hot from the Campfire", Wooded, false, "MR", captures = listOf(FireBro)),
            Moon("Wooded Kingdom Timer Challenge 3", Wooded, false, "MR", tags = listOf(Timer)),
            Moon("Moon Shards in the Forest", Wooded, false, "MR", captures = listOf(Uproot)),
            Moon("Taking Notes: On Top of the Wall", Wooded, false, "MR", tags = listOf(Notes)),
            Moon("Taking Notes: Stretching", Wooded, false, "MR", tags = listOf(Notes)),
            Moon(
                "Wooded Kingdom Master Cup", Wooded, false, "MR",
                prereqs = listOf("Wooded Kingdom Regular Cup"), tags = listOf(Kfr)
            ),
            Moon("I Met an Uproot!", Wooded, false, "MR", captures = listOf(Uproot)),
            Moon("Invisible Road: Danger!", Wooded, false, "MR"),
            Moon("Invisible Road: Hidden Room", Wooded, false, "MR", tags = listOf(Chest)),
            Moon("Herding Sheep Above the Forest Fog", Wooded, false, "MR", tags = listOf(Sheep)),
            Moon("Herding Sheep on the Iron Bridge", Wooded, false, "MR", tags = listOf(Sheep)),
            Moon("Down and Back Breakdown Road", Wooded, false, "MR"),
            Moon("Below Breakdown Road", Wooded, false, captures = listOf(Banzai))
        )
    }
}