# undoandredo
Simple mobile text entry interface based on device's directional motion.

<!-- ABOUT THE PROJECT -->
## Demo of the implementation

[Link to the Demo](https://drive.google.com/file/d/1Q_k_zn8cHJOkrOJ-bx3SLaQYBko7nsJ9/view?usp=sharing)


<!-- GETTING STARTED -->
## Getting Started

This project is developed on Andriod Studio and tested on the Emulator. Please download and open it through Android studio to access the code and run the application.

## About the Project.

This is a programming assignment under CS522 Human Computer Interaction. The goal was to develop a simple mobile text entry interface that uses the device's directional 
motion to allow users to undo and redo (limited to characters instead of string).

To implement a natural interface, gesture interaction, which is based on motion, used for undo is tilting left and tilting right for redo. This is to make it closest to real-life actions and increase familiarity.

For tracking the tilt of the phone, I went with monitoring gyroscope instead of accelorometer to prevent accidental invokation of undo/redo.

### Tilt Detection 

Decide on a maximum time and a minimum sensor difference for a legitimate movement. If the phone is move too quickly it might be due to an accidental fall or hand jerk. And if its too slow it might be just phone orientation changing.
A large rotation is good if it is done quickly enough, while a fast movement is good if the angle difference is large enough.

Also once a tilt is detected the phone has to go back to (near) its original orientation to invoke undo/redo once again. Ideally it should be based on transition instead of the starting or ending state. This is how the gyroscope helps. 
In this application a single axis value of the gyroscope is considered (Z axis).

## References

1. https://www.analog.com/en/app-notes/an-1057.html (Used this to get basic information on tilt detection.)
2. [UndoRedo android sdk library](https://github.com/Arowa-Z/TextUndoRedo)

