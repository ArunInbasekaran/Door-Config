

### Door Config
Application used to config primary and secondary door parameters using simple MVVM architecture pattern.

![](https://imgur.com/SYYEtF7)

![](https://imgur.com/AzrBSlg)

#### Architecture flow:

Followed MVVM architecture to build this application. Retrofit is used to get the response from api. 
Application also supports offline support once the data is loaded from network.

![](https://imgur.com/6QoMoeE)


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



