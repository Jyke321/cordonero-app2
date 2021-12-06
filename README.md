# User Guide
| Table of Contents |
| ------ |
|Starting up this application |
|Using the application |
|Copyright|


### Starting up this application
This program is fairly simple to run if you have [Java] (JDK16), [IntelliJ] and [GIT] installed.
> If you have these resources installed, then you can follow this set up guide:

First open up a location in file explorer to save the application contents to:

![Empty file Location][image1]

When you have a spot you are satisfied with, right click in the directory and open git bash:

![Open git bash here][image2]

After opening git bash, type in:
```sh
git clone https://github.com/Jyke321/cordonero-app2
```
You should now see a cloned repository folder in the location you previously opened:

![Cloned Repository][image3]

Right-click it and open it with IntelliJ

![Open IntelliJ][image4]

Select trust this file and then click on gradle on the right on the screen to open it up.
After doing so, open the tasks and then the application folders and double-clciking run:

![IntelliJ][image5]

The application should start up:

![IntelliJ][image6]

### Using the application
> Setting this up took more time than anticipated, more images may or may not be added in the future

The application has many functions as per required for the assigment it was made for. The first being adding items to the list.

##### Adding Items
In order to add an item first select the name text field with the mouse. When selected you can add text to it by typing with your keyboard.
After you are satisfied with the name, in order to switch to the next text field (Monetary Value) you can either:
 - Select the next text field with your mouse
 - Press the tab key on your keyboard

The monetary value text field automatically provides the '$' character when you type, please only use numbers to avoid errors.
After you are done typing in a monetary value you can go to the next text field (Serial Number) by:
 - Selecting the next text field with your mouse
 - Pressing the tab key on your keyboard

The serial number text field must be given a value in the format "A-XXX-XXX-XXX" where 'A' is a letter and 'X' represents any letter or number.
When you are satisfied with the value you have enter in the serial number you are ready to add a number. This can be done by either:
 - Clicking on the "Add Item" button
 - Pressing the enter key on your keyboard

##### Editing Values
You can edit values entered in the table by double clicking on the item you wish to edit and pressing enter when you are satisfied with your changes.
The value you change must be a valid, keep in mind that monetary value needs to be a number and have a '$' to be valid.

##### Removing Items
There are two available options for removing items:
 - Delete Item
 - Delete All Items

Both of these options can be found at the top of the application under the "Edit" menu option.
Delete Item will delete the selected item. You can select items by clicking on them once.
Delete All Items will clear the list of all items in the list.

##### Sorting Items
There are three available sorting options:
 - Sort by Name
 - Sort by Value
 - Sort by Serial Number

All three of these methods can be accessed by clicking on the table headers. When done, an arrow should appear next to the header signifying how the particular header is being sorted.

##### Searching For Items
There are two available search methods:
 - Search by Name
 - Search by Serial Number

These methods can be accessed by hovering over "View" in the menu, and then hovering over "Search by" before finally selecting with method you wish to serach by. By default both methods are selected at start up and deselecting both cause the opposite to automatically be selected.

To activate searching, one must type into the search text field to enter a search key. Text typed in the search text field automatically updates what is being searched for.

##### Saving and Loading
You can save and load by going to the "File" menu and selecting "Save" or "Load". Saving can also be done by pressing (ctrl + s).
When saving is selected a window will be brought up to save the file at a designated spot. By default, the opened spot is in the application folder, but the user may select anywhere else to save the data.
The data can be saved in one of the three different extensions:
 - .txt (formated as TSV)
 - .html
 - .json

When loading a window similar to saving window is brought up allowing the user to load files of the following extensions:
 - .txt (formated as TSV)
 - .html
 - .json

>  *** Loading files not created by this application has not been tested and can very likely cause crashes ***

##### Getting Help
Using the "Help" menu in the application you may be brought to the github page for the application with this guid on it.

##### Closing the application
There are two ways to close the application:
 - Close - available through the File Menu
 - The 'X' button - In the top right of windows applications (this may not be there on non-windows devices)

### Copyright

> UCF COP3330 Fall 2021 Application Assignment 2 Repository 
> Copyright 2021 Jacob Cordonero

[Java]: <https://jdk.java.net/16/>
[IntelliJ]: <https://www.jetbrains.com/idea/download/>
[GIT]: <http://git-scm.com/>
[image1]: <https://github.com/Jyke321/cordonero-app2/blob/main/docs/readmeImages/image1.png>
[image2]: <https://github.com/Jyke321/cordonero-app2/blob/main/docs/readmeImages/image2.png>
[image3]: <https://github.com/Jyke321/cordonero-app2/blob/main/docs/readmeImages/image3.png>
[image4]: <https://github.com/Jyke321/cordonero-app2/blob/main/docs/readmeImages/image4.png>
[image5]: <https://github.com/Jyke321/cordonero-app2/blob/main/docs/readmeImages/image5.png>
[image6]: <https://github.com/Jyke321/cordonero-app2/blob/main/docs/readmeImages/image6.png>