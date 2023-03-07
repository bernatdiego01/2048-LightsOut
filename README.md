# 2048-LightsOut

## Android Project

For the last couple of months, I have been developing the following code with the objective of creating an Android application that resembles the popular games LightsOut and 2048. In this readMe I try and explain every single class and method so you can understand how the app was developed and how it works.


## CODE

### MainActivity and menu

This main activity is used to create a menu so the user can choose which game they want to play. Is not a very complex activity and is pretty simple to understand, nevertheless, it's inner workings do as follows.

This code defines an Android activity named "MainActivity" that extends the "AppCompatActivity" class. Inside the "onCreate" method, the activity's layout is set with "activity_main". The activity has several TextViews and an EditText object, used to choose which new activity the user wants to go to and open.

Each TextView is set up with a "OnClickListener" that listens for user clicks and performs a specific action when clicked. When the "dosmil" TextView is clicked, the "set2048" method is called with the "usuari" EditText object as a parameter. Similarly, when the "lights" TextView is clicked, the "setLights" method is called with the "usuari" EditText object as a parameter. When the "clasi" TextView is clicked, the "setClasi" method is called.


### 2048

Once the player clicks on the "dosmil" textview, de player is transportet into the g2048.class and there he can play the knowned game 2048. The class g2048 is very complicated and difficult to understand, even for the one creating it. Either way, I'll try to explain it.

The code starts with defining various variables including "SIZE" , "tiles" , "values", "anterior", "swipeListener", "puntuacio" , "punts", and "intento". The "g2048" class also has a boolean variable named "gameOver" and a default constructor with no parameters. The class overrides the "onCreate" method and sets the content view to a layout file named "activity_2048". It also initializes the "puntuacio" variable to 0 and sets the "punts" variable to a TextView object with an ID of "contador". An instance of the "SwipeListener" class is created with the view object passed as a parameter.

The "onCreate" method also initializes and sets click listeners to four buttons with IDs "cuatroporcuatro", "sispersis", "ochoporocho", and "enrera". Each of these buttons has a corresponding "OnClickListener" that sets the "SIZE" variable to either 4, 6, or 8 and calls the "crear" method. The "crear" method initializes the "tiles", "values", and "anterior" variables as 2D arrays with the appropriate size. It then adds TextView objects to a GridLayout object named "gridLayout" and initializes their text and layout properties. Two random tiles are added to the game board using the "addRandomTile" method and the "updateUI" method is called to update the display.

The "SwipeListener" class implements the "OnTouchListener" interface and uses an instance of the "GestureDetector" class to detect swipe gestures on the game board. If a swipe is detected, the "moverDerecha", "moverIzquierda", "moverAbajo", or "moverArriba" method is called to move the tiles in the appropriate direction.

#### Once the a app is created the game rilies on its methods so the game works as it should:

The possarAnteriorUI function updates the UI with the previous state of the game. The function iterates through all tiles and sets the background color and text value of the tile to the previous state values once the button . The setnewvalues function is called, which updates the current values of the game to the previous state.

The updateUI function updates the current state of the game in the UI. The function iterates through all tiles and sets the background color and text value of the tile to the current state values.

The elegirColor function returns the color of a tile based on the tile's value. The function takes the tile's value as an input and returns the corresponding color.

The getTileIndex function returns the index of the tile based on the tile's value. The function takes the tile's value as an input and returns the index of the tile. The index of the tile is used to determine the tile's background color.

The canMove function checks if a move is possible. The function returns true if there is an empty tile or if there are two adjacent tiles with the same value. If there is no move possible, the function calls the GameOver function.

The moverArriba, moverAbajo, moverIzquierda and moverDerecha functions move the tiles up, down, or left respectively. The functions first call the canMove function to check if the move is possible. The functions then iterate through all the tiles and move them accordingly. If two adjacent tiles have the same value, they are merged, and the score is updated accordingly. The addRandomTile function is called, which adds a new tile with a value of 2 or 4 randomly. Finally, the updateUI function is called to update the UI with the new state of the game.

Once evething is set, the game works just as the Original. And once the player loses, it score will the class GameOver2048 will pop up.

### Game Over 2048

Once the player loses in the 2048 game, it's score and name will be sent to this new class. In which there is a button that will coduct him to the Classification, where all the socres of all the people who has played will be stored alongside their names.



