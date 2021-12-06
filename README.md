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
[image1]: <>
[image2]: <https://previews.dropbox.com/p/thumb/ABXtJ0KnpSmXdfBY0ecKWXArzmEC4JFWPkiKI-hdPJSgIxj4sye4AHaHNjXV2ST3u2tfFSjf8r09cKYk_NbAsBibZojYZxFSbyfCA07ahc3uO4-hiwzNs0j9FqV0Nipj4GhFlcyrvE3U6W8BnmNc0kZvzAHlF1kuvMAbmT5HJaeAJkNPJICz_xNHE2g5Nni8snRSh6aIrLG1xkt_x2iUu6ouWZAdLK-5mKkMzKVJ_gZJL8tXZPd9gRQ7UjO8rQrfCj0t6Zp62-uVu2gpJjsn2jQXbTRsdl1FPBK6EwL82E5_CGn2hjNWRy54PRpJ-TP4L7PB5F2ADVeHdr2DLWpciOXGnGnXQO5n1wLnaUcTKMIGXA/p.png>
[image3]: <https://previews.dropbox.com/p/thumb/ABUABVmnC6pzTc6L5g9EREc0slArJb8zDPmHdfkQHjaAfNzwKwBaoyEzw83-m1mEOgE-d3SJ3o_bARdLT4XJgpZapRYOrzrJ6eRwvhqI7MmbIzTeBsj9VJBrqtvipWxsDM-_Ot_UAgGgU6QxocMf97z7L7X09JTTXPI0dTHCs-H3bFDq2qf6nXXXbJvtMNWEWzQ0-0YoJlIREcfgeMwBLSW3VCafGYUqOw8knpc_SH8-B_zHUEZigwkjDRvZmXreb5KTwEiH6OILc1qWPH5Ntm_9dER1lhDZxSKr6-YutPstD3p-_I6-tBYBvET33I2RJT377AtG1fgZ4k9qH6HbP1DdXnyGkgrIkfaBJMcq4J8Jog/p.png>
[image4]: <https://previews.dropbox.com/p/thumb/ABVQ5iDSTc13PHg6syHHFxKnvs2qP8bXeFrNcEqG4p1XXTBOl4LaDkrmBG1LuUeZol7J17IfEdIz1y_cu6tkXnEtIO6-H_rP3D9-0asnNDMg4HxYb7ZkaDVTeHhZDpbkrH5FOxPqNdim-ZakYUubIpC9cIxU43FcGtzl1JaeSB3RdmSRzPnIHTNHhWa4sA95u6m-PQQ2gG6O5Sp-YcQ-YOo5Y6Nhj2ygnrci0BoZCh4na-5KdlmrN36GKDpbwvSZxGXabgE-ncxL-qB36x-4JC4cxiBV0Yf4x4rFhXl1pGkNTW_KeO7JuSEcUOH8KQWEGDcsKlhoAMb4MMNonsNOFCOTm5o-nsJ7qYKc-W5AwWYpYg/p.png>
[image5]: <https://previews.dropbox.com/p/thumb/ABWT0DQQV-psC0_x2S61LXxQ_yG0CR_vtTYti-HYlzBJll9BB8DZnTd6vJuMSqpN4GozoW7ZK_4erHgc3abayvRGxt8N4QCvn09NJ1DEQ3xYl-F-zyD6s47DUZvYwV6NE1UV8SlNf3DMpcEsJLsQerS9WMLi4x6Xd3fICgU_1HW77H34u70xK0PHYV6a5QXB2XB2NHrwBhEeyjaywLJJhfc0hXK9gnuIB6eG6FczOCK8c7uEZnG3SnWKLrnq49YK2cWPxHe3waOG7c78yc1e3YP4QQJ8YIp3E-dRrlt7OkQ0RuA3OB4wnVelQirucWBFfVtrUcUXShOtAwtMbA1BUk9pSFbwEVCdF13-wkDE3JSruA/p.png>
[image6]: <https://previews.dropbox.com/p/thumb/ABXwunK7OghXdrGTWicKuJSTikj0_F_0y0sIqVVVoge5s9kUNHK3UNyCjVb0toF5k77Yq8D9udUe4hdW45r2ShNq9_VJZz675CMidXLaq8o2C-R6aLhftc3cy7gFhF8Wts_DXnTiZWduNieGJ8foMHhx8M5Yg81Df11xchPwmLX5VxkqUX1WJfJgKbRwNrzWDeT_rK3FpfVFnDMGJZ_SEH-y0vAbXcfXV0khl8CLDWAL_SKZp2uZqW5J6Q2W2LQ5NKOHDfG-76jp1nbMVfgVqZqdD_VZpwR1yVkXj1Fl3Sj2B3ziS7GB-vPNRlopBAI0Jkyt6Lplyc0Wc_OKkigxYym0HHSsGjDnNp8cLwiU-H8wMg/p.png>