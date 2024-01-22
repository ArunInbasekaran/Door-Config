
### Door Config
Application used to config primary and secondary door parameters using simple MVVM architecture pattern.

![Screenshot_20240121-235049_Door Config](https://github.com/ArunInbasekaran/Door-Config/assets/157306749/6ebe75c0-1f19-4e90-a770-1d013758413e)

![Screenshot_20240121-235057_Door Config](https://github.com/ArunInbasekaran/Door-Config/assets/157306749/5c485d5b-fe14-401d-b708-e5fb8688cc3a)

#### Architecture flow:

Followed MVVM architecture to build this application. Retrofit is used to get the response from api. 
Application also supports offline support once the data is loaded from network.

![architecture](https://github.com/ArunInbasekaran/Door-Config/assets/157306749/57b7dac2-f9d3-45f6-ab8b-ebb67ef0e102)

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



