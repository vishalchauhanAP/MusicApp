Screen Shots : https://drive.google.com/open?id=15YpMW63bPgi3ld0yTZbzzRhfoZKW7RQz

1 App Name: MusicApp- A Virtusa Practice
2 Used MVVM (ModelView-ViewModel) Design Pattern
3 Used LiveData, and Observe that data using ViewModel
4 Make three Repositories, 
     One is RemoteRepo, Fetched Data from Remote Source.
     Second is LocalRepo, Save Data Locally already fetched using RemoteRepo
     Third is MockTestRepo, for Mock Testing

5 Used ORM - Room Database, for Saving data
6 Used Dependency Injection, There is class Injection.java which provides RemoteRepo, You can switch to MockTestRepo from this class
7 Used Retrofit 2 , for fetching remote data.
8 For User Interface, used Constraint layout.
9 App First Fetched data Remotely, Next time if we start app, it displays local content, if we delete DB using device Explorer, it again fetches from Remote Source.
10 ToolBar at bottom displays, whether data fetched remotely, from Local Database.

Future Enhancements :
 1 We an implement DataBinding works well with MVVM.
 2 Convert this App to Kotlin Programming Language to take advantages to modern features.
