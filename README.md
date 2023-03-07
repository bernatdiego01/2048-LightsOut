# 2048-LightsOut

## Android Project

For the last couple of months, I have been developing the following code with the objective of creating an Android application that resembles the popular games LightsOut and 2048. In this readMe I try and explain every single class and method so you can understand how the app was developed and how it works.


## CODE

### MainActivity and menu

This main activity is used to create a menu so the user can choose which game they want to play. Is not a very complex activity and is pretty simple to understand, nevertheless, it's inner workings do as follows.

This code defines an Android activity named "MainActivity" that extends the "AppCompatActivity" class. Inside the "onCreate" method, the activity's layout is set with "activity_main". The activity has several TextViews and an EditText object, used to choose which new activity the user wants to go to and open.

Each TextView is set up with a "OnClickListener" that listens for user clicks and performs a specific action when clicked. When the "dosmil" TextView is clicked, the "set2048" method is called with the "usuari" EditText object as a parameter. Similarly, when the "lights" TextView is clicked, the "setLights" method is called with the "usuari" EditText object as a parameter. When the "clasi" TextView is clicked, the "setClasi" method is called.


### 2048

![cap2](https://user-images.githubusercontent.com/91748429/223401153-903c9a74-e542-4554-b555-6e52538f3af8.PNG)


Once the player clicks on the "dosmil" textview, de player is transportet into the g2048.class and there he can play the knowned game 2048. The class g2048 is very complicated and difficult to understand, even for the one creating it. Either way, I'll try to explain it.

The code starts with defining various variables including "SIZE" , "tiles" , "values", "anterior", "swipeListener", "puntuacio" , "punts", and "intento". The "g2048" class also has a boolean variable named "gameOver" and a default constructor with no parameters. The class overrides the "onCreate" method and sets the content view to a layout file named "activity_2048". It also initializes the "puntuacio" variable to 0 and sets the "punts" variable to a TextView object with an ID of "contador". An instance of the "SwipeListener" class is created with the view object passed as a parameter.

The "onCreate" method also initializes and sets click listeners to four buttons with IDs "cuatroporcuatro", "sispersis", "ochoporocho", and "enrera". Each of these buttons has a corresponding "OnClickListener" that sets the "SIZE" variable to either 4, 6, or 8 and calls the "crear" method. The "crear" method initializes the "tiles", "values", and "anterior" variables as 2D arrays with the appropriate size. It then adds TextView objects to a GridLayout object named "gridLayout" and initializes their text and layout properties. Two random tiles are added to the game board using the "addRandomTile" method and the "updateUI" method is called to update the display.

![cap1](https://user-images.githubusercontent.com/91748429/223400897-e8cd50b9-fea3-4a33-8c7b-a1b9e980307e.PNG)

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


### Classification

![cap3](https://user-images.githubusercontent.com/91748429/223402381-90298e0a-432d-47f3-96da-4b6c7701caaf.PNG)


The class Classification shows the players and its scores in a reclycle view. The class has instance variables "nom" and "punts", which are both of type "ArrayList<String>". There is also an instance of "DBHelper" class named "dbHelper". An instance of "TextAdapter" class named "adapter" is also defined.

In the "onCreate" method, the layout for the activity is set using the "setContentView" method. An instance of "SQLiteDatabase" is created using the "DBHelper" class. The intent is retrieved using the "getIntent" method. The "nombre" and "puntos" strings are retrieved from the intent. Two empty ArrayLists are created to store the data. The adapter is instantiated with these ArrayLists.

The "checkinsertdata" variable is a Boolean that is returned by the "insertdatas" method of the "DBHelper" class. That method inserta dadas is used to inserd the "punts" and the name of the player inside the database. 

Later a "RecyclerView" named "rcV" is created using the "findViewById" method. The adapter is set to the RecyclerView using the "setAdapter" method. The layout manager is set using the "setLayoutManager" method. Finally, the "displaydata" method is called to populate the ArrayLists with the data from the database.

Finally the "displaydata" method retrieves the data from the database using the "getdata" method of the "DBHelper" class. It iterates over the "Cursor" object using the "moveToNext" method, and retrieves the "nombre" and "puntos" values. These values are added to the respective ArrayLists using the "add" method.
  
  
  
### DB Helper
  
  The class DbHelper is used only with the Class Classificacio in order to set and retrive the names and points of the players stored inside the database.The class starts defining constants for the database name, database version, table name, column names, and the SQL statement to create the table. The table has two columns, "Nombre" and "Puntuacion".

In the constructor, the superclass constructor is called with the database name, null for the cursor factory, and the database version.

The "onCreate" method is overridden and it executes the SQL statement to create the table.

The "onUpgrade" method is also overridden and it drops the table if it already exists, and then calls the "onCreate" method.

The "insertdatas" method takes two String parameters for "nom" and "punts". It gets a writable database using the "getWritableDatabase" method. It creates a new ContentValues object and puts the "nom" and "punts" values into it. It then calls the "insert" method on the database object, passing in the table name, null for the null column hack, and the ContentValues object. If the result is -1, indicating an error, the method returns false. Otherwise, it returns true.

The "getdata" method returns a Cursor object that contains all the rows in the "CLASSIFICACIO" table. It gets a writable database using the "getWritableDatabase" method. It then executes a raw query using the "rawQuery" method, passing in the SQL statement and null for the selection arguments. It returns the resulting Cursor object.

Overall, this class provides methods to create and manage a SQLite database for the "CLASSIFICACIO" table, and provides methods to insert data into and retrieve data from the table.
  
### LIGHTS OUT
  
  This other class respresents the famouse game "LightsOut". The game consists of a grid of lights, where each light is either "on" or "off". Clicking a light toggles its state and also the state of its neighbors. The goal is to turn off all the lights.

The class extends AppCompatActivity and has several instance variables, including an integer constant SIZE, a 2D integer array called "matriu" that stores the state of each button, and two integer variables called "filaM" and "columnaM" that keep track of the current row and column of the button being clicked. There is also an integer variable called "count" that keeps track of the number of times the game has been won and a TextView called "contador" to display the count.

The class has an ImageButton 2D array called "buttons" that represents the buttons on the screen. There is also a GridLayout 2D array called "gridM" which is not used in the code.

In the onCreate method, the layout is set to activity_lightsout.xml and several buttons are initialized. Each button has an OnClickListener that sets the value of the SIZE constant and calls the create method to create a new grid of buttons.

The create method creates a new GridLayout and a grid of buttons by iterating over each row and column of the grid and creating a new ImageButton for each cell. Each button is given a random initial state by calling the activarBombilles method, and an OnClickListener that calls the instrucciones method when the button is clicked. The ImageButton objects are stored in the "buttons" array.

The instrucciones method is called when a button is clicked. It toggles the state of the clicked button and its neighbors by calling the cambiarBombilla method. It then calls the mirarmatriz method to check if all the buttons have been turned off and if so, calls the popup method to display a message and reset the grid.

The mirarmatriz method checks the state of the "matriu" array to see if all the buttons have been turned off. If so, it calls the popup method to display a message and reset the grid. It also increments the "count" variable and updates the "contador" TextView.

The popup method displays a message using a Toast and increments the "count" variable. It then calls the create method to reset the grid.

The cambiarBombilla method toggles the state of a button and changes its background image to either a lit or unlit light bulb, depending on the state. The state of the button is also updated in the "matriu" array.

Finally, there is an activarBombilles method that returns a random Drawable object representing a lit or unlit light bulb. This method is used to initialize the state of each button when the grid is created.
  
  
  ![cap4](https://user-images.githubusercontent.com/91748429/223406125-0dcbfa52-26c3-461e-853e-f627ec7d4f26.PNG)






