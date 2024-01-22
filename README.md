

### Door Config
Application used to config primary and secondary door parameters using simple MVVM architecture pattern.

![](https://imgur.com/a/1B7r0po)

#### Architecture flow:

Followed MVVM architecture to build this application. Retrofit is used to get the response from api. 
Application also supports offline support once the data is loaded from network.

![](https://imgur.com/a/rNX448j)


#### The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **ui**: View classes along with their corresponding ViewModel.
4. **utils**: Utility classes.

#### Components used
* Room
* LiveData
* ViewModel
* Retrofit
* Data Binding



