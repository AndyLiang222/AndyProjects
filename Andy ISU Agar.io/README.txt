Controls:
all controls are on the mouse
- Left-Click will split the blob
- Right-Click will feed
- Curser location will move the player
- side button will go to developer mode.

List to consider:

- Bots will not eat food sent to it when moving 

- Multiplayer doesn't work very well since you need a mouse to play the game and you can only control one curser at a time which makes it difficult to player multiplayer. I probably should of made a server type class to allow people to player on different computers

- since I am multi threading the game, objects are changing, adding, and removing which leads to concurrent modification errar as well as null pointer exception. Most of these are occuring because I recently added multiplayer and I didn't account for multipler player objects. (My strategy to solving this is by testing the game over time until I find an error in which I fix).

- When I open my eclipse and run my code for the first time in a while, the screen doesn't load and needs to be reloaded in order to work idk if this is my code or my game.

- my bots aren't that smart and usually die quickly and get tricked easily. This ultimately causes them to die quickly.

Improvements:

- instead of making players be a seperate entity as other blobs, I should have made the player just like a renderer while making a hashmap in the game to hold a blob entity that the player would then control. This would most likely make my code less redundant and probably easier. 

- Should have made this game on a server or smtg so that multiplayer is actually playable.

- instead of having the player move using the players own thread, I should have put it in the update thread of the game object, it makes more logical sense from a security sense as well as avoid concurrent modification. 

- I was going to have the camera pan out as you got bigger but I ran out of time. To do this I would have kept a ratio between the window size and players vision size then multiply each entity to render's position by the ratio. Including this feature would have made the game feel better.

- Should have added music and sound effects but I forgot sound existed until 3 hours before the deadline.

New features:

- Added multiple player skins
- Added multiple colors
- Added feature to allow player to edit the game settings
- Added name change
- Added devloper mode

Features not added:
- Control changing
    - Doesn't really make sense since the original agar.io doesn't allow control change and mouse movement makes sense

