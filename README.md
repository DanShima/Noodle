SDA Individual Development Project Report
November 2017

>**Features:**
The noodle objects are stored in a SQLite database.
The noodles are divided into five categories(Japanese, Korean, Vietnamese, Thai, and Chinese).
Each noodle dish is shown with name, description, image, and recommended restaurant in downtown Stockholm.
The user can search for a particular noodle dish or dishes based on name or description. The search results are displayed in alphabetical order.
The user can save his favorite noodles.
The user can enter and delete his experience in "My List", which is like a quick notepad
The user can share to others through email/message (no database content).

>**Implementation:**
After finalizing my idea, design and UI on sketch, I gathered all resource files (images, descriptions etc)
Then I made a base model with Noodle.class, Category.class (arraylists), Log.class and Main and tried them out in Terminal. But fairly quickly, I decided to move on to Android Studio to create layouts and views.
In Android Studio, I first built a prototype with simple list view.
After the prototype, I set up a SQLite database using databasehelper and added a navigation drawer, which shows the five categories, my favorite and log. I also implemented search function, which allows the user to look up a noodle by name or any word in its description. The search result is returned and displayed in alphabetical order. There is a search functionality too, but it does not share anything more than a string instead of app content. The “My Log” option was the last feature to be added, because I felt that my app would be better with some form of user input.
