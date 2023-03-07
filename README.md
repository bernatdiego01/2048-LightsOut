# 2048-LightsOut

## Android Project

For the last couple of months, I have been developing the following code with the objective of creating an Android application that resembles the popular games LightsOut and 2048. In this readMe I try and explain every single class and method so you can understand how the app was developed and how it works.


## CODE

### MainActivity and menu

This main activity is used to create a menu so the user can choose which game they want to play. Is not a very complex activity and is pretty simple to understand, nevertheless, it's inner workings do as follows.

This code defines an Android activity named "MainActivity" that extends the "AppCompatActivity" class. Inside the "onCreate" method, the activity's layout is set with "activity_main". The activity has several TextViews and an EditText object, used to choose which new activity the user wants to go to and open.

Each TextView is set up with a "OnClickListener" that listens for user clicks and performs a specific action when clicked. When the "dosmil" TextView is clicked, the "set2048" method is called with the "usuari" EditText object as a parameter. Similarly, when the "lights" TextView is clicked, the "setLights" method is called with the "usuari" EditText object as a parameter. When the "clasi" TextView is clicked, the "setClasi" method is called.


### 2048

Once the player clicks on the "dosmil" textview, de player is transportet into the g2048.class and there he can play the knowned game 2048. 
